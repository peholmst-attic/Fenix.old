package net.pkhapps.fenix.communication.boundary;

import net.pkhapps.fenix.communication.entity.CommunicationMethod;
import net.pkhapps.fenix.communication.entity.MessageReceipt;
import net.pkhapps.fenix.communication.entity.Recipient;

import java.util.Collection;
import java.util.concurrent.Future;

/**
 * TODO Document me!
 */
@Deprecated
public interface MessageSenderService {

    /**
     * Sends the specified message asynchronously.
     *
     * @param message    the message to send.
     * @param recipients the recipients of the message.
     * @param sendAs     the communication methods to use when sending the message.
     * @return a {@link java.util.concurrent.Future} containing the message receipt.
     */
    Future<MessageReceipt> sendMessage(String message, Collection<Recipient> recipients, Collection<CommunicationMethod> sendAs);

}
