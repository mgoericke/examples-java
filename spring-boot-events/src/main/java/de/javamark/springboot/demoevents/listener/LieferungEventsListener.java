package de.javamark.springboot.demoevents.listener;

import de.javamark.springboot.demoevents.domain.Lieferung;
import de.javamark.springboot.demoevents.events.LieferungCreatedEvent;
import de.javamark.springboot.demoevents.events.LieferungDeletedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
public class LieferungEventsListener {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleLieferungCreatedEvent(final LieferungCreatedEvent event) {
        final Lieferung lieferung = event.getLieferung();
        log.info("[+] handleLieferungCreatedEvent: {} ({})", lieferung.getName(),lieferung.getId());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleLieferungDeletedEvent(final LieferungDeletedEvent event) {
        final Lieferung lieferung = event.getLieferung();
        log.info("[+] handleLieferungDeletedEvent: {} ({})", lieferung.getName(),lieferung.getId());
    }
}
