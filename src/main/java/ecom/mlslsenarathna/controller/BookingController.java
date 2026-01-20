package ecom.mlslsenarathna.controller;

import ecom.mlslsenarathna.model.dto.BookingDTO;
import ecom.mlslsenarathna.model.dto.UserResponseDTO;
import ecom.mlslsenarathna.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {
    final BookingService bookingService;
    @PostMapping("/newBooking")
    public UserResponseDTO NewBooking(@RequestBody BookingDTO bookingDTO){
        return bookingService.registerNewBooking(bookingDTO);
    }
    @PutMapping("/completeBooking/{id}")
    public UserResponseDTO completeBooking(@PathVariable String id){
       return  bookingService.completeBooking(id,"CONFIRMED");
    }


}
