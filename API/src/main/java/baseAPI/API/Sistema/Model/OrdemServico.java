package baseAPI.API.Sistema.Model;

import baseAPI.API.Sistema.DTO.OrdemServicoDTO;
import baseAPI.API.Sistema.Enum.Aparelho;
import baseAPI.API.Sistema.Enum.SelecionarPagamento;
import baseAPI.API.Sistema.Enum.StatusOrdenServico;
import baseAPI.API.Sistema.Enum.StatusPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@Entity
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ordemServico_cliente_Id")
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    private Aparelho aparelho;

    private String relatoCliente;

    @ManyToOne
    @JoinColumn(name = "ordemServico_colaborador_Id")
    private Colaborador colaborador;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reparo_id", referencedColumnName = "id")
    private Reparo reparo;

    @Column(unique = true)
    private String codigo;

    @Enumerated(EnumType.STRING)
    private StatusOrdenServico statusOrdenServico;

    private String defeito;

    private Double valorTotal;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataEntrada;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataAprovacao;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataAnalise;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataFinalizacaoReparo;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataEntrega;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime datarecusado;

    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;

    @Enumerated(EnumType.STRING)
    private SelecionarPagamento selecionarPagamento;

    private Boolean finalizado;

    public Double CalValorTotal()
    {
        Double porcentagem = (30.0/100);
        this.valorTotal = (reparo.getValortotalReparo() * porcentagem) + reparo.getValortotalReparo();
        return valorTotal;
    }
}
