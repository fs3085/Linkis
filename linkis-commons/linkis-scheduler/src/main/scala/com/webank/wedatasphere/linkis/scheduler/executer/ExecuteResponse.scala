/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.webank.wedatasphere.linkis.scheduler.executer


trait ExecuteResponse
trait CompletedExecuteResponse extends ExecuteResponse
case class SuccessExecuteResponse() extends CompletedExecuteResponse

trait OutputExecuteResponse extends ExecuteResponse {
  def getOutput: String
}

case class AliasOutputExecuteResponse(alias: String, output: String) extends OutputExecuteResponse {
  override def getOutput: String = output
}

case class ErrorExecuteResponse(message: String, t: Throwable) extends CompletedExecuteResponse

case class IncompleteExecuteResponse(message: String) extends ExecuteResponse

case class SubmitResponse(taskId: String) extends ExecuteResponse

trait AsynReturnExecuteResponse extends ExecuteResponse {
  def notify(rs: ExecuteResponse => Unit): Unit
}