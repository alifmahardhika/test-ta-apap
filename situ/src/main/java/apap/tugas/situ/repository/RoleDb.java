package apap.tugas.situ.repository;


import apap.tugas.situ.model.RoleModel;
import apap.tugas.situ.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface RoleDb extends JpaRepository<RoleModel, Long> {
    UserModel findByNama(String nama);

    Optional<RoleModel> findById(Long id);
}