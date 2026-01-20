package ecom.mlslsenarathna.service;

import ecom.mlslsenarathna.model.dto.BookingDTO;
import ecom.mlslsenarathna.model.dto.UserResponseDTO;

public interface BookingService {
    UserResponseDTO registerNewBooking(BookingDTO bookingDTO);

    UserResponseDTO completeBooking(String id, String status);

}
