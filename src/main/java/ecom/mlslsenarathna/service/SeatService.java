package ecom.mlslsenarathna.service;

import ecom.mlslsenarathna.model.dto.SeatDTO;
import ecom.mlslsenarathna.service.impl.SeatLockedException;

import java.util.List;

public interface SeatService {
    List<SeatDTO> getAvailableSeatList();

    SeatDTO getSeatByID(String seatId);

    void updateSeatInfo(SeatDTO seatDTO);
    SeatDTO holdSeat(String seatId,String userId) throws SeatLockedException;
}
