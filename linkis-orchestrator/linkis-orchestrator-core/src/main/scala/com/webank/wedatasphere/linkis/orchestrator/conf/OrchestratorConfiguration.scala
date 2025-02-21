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

package com.webank.wedatasphere.linkis.orchestrator.conf

import com.webank.wedatasphere.linkis.common.conf.{CommonVars, TimeType}

/**
  *
  *
  */
object OrchestratorConfiguration {

  val ORCHESTRATOR_BUILDER_CLASS = CommonVars("wds.linkis.orchestrator.builder.class", "")


  val ORCHESTRATOR_VERSION = CommonVars("wds.linkis.orchestrator.version", "1.0.0")

  val ORCHESTRATOR_LISTENER_ASYNC_QUEUE_CAPACITY = CommonVars("wds.linkis.orchestrator.listener.async.queue.size.max", 300)

  val ORCHESTRATOR_LISTENER_ASYNC_CONSUMER_THREAD_MAX = CommonVars("wds.linkis.orchestrator.listener.async.consumer.thread.max", 5)


  val ORCHESTRATOR_LISTENER_ASYNC_CONSUMER_THREAD_FREE_TIME_MAX = CommonVars("wds.linkis.orchestrator.listener.async.consumer.freetime.max", new TimeType("5000ms"))

  val ORCHESTRATOR_EXECUTOR_THREAD_MAX = CommonVars("wds.linkis.orchestrator.executor.thread.max", 20)


  val TASK_SCHEDULER_CLEAR_TIME = CommonVars("wds.linkis.task.scheduler.clear.time", new TimeType("1m"))


  val EXECUTION_TASK_MAX_PARALLELISM = CommonVars("wds.linkis.orchestrator.execution.task.max.parallelism", 5)

  val TASK_RUNNER_MAX_SIZE = CommonVars("wds.linkis.orchestrator.execution.task.runner.max.size", 200)

  val EXEC_RUNNER_FACTORY_CLASS = CommonVars("wds.linkis.orchestrator.exec.task.runner.factory.class", "")


  val TASK_MAX_PERSIST_WAIT_TIME = CommonVars("wds.linkis.orchestrator.task.persist.wait.max", new TimeType("5m"))

  val RETRY_TASK_WAIT_TIME = CommonVars("wds.linkis.orchestrator.task.retry.wait.time", 10000)

  val TASK_SCHEDULER_THREAD_POOL = CommonVars("wds.linkis.orchestrator.task.scheduler.thread.pool", 200)

  val ORCHESTRATOR_EXECUTION_FACTORY = CommonVars("wds.linkis.orchestrator.execution.factory.class", "com.webank.wedatasphere.linkis.orchestrator.code.plans.execution.CodeExecutionFactory")

  val TASK_CONSUMER_WAIT = CommonVars("wds.linkis.orchestrator.task.consumer.wait", 500)

  val ORCHESTRATOR_USER_MAX_RUNNING = CommonVars("wds.linkis.task.user.max.running", 5)
}
