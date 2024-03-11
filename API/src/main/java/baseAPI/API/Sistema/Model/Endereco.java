package baseAPI.API.Sistema.Model;

import baseAPI.API.Sistema.DTO.ClienteDTO;
import baseAPI.API.Sistema.DTO.ColaboradorDTO;
import baseAPI.API.Sistema.DTO.EnderecoDTO;
import baseAPI.API.Sistema.DTO.FornecedorDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Logradouro ;

    private String numero ;

    private String bairro  ;

    private Long cep ;

    private String cidade ;

    private String estado ;

    public Endereco(ClienteDTO dto) {
        Logradouro = dto.Logradouro();
        this.numero = dto.numero();
        this.bairro = dto.bairro();
        this.cep = dto.cep();
        this.cidade = dto.cidade();
        this.estado = dto.estado();
    }

    public Endereco(FornecedorDTO dto) {
        Logradouro = dto.Logradouro();
        this.numero = dto.numero();
        this.bairro = dto.bairro();
        this.cep = dto.cep();
        this.cidade = dto.cidade();
        this.estado = dto.estado();
    }

    public Endereco(ColaboradorDTO dto) {
        Logradouro = dto.Logradouro();
        this.numero = dto.numero();
        this.bairro = dto.bairro();
        this.cep = dto.cep();
        this.cidade = dto.cidade();
        this.estado = dto.estado();
    }


}
