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

package com.webank.wedatasphere.linkis.cs.condition.impl;

import com.webank.wedatasphere.linkis.cs.condition.Condition;
import com.webank.wedatasphere.linkis.cs.condition.ConditionType;
import com.webank.wedatasphere.linkis.cs.condition.UnaryLogicCondition;

public class NearestCondition extends UnaryLogicCondition {

    String currentNode;
    Integer number;
    Boolean upstreamOnly;

    public NearestCondition(Condition origin, String currentNode, Integer number, Boolean upstreamOnly) {
        super(origin);
        this.currentNode = currentNode;
        this.number = number;
        this.upstreamOnly = upstreamOnly;
    }

    public String getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(String currentNode) {
        this.currentNode = currentNode;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getUpstreamOnly() {
        return upstreamOnly;
    }

    public void setUpstreamOnly(Boolean upstreamOnly) {
        this.upstreamOnly = upstreamOnly;
    }

    @Override
    public ConditionType getConditionType() {
        return ConditionType.Logic;
    }
}
