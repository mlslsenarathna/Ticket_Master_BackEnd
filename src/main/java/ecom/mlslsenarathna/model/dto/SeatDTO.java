package ecom.mlslsenarathna.model.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SeatDTO {
    private  String seatId;
    private String eventID;
    private String seatNumber;
    private String status;
    private String userId;
    private Long expiry;

}
