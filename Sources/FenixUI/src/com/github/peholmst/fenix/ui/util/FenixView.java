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

import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.peholmst.i18n4vaadin.I18N;
import com.github.peholmst.i18n4vaadin.I18NListener;
import com.github.peholmst.mvp4vaadin.AbstractView;
import com.github.peholmst.mvp4vaadin.Presenter;
import com.github.peholmst.mvp4vaadin.VaadinView;
import com.github.peholmst.mvp4vaadin.View;

/**
 * 
 * @author peholmst
 *
 * @param <V>
 * @param <P>
 */
public abstract class FenixView<V extends View, P extends Presenter<V>> extends AbstractView<V, P> implements VaadinView, I18NListener {

    private static final long serialVersionUID = 2051721540974578241L;

    private static final Logger log = LoggerFactory.getLogger(FenixView.class);

    @Inject
    private I18N i18n;
    
    @Override
    @PostConstruct
    public void init() throws IllegalStateException {
        super.init();
        log.debug("Adding I18NListener to {}", i18n);
        i18n.addListener(this);
    }
    
    /**
     * 
     */
    @PreDestroy
    public void destroy() {
        log.debug("Removing I18NListener from {}", i18n);
        i18n.removeListener(this);
    }

    /**
     * 
     */
    protected abstract void updateCaptions();
    
    @Override
    public void localeChanged(I18N sender, Locale oldLocale, Locale newLocale) {
        updateCaptions();
    }    
    
    /**
     * 
     * @return
     */
    public I18N getI18N() {
        return i18n;
    }
    
}
