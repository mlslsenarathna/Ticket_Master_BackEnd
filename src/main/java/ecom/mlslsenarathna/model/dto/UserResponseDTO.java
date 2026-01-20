package ecom.mlslsenarathna.model.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponseDTO {
    private String userName;
    private String eventId;
    private String seatId;
    private LocalDate eventDate;
    private double price;
    private String status;

}
