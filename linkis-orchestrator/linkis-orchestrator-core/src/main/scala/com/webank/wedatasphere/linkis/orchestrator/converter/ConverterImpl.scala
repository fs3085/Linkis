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

package com.webank.wedatasphere.linkis.orchestrator.converter

import com.webank.wedatasphere.linkis.orchestrator.domain.JobReq
import com.webank.wedatasphere.linkis.orchestrator.extensions.catalyst.{ConverterCheckRuler, ConverterTransform}
import com.webank.wedatasphere.linkis.orchestrator.plans.ast.ASTContext

/**
  *
  */
class ConverterImpl extends AbstractConverter {

  override protected def converterTransforms: Array[ConverterTransform] = Array.empty

  override protected def converterCheckRulers: Array[ConverterCheckRuler] = {
    Array()
  }

  override protected def createASTContext(jobReq: JobReq): ASTContext = ASTContextImpl.newBuilder().setJobReq(jobReq).build()

}
