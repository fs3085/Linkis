package com.webank.wedatasphere.linkis.manager.persistence;
/*
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import com.webank.wedatasphere.linkis.manager.common.entity.metrics.NodeMetrics;
import com.webank.wedatasphere.linkis.manager.common.entity.node.Node;
import com.webank.wedatasphere.linkis.manager.exception.PersistenceErrorException;

import java.util.List;


public interface NodeMetricManagerPersistence {

    /**
     * 保存节点的Metric信息
     * @param nodeMetrics
     * @throws PersistenceErrorException
     */
    void addNodeMetrics(NodeMetrics nodeMetrics) throws PersistenceErrorException;

    /**
     * 保存或更新节点的metric
     * @param nodeMetrics
     * @throws PersistenceErrorException
     */
    void addOrupdateNodeMetrics(NodeMetrics nodeMetrics) throws PersistenceErrorException;

    /**
     * 获取多个节点的 metrics列表
     * @param nodes
     * @return
     * @throws PersistenceErrorException
     */
    List<NodeMetrics> getNodeMetrics(List<? extends Node> nodes) throws PersistenceErrorException;

    /**
     *获取节点的nodemetrics
     * @param node
     * @return
     * @throws PersistenceErrorException
     */

    NodeMetrics getNodeMetrics(Node node) throws PersistenceErrorException;

    /**
     * 删除节点的metric信息
     * @param node
     * @throws PersistenceErrorException
     */
    void deleteNodeMetrics(Node node) throws PersistenceErrorException;

    List<NodeMetrics> getAllNodeMetrics() throws PersistenceErrorException;
}
