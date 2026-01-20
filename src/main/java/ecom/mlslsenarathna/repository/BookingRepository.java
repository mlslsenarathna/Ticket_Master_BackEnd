package ecom.mlslsenarathna.repository;

import ecom.mlslsenarathna.model.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingEntity,String> {
}
