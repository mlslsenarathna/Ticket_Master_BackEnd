package ecom.mlslsenarathna.service.impl;

import ecom.mlslsenarathna.model.dto.SeatDTO;
import ecom.mlslsenarathna.model.entity.SeatEntity;
import ecom.mlslsenarathna.repository.SeatRepository;
import ecom.mlslsenarathna.service.SeatService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {
    final SeatRepository seatRepository;
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
    public SeatDTO getSeatByID(String seatId) {
        Optional<SeatEntity> seatEntity=seatRepository.findById(seatId);
        SeatEntity entity=seatEntity.orElseThrow();
        return mapper.map(entity,SeatDTO.class);
    }

    @Override
    public void updateSeatInfo(SeatDTO seatDTO) {
        seatRepository.save(mapper.map(seatDTO,SeatEntity.class));
    }
}
