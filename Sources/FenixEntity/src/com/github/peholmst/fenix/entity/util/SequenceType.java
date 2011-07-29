/*
 * Copyright (c) 2011 The original developers
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
package com.github.peholmst.fenix.entity.util;

/**
 * Enumeration of different sequence types.
 * 
 * @see Sequence
 * 
 * @author Petter Holmström
 */
public enum SequenceType {
    /**
     * Sequence for generating master IDs for historical entities.
     * 
     * @see HistoricalEntityBase#setMasterId(Long)
     */
    MASTER_ID
}
