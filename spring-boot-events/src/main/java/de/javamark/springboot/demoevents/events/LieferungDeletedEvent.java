package de.javamark.springboot.demoevents.events;

import de.javamark.springboot.demoevents.domain.Lieferung;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class LieferungDeletedEvent {
    private final Lieferung lieferung;
}
