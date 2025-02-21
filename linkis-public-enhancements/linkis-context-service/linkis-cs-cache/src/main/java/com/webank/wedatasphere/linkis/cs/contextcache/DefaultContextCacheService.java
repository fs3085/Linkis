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
import com.webank.wedatasphere.linkis.cs.contextcache.cache.ContextCache;

import com.webank.wedatasphere.linkis.cs.contextcache.cache.csid.ContextIDValue;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DefaultContextCacheService implements ContextCacheService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultContextCacheService.class);


    @Autowired
    private ContextCache contextCache ;

    @Override
    public ContextKeyValue put(ContextID contextID, ContextKeyValue csKeyValue) throws CSErrorException {

        if (null == contextID || null == csKeyValue) {
            return null;
        }

        logger.info("Start to put contextKey({}) to ContextID({})", csKeyValue.getContextKey().getKey(), contextID.getContextId());
        ContextIDValue contextIDValue = contextCache.getContextIDValue(contextID);
        ContextKeyValue oldValue = contextIDValue.getContextKeyValueContext().put(csKeyValue);
        logger.info("Finished to put contextKey({}) to ContextID({})", csKeyValue.getContextKey().getKey(), contextID.getContextId());

        return oldValue;
    }

    @Override
    public ContextKeyValue rest(ContextID contextID, ContextKey csKey) {
        if (null == contextID || null == csKey) {
            return null;
        }
        try {
            ContextIDValue contextIDValue = contextCache.getContextIDValue(contextID);
            return contextIDValue.getContextKeyValueContext().remove(csKey);
        } catch (Exception e) {
            logger.error(String.format("Failed to rest contextID(%s) of csKey(%s)",
                    contextID.getContextId(), csKey.getKey()), e);
        }
        return null;
    }

    @Override
    public ContextKeyValue get(ContextID contextID, ContextKey csKey) {
        if (null == contextID || null == csKey) {
            return null;
        }
        try {
            ContextIDValue contextIDValue = contextCache.getContextIDValue(contextID);
            return contextIDValue.getContextKeyValueContext().getContextKeyValue(csKey, csKey.getContextType());
        } catch (Exception e) {
            logger.error(String.format("Failed to get contextID(%s) of csKey(%s)",
                    contextID.getContextId(), csKey.getKey()), e);
        }
        return null;
    }

    @Override
    public List<ContextKeyValue> getValues(ContextID contextID, String keyword, ContextType csType) {
        if (null == contextID || StringUtils.isBlank(keyword)) {
            return null;
        }
        try {
            ContextIDValue contextIDValue = contextCache.getContextIDValue(contextID);
            return contextIDValue.getContextKeyValueContext().getValues(keyword, csType);
        } catch (Exception e) {
            logger.error(String.format("Failed to getValues contextID(%s) of keyword(%s)",
                    contextID.getContextId(), keyword), e);
        }
        return null;
    }

    @Override
    public List<ContextKeyValue> getAllLikes(ContextID contextID, String regex, ContextType csType) {
        if (null == contextID || StringUtils.isBlank(regex)) {
            return null;
        }
        try {
            ContextIDValue contextIDValue = contextCache.getContextIDValue(contextID);
            return contextIDValue.getContextKeyValueContext().getAllLikes(regex, csType);
        } catch (Exception e) {
            logger.error(String.format("Failed to getAllLikes contextID(%s) of regex(%s)",
                    contextID.getContextId(), regex), e);
        }
        return null;
    }

    @Override
    public List<ContextKeyValue> getAll(ContextID contextID) {

        if (null == contextID) {
            return null;
        }
        try {
            ContextIDValue contextIDValue = contextCache.getContextIDValue(contextID);
            return contextIDValue.getContextKeyValueContext().getAll();
        } catch (Exception e) {
            logger.error(String.format("Failed to getAllByType contextID(%s)",
                    contextID.getContextId()), e);
        }
        return null;
    }

    @Override
    public List<ContextKeyValue> getAllByScope(ContextID contextID, ContextScope scope, ContextType csType) {
        if (null == contextID && null == scope) {
            return null;
        }
        try {
            ContextIDValue contextIDValue = contextCache.getContextIDValue(contextID);
            List<ContextKeyValue> valueCSTypeList = contextIDValue.getContextKeyValueContext().getAllValues(csType);
            if (CollectionUtils.isNotEmpty(valueCSTypeList)){
                return  valueCSTypeList.stream()
                        .filter(contextKeyValue -> scope.equals(contextKeyValue.getContextKey().getContextScope()))
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            logger.error(String.format("Failed to getAllByScope contextID(%s) of ContextScope(%s) of csType(%s)",
                    contextID.getContextId(), scope, csType), e);
        }
        return null;
    }

    @Override
    public List<ContextKeyValue> getAllByType(ContextID contextID, ContextType csType) {
        if (null == contextID || StringUtils.isBlank(contextID.getContextId())) {
            return null;
        }
        try {
            ContextIDValue contextIDValue = contextCache.getContextIDValue(contextID);
            List<ContextKeyValue> allValues = contextIDValue.getContextKeyValueContext().getAllValues(csType);
            return allValues;
        } catch (Exception e) {
            logger.error(String.format("Failed to getAllByType contextID(%s) of csType(%s)",
                    contextID.getContextId(), csType), e);
        }
        return null;
    }

    @Override
    public ContextKeyValue remove(ContextID contextID, ContextKey csKey) {
        if (null == contextID || csKey == null) {
            return null;
        }
        try {
            ContextIDValue contextIDValue = contextCache.getContextIDValue(contextID);
            return contextIDValue.getContextKeyValueContext().remove(csKey);
        } catch (Exception e) {
            logger.error(String.format("Failed to remove contextID(%s) of csKey(%s)",
                    contextID.getContextId(), csKey.getKey()), e);
        }
        return null;
    }

    @Override
    public void removeAll(ContextID contextID) {
        if (null == contextID) {
            return;
        }
        try {
            contextCache.remove(contextID);
        } catch (Exception e) {
            logger.error(String.format("Failed to removeAll contextID(%s)", contextID.getContextId()), e);
        }
    }

    /**
     * TODO This method has poor performance and is not implemented now
     * @param contextID
     * @param scope
     * @param csType
     */
    @Override
    public void removeAll(ContextID contextID, ContextScope scope, ContextType csType) {

    }

    @Override
    public void removeAll(ContextID contextID, ContextType csType) {
        if (null == contextID) {
            return;
        }
        try {
            ContextIDValue contextIDValue = contextCache.getContextIDValue(contextID);
            contextIDValue.getContextKeyValueContext().removeAll(csType);
        } catch (Exception e) {
            logger.error(String.format("Failed to removeAll contextID(%s) of csType(%s)", contextID.getContextId(), csType), e);
        }
    }

    @Override
    public void removeByKeyPrefix(ContextID contextID, String preFix) {
        if (null == contextID || StringUtils.isBlank(preFix)) {
            return ;
        }
        try {
            ContextIDValue contextIDValue = contextCache.getContextIDValue(contextID);
            contextIDValue.getContextKeyValueContext().removeByKeyPrefix(preFix);
        } catch (Exception e) {
            logger.error(String.format("Failed to removeByKeyPrefix contextID(%s) of key preFix(%s)",
                    contextID.getContextId(), preFix), e);
        }
    }

    @Override
    public void removeByKeyPrefix(ContextID contextID, String preFix, ContextType csType) {
        if (null == contextID || StringUtils.isBlank(preFix) || null == csType) {
            return ;
        }
        try {
            ContextIDValue contextIDValue = contextCache.getContextIDValue(contextID);
            contextIDValue.getContextKeyValueContext().removeByKeyPrefix(preFix, csType);
        } catch (Exception e) {
            logger.error(String.format("Failed to removeByKeyPrefix contextID(%s) of key preFix(%s) and csyTye(%s)",
                    contextID.getContextId(), preFix, csType.name()), e);
        }
    }
}
