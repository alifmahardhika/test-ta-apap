package apap.tugas.situ.repository;


import apap.tugas.situ.model.RoleModel;
import apap.tugas.situ.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDb extends JpaRepository<RoleModel, Long> {
    UserModel findByRole(String role);
}