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

package com.webank.wedatasphere.linkis.orchestrator.validator

import com.webank.wedatasphere.linkis.orchestrator.extensions.catalyst.ValidatorCheckRuler
import com.webank.wedatasphere.linkis.orchestrator.plans.ast.ASTOrchestration

/**
  *
  *
  */
abstract class AbstractValidator extends Validator {


  override def validate(astPlan: ASTOrchestration[_]): Unit = {
    if (null != validatorCheckRulers) {
      validatorCheckRulers.foreach(_.apply(astPlan, astPlan.getASTContext))
    }
  }

  protected def validatorCheckRulers: Array[ValidatorCheckRuler]
}
