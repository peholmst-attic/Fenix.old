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
package com.github.peholmst.fenix.entity;

import com.github.peholmst.fenix.common.Language;
import com.github.peholmst.fenix.entity.util.HistoricalEntityBaseTestCase;
import com.github.peholmst.fenix.entity.util.MultilingualString;

public class CFDTest extends HistoricalEntityBaseTestCase<CFD> {

    @Override
    protected Class<CFD> getEntityClass() {
        return CFD.class;
    }

    @Override
    protected void populateTransientEntityWithData(CFD entity) {
        MultilingualString name = MultilingualString
                .createEntity(MultilingualString.class);
        name.setText(Language.SWEDISH, "Svenska");
        entity.setName(name);
    }

}
