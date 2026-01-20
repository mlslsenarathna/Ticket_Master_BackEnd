package ecom.mlslsenarathna.service;

import ecom.mlslsenarathna.model.dto.EventDTO;

public interface EventService {
    void newEventRegistration(EventDTO eventDTO);

    String getNextEventID();

    EventDTO getEventByID(String eventId);
}
