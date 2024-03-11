package baseAPI.API.Sistema.Controller;

import baseAPI.API.Sistema.DTO.ClienteDTO;
import baseAPI.API.Sistema.DTO.ClienteEdtDTO;
import baseAPI.API.Sistema.DTO.EnderecoDTO;
import baseAPI.API.Sistema.Model.Backup;
import baseAPI.API.Sistema.Model.Cliente;
import baseAPI.API.Sistema.Service.BackupService;
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
@RequestMapping("backup")
@Tag(name = "backup", description = "Manipulação de dados relacionados a entidade")
public class BackupController {

    @Autowired
    BackupService service;

    @Operation(summary = "Lista Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping()
    public ResponseEntity <List<Backup>> listarCliente() throws Exception
    { return service.listarBackup();}

}
