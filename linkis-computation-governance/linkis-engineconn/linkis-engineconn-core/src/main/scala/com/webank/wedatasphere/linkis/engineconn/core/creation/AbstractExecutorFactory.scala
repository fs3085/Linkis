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

package com.webank.wedatasphere.linkis.engineconn.core.creation

import com.webank.wedatasphere.linkis.engineconn.common.creation.EngineCreationContext
import com.webank.wedatasphere.linkis.engineconn.common.engineconn.EngineConn
import com.webank.wedatasphere.linkis.engineconn.core.executor.ExecutorManager
import com.webank.wedatasphere.linkis.engineconn.executor.entity.Executor
import com.webank.wedatasphere.linkis.manager.engineplugin.common.creation.ExecutorFactory


trait AbstractExecutorFactory extends ExecutorFactory {

  protected def newExecutor(id: Int, engineCreationContext: EngineCreationContext,
                            engineConn: EngineConn): Executor

  override def createExecutor(engineCreationContext: EngineCreationContext,
                              engineConn: EngineConn): Executor = {
    val id = ExecutorManager.getInstance.generateExecutorId()
    newExecutor(id, engineCreationContext, engineConn)
  }

}
