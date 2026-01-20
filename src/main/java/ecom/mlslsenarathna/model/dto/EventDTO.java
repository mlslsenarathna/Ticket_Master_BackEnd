package ecom.mlslsenarathna.model.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EventDTO {
    private String eventId;
    private String name;
    private double price;
    private boolean isHighDemand;
    private LocalDate eventDate;
}
