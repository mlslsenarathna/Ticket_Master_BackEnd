package ecom.mlslsenarathna.controller;

import ecom.mlslsenarathna.model.dto.EventDTO;
import ecom.mlslsenarathna.model.dto.UserDTO;
import ecom.mlslsenarathna.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController{
    final EventService eventService;

    @PostMapping("/newEventRegistration")
    public void registration(@RequestBody EventDTO eventDTO){
       eventService.newEventRegistration(eventDTO);
    }
    @PostMapping("/updateEventInfo")
    public void updateEventInformation(@RequestBody  EventDTO eventDTO){
        eventService.newEventRegistration(eventDTO);
    }
    @GetMapping("/getNextEventId")
    public String getNextEventId(){
        return eventService.getNextEventID();

    }

}
