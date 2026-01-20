package ecom.mlslsenarathna.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {
    private String userId;
    private String userName;
    private String userEmail;
    private String tier;

}
