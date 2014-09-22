package net.pkhapps.fenix.communication.ui;

import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import net.pkhapps.fenix.core.annotations.PrototypeScope;
import net.pkhapps.fenix.core.components.AbstractView;
import net.pkhapps.fenix.core.components.CustomTwinColSelect;
import net.pkhapps.fenix.core.components.PrimaryButton;
import net.pkhapps.fenix.core.components.SmallLabel;
import net.pkhapps.fenix.theme.FenixTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.VaadinComponent;
import org.vaadin.spring.i18n.I18N;

import java.util.Collection;

/**
 * View for creating and sending new messages. Designed to be used in {@link net.pkhapps.fenix.core.components.ViewWindow}s.
 */
@VaadinComponent
@PrototypeScope
public class NewMessageView extends AbstractView<NewMessagePresenter.ViewDelegate, NewMessagePresenter> implements NewMessagePresenter.ViewDelegate {

    public static final String VIEW_NAME = "communication/newMessage";

    private CommunicationMethodField sendMessageAs;
    private TextArea messageText;
    private SmallLabel charsLeft;
    private PrimaryButton send;
    private Button cancel;
    private CustomTwinColSelect<NewMessagePresenter.Recipient> recipients;

    @Autowired
    protected NewMessageView(NewMessagePresenter presenter, I18N i18n) {
        super(presenter, i18n);
    }

    @Override
    protected void doInit() {
        setSizeFull();
        final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        root.setSpacing(true);
        root.setMargin(true);
        setCompositionRoot(root);

        sendMessageAs = new CommunicationMethodField(getI18N().get(getMessages().key("sendMessageAs.caption")), getI18N());
        sendMessageAs.setMultiSelect(true);
        sendMessageAs.addStyleName(FenixTheme.OPTIONGROUP_HORIZONTAL);
        root.addComponent(sendMessageAs);

        messageText = new TextArea(getI18N().get(getMessages().key("messageText.caption")));
        messageText.setWidth(100, Unit.PERCENTAGE);
        messageText.setHeight(80, Unit.PIXELS);
        root.addComponent(messageText);

        charsLeft = new SmallLabel(getI18N().get(getMessages().key("charsLeft"), 0, 900));
        root.addComponent(charsLeft);
        root.setComponentAlignment(charsLeft, Alignment.TOP_RIGHT);

        recipients = new CustomTwinColSelect<>(CustomTwinColSelect.Direction.HORIZONTAL, NewMessagePresenter.Recipient.class);
        recipients.setCaption(getI18N().get(getMessages().key("recipients.caption")));
        recipients.setSizeFull();
        recipients.setItemIconPropertyId(NewMessagePresenter.Recipient.PROP_ICON);
        recipients.setItemCaptionPropertyId(NewMessagePresenter.Recipient.PROP_NAME);
        recipients.setFilterInputPrompt(getI18N().get(getMessages().key("recipients.search.inputPrompt")));
        root.addComponent(recipients);
        root.setExpandRatio(recipients, 1);

        final HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.setSpacing(true);
        root.addComponent(buttonsLayout);
        root.setComponentAlignment(buttonsLayout, Alignment.BOTTOM_RIGHT);

        send = new PrimaryButton(getI18N().get(getMessages().key("send.caption")));
        buttonsLayout.addComponent(send);

        cancel = new Button(getI18N().get(getMessages().key("cancel.caption")), event -> ((Window) getParent()).close());
        cancel.setClickShortcut(ShortcutAction.KeyCode.ESCAPE);
        buttonsLayout.addComponent(cancel);
    }

    @Override
    public void setAvailableRecipients(Collection<NewMessagePresenter.Recipient> recipients) {
        this.recipients.setItems(recipients);
    }

}
