package de.javamark.springboot.demoevents.service;

import de.javamark.springboot.demoevents.domain.Lieferung;
import de.javamark.springboot.demoevents.domain.LieferungRepository;
import de.javamark.springboot.demoevents.events.LieferungCreatedEvent;
import de.javamark.springboot.demoevents.events.LieferungDeletedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class LieferungService {

    private final LieferungRepository lieferungRepository;
    private final ApplicationEventPublisher eventPublisher;

    public Lieferung createLieferung(final Lieferung lieferung) {
        log.info("[+] createLieferung: {}", lieferung.getName());
        Lieferung created = this.lieferungRepository.save(lieferung);
        eventPublisher.publishEvent(new LieferungCreatedEvent(created));
        return created;
    }
    public void deleteLieferung(final Lieferung toDelete) {
        log.info("[+] deleteLieferung: {}", toDelete.getName());
        this.lieferungRepository.delete(toDelete);
        eventPublisher.publishEvent(new LieferungDeletedEvent(toDelete));
    }
}
