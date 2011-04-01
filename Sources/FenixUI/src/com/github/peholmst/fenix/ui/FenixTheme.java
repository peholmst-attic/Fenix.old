/*
 * Copyright (c) 2011 Petter Holmström
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
package com.github.peholmst.fenix.ui;

import com.vaadin.ui.themes.Reindeer;

/**
 * This class contains constants used by the Fenix Theme.
 * 
 * @author Petter Holmström
 */
public final class FenixTheme extends Reindeer {

    public static final String THEME_NAME = "Fenix";

    private FenixTheme() {

    }

    public static String themeName() {
        return THEME_NAME;
    }

    public static final String BREADCRUMB_PANEL = "breadcrumbs";
}
