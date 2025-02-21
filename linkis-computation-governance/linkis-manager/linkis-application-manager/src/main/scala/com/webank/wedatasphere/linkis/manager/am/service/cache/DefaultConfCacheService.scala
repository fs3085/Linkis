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
package com.webank.wedatasphere.linkis.manager.am.service.cache

import com.webank.wedatasphere.linkis.common.utils.Logging
import com.webank.wedatasphere.linkis.governance.common.protocol.conf.{RequestQueryEngineConfig, RequestQueryGlobalConfig}
import com.webank.wedatasphere.linkis.manager.common.protocol.conf.RemoveCacheConfRequest
import com.webank.wedatasphere.linkis.message.annotation.Receiver
import com.webank.wedatasphere.linkis.rpc.interceptor.common.CacheableRPCInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DefaultConfCacheService extends Logging {

  @Autowired
  private var cacheableRPCInterceptor: CacheableRPCInterceptor = _

  @Receiver
  def removeCacheConfiguration(removeCacheConfRequest: RemoveCacheConfRequest): Boolean = {
    if (removeCacheConfRequest.getUserCreatorLabel != null) {
      if (removeCacheConfRequest.getEngineTypeLabel != null) {
        val request = RequestQueryEngineConfig(removeCacheConfRequest.getUserCreatorLabel, removeCacheConfRequest.getEngineTypeLabel)
        cacheableRPCInterceptor.removeCache(request.toString)
        info(s"success to clear cache about configuration of ${removeCacheConfRequest.getEngineTypeLabel.getStringValue}-${removeCacheConfRequest.getEngineTypeLabel.getStringValue}")
      } else {
        val request = RequestQueryGlobalConfig(removeCacheConfRequest.getUserCreatorLabel.getUser)
        cacheableRPCInterceptor.removeCache(request.toString)
        info(s"success to clear cache about global configuration of ${removeCacheConfRequest.getUserCreatorLabel.getUser}")
      }
    }
    true
  }
}
