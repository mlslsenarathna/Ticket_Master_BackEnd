package ecom.mlslsenarathna.repository;

import ecom.mlslsenarathna.model.entity.BookingEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookingRepository extends JpaRepository<BookingEntity,String> {
    @Modifying
    @Transactional
    @Query("UPDATE BookingEntity b SET b.status = 'CANCELLED' " +
            "WHERE b.status = 'PENDING' AND b.seatId IN " +
            "(SELECT s.seatId FROM SeatEntity s WHERE s.expiry < :currentTime)")
    int cancelExpiredPendingBookings(@Param("currentTime") long currentTime);
}
