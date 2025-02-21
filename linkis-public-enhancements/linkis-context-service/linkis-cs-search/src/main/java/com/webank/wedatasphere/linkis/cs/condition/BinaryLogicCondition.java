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

package com.webank.wedatasphere.linkis.cs.condition;

public abstract class BinaryLogicCondition extends AbstractCommonCondition{

    Condition left;
    Condition right;

    public BinaryLogicCondition(Condition left, Condition right) {
        this.left = left;
        this.right = right;
    }

    public void shift(){
        Condition tmp = this.left;
        this.left = this.right;
        this.right = tmp;
    }

    public Condition getLeft() {
        return left;
    }

    public void setLeft(Condition left) {
        this.left = left;
    }

    public Condition getRight() {
        return right;
    }

    public void setRight(Condition right) {
        right = right;
    }
}
