package de.javamark.springboot.demoevents.events;

import de.javamark.springboot.demoevents.domain.Lieferung;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class LieferungCreatedEvent {
    private final Lieferung lieferung;
}
