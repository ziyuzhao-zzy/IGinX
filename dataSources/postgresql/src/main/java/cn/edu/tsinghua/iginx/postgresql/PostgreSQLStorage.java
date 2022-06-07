package cn.edu.tsinghua.iginx.postgresql;

import static cn.edu.tsinghua.iginx.postgresql.tools.FilterTransformer.MAX_TIMESTAMP;

import cn.edu.tsinghua.iginx.engine.physical.exception.NonExecutablePhysicalTaskException;
import cn.edu.tsinghua.iginx.engine.physical.exception.PhysicalException;
import cn.edu.tsinghua.iginx.engine.physical.exception.PhysicalTaskExecuteFailureException;
import cn.edu.tsinghua.iginx.engine.physical.exception.StorageInitializationException;
import cn.edu.tsinghua.iginx.engine.physical.storage.IStorage;
import cn.edu.tsinghua.iginx.engine.physical.storage.domain.Timeseries;
import cn.edu.tsinghua.iginx.engine.physical.task.StoragePhysicalTask;
import cn.edu.tsinghua.iginx.engine.physical.task.TaskExecuteResult;
import cn.edu.tsinghua.iginx.engine.shared.TimeRange;
import cn.edu.tsinghua.iginx.engine.shared.data.read.Field;
import cn.edu.tsinghua.iginx.engine.shared.data.read.RowStream;
import cn.edu.tsinghua.iginx.engine.shared.data.write.DataView;
import cn.edu.tsinghua.iginx.engine.shared.data.write.RowDataView;
import cn.edu.tsinghua.iginx.engine.shared.operator.Delete;
import cn.edu.tsinghua.iginx.engine.shared.operator.Insert;
import cn.edu.tsinghua.iginx.engine.shared.operator.Operator;
import cn.edu.tsinghua.iginx.engine.shared.operator.OperatorType;
import cn.edu.tsinghua.iginx.engine.shared.operator.Project;
import cn.edu.tsinghua.iginx.engine.shared.operator.Select;
import cn.edu.tsinghua.iginx.engine.shared.operator.filter.AndFilter;
import cn.edu.tsinghua.iginx.engine.shared.operator.filter.Filter;
import cn.edu.tsinghua.iginx.engine.shared.operator.filter.Op;
import cn.edu.tsinghua.iginx.engine.shared.operator.filter.TimeFilter;
import cn.edu.tsinghua.iginx.metadata.entity.FragmentMeta;
import cn.edu.tsinghua.iginx.metadata.entity.StorageEngineMeta;
import cn.edu.tsinghua.iginx.metadata.entity.TimeInterval;
import cn.edu.tsinghua.iginx.metadata.entity.TimeSeriesInterval;
import cn.edu.tsinghua.iginx.postgresql.entity.PostgreSQLQueryRowStream;
import cn.edu.tsinghua.iginx.postgresql.tools.DataTypeTransformer;
import cn.edu.tsinghua.iginx.postgresql.tools.FilterTransformer;
import cn.edu.tsinghua.iginx.thrift.DataType;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cn.edu.tsinghua.iginx.utils.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostgreSQLStorage implements IStorage {

  private static final Logger logger = LoggerFactory.getLogger(PostgreSQLStorage.class);

  private static final int BATCH_SIZE = 10000;

  private static final String STORAGE_ENGINE = "postgresql";

  private static final String USERNAME = "username";

  private static final String PASSWORD = "password";

  private static final String DBNAME = "dbname";

  private static final String DEFAULT_USERNAME = "postgres";

  private static final String DEFAULT_PASSWORD = "123456";

  private static final String QUERY_DATA = "SELECT time, %s FROM %s WHERE %s";

  private static final String DELETE_DATA = "DELETE FROM %s WHERE time >= to_timestamp(%d) and time < to_timestamp(%d)";

  private static final String IGINX_SEPARATOR = ".";

  private static final String POSTGRESQL_SEPARATOR = "$";

  private final StorageEngineMeta meta;

  private Connection connection;

  public PostgreSQLStorage(StorageEngineMeta meta) throws StorageInitializationException {
    this.meta = meta;
    if (!testConnection()) {
      throw new StorageInitializationException("cannot connect to " + meta.toString());
    }
    Map<String, String> extraParams = meta.getExtraParams();
    String username = extraParams.getOrDefault(USERNAME, DEFAULT_USERNAME);
    String password = extraParams.getOrDefault(PASSWORD, DEFAULT_PASSWORD);
    String connUrl = String
        .format("jdbc:postgresql://%s:%s/?user=%s&password=%s", meta.getIp(), meta.getPort(),
            username, password);
    try {
      connection = DriverManager.getConnection(connUrl);
    } catch (SQLException e) {
      throw new StorageInitializationException("cannot connect to " + meta.toString());
    }
  }

  private boolean testConnection() {
    Map<String, String> extraParams = meta.getExtraParams();
    String username = extraParams.getOrDefault(USERNAME, DEFAULT_USERNAME);
    String password = extraParams.getOrDefault(PASSWORD, DEFAULT_PASSWORD);
    String connUrl = String
        .format("jdbc:postgresql://%s:%s?user=%s&password=%s", meta.getIp(), meta.getPort(),
            username, password);
    try {
      Class.forName("org.postgresql.Driver");
      DriverManager.getConnection(connUrl);
      return true;
    } catch (SQLException | ClassNotFoundException e) {
      return false;
    }
  }

  @Override
  public Pair<TimeSeriesInterval, TimeInterval> getBoundaryOfStorage() throws PhysicalException {
    return new Pair<>(new TimeSeriesInterval(null, null), new TimeInterval(0, Long.MAX_VALUE));
  }

  @Override
  public TaskExecuteResult execute(StoragePhysicalTask task) {
    List<Operator> operators = task.getOperators();
    if (operators.size() != 1) {
      return new TaskExecuteResult(
          new NonExecutablePhysicalTaskException("unsupported physical task"));
    }
    FragmentMeta fragment = task.getTargetFragment();
    Operator op = operators.get(0);
    String storageUnit = task.getStorageUnit();
    // 先切换数据库
    useDatabase(storageUnit);

    if (op.getType() == OperatorType.Project) { // 目前只实现 project 操作符
      Project project = (Project) op;
      Filter filter;
      if (operators.size() == 2) {
        filter = ((Select) operators.get(1)).getFilter();
      } else {
        filter = new AndFilter(Arrays
            .asList(new TimeFilter(Op.GE, fragment.getTimeInterval().getStartTime()),
                new TimeFilter(Op.L, fragment.getTimeInterval().getEndTime())));
      }
      return executeProjectTask(project, filter);
    } else if (op.getType() == OperatorType.Insert) {
      Insert insert = (Insert) op;
      return executeInsertTask(insert);
    } else if (op.getType() == OperatorType.Delete) {
      Delete delete = (Delete) op;
      return executeDeleteTask(delete);
    }
    return new TaskExecuteResult(
        new NonExecutablePhysicalTaskException("unsupported physical task"));
  }

  @Override
  public List<Timeseries> getTimeSeries() throws PhysicalException {
    List<Timeseries> timeseries = new ArrayList<>();
    try {
      DatabaseMetaData databaseMetaData = connection.getMetaData();
      ResultSet tableSet = databaseMetaData.getTables(null, "%", "%", new String[]{"TABLE"});
      while (tableSet.next()) {
        String tableName = tableSet.getString(3);//获取表名称
        ResultSet columnSet = databaseMetaData.getColumns(null, "%", tableName, "%");
        while (columnSet.next()) {
          String columnName = columnSet.getString("COLUMN_NAME");//获取列名称
          String typeName = columnSet.getString("TYPE_NAME");//列字段类型
          timeseries.add(new Timeseries(
              tableName.replace(POSTGRESQL_SEPARATOR, IGINX_SEPARATOR) + IGINX_SEPARATOR
                  + columnName.replace(POSTGRESQL_SEPARATOR, IGINX_SEPARATOR),
              DataTypeTransformer.fromPostgreSQL(typeName)));
        }
      }
    } catch (SQLException e) {
      throw new PhysicalException(e);
    }
    return timeseries;
  }

  private TaskExecuteResult executeProjectTask(Project project, Filter filter) { // 未来可能要用 tsInterval 对查询出来的数据进行过滤
    try {
      List<ResultSet> resultSets = new ArrayList<>();
      List<Field> fields = new ArrayList<>();
      for (String path : project.getPatterns()) {
        String table = path.substring(0, path.lastIndexOf('.'));
        table = table.replace(IGINX_SEPARATOR, POSTGRESQL_SEPARATOR);
        String sensor = path.substring(path.lastIndexOf('.') + 1);
        sensor = sensor.replace(IGINX_SEPARATOR, POSTGRESQL_SEPARATOR);
        // 查询序列类型
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet columnSet = databaseMetaData.getColumns(null, "%", table, sensor);
        if (columnSet.next()) {
          String typeName = columnSet.getString("TYPE_NAME");//列字段类型
          fields
              .add(new Field(table.replace(POSTGRESQL_SEPARATOR, IGINX_SEPARATOR) + IGINX_SEPARATOR
                  + sensor.replace(POSTGRESQL_SEPARATOR, IGINX_SEPARATOR)
                  , DataTypeTransformer.fromPostgreSQL(typeName)));
          String statement = String
              .format(QUERY_DATA, sensor, table, FilterTransformer.toString(filter));
          Statement stmt = connection.createStatement();
          ResultSet rs = stmt.executeQuery(statement);
          resultSets.add(rs);
        }
      }
      RowStream rowStream = new PostgreSQLQueryRowStream(resultSets, fields);
      return new TaskExecuteResult(rowStream);
    } catch (SQLException e) {
      return new TaskExecuteResult(
          new PhysicalTaskExecuteFailureException("execute project task in postgresql failure",
              e));
    }
  }

  private TaskExecuteResult executeInsertTask(Insert insert) {
    DataView dataView = insert.getData();
    Exception e = null;
    switch (dataView.getRawDataType()) {
      case Row:
      case NonAlignedRow:
      case Column:
      case NonAlignedColumn:
        e = insertRowRecords((RowDataView) dataView);
        break;
    }
    if (e != null) {
      return new TaskExecuteResult(null,
          new PhysicalException("execute insert task in iotdb12 failure", e));
    }
    return new TaskExecuteResult(null, null);
  }

  private void createTimeSeriesIfNotExists(String table, String column, DataType dataType) {
    try {
      DatabaseMetaData databaseMetaData = connection.getMetaData();
      ResultSet tableSet = databaseMetaData.getTables(null, "%", table, new String[]{"TABLE"});
      if (!tableSet.next()) {
        Statement stmt = connection.createStatement();
        stmt.execute(String
            .format("CREATE TABLE %s (time TIMESTAMPTZ NOT NULL,%s %s NULL)", table, column,
                DataTypeTransformer.toPostgreSQL(dataType)));
      } else {
        ResultSet columnSet = databaseMetaData.getColumns(null, "%", table, column);
        if (!columnSet.next()) {
          Statement stmt = connection.createStatement();
          stmt.execute(String.format("ALTER TABLE %s ADD COLUMN %s %s NULL", table, column,
              DataTypeTransformer.toPostgreSQL(dataType)));
        }
      }
    } catch (SQLException e) {
      logger.error("create timeseries error", e);
    }
  }

  private void useDatabase(String dbname) {
    try {
      Statement stmt = connection.createStatement();
      stmt.execute(String.format("create database %s", dbname));
    } catch (SQLException e) {
      logger.info("create database error", e);
    }
    try {
      Map<String, String> extraParams = meta.getExtraParams();
      String username = extraParams.getOrDefault(USERNAME, DEFAULT_USERNAME);
      String password = extraParams.getOrDefault(PASSWORD, DEFAULT_PASSWORD);
      String connUrl = String
          .format("jdbc:postgresql://%s:%s/%s?user=%s&password=%s", meta.getIp(), meta.getPort(),
              dbname, username, password);
      connection = DriverManager.getConnection(connUrl);
    } catch (SQLException e) {
      logger.info("change database error", e);
    }
  }

  private Exception insertRowRecords(RowDataView data) {
    // TODO 按timestamp进行行排序再插入
    int batchSize = Math.min(data.getTimeSize(), BATCH_SIZE);
    try {
      Statement stmt = connection.createStatement();
      for (int i = 0; i < data.getPathNum(); i++) {
        String path = data.getPath(i);
        DataType dataType = data.getDataType(i);
        String table = path.substring(0, path.lastIndexOf('.'));
        table = table.replace(IGINX_SEPARATOR, POSTGRESQL_SEPARATOR);
        String sensor = path.substring(path.lastIndexOf('.') + 1);
        sensor = sensor.replace(IGINX_SEPARATOR, POSTGRESQL_SEPARATOR);
        createTimeSeriesIfNotExists(table, sensor, dataType);
        for (int j = 0; j < data.getTimeSize(); j++) {
          long time = data.getTimestamp(j) / 1000; // postgresql存10位时间戳，java为13位时间戳
          String value = data.getValue(j, i).toString();
          stmt.addBatch(String
              .format("INSERT INTO %s (time, %s) values (to_timestamp(%d), %s)", table, sensor,
                  time,
                  value));
          if (j > 0 && (j + 1) % batchSize == 0) {
            stmt.executeBatch();
          }
        }
      }
      stmt.executeBatch();
    } catch (SQLException e) {
      return e;
    }

    return null;
  }

  private TaskExecuteResult executeDeleteTask(Delete delete) {
    // only support to the level of device now
    // TODO support the delete to the level of sensor
    try {
      for (int i = 0; i < delete.getPatterns().size(); i++) {
        String path = delete.getPatterns().get(i);
        TimeRange timeRange = delete.getTimeRanges().get(i);
        String table = path.substring(0, path.lastIndexOf('.'));
        table = table.replace(IGINX_SEPARATOR, POSTGRESQL_SEPARATOR);
        String sensor = path.substring(path.lastIndexOf('.') + 1);
        sensor = sensor.replace(IGINX_SEPARATOR, POSTGRESQL_SEPARATOR);
        // 查询序列类型
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet columnSet = databaseMetaData.getColumns(null, "%", table, sensor);
        if (columnSet.next()) {
          String statement = String
              .format(DELETE_DATA, table,
                  timeRange.getBeginTime(), Math.min(timeRange.getEndTime(), MAX_TIMESTAMP));
          Statement stmt = connection.createStatement();
          stmt.execute(statement);
        }
      }
      return new TaskExecuteResult(null, null);
    } catch (SQLException e) {
      return new TaskExecuteResult(
          new PhysicalTaskExecuteFailureException("execute delete task in postgresql failure",
              e));
    }
  }

  @Override
  public void release() throws PhysicalException {

  }

}