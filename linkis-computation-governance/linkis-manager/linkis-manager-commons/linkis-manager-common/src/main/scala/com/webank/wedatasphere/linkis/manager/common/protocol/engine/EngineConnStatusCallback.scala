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

package com.webank.wedatasphere.linkis.manager.common.protocol.engine

import com.webank.wedatasphere.linkis.common.ServiceInstance
import com.webank.wedatasphere.linkis.manager.common.entity.enumeration.NodeStatus
import com.webank.wedatasphere.linkis.protocol.RetryableProtocol
import com.webank.wedatasphere.linkis.protocol.message.RequestProtocol


case class EngineConnStatusCallback(serviceInstance: ServiceInstance, ticketId: String, status: NodeStatus, initErrorMsg: String) extends RequestProtocol

/**
  * engineConnManager send to Manager
  */
case class EngineConnStatusCallbackToAM(serviceInstance: ServiceInstance, status: NodeStatus, initErrorMsg: String, canRetry: Boolean = false) extends RequestProtocol with RetryableProtocol


