package baseAPI.API.Sistema.Model;

import baseAPI.API.Sistema.DTO.ColaboradorDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sobrenome;

    @Column(unique = true)
    private Long cpf;

    @Column(unique = true)
    private Long telefone;

    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @OneToOne
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    @OneToOne
    @JoinColumn(name = "documentos_id", referencedColumnName = "id")
    private Documentos documentos;

    @ManyToOne
    @JoinColumn(name = "colaborador_cargo_Id")
    private Cargo cargo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataEntrada;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDesligamento;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private String motivoDesligamento;

    public Colaborador(ColaboradorDTO dto) {
        this.nome = dto.nome();
        this.sobrenome = dto.sobrenome();
        this.cpf = dto.cep();
        this.telefone = dto.telefone();
        this.email = dto.email();
        this.dataNascimento = dto.dataNascimento();
        this.dataEntrada = dto.dataEntrada();
    }
}
