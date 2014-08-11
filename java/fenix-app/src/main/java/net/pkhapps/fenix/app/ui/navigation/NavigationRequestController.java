package net.pkhapps.fenix.app.ui.navigation;

import com.vaadin.ui.UI;
import net.pkhapps.fenix.core.navigation.NavigationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.UIScope;
import org.vaadin.spring.VaadinComponent;
import org.vaadin.spring.events.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by peholmst on 2014-06-19.
 */
@VaadinComponent
@UIScope
class NavigationRequestController implements EventBusListener<NavigationRequest> {

    private final EventBus eventBus;
    private final UI ui;

    @Autowired
    NavigationRequestController(@EventBusScope(EventScope.UI) EventBus eventBus, UI ui) {
        this.eventBus = eventBus;
        this.ui = ui;
    }

    @PostConstruct
    void init() {
        eventBus.subscribe(this);
    }

    @PreDestroy
    void destroy() {
        eventBus.unsubscribe(this);
    }

    @Override
    public void onEvent(Event<NavigationRequest> event) {
        ui.getNavigator().navigateTo(event.getPayload().getNavigationState());
    }
}
