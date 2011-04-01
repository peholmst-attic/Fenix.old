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
package com.github.peholmst.fenix.ui.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;

import com.github.peholmst.fenix.common.SystemConstants;
import com.github.peholmst.i18n4vaadin.I18N;
import com.github.peholmst.i18n4vaadin.ResourceBundleI18N;

/**
 * This is an application scoped helper class that contains a CDI producer
 * method for creating session scoped {@link I18N} instances.
 * 
 * @author Petter Holmström
 */
@ApplicationScoped
public class I18NProducer {

    @Produces
    @SessionScoped
    public I18N createI18N() {
        I18N i18n = new ResourceBundleI18N(
                "com/github/peholmst/fenix/ui/i18n/messages",
                SystemConstants.SUPPORTED_LOCALES);
        i18n.setCurrentLocale(SystemConstants.DEFAULT_LOCALE);
        return i18n;
    }
}
