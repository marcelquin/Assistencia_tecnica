package baseAPI.API.Sistema.Repository;

import baseAPI.API.Sistema.Model.Fornecedor;
import baseAPI.API.Sistema.Model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor,Long> {

    Boolean existsByrazaoSocial(String razaoSocial);
    Boolean existsBycnpj(Long cnpj);

    Fornecedor findByrazaoSocial(String razaoSocial);
    Fornecedor findBycnpj(Long cnpj);
}
