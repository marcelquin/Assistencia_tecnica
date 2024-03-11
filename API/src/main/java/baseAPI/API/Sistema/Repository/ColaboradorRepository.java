package baseAPI.API.Sistema.Repository;

import baseAPI.API.Sistema.Model.Colaborador;
import baseAPI.API.Sistema.Model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador,Long> {

    Boolean existsBycpf(Long cpf);

    Colaborador findBycpf(Long cpf);

}
