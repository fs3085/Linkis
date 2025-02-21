/*
 *
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
 *
 */

package com.webank.wedatasphere.linkis.manager.am.selector.rule

import com.webank.wedatasphere.linkis.common.utils.Logging
import com.webank.wedatasphere.linkis.manager.common.entity.enumeration.NodeStatus
import com.webank.wedatasphere.linkis.manager.common.entity.node.{AMNode, Node}
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

/**
  * require node state are idle or busy
  */
@Component
@Order(2)
class AvailableNodeSelectRule extends NodeSelectRule with Logging{

  override def ruleFiltering(nodes: Array[Node]): Array[Node] = {
    if (null != nodes) {
      nodes.filter {
        case amNode: AMNode =>
          if (! NodeStatus.isLocked(amNode.getNodeStatus) && NodeStatus.isAvailable(amNode.getNodeStatus) ) {
              true
            } else {
            info(s"engineConn ${amNode.getServiceInstance} cannot be reuse status: ${amNode.getNodeStatus}")
            false
          }
        case node: Node => NodeStatus.isAvailable(node.getNodeStatus)
      }
    } else {
      nodes
    }
  }


}
