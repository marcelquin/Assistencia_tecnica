package baseAPI.API.Sistema.Service;

import baseAPI.API.Sistema.DTO.ClienteDTO;
import baseAPI.API.Sistema.DTO.EnderecoDTO;
import baseAPI.API.Sistema.DTO.FornecedorDTO;
import baseAPI.API.Sistema.DTO.FornecedorEdtDTO;
import baseAPI.API.Sistema.Enum.SelecionarAcaoBackup;
import baseAPI.API.Sistema.Model.Backup;
import baseAPI.API.Sistema.Model.Cliente;
import baseAPI.API.Sistema.Model.Endereco;
import baseAPI.API.Sistema.Model.Fornecedor;
import baseAPI.API.Sistema.Repository.BackupRepository;
import baseAPI.API.Sistema.Repository.EnderecoRepository;
import baseAPI.API.Sistema.Repository.FornecedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
public class FornecedorService {
    @Autowired
    FornecedorRepository fornecedorRepository;
    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    BackupRepository backupRepository;

    public ResponseEntity<List<Fornecedor>> listarCliente() throws Exception
    {
        try
        {
            return new ResponseEntity<>(fornecedorRepository.findAll(), OK);
        }
        catch (Exception e)
        {
            throw new Exception("erro ao listar");
        }
    }

    public ResponseEntity<Fornecedor> BuscarClientePorId(Long id) throws Exception
    {
        try
        {
            if(fornecedorRepository.existsById(id))
            {
                Fornecedor fornecedor = fornecedorRepository.findById(id).get();
                return new ResponseEntity<>(fornecedor, OK);
            }

        }
        catch (Exception e)
        {
            throw new Exception("erro ao listar");
        }
        return null;
    }

    public ResponseEntity<Fornecedor> BuscarClientePorCnpj(Long cnpj) throws Exception
    {
        try
        {
            if(fornecedorRepository.existsBycnpj(cnpj))
            {
                Fornecedor fornecedor = fornecedorRepository.findBycnpj(cnpj);
                return new ResponseEntity<>(fornecedor, OK);
            }

        }
        catch (Exception e)
        {
            throw new Exception("erro ao listar");
        }
        return null;
    }

    public ResponseEntity<FornecedorDTO> NovoFornecedor(FornecedorDTO dto)
    {
        try{
            if(dto != null)
            {
                Fornecedor fornecedor = new Fornecedor(dto);
                Endereco endereco = new Endereco(dto);
                enderecoRepository.save(endereco);
                fornecedor.setEndereco(endereco);
                fornecedorRepository.save(fornecedor);
                Backup backup = new Backup();
                backup.setAcaoBackup(SelecionarAcaoBackup.NOVO_FORNECEDOR);
                backup.setDataEvento(LocalDateTime.now());
                backup.setFornecedor(fornecedor);
                backupRepository.save(backup);
                return new ResponseEntity<>(CREATED);
            }
            else
            {
                return new ResponseEntity<>(BAD_REQUEST);
            }
        }catch (Exception e)
        {
            System.out.println("Ops algo deu errado!");
            e.getStackTrace();
        }
        return null;
    }

    public ResponseEntity<FornecedorDTO> AlterarDadosFornecedor(Long id,FornecedorEdtDTO dto)
    {
        try{
            if(fornecedorRepository.existsById(id))
            {
                Fornecedor fornecedor = fornecedorRepository.findById(id).get();
                fornecedor.setNome(dto.nome());
                fornecedor.setRazaoSocial(dto.razaoSocial());
                fornecedor.setCnpj(dto.cnpj());
                fornecedor.setTelefone(dto.telefone());
                fornecedor.setEmail(dto.email());
                fornecedorRepository.save(fornecedor);
                Backup backup = new Backup();
                backup.setAcaoBackup(SelecionarAcaoBackup.EDITAR_FORNECEDOR);
                backup.setDataEvento(LocalDateTime.now());
                backup.setFornecedor(fornecedor);
                backupRepository.save(backup);
                return new ResponseEntity<>(OK);
            }
            else
            {
                return new ResponseEntity<>(BAD_REQUEST);
            }
        }catch (Exception e)
        {
            System.out.println("Ops algo deu errado!");
            e.getStackTrace();
        }
        return null;
    }

    public ResponseEntity<FornecedorDTO> AlerarEnderecoFornecedor(Long id,EnderecoDTO dto)
    {
        try{
            if(fornecedorRepository.existsById(id))
            {
                Fornecedor fornecedor = fornecedorRepository.findById(id).get();
                Endereco endereco = enderecoRepository.findById(fornecedor.getEndereco().getId()).get();
                endereco.setLogradouro(dto.Logradouro());
                endereco.setNumero(dto.numero());
                endereco.setBairro(dto.bairro());
                endereco.setCep(dto.cep());
                endereco.setCidade(dto.cidade());
                endereco.setEstado(dto.estado());
                enderecoRepository.save(endereco);
                Backup backup = new Backup();
                backup.setAcaoBackup(SelecionarAcaoBackup.EDITAR_FORNECEDOR);
                backup.setDataEvento(LocalDateTime.now());
                backup.setFornecedor(fornecedor);
                backupRepository.save(backup);
                return new ResponseEntity<>(OK);
            }
            else
            {
                return new ResponseEntity<>(BAD_REQUEST);
            }
        }catch (Exception e)
        {
            System.out.println("Ops algo deu errado!");
            e.getStackTrace();
        }
        return null;
    }

    public ResponseEntity<FornecedorDTO> DeletarFornecedor(Long id) throws Exception
    {
        try
        {
            if(fornecedorRepository.existsById(id))
            {
                fornecedorRepository.deleteById(id);
            }
            return new ResponseEntity<>(OK);
        }
        catch (Exception e)
        {
            throw new Exception("erro ao deletar");
        }
    }
}
