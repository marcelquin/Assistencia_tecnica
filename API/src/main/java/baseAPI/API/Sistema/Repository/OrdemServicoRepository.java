package baseAPI.API.Sistema.Repository;

import baseAPI.API.Sistema.Model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico,Long> {

    Boolean existsBycodigo(String codigo);

    OrdemServico findBycodigo(String codigo);
}
