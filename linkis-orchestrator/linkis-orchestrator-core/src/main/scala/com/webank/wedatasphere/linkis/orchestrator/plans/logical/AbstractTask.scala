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

package com.webank.wedatasphere.linkis.orchestrator.plans.logical

/**
  *
  */
abstract class AbstractTask extends Task {

  private var taskDesc: TaskDesc = _
  private var _isResolved: Boolean = false

  override def getTaskDesc: TaskDesc = taskDesc

  override def setTaskDesc(taskDesc: TaskDesc): Unit = this.taskDesc = taskDesc

  override def isResolved: Boolean = _isResolved

  override def simpleString: String = ???

  override def verboseString: String = ???

  //override def theSame(other: Task): Boolean = ???

}
