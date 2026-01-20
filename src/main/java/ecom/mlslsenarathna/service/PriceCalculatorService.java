package ecom.mlslsenarathna.service;

import ecom.mlslsenarathna.model.dto.EventDTO;
import ecom.mlslsenarathna.model.dto.UserDTO;

public interface PriceCalculatorService {
    double calculatePrice(UserDTO userDTO, EventDTO eventDTO);
}
