package baseAPI.API.Sistema.Controller;

import baseAPI.API.Sistema.DTO.ClienteDTO;
import baseAPI.API.Sistema.DTO.ClienteEdtDTO;
import baseAPI.API.Sistema.DTO.EnderecoDTO;
import baseAPI.API.Sistema.Model.Cliente;
import baseAPI.API.Sistema.Service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cliente")
@Tag(name = "cliente", description = "Manipulação de dados relacionados a entidade")
public class ClienteController {
    @Autowired
    ClienteService service;

    @Operation(summary = "Lista Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping()
    public ResponseEntity<List<Cliente>> listarCliente() throws Exception
    { return service.listarCliente();}

    @Operation(summary = "Busca Registro da tabela por Id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarClientePorId")
    public ResponseEntity<Cliente> BuscarClientePorId(@RequestParam Long id) throws Exception
    { return service.BuscarClientePorId(id);}

    @Operation(summary = "Busca Registro da tabela por Nome", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarClientePorNome")
    public ResponseEntity<Cliente> BuscarClientePorNome(@RequestParam String nome) throws Exception
    { return service.BuscarClientePorNome(nome);}

    @Operation(summary = "Busca Registro da tabela por Telefone", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarClientePorTelefone")
    public ResponseEntity<Cliente> BuscarClientePorTelefone(@RequestParam Long telefone) throws Exception
    {return service.BuscarClientePorTelefone(telefone);}

    @Operation(summary = "Salva Novo Registro na tabela", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping()
    public ResponseEntity<ClienteDTO> NovoCliente(ClienteDTO dto) throws Exception
    { return service.NovoCliente(dto);}

    @Operation(summary = "Edita Contato do Registro na tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/AlterarContatoCliente")
    public ResponseEntity<ClienteDTO> AlterarContatoCliente(@RequestParam Long id,@RequestParam Long telefone, @RequestParam String email) throws Exception
    { return service.AlterarContatoCliente(id, telefone, email);}


    @Operation(summary = "Edita Dados do Registro na tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/AlterarDadosCliente")
    public ResponseEntity<ClienteDTO> AlterarDadosCliente(@RequestParam Long id, ClienteEdtDTO dto) throws Exception
    { return service.AlterarDadosCliente(id, dto);}

    @Operation(summary = "Edita Endereço do Registro na tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
        })
    @PutMapping("/AlterarEnderecoCliente")
    public ResponseEntity<ClienteDTO> AlterarEnderecoCliente(@RequestParam Long id, EnderecoDTO dto) throws Exception
    { return service.AlterarEnderecoCliente(id, dto);}


    @Operation(summary = "Deleta Registro na tabela", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @DeleteMapping("/DeletarCliente")
    public ResponseEntity<ClienteDTO> DeletarCliente(@RequestParam Long id) throws Exception
    { return service.DeletarCliente(id);}

}
