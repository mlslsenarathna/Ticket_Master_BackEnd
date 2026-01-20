package ecom.mlslsenarathna.service.impl;

import ecom.mlslsenarathna.model.dto.*;
import ecom.mlslsenarathna.model.entity.BookingEntity;
import ecom.mlslsenarathna.model.entity.SeatEntity;
import ecom.mlslsenarathna.repository.BookingRepository;
import ecom.mlslsenarathna.service.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class BookingServiceImpl implements BookingService {
    final SeatService seatService;
    final BookingRepository bookingRepository;
    final UserService userService;
    final EventService eventService;
    final PriceCalculatorService priceCalculatorService;
    ModelMapper mapper=new ModelMapper();
    @Override
    public UserResponseDTO registerNewBooking(BookingDTO bookingDTO) {
        bookingRepository.save(mapper.map(bookingDTO,BookingEntity.class));
        UserDTO userDTO=userService.getUserById(bookingDTO.getUserId());
        EventDTO eventDTO=eventService.getEventByID(bookingDTO.getEventId());
        double price=priceCalculatorService.calculatePrice(userDTO,eventDTO);
        SeatDTO seatDTO=seatService.getSeatByID(bookingDTO.getSeatId());

        return new UserResponseDTO(
                userDTO.getUserName(),
                bookingDTO.getEventId(),
                bookingDTO.getSeatId(),
                eventDTO.getEventDate(),
                price,
                bookingDTO.getStatus()
                );
    }


    @Override
    public UserResponseDTO completeBooking(String id, String status) {
        SeatDTO seatDTO= seatService.getSeatByID(id);
        if(seatDTO.getStatus().equalsIgnoreCase("AVAILABLE")){
            seatDTO.setStatus("BOOKED");
        }
        seatService.updateSeatInfo(seatDTO);
        return null;
    }



//    @Override
//    public BookingDTO updateStatus(String status,String bookingId){
//       Optional<BookingEntity> bookingEntity=bookingRepository.findById(bookingId);
//       BookingEntity entity=bookingEntity.orElseThrow();
//       entity.setStatus(status);
//       bookingRepository.save(entity);
//       return mapper.map(entity,BookingDTO.class);
//    }
}
