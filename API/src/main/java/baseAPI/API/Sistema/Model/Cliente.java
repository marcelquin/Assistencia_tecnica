package baseAPI.API.Sistema.Model;

import baseAPI.API.Sistema.DTO.ClienteDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode(of = "id")
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sobrenome;

    @Column(unique = true)
    private Long telefone;

    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @OneToOne
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    private Boolean bloqueado;

    public Cliente(ClienteDTO dto) {
        this.nome = dto.nome();
        this.sobrenome = dto.sobrenome();
        this.telefone = dto.telefone();
        this.email = dto.email();
        this.dataNascimento = dto.dataNascimento();
    }
}
