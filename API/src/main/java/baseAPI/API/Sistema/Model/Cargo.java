package baseAPI.API.Sistema.Model;

import baseAPI.API.Sistema.DTO.ColaboradorDTO;
import baseAPI.API.Sistema.Enum.Departamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Departamento departamento;

    private String nome;

    private String descrisao;

    private Double salario;

    public Cargo(ColaboradorDTO dto) {
        this.departamento = dto.departamento();
        this.nome = dto.cargo();
        this.descrisao = dto.descrisao();
        this.salario = dto.salario();
    }
}
