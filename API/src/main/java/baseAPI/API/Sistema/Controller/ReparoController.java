package baseAPI.API.Sistema.Controller;

import baseAPI.API.Sistema.DTO.PedidoDTO;
import baseAPI.API.Sistema.DTO.ReparoDTO;
import baseAPI.API.Sistema.Model.Backup;
import baseAPI.API.Sistema.Model.Reparo;
import baseAPI.API.Sistema.Service.BackupService;
import baseAPI.API.Sistema.Service.ReparoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reparo")
@Tag(name = "reparo", description = "Manipulação de dados relacionados a entidade")
public class ReparoController {

    @Autowired
    ReparoService service;

    @Operation(summary = "Lista Registro da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping()
    public ResponseEntity<List<Reparo>> listarReparo() throws Exception
    {return service.listarReparo();}

    @Operation(summary = "Busca Registro da tabela por Id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarReparoPorId")
    public ResponseEntity<Reparo> BuscarReparoPorId(Long id) throws Exception
    { return  service.BuscarReparoPorId(id);}

    @Operation(summary = "Salva novo Registro na tabela", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping("/NovoReparo")
    public ResponseEntity<ReparoDTO> NovoReparo(@RequestParam Long idOrdemServico, ReparoDTO dto)
    { return service.NovoReparo(idOrdemServico,dto);}

    @Operation(summary = "Salva novo Registro na tabela", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping("/NovoPedido")
    public ResponseEntity<ReparoDTO> NovoPedido(@RequestParam Long idReparo, PedidoDTO dto)
    { return service.NovoPedido(idReparo, dto);}

    @Operation(summary = "Deleta Registro na tabela", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @DeleteMapping()
    public ResponseEntity<ReparoDTO> DeletarReparo(Long id) throws Exception
    { return service.DeletarReparo(id);}

}
