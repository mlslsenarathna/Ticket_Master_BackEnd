package ecom.mlslsenarathna.service.impl;

import ecom.mlslsenarathna.model.dto.EventDTO;
import ecom.mlslsenarathna.model.dto.UserDTO;
import ecom.mlslsenarathna.service.PriceCalculatorService;
import org.springframework.stereotype.Service;

@Service
public class PriceCalculatorServiceImpl implements PriceCalculatorService {
    @Override
    public double calculatePrice(UserDTO userDTO, EventDTO eventDTO) {
        if(userDTO.getTier().equalsIgnoreCase("REGULAR")){
            return eventDTO.getPrice();
        }else if(userDTO.getTier().equalsIgnoreCase("VIP")&& eventDTO.isHighDemand()==true){
            return eventDTO.getPrice()*0.9;
        }else if(userDTO.getTier().equalsIgnoreCase("PLATINUM")){
            return eventDTO.getPrice();
        }
        return -1;
    }
}
