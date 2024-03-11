package baseAPI.API.Sistema.Model;

import baseAPI.API.Sistema.DTO.PedidoDTO;
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
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "pedido_fornecedor_Id")
    private Fornecedor fornecedor;

    private String descrisao;

    private String modelo;

    private Double valorPedido;

    public Pedido(PedidoDTO dto) {
        this.nome = dto.nome();
        this.descrisao = dto.descrisao();
        this.modelo = dto.modelo();
        this.valorPedido = dto.valorPedido();
    }
}
