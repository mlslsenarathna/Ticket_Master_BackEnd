package ecom.mlslsenarathna.service.impl;


import ecom.mlslsenarathna.model.dto.EventDTO;
import ecom.mlslsenarathna.model.entity.EventEntity;
import ecom.mlslsenarathna.repository.EventRepository;
import ecom.mlslsenarathna.service.EventService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    final EventRepository eventRepository;
    ModelMapper mapper=new ModelMapper();
    @Override
    public void newEventRegistration(EventDTO eventDTO) {
        eventRepository.save(mapper.map(eventDTO, EventEntity.class));

    }

    @Override
    public String getNextEventID() {
        EventEntity eventEntity=eventRepository.findTopByOrderByEventIdDesc();
        return  nextID(mapper.map(eventEntity,EventDTO.class));
    }

    private String nextID(EventDTO map) {
        String lastId=map.getEventId();
        if(map!=null){
            lastId = lastId.split("[A-Z]")[1];
            lastId= String.format("E%04d",(Integer.parseInt(lastId)+1));
            return lastId;

        }
        return "E0001";
    }
}
