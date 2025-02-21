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

package com.webank.wedatasphere.linkis.cs.server.service;


import com.webank.wedatasphere.linkis.cs.common.entity.history.ContextHistory;
import com.webank.wedatasphere.linkis.cs.common.entity.source.ContextID;
import com.webank.wedatasphere.linkis.cs.common.exception.CSErrorException;

import java.util.List;

public abstract class ContextHistoryService extends AbstractService {

    public abstract void createHistroy(ContextID contextID, ContextHistory contextHistory) throws CSErrorException;

    public abstract void removeHistory(ContextID contextID, ContextHistory contextHistory) throws CSErrorException;

    public abstract List<ContextHistory> getHistories(ContextID contextID) throws CSErrorException;

    public abstract ContextHistory getHistory(ContextID contextID, String source) throws CSErrorException;

    public abstract List<ContextHistory> searchHistory(ContextID contextID, String[] keywords) throws CSErrorException;

}
