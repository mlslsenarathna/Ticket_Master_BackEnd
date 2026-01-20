package ecom.mlslsenarathna.service.impl;

import ecom.mlslsenarathna.annotation.AuditFailure;
import ecom.mlslsenarathna.model.dto.SeatDTO;
import ecom.mlslsenarathna.model.entity.SeatEntity;
import ecom.mlslsenarathna.repository.BookingRepository;
import ecom.mlslsenarathna.repository.SeatRepository;
import ecom.mlslsenarathna.service.SeatService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class SeatServiceImpl implements SeatService {
    final SeatRepository seatRepository;
    final BookingRepository bookingRepository;
    ModelMapper mapper=new ModelMapper();
    public List<SeatDTO> getSeatList(){
        List<SeatEntity> seatList=seatRepository.findAll();
        List<SeatDTO> list = null;
        for(SeatEntity seatEntity:seatList){
            list.add(mapper.map(seatEntity,SeatDTO.class));
        }
        return list;
    }
    @Override
    public  List<SeatDTO> getAvailableSeatList(){
        List<SeatDTO> seatDTOList=getSeatList();
        List<SeatDTO> availableList=null;
        for(SeatDTO seatDTO:seatDTOList){
            if(seatDTO.getStatus().equalsIgnoreCase("Available")){
                availableList.add(seatDTO);
            }

        }
        return availableList;
    }
    @Override
    public List<SeatDTO> getSoldSeatList() {
        List<SeatDTO> seatDTOList=getSeatList();
        List<SeatDTO> soldList=null;
        for(SeatDTO seatDTO:seatDTOList){
            if(seatDTO.getStatus().equalsIgnoreCase("SOLD")){
                soldList.add(seatDTO);
            }

        }
        return soldList;
    }
    @Override
    public int getEventSeatSizeEventID(String eventId) {
        List<SeatDTO>  soldlist=getSoldSeatList();
        int count=0;
        for(SeatDTO seatDTO:soldlist){
            if(seatDTO.getEventID().equalsIgnoreCase(eventId)){
              count++;
            }
        }
        return count;
    }

    @Override
    public List<SeatDTO> getSoldSeatListByEventID(String eventId) {
        List<SeatDTO>  soldlist=getSoldSeatList();
        List<SeatDTO> soldEventList=null;
        for(SeatDTO seatDTO:soldlist){
            if(seatDTO.getEventID().equalsIgnoreCase(eventId)){
                soldEventList.add(seatDTO);
            }
        }
        return soldEventList;
    }

    @Override
    public SeatDTO getSeatByID(String seatId) {
        Optional<SeatEntity> seatEntity=seatRepository.findById(seatId);
        SeatEntity entity=seatEntity.orElseThrow();
        return mapper.map(entity,SeatDTO.class);
    }

    @Override
    public void updateSeatInfo(SeatDTO seatDTO) {
        seatRepository.save(mapper.map(seatDTO,SeatEntity.class));
    }

    @Override
    @AuditFailure
    @Transactional
    public SeatDTO holdSeat(String seatId, String userId) throws SeatLockedException {
        SeatEntity seat = seatRepository.findByIdWithLock(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));
        long now = System.currentTimeMillis();
        boolean isAvailable = "AVAILABLE".equalsIgnoreCase(seat.getStatus());
        boolean isExpired = (seat.getExpiry() != null && now > seat.getExpiry());

        if (isAvailable || isExpired) {
            seat.setStatus("HELD");
            seat.setUserId(userId);
            seat.setExpiry(now+ (10 * 60 * 1000));
            SeatEntity savedSeat = seatRepository.save(seat);
            return mapper.map(savedSeat, SeatDTO.class);
        } else {

            long remainingSeconds = Math.max(0, (seat.getExpiry() - now) / 1000);
            throw new SeatLockedException(remainingSeconds);
        }
    }

        @Scheduled(fixedRate = 60000)
        @Transactional
        public void releaseExpiredHolds() {
            long now = System.currentTimeMillis();

            int updatedCount = seatRepository.releaseExpiredSeats(now);
            int cancelledBookings = bookingRepository.cancelExpiredPendingBookings(now);

            if (updatedCount > 0) {
                log.info("Cleanup Service: Released {} expired seat holds.", updatedCount);
            }
        }

}
