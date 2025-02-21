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

package com.webank.wedatasphere.linkis.manager.am.service

import java.util

import com.webank.wedatasphere.linkis.manager.common.entity.node.{EMNode, EngineNode, ScoreServiceInstance}
import com.webank.wedatasphere.linkis.manager.common.protocol.em._
import com.webank.wedatasphere.linkis.manager.engineplugin.common.launch.entity.EngineConnBuildRequest
import com.webank.wedatasphere.linkis.manager.label.entity.Label


trait EMEngineService {


  def listEngines(getEMEnginesRequest: GetEMEnginesRequest): util.List[EngineNode]

  def createEngine(engineBuildRequest: EngineConnBuildRequest, emNode: EMNode): EngineNode

  def stopEngine(engineNode: EngineNode, EMNode: EMNode): Unit

  def getEMNodes(scoreServiceInstances: Array[ScoreServiceInstance]): Array[EMNode]

  def getEMNodes(labels: util.List[Label[_]]): Array[EMNode]

}
