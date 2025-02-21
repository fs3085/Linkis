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

package com.webank.wedatasphere.linkis.cs.client.listener;

import com.webank.wedatasphere.linkis.common.listener.Event;
import com.webank.wedatasphere.linkis.common.listener.ListenerEventBus;

public class ContextClientListenerBus<L extends ContextClientListener, E extends Event> extends ListenerEventBus<L, E> {


    private static final String NAME = "ContextClientListenerBus";

    private static final int CAPACITY = 10;

    private static final int THREAD_SIZE = 20;

    private static final int MAX_FREE_TIME = 5000;

    public ContextClientListenerBus(){
        super(CAPACITY, NAME,THREAD_SIZE,MAX_FREE_TIME);
    }



    @Override
    public void doPostEvent(L listener, E event) {
        listener.onEvent(event);
    }
}
