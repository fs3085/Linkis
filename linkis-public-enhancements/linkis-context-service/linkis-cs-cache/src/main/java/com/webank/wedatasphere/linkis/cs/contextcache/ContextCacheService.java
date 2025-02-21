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
package com.webank.wedatasphere.linkis.cs.contextcache;

import com.webank.wedatasphere.linkis.cs.common.entity.enumeration.ContextScope;
import com.webank.wedatasphere.linkis.cs.common.entity.enumeration.ContextType;
import com.webank.wedatasphere.linkis.cs.common.entity.source.ContextID;
import com.webank.wedatasphere.linkis.cs.common.entity.source.ContextKey;
import com.webank.wedatasphere.linkis.cs.common.entity.source.ContextKeyValue;
import com.webank.wedatasphere.linkis.cs.common.exception.CSErrorException;

import java.util.List;

public interface ContextCacheService {

   ContextKeyValue put(ContextID contextID, ContextKeyValue csKeyValue) throws CSErrorException;

   ContextKeyValue rest(ContextID contextID, ContextKey csKey);

   ContextKeyValue get(ContextID contextID, ContextKey csKey);

   List<ContextKeyValue> getValues(ContextID contextID, String keyword, ContextType csType);

   List<ContextKeyValue> getAllLikes(ContextID contextID, String regex, ContextType csType);

   List<ContextKeyValue> getAll(ContextID contextID);

   List<ContextKeyValue> getAllByScope(ContextID contextID, ContextScope scope, ContextType csType);

   List<ContextKeyValue> getAllByType(ContextID contextID, ContextType csType);

   ContextKeyValue remove(ContextID contextID, ContextKey csKey);

   void  removeAll(ContextID contextID);

   void  removeAll(ContextID contextID, ContextScope scope, ContextType csType);

   void  removeAll(ContextID contextID, ContextType csType);

   void removeByKeyPrefix(ContextID contextID, String preFix);

   void removeByKeyPrefix(ContextID contextID, String preFix, ContextType csType);
}
