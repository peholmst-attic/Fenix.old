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
package com.github.peholmst.fenix.ui.components;

import java.util.Locale;

import com.github.peholmst.fenix.common.SystemConstants;
import com.github.peholmst.fenix.ui.FenixTheme;
import com.github.peholmst.i18n4vaadin.I18N;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;

/**
 * This component allows the user to change the current locale of an
 * {@link I18N}-instance. The available locales to choose from have been taken
 * from {@link SystemConstants#SUPPORTED_LOCALES}.
 * 
 * @author Petter Holmström
 */
public class ChangeLanguageComponent extends HorizontalLayout {

    private static final long serialVersionUID = 611280978260610928L;

    private final Button.ClickListener languageButtonListener = createLanguageButtonListener();

    private final I18N i18n;

    public ChangeLanguageComponent(I18N i18n) {
        this.i18n = i18n;
        setSpacing(true);
        initLanguageButtons();
    }

    private Button.ClickListener createLanguageButtonListener() {
        return new Button.ClickListener() {

            private static final long serialVersionUID = 3397745770432307153L;

            @Override
            public void buttonClick(ClickEvent event) {
                final Locale newLocale = (Locale) event.getButton().getData();
                i18n.setCurrentLocale(newLocale);
            }
        };
    }

    private void initLanguageButtons() {
        for (int i = 0; i < SystemConstants.SUPPORTED_LOCALES.length; ++i) {
            final Locale locale = SystemConstants.SUPPORTED_LOCALES[i];
            final String displayName = SystemConstants.SUPPORTED_LOCALE_DISPLAY_NAMES[i];

            final Button languageButton = new Button(displayName);
            languageButton.setStyleName(FenixTheme.BUTTON_LINK);
            languageButton.setData(locale);
            languageButton.addListener(languageButtonListener);
            addComponent(languageButton);
        }
    }

}
