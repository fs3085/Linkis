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

package com.webank.wedatasphere.linkis.cs.listener.conf;

import com.webank.wedatasphere.linkis.common.conf.CommonVars;

public class ContextListenerConf {
    public final static Integer WDS_CS_LISTENER_ASYN_CONSUMER_THREAD_MAX = Integer.parseInt(CommonVars.apply("wds.linkis.cs.listener.asyn.consumer.thread.max","5").getValue());
    public final static Long WDS_CS_LISTENER_ASYN_CONSUMER_THREAD_FREE_TIME_MAX = Long.parseLong(CommonVars.apply("wds.linkis.cs.listener.asyn.consumer.freeTime.max","5000").getValue());
    public final static Integer WDS_CS_LISTENER_ASYN_QUEUE_CAPACITY =Integer.parseInt(CommonVars.apply("wds.linkis.cs.listener.asyn.queue.size.max","300").getValue()) ;
}
