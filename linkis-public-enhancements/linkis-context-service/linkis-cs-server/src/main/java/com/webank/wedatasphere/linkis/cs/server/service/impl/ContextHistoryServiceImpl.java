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

import com.webank.wedatasphere.linkis.cs.common.entity.history.ContextHistory;
import com.webank.wedatasphere.linkis.cs.common.entity.source.ContextID;
import com.webank.wedatasphere.linkis.cs.common.exception.CSErrorException;
import com.webank.wedatasphere.linkis.cs.persistence.ContextPersistenceManager;
import com.webank.wedatasphere.linkis.cs.persistence.persistence.ContextHistoryPersistence;
import com.webank.wedatasphere.linkis.cs.persistence.persistence.KeywordContextHistoryPersistence;
import com.webank.wedatasphere.linkis.cs.server.enumeration.ServiceType;
import com.webank.wedatasphere.linkis.cs.server.service.ContextHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ContextHistoryServiceImpl extends ContextHistoryService {

    @Autowired
    private ContextPersistenceManager persistenceManager;

    @Override
    public String getName() {
        return ServiceType.CONTEXT_HISTORY.name();
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    private ContextHistoryPersistence getPersistence() throws CSErrorException {
        return persistenceManager.getContextHistoryPersistence();
    }

    private KeywordContextHistoryPersistence getKeywordPersistence() throws CSErrorException {
        return persistenceManager.getKeywordContextHistoryPersistence();
    }


    @Override
    public void createHistroy(ContextID contextID, ContextHistory contextHistory) throws CSErrorException {
        getPersistence().createHistory(contextID, contextHistory);
        logger.info(String.format("createHistory,csId:%s,contextType:%s,source:%s"
                , contextID.getContextId(), contextHistory.getContextType(), contextHistory.getSource()));
    }

    @Override
    public void removeHistory(ContextID contextID, ContextHistory contextHistory) throws CSErrorException {
        getPersistence().removeHistory(contextID, contextHistory.getSource());
        logger.info(String.format("removeHistory,csId:%s,contextType:%s,source:%s"
                , contextID.getContextId(), contextHistory.getContextType(), contextHistory.getSource()));
    }

    @Override
    public List<ContextHistory> getHistories(ContextID contextID) throws CSErrorException {
        logger.info(String.format("getHistories,csId:%s", contextID.getContextId()));
        return getPersistence().getHistories(contextID);
    }

    @Override
    public ContextHistory getHistory(ContextID contextID, String source) throws CSErrorException {
        logger.info(String.format("getHistory,csId:%s,source:%s", contextID.getContextId(), source));
        return getPersistence().getHistory(contextID, source);

    }

    @Override
    public List<ContextHistory> searchHistory(ContextID contextID, String[] keywords) throws CSErrorException {
        //return List<ContextHistory>
        logger.info(String.format("searchHistory,csId:%s,keywords:%s", contextID.getContextId(), Arrays.toString(keywords)));
        return getKeywordPersistence().search(contextID, keywords);
    }
}
