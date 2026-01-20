package ecom.mlslsenarathna.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="event")
public class EventEntity {
    @Id
    private String eventId;
    private String name;
    private double price;
    private boolean isHighDemand;
    private LocalDate eventDate;
}
