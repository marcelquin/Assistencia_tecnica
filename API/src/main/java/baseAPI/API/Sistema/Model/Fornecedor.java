package baseAPI.API.Sistema.Model;

import baseAPI.API.Sistema.DTO.FornecedorDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String razaoSocial ;

    private Long cnpj ;

    private Long telefone  ;

    private String email ;

    @OneToOne
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    public Fornecedor(FornecedorDTO dto) {
        this.nome = dto.nome();
        this.razaoSocial = dto.razaoSocial();
        this.cnpj = dto.cnpj();
        this.telefone = dto.telefone();
        this.email = dto.email();
    }

}
