package ecom.mlslsenarathna.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "seat")
public class SeatEntity{
    @Id
    private  String seatId;
    private String eventID;
    private String seatNumber;
    private String status;
    private String userId;
    private Long expiry;


}