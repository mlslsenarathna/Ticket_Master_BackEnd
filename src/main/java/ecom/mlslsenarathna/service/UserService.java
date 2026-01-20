package ecom.mlslsenarathna.service;

import ecom.mlslsenarathna.model.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    void registrationNewUser(UserDTO userDTO);

    String getNewUserId();

    UserDTO getUserById(String userId);
}
