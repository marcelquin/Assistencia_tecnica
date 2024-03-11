package baseAPI.API.Sistema.Controller;


import baseAPI.API.Sistema.DTO.OrdemServicoDTO;
import baseAPI.API.Sistema.Enum.Aparelho;
import baseAPI.API.Sistema.Enum.SelecionarPagamento;
import baseAPI.API.Sistema.Model.OrdemServico;
import baseAPI.API.Sistema.Service.OrdemServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ordemServico")
@Tag(name = "ordemServico", description = "Manipulação de dados relacionados a entidade")
public class OrdemServicoController {

    @Autowired
    OrdemServicoService service;

    @Operation(summary = "Lista Registro da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping()
    public ResponseEntity<List<OrdemServico>> listarOrdemServico() throws Exception
    { return service.listarOrdemServico();}

    @Operation(summary = "Busca Registro da tabela por Id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarOrdemServicoPorId")
    public ResponseEntity<OrdemServico> BuscarOrdemServicoPorId(@RequestParam Long id) throws Exception
    { return service.BuscarOrdemServicoPorId(id);}

    @Operation(summary = "Busca Registro da tabela por Codigo", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarOrdemServicoPorCodigo")
    public ResponseEntity<OrdemServico> BuscarOrdemServicoPorCodigo(@RequestParam String codigo) throws Exception
    { return service.BuscarOrdemServicoPorCodigo(codigo);}

    @Operation(summary = "Salva Novo Registro na tabela", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping()
    public ResponseEntity<OrdemServicoDTO> NovaOrdemServico(@RequestParam Long idCLiente, @RequestParam Long idColaborador,@RequestParam Aparelho aparelho,@RequestParam String relatoCliente) throws Exception
    {return service.NovaOrdemServico(idCLiente, idColaborador, aparelho, relatoCliente);}

/*    @Operation(summary = "Faz o pedido da peça de reposição do reparo", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/PedidoItemReparo")
    public ResponseEntity<OrdemServicoDTO> PedidoItemReparo(@RequestParam Long idFornecedor,@RequestParam Long idOrdemServico, PedidoPecaDTO dto)
    { re    turn service.PedidoItemReparo(idOrdemServico, idFornecedor, dto);}*/

    @Operation(summary = "Final de analize e identificando o defento para aprovação", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/AnaliseOrdemServico")
    public ResponseEntity<OrdemServicoDTO> AnaliseOrdemServico(@RequestParam String codigoOrdemServico,@RequestParam String defeito)
    { return service.AnaliseOrdemServico(codigoOrdemServico, defeito);}

    @Operation(summary = "Autoriza o reparou ou recusa o mesmo", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/AlterarStatusOrdemServico")
    public ResponseEntity<OrdemServicoDTO> AlterarStatusOrdemServico(@RequestParam String codigoOrdemServico, @RequestParam Boolean OSCOnfirmar)
    { return service.AlterarStatusOrdemServico(codigoOrdemServico, OSCOnfirmar);}

    @Operation(summary = "Finaliza Reparo fazendo o mesmo pronto para entrega", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/FinalizarReparoOrdemServico")
    public ResponseEntity<OrdemServicoDTO> FinalizarReparoOrdemServico(@RequestParam Long idOrdemServico)
    { return service.FinalizarReparoOrdemServico(idOrdemServico);}

    @Operation(summary = "Finaliza Ordem de Serviço ao entregar produto", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/FinalizarOrdemServico")
    public ResponseEntity<OrdemServicoDTO> FinalizarOrdemServico(@RequestParam String codigo, @RequestParam SelecionarPagamento selecionarPagamento)
    { return service.FinalizarOrdemServico(codigo, selecionarPagamento);}

    @Operation(summary = "Finaliza Ordem de Serviço não entregue", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/ProdutoNaoRetirado")
    public ResponseEntity<OrdemServicoDTO> ProdutoNaoRetirado(String codigo) throws Exception
    { return service.ProdutoNaoRetirado(codigo);}

    @Operation(summary = "Deleta Registro na tabela", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @DeleteMapping()
    public ResponseEntity<OrdemServicoDTO> DeletarOrdemServico(@RequestParam Long id) throws Exception
    { return service.DeletarOrdemServico(id);}

}
