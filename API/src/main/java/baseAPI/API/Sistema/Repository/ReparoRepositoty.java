package baseAPI.API.Sistema.Repository;

import baseAPI.API.Sistema.Model.Reparo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReparoRepositoty extends JpaRepository<Reparo,Long> {
}
