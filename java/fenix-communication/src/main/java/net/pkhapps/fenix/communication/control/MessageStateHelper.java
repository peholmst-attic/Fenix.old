package net.pkhapps.fenix.communication.control;

import net.pkhapps.fenix.communication.entity.ArchivedMessage;
import net.pkhapps.fenix.communication.entity.CommunicationMethod;
import net.pkhapps.fenix.communication.entity.MessageCommunicationMethodStateRepository;
import net.pkhapps.fenix.communication.entity.MessageState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Helper class for updating the {@link net.pkhapps.fenix.communication.entity.MessageState} of a {@link net.pkhapps.fenix.communication.entity.ArchivedMessage}.
 */
@Service
public class MessageStateHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageStateHelper.class);

    private final MessageCommunicationMethodStateRepository messageCommunicationMethodStateRepository;

    @Autowired
    public MessageStateHelper(MessageCommunicationMethodStateRepository messageCommunicationMethodStateRepository) {
        this.messageCommunicationMethodStateRepository = messageCommunicationMethodStateRepository;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void updateState(ArchivedMessage message, CommunicationMethod communicationMethod, MessageState newState) {
/*        LOGGER.debug("Updating state of message {} to {} for {}", message, newState, communicationMethod);
        final MessageCommunicationMethodState stateEntity = new MessageCommunicationMethodState.Builder()
                .setMessage(message)
                .setCommunicationMethod(CommunicationMethod.SMS)
                .setState(newState)
                .build();
        messageCommunicationMethodStateRepository.saveAndFlush(stateEntity);*/
    }
}
