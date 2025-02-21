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

import tokenTypes from './tokenTypes';

const INLINE_MAX_LENGTH = 50;

/**
 * Bookkeeper for inline blocks.
 *
 * Inline blocks are parenthized expressions that are shorter than INLINE_MAX_LENGTH.
 * These blocks are formatted on a single line, unlike longer parenthized
 * expressions where open-parenthesis causes newline and increase of indentation.
 */
export default class InlineBlock {
  /**
     *
     */
  constructor() {
    this.level = 0;
  }

  /**
     * Begins inline block when lookahead through upcoming tokens determines
     * that the block would be smaller than INLINE_MAX_LENGTH.
     * @param  {Object[]} tokens Array of all tokens
     * @param  {Number} index Current token position
     */
  beginIfPossible(tokens, index) {
    if (this.level === 0 && this.isInlineBlock(tokens, index)) {
      this.level = 1;
    } else if (this.level > 0) {
      this.level++;
    } else {
      this.level = 0;
    }
  }

  /**
     * Finishes current inline block.
     * There might be several nested ones.
     */
  end() {
    this.level--;
  }

  /**
     * True when inside an inline block
     * @return {Boolean}
     */
  isActive() {
    return this.level > 0;
  }

  /**
     * Check if this should be an inline parentheses block
     * Examples are "NOW()", "COUNT(*)", "int(10)", key(`somecolumn`), DECIMAL(7,2)
     * @param {*} tokens
     * @param {*} index
     * @return {*}
     */
  isInlineBlock(tokens, index) {
    let length = 0;
    let level = 0;

    for (let i = index; i < tokens.length; i++) {
      const token = tokens[i];
      length += token.value.length;

      // Overran max length
      if (length > INLINE_MAX_LENGTH) {
        return false;
      }

      if (token.type === tokenTypes.OPEN_PAREN) {
        level++;
      } else if (token.type === tokenTypes.CLOSE_PAREN) {
        level--;
        if (level === 0) {
          return true;
        }
      }

      if (this.isForbiddenToken(token)) {
        return false;
      }
    }
    return false;
  }

  /**
     * Reserved words that cause newlines, comments and semicolons
     * are not allowed inside inline parentheses block
     * @param {*} param0
     * @return {*}
     */
  isForbiddenToken({ type, value }) {
    return type === tokenTypes.RESERVED_TOPLEVEL ||
            type === tokenTypes.RESERVED_NEWLINE ||
            type === tokenTypes.COMMENT ||
            type === tokenTypes.BLOCK_COMMENT ||
            value === ';';
  }
}
