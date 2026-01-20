package ecom.mlslsenarathna.service.impl;

import ecom.mlslsenarathna.model.dto.UserDTO;
import ecom.mlslsenarathna.model.entity.UserEntity;
import ecom.mlslsenarathna.repository.UserRepository;
import ecom.mlslsenarathna.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSeriviceImpl implements UserService {
    final UserRepository userRepository;
    ModelMapper mapper=new ModelMapper();
    @Override
    public void registrationNewUser(UserDTO userDTO) {
        userRepository.save(mapper.map(userDTO,UserEntity.class));
    }

    @Override
    public String getNewUserId() {
        UserEntity userEntity=userRepository.findTopByOrderByUserIdDesc();
        return nextID(mapper.map(userEntity,UserDTO.class));
    }

    private String nextID(UserDTO map) {
        String lastId=map.getUserId();
        if(map!=null){
            lastId = lastId.split("[A-Z]")[1];
            lastId= String.format("S%04d",(Integer.parseInt(lastId)+1));
            return lastId;

        }
        return "S0001";
    }
}
