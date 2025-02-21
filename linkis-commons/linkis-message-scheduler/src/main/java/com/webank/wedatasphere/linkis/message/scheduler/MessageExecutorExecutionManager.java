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

package com.webank.wedatasphere.linkis.message.scheduler;

import com.webank.wedatasphere.linkis.common.utils.Utils;
import com.webank.wedatasphere.linkis.scheduler.executer.Executor;
import com.webank.wedatasphere.linkis.scheduler.executer.ExecutorManager;
import com.webank.wedatasphere.linkis.scheduler.listener.ExecutorListener;
import com.webank.wedatasphere.linkis.scheduler.queue.SchedulerEvent;
import scala.Option;
import scala.Some;
import scala.concurrent.duration.Duration;

import java.util.concurrent.ExecutorService;


public class MessageExecutorExecutionManager extends ExecutorManager {

    private final ExecutorService executorService = Utils.newCachedThreadPool(
            Runtime.getRuntime().availableProcessors() * 2, "message-executor_", false);

    @Override
    public void setExecutorListener(ExecutorListener executorListener) {

    }

    @Override
    public Executor createExecutor(SchedulerEvent event) {
        return new DefaultMessageExecutor(event, executorService);
    }

    @Override
    public Option<Executor> askExecutor(SchedulerEvent event) {
        return new Some<>(createExecutor(event));
    }

    @Override
    public Option<Executor> askExecutor(SchedulerEvent event, Duration wait) {
        return askExecutor(event);
    }

    @Override
    public Option<Executor> getById(long id) {
        return new Some<>(null);
    }

    @Override
    public Executor[] getByGroup(String groupName) {
        return new Executor[0];
    }

    @Override
    public void delete(Executor executor) {

    }

    @Override
    public void shutdown() {

    }
}
