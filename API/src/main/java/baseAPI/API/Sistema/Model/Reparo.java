package baseAPI.API.Sistema.Model;

import baseAPI.API.Sistema.DTO.ReparoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Reparo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descrisao;

    @OneToMany
    private List<Pedido> pedidos;

    private Double valortotalReparo;

    public Double CalvalortotalReparo()
    {
        this.valortotalReparo = pedidos.stream().mapToDouble(valor -> valor.getValorPedido()).sum();
        return valortotalReparo;
    }

    public Reparo(ReparoDTO dto) {
        this.descrisao = dto.descrisao();
    }
}
