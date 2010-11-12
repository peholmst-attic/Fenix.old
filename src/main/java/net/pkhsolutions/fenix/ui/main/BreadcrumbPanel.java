/*
 * Copyright (c) 2010 The original author(s)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.pkhsolutions.fenix.ui.main;

import com.vaadin.ui.AbstractComponent;
import net.pkhsolutions.fenix.ui.FenixTheme;
import net.pkhsolutions.fenix.ui.mvp.View;
import net.pkhsolutions.fenix.ui.mvp.ViewController;
import net.pkhsolutions.fenix.ui.mvp.ViewControllerListener;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import java.util.Iterator;

/**
 * TODO Document me!
 * 
 * @author petter
 *
 */
public class BreadcrumbPanel extends CssLayout implements ViewControllerListener {

	private static final long serialVersionUID = -792768199294588050L;
	
	private ViewController viewController;
		
	public BreadcrumbPanel() {
		//setSpacing(true);
		setMargin(false, true, false, true);
		addStyleName(FenixTheme.BREADCRUMB_PANEL);
	}
	
	public ViewController getViewController() {
		return viewController;
	}
	
	public void setViewController(ViewController viewController) {
		if (this.viewController != null) {
			this.viewController.removeListener(this);
		}
		this.viewController = viewController;
		if (this.viewController != null) {
			this.viewController.addListener(this);
		}
	}
	
	@Override
	public void currentViewChanged(final ViewController source, final View oldView,
			final View newView, final Direction direction) {
		if (direction.equals(Direction.FORWARD)) {
			// TODO i18n changes!
			Button btn = new Button(newView.getDisplayName());
			btn.setDescription(newView.getDescription());
			btn.setStyleName(FenixTheme.BUTTON_LINK);
			btn.setSizeUndefined();
			btn.addListener(new Button.ClickListener() {
				private static final long serialVersionUID = 1L;
				
				@Override
				public void buttonClick(ClickEvent event) {
					source.goToView(newView);
				}
			});
			btn.setData(newView);
			if (source.getTrail().size() > 1) {
				final Label lbl = new Label("»");
				lbl.setSizeUndefined();
				lbl.setData(newView);
				addComponent(lbl);
			}
			addComponent(btn);
		} else if (direction.equals(Direction.BACKWARD)) {
			Iterator<Component> it = components.descendingIterator();
			while (it.hasNext()) {
				AbstractComponent c = (AbstractComponent) it.next();
				if (c.getData() == newView) {
					break;
				} else {
					it.remove();
				}
			}
			requestRepaint();
		}
	}

}
