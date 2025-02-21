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
 *//*


package com.webank.wedatasphere.linkis.cs.common.serialize.impl.value;

import com.webank.wedatasphere.linkis.common.io.MetaData;
import com.webank.wedatasphere.linkis.cs.common.exception.CSErrorException;
import com.webank.wedatasphere.linkis.cs.common.serialize.AbstractSerializer;
import com.webank.wedatasphere.linkis.cs.common.serialize.ContextSerializer;


public class MetadataContextSerializer implements ContextSerializer<MetaData> {

    private AbstractSerializer contextSerializer = new JSONSerializer();


    @Override
    public boolean accepts(String json) {
        return false;
    }

    @Override
    public boolean accepts(Object obj) {
        return false;
    }

    @Override
    public String serialize(MetaData metaData) throws CSErrorException {
        return contextSerializer.encode(metaData);
    }

    @Override
    public MetaData deserialize(String s) throws CSErrorException {
        return contextSerializer.decode(s, MetaData.class);
    }

}
*/
