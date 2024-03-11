package baseAPI.API.Sistema.Repository;

import baseAPI.API.Sistema.Model.Cliente;
import baseAPI.API.Sistema.Model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    Boolean existsBynome(String nome);
    Boolean existsBytelefone(Long telefone);

    Cliente findBynome(String nome);
    Cliente findBytelefone(Long telefone);
}
