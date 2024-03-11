package baseAPI.API.Sistema.Model;

import baseAPI.API.Sistema.Enum.SelecionarAcaoBackup;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Backup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SelecionarAcaoBackup acaoBackup;

    @ManyToOne
    @JoinColumn(name = "backup_cliente_Id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "backup_colaborador_Id")
    private Colaborador colaborador;

    @ManyToOne
    @JoinColumn(name = "backup_fornecedor_Id")
    private Fornecedor fornecedor;


    @ManyToOne
    @JoinColumn(name = "backup_ordemServico_Id")
    private OrdemServico ordemServico;

    private Double valor;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataEvento;



}
