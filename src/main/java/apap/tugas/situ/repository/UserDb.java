package apap.tugas.situ.repository;


import apap.tugas.situ.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserDb extends JpaRepository<UserModel, Long> {
    UserModel findByUsername(String username);
    Optional<UserModel> findById(Long uuid);
}
