package ecom.mlslsenarathna.repository;

import ecom.mlslsenarathna.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {
    UserEntity findTopByOrderByUserIdDesc();
}
