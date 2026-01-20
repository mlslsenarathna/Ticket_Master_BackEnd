package ecom.mlslsenarathna.model.dto;


import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private String bookingId;
    private String userId;
    private String eventId;
    private String seatId;
    private double amount_paid;
    private String status;
}
