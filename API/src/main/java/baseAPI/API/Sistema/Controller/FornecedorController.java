package baseAPI.API.Sistema.Controller;

import baseAPI.API.Sistema.DTO.EnderecoDTO;
import baseAPI.API.Sistema.DTO.FornecedorDTO;

import baseAPI.API.Sistema.DTO.FornecedorEdtDTO;
import baseAPI.API.Sistema.Model.Fornecedor;
import baseAPI.API.Sistema.Service.FornecedorService;
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
@RequestMapping("fornecedor")
@Tag(name = "fornecedor", description = "Manipulação de dados relacionados a entidade")
public class FornecedorController {
    @Autowired
    FornecedorService service;

    @Operation(summary = "Lista Registro da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping()
    public ResponseEntity<List<Fornecedor>> listarCliente() throws Exception
    { return service.listarCliente();}

    @Operation(summary = "Busca Registro da tabela por Id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarClientePorId")
    public ResponseEntity<Fornecedor> BuscarClientePorId(@RequestParam Long id) throws Exception
    { return service.BuscarClientePorId(id);}

    @Operation(summary = "Busca Registro da tabela por CNPJ", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarClientePorCnpj")
    public ResponseEntity<Fornecedor> BuscarClientePorCnpj(@RequestParam Long cnpj) throws Exception
    { return service.BuscarClientePorCnpj(cnpj);}


    @Operation(summary = "Salva Novo Registro na tabela", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping()
    public ResponseEntity<FornecedorDTO> NovoFornecedor(FornecedorDTO dto)
    { return service.NovoFornecedor(dto);}

    @Operation(summary = "Edita dados do  Registro na tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/AlterarDadosFornecedor")
    public ResponseEntity<FornecedorDTO> AlterarDadosFornecedor(@RequestParam Long id, FornecedorEdtDTO dto)
    { return service.AlterarDadosFornecedor(id, dto);}


    @Operation(summary = "Edita Endereço do Registro na tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/AlerarEnderecoFornecedor")
    public ResponseEntity<FornecedorDTO> AlerarEnderecoFornecedor(@RequestParam Long id, EnderecoDTO dto)
    { return service.AlerarEnderecoFornecedor(id, dto);}

    @Operation(summary = "Deleta Registro na tabela por id", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @DeleteMapping()
    public ResponseEntity<FornecedorDTO> DeletarFornecedor(Long id) throws Exception
    { return service.DeletarFornecedor(id);}


}
