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

package com.webank.wedatasphere.linkis.manager.engineplugin.io.domain

import com.webank.wedatasphere.linkis.common.io.Fs
import com.webank.wedatasphere.linkis.storage.utils.StorageConfiguration

/**
  * FS信息记录，包括Entrance的ID信息
  */
class FSInfo(val id: Long, val fs: Fs, var lastAccessTime: Long = System.currentTimeMillis()) {
  def timeout = System.currentTimeMillis() - lastAccessTime > (StorageConfiguration.IO_FS_EXPIRE_TIME.getValue + 60 * 1000)
}
