/**
 * Autogenerated by Thrift Compiler (0.13.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package cn.edu.tsinghua.iginx.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.13.0)", date = "2021-03-04")
public class InsertRecordsReq implements org.apache.thrift.TBase<InsertRecordsReq, InsertRecordsReq._Fields>, java.io.Serializable, Cloneable, Comparable<InsertRecordsReq> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("InsertRecordsReq");

  private static final org.apache.thrift.protocol.TField SESSION_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("sessionId", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField PATHS_FIELD_DESC = new org.apache.thrift.protocol.TField("paths", org.apache.thrift.protocol.TType.LIST, (short)2);
  private static final org.apache.thrift.protocol.TField TIMESTAMPS_FIELD_DESC = new org.apache.thrift.protocol.TField("timestamps", org.apache.thrift.protocol.TType.LIST, (short)3);
  private static final org.apache.thrift.protocol.TField VALUES_FIELD_DESC = new org.apache.thrift.protocol.TField("values", org.apache.thrift.protocol.TType.LIST, (short)4);
  private static final org.apache.thrift.protocol.TField ATTRIBUTES_FIELD_DESC = new org.apache.thrift.protocol.TField("attributes", org.apache.thrift.protocol.TType.LIST, (short)5);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new InsertRecordsReqStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new InsertRecordsReqTupleSchemeFactory();

  public long sessionId; // required
  public @org.apache.thrift.annotation.Nullable java.util.List<java.lang.String> paths; // required
  public @org.apache.thrift.annotation.Nullable java.util.List<java.lang.Long> timestamps; // required
  public @org.apache.thrift.annotation.Nullable java.util.List<java.nio.ByteBuffer> values; // required
  public @org.apache.thrift.annotation.Nullable java.util.List<java.util.Map<java.lang.String,java.lang.String>> attributes; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    SESSION_ID((short)1, "sessionId"),
    PATHS((short)2, "paths"),
    TIMESTAMPS((short)3, "timestamps"),
    VALUES((short)4, "values"),
    ATTRIBUTES((short)5, "attributes");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // SESSION_ID
          return SESSION_ID;
        case 2: // PATHS
          return PATHS;
        case 3: // TIMESTAMPS
          return TIMESTAMPS;
        case 4: // VALUES
          return VALUES;
        case 5: // ATTRIBUTES
          return ATTRIBUTES;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __SESSIONID_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.ATTRIBUTES};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.SESSION_ID, new org.apache.thrift.meta_data.FieldMetaData("sessionId", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.PATHS, new org.apache.thrift.meta_data.FieldMetaData("paths", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    tmpMap.put(_Fields.TIMESTAMPS, new org.apache.thrift.meta_data.FieldMetaData("timestamps", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64))));
    tmpMap.put(_Fields.VALUES, new org.apache.thrift.meta_data.FieldMetaData("values", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING            , true))));
    tmpMap.put(_Fields.ATTRIBUTES, new org.apache.thrift.meta_data.FieldMetaData("attributes", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING), 
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(InsertRecordsReq.class, metaDataMap);
  }

  public InsertRecordsReq() {
  }

  public InsertRecordsReq(
    long sessionId,
    java.util.List<java.lang.String> paths,
    java.util.List<java.lang.Long> timestamps,
    java.util.List<java.nio.ByteBuffer> values)
  {
    this();
    this.sessionId = sessionId;
    setSessionIdIsSet(true);
    this.paths = paths;
    this.timestamps = timestamps;
    this.values = values;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public InsertRecordsReq(InsertRecordsReq other) {
    __isset_bitfield = other.__isset_bitfield;
    this.sessionId = other.sessionId;
    if (other.isSetPaths()) {
      java.util.List<java.lang.String> __this__paths = new java.util.ArrayList<java.lang.String>(other.paths);
      this.paths = __this__paths;
    }
    if (other.isSetTimestamps()) {
      java.util.List<java.lang.Long> __this__timestamps = new java.util.ArrayList<java.lang.Long>(other.timestamps);
      this.timestamps = __this__timestamps;
    }
    if (other.isSetValues()) {
      java.util.List<java.nio.ByteBuffer> __this__values = new java.util.ArrayList<java.nio.ByteBuffer>(other.values);
      this.values = __this__values;
    }
    if (other.isSetAttributes()) {
      java.util.List<java.util.Map<java.lang.String,java.lang.String>> __this__attributes = new java.util.ArrayList<java.util.Map<java.lang.String,java.lang.String>>(other.attributes.size());
      for (java.util.Map<java.lang.String,java.lang.String> other_element : other.attributes) {
        java.util.Map<java.lang.String,java.lang.String> __this__attributes_copy = new java.util.HashMap<java.lang.String,java.lang.String>(other_element);
        __this__attributes.add(__this__attributes_copy);
      }
      this.attributes = __this__attributes;
    }
  }

  public InsertRecordsReq deepCopy() {
    return new InsertRecordsReq(this);
  }

  @Override
  public void clear() {
    setSessionIdIsSet(false);
    this.sessionId = 0;
    this.paths = null;
    this.timestamps = null;
    this.values = null;
    this.attributes = null;
  }

  public long getSessionId() {
    return this.sessionId;
  }

  public InsertRecordsReq setSessionId(long sessionId) {
    this.sessionId = sessionId;
    setSessionIdIsSet(true);
    return this;
  }

  public void unsetSessionId() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __SESSIONID_ISSET_ID);
  }

  /** Returns true if field sessionId is set (has been assigned a value) and false otherwise */
  public boolean isSetSessionId() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __SESSIONID_ISSET_ID);
  }

  public void setSessionIdIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __SESSIONID_ISSET_ID, value);
  }

  public int getPathsSize() {
    return (this.paths == null) ? 0 : this.paths.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<java.lang.String> getPathsIterator() {
    return (this.paths == null) ? null : this.paths.iterator();
  }

  public void addToPaths(java.lang.String elem) {
    if (this.paths == null) {
      this.paths = new java.util.ArrayList<java.lang.String>();
    }
    this.paths.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.List<java.lang.String> getPaths() {
    return this.paths;
  }

  public InsertRecordsReq setPaths(@org.apache.thrift.annotation.Nullable java.util.List<java.lang.String> paths) {
    this.paths = paths;
    return this;
  }

  public void unsetPaths() {
    this.paths = null;
  }

  /** Returns true if field paths is set (has been assigned a value) and false otherwise */
  public boolean isSetPaths() {
    return this.paths != null;
  }

  public void setPathsIsSet(boolean value) {
    if (!value) {
      this.paths = null;
    }
  }

  public int getTimestampsSize() {
    return (this.timestamps == null) ? 0 : this.timestamps.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<java.lang.Long> getTimestampsIterator() {
    return (this.timestamps == null) ? null : this.timestamps.iterator();
  }

  public void addToTimestamps(long elem) {
    if (this.timestamps == null) {
      this.timestamps = new java.util.ArrayList<java.lang.Long>();
    }
    this.timestamps.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.List<java.lang.Long> getTimestamps() {
    return this.timestamps;
  }

  public InsertRecordsReq setTimestamps(@org.apache.thrift.annotation.Nullable java.util.List<java.lang.Long> timestamps) {
    this.timestamps = timestamps;
    return this;
  }

  public void unsetTimestamps() {
    this.timestamps = null;
  }

  /** Returns true if field timestamps is set (has been assigned a value) and false otherwise */
  public boolean isSetTimestamps() {
    return this.timestamps != null;
  }

  public void setTimestampsIsSet(boolean value) {
    if (!value) {
      this.timestamps = null;
    }
  }

  public int getValuesSize() {
    return (this.values == null) ? 0 : this.values.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<java.nio.ByteBuffer> getValuesIterator() {
    return (this.values == null) ? null : this.values.iterator();
  }

  public void addToValues(java.nio.ByteBuffer elem) {
    if (this.values == null) {
      this.values = new java.util.ArrayList<java.nio.ByteBuffer>();
    }
    this.values.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.List<java.nio.ByteBuffer> getValues() {
    return this.values;
  }

  public InsertRecordsReq setValues(@org.apache.thrift.annotation.Nullable java.util.List<java.nio.ByteBuffer> values) {
    this.values = values;
    return this;
  }

  public void unsetValues() {
    this.values = null;
  }

  /** Returns true if field values is set (has been assigned a value) and false otherwise */
  public boolean isSetValues() {
    return this.values != null;
  }

  public void setValuesIsSet(boolean value) {
    if (!value) {
      this.values = null;
    }
  }

  public int getAttributesSize() {
    return (this.attributes == null) ? 0 : this.attributes.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<java.util.Map<java.lang.String,java.lang.String>> getAttributesIterator() {
    return (this.attributes == null) ? null : this.attributes.iterator();
  }

  public void addToAttributes(java.util.Map<java.lang.String,java.lang.String> elem) {
    if (this.attributes == null) {
      this.attributes = new java.util.ArrayList<java.util.Map<java.lang.String,java.lang.String>>();
    }
    this.attributes.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.List<java.util.Map<java.lang.String,java.lang.String>> getAttributes() {
    return this.attributes;
  }

  public InsertRecordsReq setAttributes(@org.apache.thrift.annotation.Nullable java.util.List<java.util.Map<java.lang.String,java.lang.String>> attributes) {
    this.attributes = attributes;
    return this;
  }

  public void unsetAttributes() {
    this.attributes = null;
  }

  /** Returns true if field attributes is set (has been assigned a value) and false otherwise */
  public boolean isSetAttributes() {
    return this.attributes != null;
  }

  public void setAttributesIsSet(boolean value) {
    if (!value) {
      this.attributes = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case SESSION_ID:
      if (value == null) {
        unsetSessionId();
      } else {
        setSessionId((java.lang.Long)value);
      }
      break;

    case PATHS:
      if (value == null) {
        unsetPaths();
      } else {
        setPaths((java.util.List<java.lang.String>)value);
      }
      break;

    case TIMESTAMPS:
      if (value == null) {
        unsetTimestamps();
      } else {
        setTimestamps((java.util.List<java.lang.Long>)value);
      }
      break;

    case VALUES:
      if (value == null) {
        unsetValues();
      } else {
        setValues((java.util.List<java.nio.ByteBuffer>)value);
      }
      break;

    case ATTRIBUTES:
      if (value == null) {
        unsetAttributes();
      } else {
        setAttributes((java.util.List<java.util.Map<java.lang.String,java.lang.String>>)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case SESSION_ID:
      return getSessionId();

    case PATHS:
      return getPaths();

    case TIMESTAMPS:
      return getTimestamps();

    case VALUES:
      return getValues();

    case ATTRIBUTES:
      return getAttributes();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case SESSION_ID:
      return isSetSessionId();
    case PATHS:
      return isSetPaths();
    case TIMESTAMPS:
      return isSetTimestamps();
    case VALUES:
      return isSetValues();
    case ATTRIBUTES:
      return isSetAttributes();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof InsertRecordsReq)
      return this.equals((InsertRecordsReq)that);
    return false;
  }

  public boolean equals(InsertRecordsReq that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_sessionId = true;
    boolean that_present_sessionId = true;
    if (this_present_sessionId || that_present_sessionId) {
      if (!(this_present_sessionId && that_present_sessionId))
        return false;
      if (this.sessionId != that.sessionId)
        return false;
    }

    boolean this_present_paths = true && this.isSetPaths();
    boolean that_present_paths = true && that.isSetPaths();
    if (this_present_paths || that_present_paths) {
      if (!(this_present_paths && that_present_paths))
        return false;
      if (!this.paths.equals(that.paths))
        return false;
    }

    boolean this_present_timestamps = true && this.isSetTimestamps();
    boolean that_present_timestamps = true && that.isSetTimestamps();
    if (this_present_timestamps || that_present_timestamps) {
      if (!(this_present_timestamps && that_present_timestamps))
        return false;
      if (!this.timestamps.equals(that.timestamps))
        return false;
    }

    boolean this_present_values = true && this.isSetValues();
    boolean that_present_values = true && that.isSetValues();
    if (this_present_values || that_present_values) {
      if (!(this_present_values && that_present_values))
        return false;
      if (!this.values.equals(that.values))
        return false;
    }

    boolean this_present_attributes = true && this.isSetAttributes();
    boolean that_present_attributes = true && that.isSetAttributes();
    if (this_present_attributes || that_present_attributes) {
      if (!(this_present_attributes && that_present_attributes))
        return false;
      if (!this.attributes.equals(that.attributes))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(sessionId);

    hashCode = hashCode * 8191 + ((isSetPaths()) ? 131071 : 524287);
    if (isSetPaths())
      hashCode = hashCode * 8191 + paths.hashCode();

    hashCode = hashCode * 8191 + ((isSetTimestamps()) ? 131071 : 524287);
    if (isSetTimestamps())
      hashCode = hashCode * 8191 + timestamps.hashCode();

    hashCode = hashCode * 8191 + ((isSetValues()) ? 131071 : 524287);
    if (isSetValues())
      hashCode = hashCode * 8191 + values.hashCode();

    hashCode = hashCode * 8191 + ((isSetAttributes()) ? 131071 : 524287);
    if (isSetAttributes())
      hashCode = hashCode * 8191 + attributes.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(InsertRecordsReq other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetSessionId()).compareTo(other.isSetSessionId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSessionId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.sessionId, other.sessionId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetPaths()).compareTo(other.isSetPaths());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPaths()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.paths, other.paths);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetTimestamps()).compareTo(other.isSetTimestamps());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTimestamps()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.timestamps, other.timestamps);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetValues()).compareTo(other.isSetValues());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetValues()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.values, other.values);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetAttributes()).compareTo(other.isSetAttributes());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAttributes()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.attributes, other.attributes);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("InsertRecordsReq(");
    boolean first = true;

    sb.append("sessionId:");
    sb.append(this.sessionId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("paths:");
    if (this.paths == null) {
      sb.append("null");
    } else {
      sb.append(this.paths);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("timestamps:");
    if (this.timestamps == null) {
      sb.append("null");
    } else {
      sb.append(this.timestamps);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("values:");
    if (this.values == null) {
      sb.append("null");
    } else {
      org.apache.thrift.TBaseHelper.toString(this.values, sb);
    }
    first = false;
    if (isSetAttributes()) {
      if (!first) sb.append(", ");
      sb.append("attributes:");
      if (this.attributes == null) {
        sb.append("null");
      } else {
        sb.append(this.attributes);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'sessionId' because it's a primitive and you chose the non-beans generator.
    if (paths == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'paths' was not present! Struct: " + toString());
    }
    if (timestamps == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'timestamps' was not present! Struct: " + toString());
    }
    if (values == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'values' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class InsertRecordsReqStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public InsertRecordsReqStandardScheme getScheme() {
      return new InsertRecordsReqStandardScheme();
    }
  }

  private static class InsertRecordsReqStandardScheme extends org.apache.thrift.scheme.StandardScheme<InsertRecordsReq> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, InsertRecordsReq struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // SESSION_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.sessionId = iprot.readI64();
              struct.setSessionIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // PATHS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list78 = iprot.readListBegin();
                struct.paths = new java.util.ArrayList<java.lang.String>(_list78.size);
                @org.apache.thrift.annotation.Nullable java.lang.String _elem79;
                for (int _i80 = 0; _i80 < _list78.size; ++_i80)
                {
                  _elem79 = iprot.readString();
                  struct.paths.add(_elem79);
                }
                iprot.readListEnd();
              }
              struct.setPathsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TIMESTAMPS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list81 = iprot.readListBegin();
                struct.timestamps = new java.util.ArrayList<java.lang.Long>(_list81.size);
                long _elem82;
                for (int _i83 = 0; _i83 < _list81.size; ++_i83)
                {
                  _elem82 = iprot.readI64();
                  struct.timestamps.add(_elem82);
                }
                iprot.readListEnd();
              }
              struct.setTimestampsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // VALUES
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list84 = iprot.readListBegin();
                struct.values = new java.util.ArrayList<java.nio.ByteBuffer>(_list84.size);
                @org.apache.thrift.annotation.Nullable java.nio.ByteBuffer _elem85;
                for (int _i86 = 0; _i86 < _list84.size; ++_i86)
                {
                  _elem85 = iprot.readBinary();
                  struct.values.add(_elem85);
                }
                iprot.readListEnd();
              }
              struct.setValuesIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // ATTRIBUTES
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list87 = iprot.readListBegin();
                struct.attributes = new java.util.ArrayList<java.util.Map<java.lang.String,java.lang.String>>(_list87.size);
                @org.apache.thrift.annotation.Nullable java.util.Map<java.lang.String,java.lang.String> _elem88;
                for (int _i89 = 0; _i89 < _list87.size; ++_i89)
                {
                  {
                    org.apache.thrift.protocol.TMap _map90 = iprot.readMapBegin();
                    _elem88 = new java.util.HashMap<java.lang.String,java.lang.String>(2*_map90.size);
                    @org.apache.thrift.annotation.Nullable java.lang.String _key91;
                    @org.apache.thrift.annotation.Nullable java.lang.String _val92;
                    for (int _i93 = 0; _i93 < _map90.size; ++_i93)
                    {
                      _key91 = iprot.readString();
                      _val92 = iprot.readString();
                      _elem88.put(_key91, _val92);
                    }
                    iprot.readMapEnd();
                  }
                  struct.attributes.add(_elem88);
                }
                iprot.readListEnd();
              }
              struct.setAttributesIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      if (!struct.isSetSessionId()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'sessionId' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, InsertRecordsReq struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(SESSION_ID_FIELD_DESC);
      oprot.writeI64(struct.sessionId);
      oprot.writeFieldEnd();
      if (struct.paths != null) {
        oprot.writeFieldBegin(PATHS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.paths.size()));
          for (java.lang.String _iter94 : struct.paths)
          {
            oprot.writeString(_iter94);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.timestamps != null) {
        oprot.writeFieldBegin(TIMESTAMPS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I64, struct.timestamps.size()));
          for (long _iter95 : struct.timestamps)
          {
            oprot.writeI64(_iter95);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.values != null) {
        oprot.writeFieldBegin(VALUES_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.values.size()));
          for (java.nio.ByteBuffer _iter96 : struct.values)
          {
            oprot.writeBinary(_iter96);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.attributes != null) {
        if (struct.isSetAttributes()) {
          oprot.writeFieldBegin(ATTRIBUTES_FIELD_DESC);
          {
            oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.MAP, struct.attributes.size()));
            for (java.util.Map<java.lang.String,java.lang.String> _iter97 : struct.attributes)
            {
              {
                oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, _iter97.size()));
                for (java.util.Map.Entry<java.lang.String, java.lang.String> _iter98 : _iter97.entrySet())
                {
                  oprot.writeString(_iter98.getKey());
                  oprot.writeString(_iter98.getValue());
                }
                oprot.writeMapEnd();
              }
            }
            oprot.writeListEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class InsertRecordsReqTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public InsertRecordsReqTupleScheme getScheme() {
      return new InsertRecordsReqTupleScheme();
    }
  }

  private static class InsertRecordsReqTupleScheme extends org.apache.thrift.scheme.TupleScheme<InsertRecordsReq> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, InsertRecordsReq struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeI64(struct.sessionId);
      {
        oprot.writeI32(struct.paths.size());
        for (java.lang.String _iter99 : struct.paths)
        {
          oprot.writeString(_iter99);
        }
      }
      {
        oprot.writeI32(struct.timestamps.size());
        for (long _iter100 : struct.timestamps)
        {
          oprot.writeI64(_iter100);
        }
      }
      {
        oprot.writeI32(struct.values.size());
        for (java.nio.ByteBuffer _iter101 : struct.values)
        {
          oprot.writeBinary(_iter101);
        }
      }
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetAttributes()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetAttributes()) {
        {
          oprot.writeI32(struct.attributes.size());
          for (java.util.Map<java.lang.String,java.lang.String> _iter102 : struct.attributes)
          {
            {
              oprot.writeI32(_iter102.size());
              for (java.util.Map.Entry<java.lang.String, java.lang.String> _iter103 : _iter102.entrySet())
              {
                oprot.writeString(_iter103.getKey());
                oprot.writeString(_iter103.getValue());
              }
            }
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, InsertRecordsReq struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.sessionId = iprot.readI64();
      struct.setSessionIdIsSet(true);
      {
        org.apache.thrift.protocol.TList _list104 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
        struct.paths = new java.util.ArrayList<java.lang.String>(_list104.size);
        @org.apache.thrift.annotation.Nullable java.lang.String _elem105;
        for (int _i106 = 0; _i106 < _list104.size; ++_i106)
        {
          _elem105 = iprot.readString();
          struct.paths.add(_elem105);
        }
      }
      struct.setPathsIsSet(true);
      {
        org.apache.thrift.protocol.TList _list107 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I64, iprot.readI32());
        struct.timestamps = new java.util.ArrayList<java.lang.Long>(_list107.size);
        long _elem108;
        for (int _i109 = 0; _i109 < _list107.size; ++_i109)
        {
          _elem108 = iprot.readI64();
          struct.timestamps.add(_elem108);
        }
      }
      struct.setTimestampsIsSet(true);
      {
        org.apache.thrift.protocol.TList _list110 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
        struct.values = new java.util.ArrayList<java.nio.ByteBuffer>(_list110.size);
        @org.apache.thrift.annotation.Nullable java.nio.ByteBuffer _elem111;
        for (int _i112 = 0; _i112 < _list110.size; ++_i112)
        {
          _elem111 = iprot.readBinary();
          struct.values.add(_elem111);
        }
      }
      struct.setValuesIsSet(true);
      java.util.BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list113 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.MAP, iprot.readI32());
          struct.attributes = new java.util.ArrayList<java.util.Map<java.lang.String,java.lang.String>>(_list113.size);
          @org.apache.thrift.annotation.Nullable java.util.Map<java.lang.String,java.lang.String> _elem114;
          for (int _i115 = 0; _i115 < _list113.size; ++_i115)
          {
            {
              org.apache.thrift.protocol.TMap _map116 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, iprot.readI32());
              _elem114 = new java.util.HashMap<java.lang.String,java.lang.String>(2*_map116.size);
              @org.apache.thrift.annotation.Nullable java.lang.String _key117;
              @org.apache.thrift.annotation.Nullable java.lang.String _val118;
              for (int _i119 = 0; _i119 < _map116.size; ++_i119)
              {
                _key117 = iprot.readString();
                _val118 = iprot.readString();
                _elem114.put(_key117, _val118);
              }
            }
            struct.attributes.add(_elem114);
          }
        }
        struct.setAttributesIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}
