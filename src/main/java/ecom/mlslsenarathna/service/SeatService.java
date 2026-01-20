package ecom.mlslsenarathna.service;

import ecom.mlslsenarathna.model.dto.SeatDTO;

import java.util.List;

public interface SeatService {
    List<SeatDTO> getAvailableSeatList();

    SeatDTO getSeatByID(String seatId);

    void updateSeatInfo(SeatDTO seatDTO);
}
