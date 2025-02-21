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

package com.webank.wedatasphere.linkis.cs.server.service.impl;

import com.webank.wedatasphere.linkis.cs.common.entity.listener.CommonContextKeyListenerDomain;
import com.webank.wedatasphere.linkis.cs.common.entity.listener.ContextIDListenerDomain;
import com.webank.wedatasphere.linkis.cs.common.entity.listener.ContextKeyListenerDomain;
import com.webank.wedatasphere.linkis.cs.common.entity.source.ContextID;
import com.webank.wedatasphere.linkis.cs.common.entity.source.ContextKey;
import com.webank.wedatasphere.linkis.cs.common.exception.CSErrorException;
import com.webank.wedatasphere.linkis.cs.listener.callback.imp.ContextKeyValueBean;
import com.webank.wedatasphere.linkis.cs.listener.manager.imp.DefaultContextListenerManager;
import com.webank.wedatasphere.linkis.cs.persistence.ContextPersistenceManager;
import com.webank.wedatasphere.linkis.cs.persistence.persistence.ContextIDListenerPersistence;
import com.webank.wedatasphere.linkis.cs.persistence.persistence.ContextKeyListenerPersistence;
import com.webank.wedatasphere.linkis.cs.server.enumeration.ServiceType;
import com.webank.wedatasphere.linkis.cs.server.service.ContextListenerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContextListenerServiceImpl extends ContextListenerService {

    @Autowired
    private ContextPersistenceManager persistenceManager;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    private ContextIDListenerPersistence getIDListenerPersistence() throws CSErrorException {
        return persistenceManager.getContextIDListenerPersistence();
    }

    private ContextKeyListenerPersistence getKeyListenerPersistence() throws CSErrorException {
        return persistenceManager.getContextKeyListenerPersistence();
    }

    @Override
    public String getName() {
        return ServiceType.CONTEXT_LISTENER.name();
    }


    @Override
    public void onBind(ContextID contextID, ContextIDListenerDomain domain) throws CSErrorException {
        logger.info(String.format("onBind,csId:%s", contextID.getContextId()));
        domain.setContextID(contextID);
        getIDListenerPersistence().create(contextID, domain);
        DefaultContextListenerManager instance = DefaultContextListenerManager.getInstance();
        instance.getContextIDCallbackEngine().registerClient(domain);
    }

    @Override
    public void onBind(ContextID contextID, ContextKey contextKey, ContextKeyListenerDomain domain) throws CSErrorException {
        logger.info(String.format("onBind,csId:%s,key:%s", contextID.getContextId(), contextKey.getKey()));
        domain.setContextKey(contextKey);
        // TODO: 2020/2/28  
        if (domain instanceof CommonContextKeyListenerDomain) {
            ((CommonContextKeyListenerDomain) domain).setContextID(contextID);
        }
        getKeyListenerPersistence().create(contextID, domain);
        DefaultContextListenerManager instance = DefaultContextListenerManager.getInstance();
        instance.getContextKeyCallbackEngine().registerClient(domain);
    }

    @Override
    public List<ContextKeyValueBean> heartbeat(String clientSource) {
        logger.info(String.format("heartbeat,clientSource:%s", clientSource));
        DefaultContextListenerManager instance = DefaultContextListenerManager.getInstance();
        ArrayList<ContextKeyValueBean> idCallback = instance.getContextIDCallbackEngine().getListenerCallback(clientSource);
        ArrayList<ContextKeyValueBean> keyCallback = instance.getContextKeyCallbackEngine().getListenerCallback(clientSource);
        idCallback.addAll(keyCallback);
        return idCallback;
    }
}
