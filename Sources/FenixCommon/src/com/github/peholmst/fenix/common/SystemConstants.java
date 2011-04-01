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
package com.github.peholmst.fenix.common;

import java.util.Locale;

/**
 * This interface contains system wide constants that are used by different
 * modules.
 * 
 * @author Petter Holmström
 */
public interface SystemConstants {

    /**
     * The JNDI name of the JDBC data source.
     */
    public static final String JDBC_DATASOURCE_JNDI_NAME = "jdbc/fenixDatasource";

    /**
     * The name of the database sequence that is used for generating entity
     * identifiers.
     */
    public static final String ENTITY_IDENTIFIER_SEQUENCE_NAME = "seqIdentifier";

    /**
     * The supported locales. The UI can be viewed in any of these languages.
     * Localized data can also be stored in all of these languages.
     */
    public static final Locale[] SUPPORTED_LOCALES = { Locale.ENGLISH,
            new Locale("sv"), new Locale("fi") };

    /**
     * User readable names of the locales in {@link #SUPPORTED_LOCALES}.
     */
    public static final String[] SUPPORTED_LOCALE_DISPLAY_NAMES = { "English",
            "Svenska", "Suomi" };

    /**
     * The default locale used by all new application instances.
     */
    public static final Locale DEFAULT_LOCALE = Locale.ENGLISH;

    /**
     * The name of the application usable in all languages.
     */
    public static final String APPLICATION_TITLE = "Fenix";
}
