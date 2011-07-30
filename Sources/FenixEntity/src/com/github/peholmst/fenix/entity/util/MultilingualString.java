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

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;

import com.github.peholmst.fenix.common.Language;

/**
 * TODO Document me!
 * 
 * @author Petter Holmström
 */
@Entity
public class MultilingualString extends HistoricalEntityBase {

    private static final long serialVersionUID = 3284705062104224588L;

    @ElementCollection
    @CollectionTable(name = "MULTILINGUALSTRING_MAP",
            joinColumns = @JoinColumn(name = "STRING_ID"))
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "LANGUAGE")
    @Column(name = "TEXT")
    private Map<Language, String> texts = new HashMap<Language, String>();

    protected MultilingualString() {
    }

    public Map<Language, String> getTexts() {
        return texts;
    }

    public void setTexts(Map<Language, String> texts) {
        this.texts = texts;
    }

    public String getText(Language language) {
        return texts.get(language);
    }

    public void setText(Language language, String text) {
        texts.put(language, text);
    }
}
