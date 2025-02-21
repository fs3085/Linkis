/*
 *
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
 *
 */

package com.webank.wedatasphere.linkis.manager.common.protocol.conf;

import com.webank.wedatasphere.linkis.manager.label.entity.engine.EngineTypeLabel;
import com.webank.wedatasphere.linkis.manager.label.entity.engine.UserCreatorLabel;
import com.webank.wedatasphere.linkis.protocol.message.RequestProtocol;

public class RemoveCacheConfRequest implements RequestProtocol {

    private UserCreatorLabel userCreatorLabel;

    private EngineTypeLabel engineTypeLabel;

    public UserCreatorLabel getUserCreatorLabel() {
        return userCreatorLabel;
    }

    public void setUserCreatorLabel(UserCreatorLabel userCreatorLabel) {
        this.userCreatorLabel = userCreatorLabel;
    }

    public EngineTypeLabel getEngineTypeLabel() {
        return engineTypeLabel;
    }

    public void setEngineTypeLabel(EngineTypeLabel engineTypeLabel) {
        this.engineTypeLabel = engineTypeLabel;
    }
}
