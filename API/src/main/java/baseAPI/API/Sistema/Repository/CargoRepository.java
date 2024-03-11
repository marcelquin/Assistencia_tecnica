package baseAPI.API.Sistema.Repository;

import baseAPI.API.Sistema.Model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo,Long> {
}
