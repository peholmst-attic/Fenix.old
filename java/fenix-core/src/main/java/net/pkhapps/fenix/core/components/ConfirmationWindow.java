package net.pkhapps.fenix.core.components;

import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import net.pkhapps.fenix.theme.FenixTheme;
import org.vaadin.spring.i18n.I18N;

import java.util.Optional;

/**
 * Created by petterwork on 09/10/14.
 */
public class ConfirmationWindow extends AbstractWindow<Boolean> {

    private Button confirm;
    private Button cancel;
    private Label question;

    public ConfirmationWindow(I18N i18n) {
        super(i18n);
        setClosable(false);
        setModal(true);
        setResizable(false);
        center();

        final VerticalLayout content = new VerticalLayout();
        content.setMargin(true);
        content.setSpacing(true);
        setContent(content);
        setWidth("400px");

        question = new Label();
        content.addComponent(question);

        final HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSpacing(true);

        confirm = new Button(getI18N().get(getMessages().key("confirm")));
        confirm.addClickListener(event -> closeWindow(true));
        buttons.addComponent(confirm);

        cancel = new Button(getI18N().get(getMessages().key("cancel")));
        cancel.setClickShortcut(ShortcutAction.KeyCode.ESCAPE);
        cancel.addClickListener(event -> closeWindow(false));
        cancel.addStyleName(FenixTheme.BUTTON_FRIENDLY);
        buttons.addComponent(cancel);

        content.addComponent(buttons);
        content.setComponentAlignment(buttons, Alignment.BOTTOM_RIGHT);

        cancel.focus();
    }

    /**
     * @param caption
     */
    public void setConfirmButtonCaption(String caption) {
        confirm.setCaption(caption);
    }

    /**
     * @param caption
     */
    public void setCancelButtonCaption(String caption) {
        cancel.setCaption(caption);
    }

    /**
     * @param message
     */
    public void setMessage(String message) {
        question.setValue(message);
    }

    @Override
    public void openWindow(Optional<Callback<Boolean>> callback) {
        super.openWindow(callback);
    }

    @Override
    public void openWindow(UI ui, Optional<Callback<Boolean>> callback) {
        super.openWindow(ui, callback);
    }
}
