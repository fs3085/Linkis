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
package com.webank.wedatasphere.linkis.cs.contextcache.cache.csid.impl;

import com.webank.wedatasphere.linkis.common.exception.FatalException;
import com.webank.wedatasphere.linkis.cs.common.entity.listener.ContextIDListenerDomain;
import com.webank.wedatasphere.linkis.cs.common.entity.listener.ContextKeyListenerDomain;
import com.webank.wedatasphere.linkis.cs.common.entity.source.ContextID;
import com.webank.wedatasphere.linkis.cs.common.entity.source.ContextKeyValue;
import com.webank.wedatasphere.linkis.cs.common.exception.CSErrorException;
import com.webank.wedatasphere.linkis.cs.contextcache.cache.csid.ContextIDValue;
import com.webank.wedatasphere.linkis.cs.contextcache.cache.csid.ContextIDValueGenerator;
import com.webank.wedatasphere.linkis.cs.contextcache.cache.cskey.ContextKeyValueContext;
import com.webank.wedatasphere.linkis.cs.listener.ListenerBus.ContextAsyncListenerBus;
import com.webank.wedatasphere.linkis.cs.listener.callback.imp.DefaultContextIDCallbackEngine;
import com.webank.wedatasphere.linkis.cs.listener.callback.imp.DefaultContextKeyCallbackEngine;
import com.webank.wedatasphere.linkis.cs.listener.manager.imp.DefaultContextListenerManager;
import com.webank.wedatasphere.linkis.cs.persistence.ContextPersistenceManager;
import com.webank.wedatasphere.linkis.cs.persistence.persistence.ContextIDListenerPersistence;
import com.webank.wedatasphere.linkis.cs.persistence.persistence.ContextKeyListenerPersistence;
import com.webank.wedatasphere.linkis.cs.persistence.persistence.ContextMapPersistence;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public abstract class ContextIDValueGeneratorImpl implements ContextIDValueGenerator {

    private static final Logger logger = LoggerFactory.getLogger(ContextIDValueGeneratorImpl.class);

    private ContextAsyncListenerBus listenerBus = DefaultContextListenerManager.getInstance().getContextAsyncListenerBus();

    @Lookup
    protected abstract ContextKeyValueContext getContextKeyValueContext();

    @Autowired
    private ContextPersistenceManager contextPersistenceManager;


    private ContextMapPersistence contextMapPersistence;

    private ContextIDListenerPersistence contextIDListenerPersistence;

    private ContextKeyListenerPersistence contextKeyListenerPersistence;

    private DefaultContextIDCallbackEngine contextIDCallbackEngine;

    private DefaultContextKeyCallbackEngine contextKeyCallbackEngine;

    @PostConstruct
    void init() throws FatalException{
        try {
            this.contextIDCallbackEngine = DefaultContextListenerManager.getInstance().getContextIDCallbackEngine();
            this.contextKeyCallbackEngine = DefaultContextListenerManager.getInstance().getContextKeyCallbackEngine();
            this.contextMapPersistence = contextPersistenceManager.getContextMapPersistence();
            this.contextIDListenerPersistence = contextPersistenceManager.getContextIDListenerPersistence();
            this.contextKeyListenerPersistence = contextPersistenceManager.getContextKeyListenerPersistence();
        } catch (Exception e) {
            throw new FatalException(97001, "Failed to get proxy of contextMapPersistence");
        }
    }


    @Override
    public ContextIDValue createContextIDValue(ContextID contextID) throws CSErrorException {
        logger.info("Start to createContextIDValue of ContextID({}) ", contextID.getContextId());

        if (contextMapPersistence == null ) {
            throw new CSErrorException(97001, "Failed to get proxy of contextMapPersistence");
        }

        List<ContextKeyValue> contextKeyValueList = contextMapPersistence.getAll(contextID);

        ContextKeyValueContext contextKeyValueContext = getContextKeyValueContext();
        contextKeyValueContext.setContextID(contextID);
        contextKeyValueContext.putAll(contextKeyValueList);


        try {
            logger.info("For contextID({}) register contextKeyListener", contextID.getContextId());
            List<ContextKeyListenerDomain> contextKeyListenerPersistenceAll = this.contextKeyListenerPersistence.getAll(contextID);
            if (CollectionUtils.isNotEmpty(contextKeyListenerPersistenceAll)){
                for (ContextKeyListenerDomain contextKeyListenerDomain : contextKeyListenerPersistenceAll){
                    this.contextKeyCallbackEngine.registerClient(contextKeyListenerDomain);
                }
            }
            logger.info("For contextID({}) register contextIDListener", contextID.getContextId());
            List<ContextIDListenerDomain> contextIDListenerPersistenceAll = this.contextIDListenerPersistence.getAll(contextID);

            if (CollectionUtils.isNotEmpty(contextIDListenerPersistenceAll)) {
                for (ContextIDListenerDomain contextIDListenerDomain : contextIDListenerPersistenceAll) {
                    this.contextIDCallbackEngine.registerClient(contextIDListenerDomain);
                }
            }
        } catch (Throwable e) {
            logger.error("Failed to register listener: ", e);
        }

        logger.info("Finished to createContextIDValue of ContextID({}) ", contextID.getContextId());
        ContextIDValueImpl contextIDValue = new ContextIDValueImpl(contextID.getContextId(), contextKeyValueContext);
        listenerBus.addListener(contextIDValue);
        return contextIDValue;
    }

}
