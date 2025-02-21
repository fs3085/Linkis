/*
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
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

import repeat from 'lodash/repeat';
import last from 'lodash/last';

const INDENT_TYPE_TOP_LEVEL = 'top-level';
const INDENT_TYPE_BLOCK_LEVEL = 'block-level';

/**
 * Manages indentation levels.
 *
 * There are two types of indentation levels:
 *
 * - BLOCK_LEVEL : increased by open-parenthesis
 * - TOP_LEVEL : increased by RESERVED_TOPLEVEL words
 */
export default class Indentation {
  /**
     * @param {String} indent Indent value, default is "  " (2 spaces)
     */
  constructor(indent) {
    this.indent = indent || '  ';
    this.indentTypes = [];
  }

  /**
     * Returns current indentation string.
     * @return {String}
     */
  getIndent() {
    return repeat(this.indent, this.indentTypes.length);
  }

  /**
     * Increases indentation by one top-level indent.
     */
  increaseToplevel() {
    this.indentTypes.push(INDENT_TYPE_TOP_LEVEL);
  }

  /**
     * Increases indentation by one block-level indent.
     */
  increaseBlockLevel() {
    this.indentTypes.push(INDENT_TYPE_BLOCK_LEVEL);
  }

  /**
     * Decreases indentation by one top-level indent.
     * Does nothing when the previous indent is not top-level.
     */
  decreaseTopLevel() {
    if (last(this.indentTypes) === INDENT_TYPE_TOP_LEVEL) {
      this.indentTypes.pop();
    }
  }

  /**
     * Decreases indentation by one block-level indent.
     * If there are top-level indents within the block-level indent,
     * throws away these as well.
     */
  decreaseBlockLevel() {
    while (this.indentTypes.length > 0) {
      const type = this.indentTypes.pop();
      if (type !== INDENT_TYPE_TOP_LEVEL) {
        break;
      }
    }
  }
}
