package baseAPI.API.Sistema.Service;

import baseAPI.API.Sistema.Model.Backup;
import baseAPI.API.Sistema.Model.Cliente;
import baseAPI.API.Sistema.Repository.BackupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Service
public class BackupService {
    @Autowired
    BackupRepository backupRepository;

    public ResponseEntity <List<Backup>> listarBackup() throws Exception
    {
        try
        {
            return new ResponseEntity<>(backupRepository.findAll(), OK);
        }
        catch (Exception e)
        {
            throw new Exception("erro ao listar");
        }
    }
}
