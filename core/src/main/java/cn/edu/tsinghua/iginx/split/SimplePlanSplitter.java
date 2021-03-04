/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package cn.edu.tsinghua.iginx.split;

import cn.edu.tsinghua.iginx.conf.ConfigDescriptor;
import cn.edu.tsinghua.iginx.metadata.FragmentMeta;
import cn.edu.tsinghua.iginx.metadata.FragmentReplicaMeta;
import cn.edu.tsinghua.iginx.metadata.MetaManager;
import cn.edu.tsinghua.iginx.plan.DataPlan;
import cn.edu.tsinghua.iginx.plan.IginxPlan;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static cn.edu.tsinghua.iginx.fragment.FragmentProcessor.createFragment;

public class SimplePlanSplitter extends AbstractPlanSplitter implements IPlanSplitter {

	@Override
	public List<SplitInfo> getSplitResults(DataPlan plan) {
		List<SplitInfo> infoList = new ArrayList<>();

		for (Map.Entry<String, List<Integer>> entry : plan.generateIndexesOfPaths().entrySet()) {
			List<FragmentMeta> fragments =
					MetaManager.getInstance().getFragmentListByKeyAndTimeInterval(entry.getKey(), plan.getStartTime(), plan.getEndTime());
			if (fragments.isEmpty()) {
				createFragment(entry.getKey(), plan.getStartTime(), plan.getEndTime());
			}
			for (FragmentMeta fragment : fragments) {
				List<FragmentReplicaMeta> replicas = new ArrayList<>();
				if (plan.getIginxPlanType() == IginxPlan.IginxPlanType.INSERT_RECORDS) {
					replicas = chooseFragmentReplicas(fragment, false, ConfigDescriptor.getInstance().getConfig().getReplicaNum());
				} else if (plan.getIginxPlanType() == IginxPlan.IginxPlanType.QUERY_DATA) {
					replicas = chooseFragmentReplicas(fragment, true, 0);
				}
				for (FragmentReplicaMeta replica : replicas) {
					infoList.add(new SplitInfo(entry.getValue(), replica));
				}
			}
		}
		return infoList;
	}

	@Override
public List<FragmentReplicaMeta> chooseFragmentReplicas(FragmentMeta fragment, boolean isQuery, int replicaNum) {
		// random
		List<FragmentReplicaMeta> replicas = new ArrayList<>();
		Random random = new Random();
		if (isQuery) {
			replicas.add(fragment.getReplicaMetas().get(random.nextInt(fragment.getReplicaMetasNum())));
		} else {
			replicas.add(fragment.getReplicaMetas().get(0));
			for (int i = 0; i < replicaNum; i++) {
				replicas.add(fragment.getReplicaMetas().get(random.nextInt(fragment.getReplicaMetasNum() - 1) + 1));
			}
		}
		return replicas;
	}
}