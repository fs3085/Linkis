/*
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.webank.wedatasphere.linkis.datasourcemanager.core.dao;

import com.webank.wedatasphere.linkis.datasourcemanager.common.domain.DataSourceType;

import java.util.List;

public interface DataSourceTypeDao {
    /**
     * Get all types
     * @return type entity list
     */
    List<DataSourceType> getAllTypes();

    /**
     * View
     * @param typeId
     * @return
     */
    DataSourceType selectOne(Long typeId);
}
