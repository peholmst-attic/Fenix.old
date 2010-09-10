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

import net.pkhsolutions.fenix.ui.mvp.View;
import net.pkhsolutions.fenix.ui.mvp.ViewController;
import net.pkhsolutions.fenix.ui.mvp.ViewControllerListener;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 * TODO Document me!
 * 
 * @author petter
 *
 */
public class BreadcrumbPanel extends HorizontalLayout implements ViewControllerListener {

	private static final long serialVersionUID = -792768199294588050L;
	
	private ViewController viewController;
	
	public BreadcrumbPanel() {
		setSpacing(true);
		setMargin(true);
		Label lbl = new Label("You are currently here:");
		addComponent(lbl);
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
	public void currentViewChanged(ViewController source, View oldView,
			View newView, Direction direction) {
		// TODO Auto-generated method stub
		
	}

}
