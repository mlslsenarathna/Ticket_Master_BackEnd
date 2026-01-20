package ecom.mlslsenarathna.service.impl;

import ecom.mlslsenarathna.model.dto.*;
import ecom.mlslsenarathna.model.entity.BookingEntity;
import ecom.mlslsenarathna.repository.BookingRepository;
import ecom.mlslsenarathna.service.*;
import jakarta.transaction.Transactional;
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
    @Transactional
    public UserResponseDTO registerNewBooking(BookingDTO bookingDTO) {


        UserDTO userDTO=userService.getUserById(bookingDTO.getUserId());
        EventDTO eventDTO=eventService.getEventByID(bookingDTO.getEventId());
        double price=priceCalculatorService.calculatePrice(userDTO,eventDTO);
        SeatDTO seatDTO=seatService.getSeatByID(bookingDTO.getSeatId());
        if(seatDTO.getStatus().equalsIgnoreCase("AVAILABLE")){
            try {
                seatService.holdSeat(seatDTO.getSeatId(),userDTO.getUserId());
                bookingRepository.save(mapper.map(bookingDTO,BookingEntity.class));
                return new UserResponseDTO(
                        userDTO.getUserName(),
                        bookingDTO.getEventId(),
                        seatDTO.getSeatId(),
                        eventDTO.getEventDate(),
                        price,
                        seatDTO.getStatus()
                );
            } catch (SeatLockedException e) {
                throw new RuntimeException(e);
            }
        }

        return null;


    }


    @Override
    @Transactional
    public UserResponseDTO completeBooking(String id, String status) {
        Optional<BookingEntity> bookingEntity=bookingRepository.findById(id);
        BookingEntity entity=bookingEntity.orElseThrow();
        SeatDTO seatDTO= seatService.getSeatByID(entity.getSeatId());
        UserDTO userDTO=userService.getUserById(entity.getUserId());
        EventDTO eventDTO=eventService.getEventByID(entity.getEventId());
        double price=priceCalculatorService.calculatePrice(userDTO,eventDTO);
        if(seatDTO.getStatus().equalsIgnoreCase("HELD")){
            seatDTO.setStatus("SOLD");
            seatDTO.setExpiry(null);
            entity.setStatus("CONFIRMED");
            bookingRepository.save(entity);
            seatService.updateSeatInfo(seatDTO);
            return new UserResponseDTO(
                    userDTO.getUserName(),
                    entity.getEventId(),
                    seatDTO.getSeatId(),
                    eventDTO.getEventDate(),
                    price,
                    entity.getStatus()
            );
        }
        return null;

    }


    }