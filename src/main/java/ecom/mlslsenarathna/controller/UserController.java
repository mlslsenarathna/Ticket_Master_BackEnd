package ecom.mlslsenarathna.controller;

import ecom.mlslsenarathna.model.dto.UserDTO;
import ecom.mlslsenarathna.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    final UserService userService;

    @PostMapping("/newRegistration")
    public void registration(@RequestBody UserDTO userDTO){
        userService.registrationNewUser(userDTO);
    }
    @PostMapping("/updateUserInfo")
    public void updateUserInformation(@RequestBody UserDTO userDTO){
        userService.registrationNewUser(userDTO);
    }
    @GetMapping("/getNextUserId")
    public String getNextUserId(){
        return userService.getNewUserId();

    }

}
