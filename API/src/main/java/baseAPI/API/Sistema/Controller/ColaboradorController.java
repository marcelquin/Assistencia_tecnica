package baseAPI.API.Sistema.Controller;

import baseAPI.API.Sistema.DTO.CargoDTO;
import baseAPI.API.Sistema.DTO.ColaboradorDTO;
import baseAPI.API.Sistema.DTO.EnderecoDTO;
import baseAPI.API.Sistema.Model.Colaborador;
import baseAPI.API.Sistema.Service.ClienteService;
import baseAPI.API.Sistema.Service.ColaoradorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("colaborador")
@Tag(name = "colaborador", description = "Manipulação de dados relacionados a entidade")
public class ColaboradorController {
    @Autowired
    ColaoradorService service;

    @Operation(summary = "Lista Registro da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping()
    public ResponseEntity<List<Colaborador>> listarColaboradores() throws Exception
    { return service.listarColaboradores();}


    @Operation(summary = "Busca Registro da tabela por Id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarColaboradoresPorId")
    public ResponseEntity<Colaborador> BuscarColaboradoresPorId(@RequestParam Long id) throws Exception
    { return service.BuscarColaboradoresPorId(id);}


    @Operation(summary = "Busca Registro da tabela por CPF", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarColaboradoresPorCpf")
    public ResponseEntity<Colaborador> BuscarColaboradoresPorCpf(@RequestParam Long cpf) throws Exception
    { return service.BuscarColaboradoresPorCpf(cpf);}

    @Operation(summary = "Salva Novo Registro na tabela", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping(value = "NovoColaborador", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ColaboradorDTO> NovoColaborador(ColaboradorDTO dto, @RequestPart MultipartFile[] files) throws SQLException, IOException
    { return service.NovoColaborador(dto, files);}

    @Operation(summary = "Edita Cargo do Registro na tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/AlterarCargoColaborador")
    public ResponseEntity<ColaboradorDTO> AlterarCargoColaborador(@RequestParam Long id, CargoDTO dto)
    {return service.AlterarCargoColaborador(id, dto);}

    @Operation(summary = "Edita Endereço do Registro na tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping(value = "/AlterarEnderecoColaborador")
    public ResponseEntity<ColaboradorDTO> AlterarEnderecoColaborador(@RequestParam Long id, EnderecoDTO dto, @RequestPart MultipartFile comprovanteEndereco) throws SQLException, IOException
    { return service.AlterarEnderecoColaborador(id, dto, comprovanteEndereco);}

    @Operation(summary = "Adiciona Arquivo ao Registro na tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping(value = "/AdicionarArquivoColaborador", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ColaboradorDTO> AdicionarArquivoColaborador(@RequestParam Long id, @RequestPart MultipartFile[] files)
    { return service.AdicionarArquivoColaborador(id, files);}

    @Operation(summary = "Deleta Registro na tabela", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @DeleteMapping()
    public ResponseEntity<ColaboradorDTO> DeletarColaboradores(@RequestParam Long id) throws Exception
    { return service.DeletarColaboradores(id);}
}
