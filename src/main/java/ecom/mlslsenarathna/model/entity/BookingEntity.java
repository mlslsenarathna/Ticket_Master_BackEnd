package ecom.mlslsenarathna.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking")
public class BookingEntity{
    @Id
    private String bookingId;
    private String userId;
    private String eventId;
    private String seatId;
    private double amount_paid;
    private String status;
}