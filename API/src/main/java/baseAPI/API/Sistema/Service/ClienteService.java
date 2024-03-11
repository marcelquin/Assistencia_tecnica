package baseAPI.API.Sistema.Service;

import baseAPI.API.Sistema.DTO.ClienteDTO;
import baseAPI.API.Sistema.DTO.ClienteEdtDTO;
import baseAPI.API.Sistema.DTO.EnderecoDTO;
import baseAPI.API.Sistema.Enum.SelecionarAcaoBackup;
import baseAPI.API.Sistema.Model.Backup;
import baseAPI.API.Sistema.Model.Cliente;
import baseAPI.API.Sistema.Model.Endereco;
import baseAPI.API.Sistema.Repository.BackupRepository;
import baseAPI.API.Sistema.Repository.ClienteRepository;
import baseAPI.API.Sistema.Repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    BackupRepository backupRepository;

    public ResponseEntity <List<Cliente>> listarCliente() throws Exception
    {
        try
        {
            return new ResponseEntity<>(clienteRepository.findAll(), OK);
        }
        catch (Exception e)
        {
            throw new Exception("erro ao listar");
        }
    }

    public ResponseEntity<Cliente> BuscarClientePorId(Long id) throws Exception
    {
        try
        {
            if(clienteRepository.existsById(id))
            {
                Cliente cliente = clienteRepository.findById(id).get();
                return new ResponseEntity<>(cliente, OK);
            }

        }
        catch (Exception e)
        {
            throw new Exception("erro ao listar");
        }
        return null;
    }

    public ResponseEntity<Cliente> BuscarClientePorNome(String nome) throws Exception
    {
        try
        {
            if(clienteRepository.existsBynome(nome))
            {
                Cliente cliente = clienteRepository.findBynome(nome);
                return new ResponseEntity<>(cliente, OK);
            }

        }
        catch (Exception e)
        {
            throw new Exception("erro ao listar");
        }
        return null;
    }

    public ResponseEntity<Cliente> BuscarClientePorTelefone(Long telefone) throws Exception
    {
        try
        {
            if(clienteRepository.existsBytelefone(telefone))
            {
                Cliente cliente = clienteRepository.findBytelefone(telefone);
                return new ResponseEntity<>(cliente, OK);
            }

        }
        catch (Exception e)
        {
            throw new Exception("erro ao listar");
        }
        return null;
    }

    public ResponseEntity<ClienteDTO> NovoCliente(ClienteDTO dto) throws Exception
    {
        try{
            if(dto != null)
            {
                Endereco endereco = new Endereco(dto);
                Cliente cliente = new Cliente(dto);
                Backup backup = new Backup();
                enderecoRepository.save(endereco);
                cliente.setEndereco(endereco);
                clienteRepository.save(cliente);
                backup.setAcaoBackup(SelecionarAcaoBackup.NOVO_CLIENTE);
                backup.setCliente(cliente);
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

    public ResponseEntity<ClienteDTO> AlterarContatoCliente(Long id,Long telefone, String email) throws Exception
    {
        try{
            if(clienteRepository.existsById(id))
            {
                Cliente cliente = clienteRepository.findById(id).get();
                if(telefone != null)
                {
                    cliente.setTelefone(telefone);
                }
                if(email != null)
                {
                    cliente.setEmail(email);
                }
                clienteRepository.save(cliente);
                Backup backup = new Backup();
                backup.setAcaoBackup(SelecionarAcaoBackup.EDITAR_CLIENTE);
                backup.setDataEvento(LocalDateTime.now());
                backup.setCliente(cliente);
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

    public ResponseEntity<ClienteDTO> AlterarDadosCliente(Long id, ClienteEdtDTO dto) throws Exception
    {
        try{
            if(clienteRepository.existsById(id))
            {
                Cliente cliente = clienteRepository.findById(id).get();
                cliente.setNome(dto.nome());
                cliente.setSobrenome(dto.sobrenome());
                cliente.setTelefone(dto.telefone());
                cliente.setDataNascimento(dto.dataNascimento());
                cliente.setEmail(dto.email());
                clienteRepository.save(cliente);
                Backup backup = new Backup();
                backup.setAcaoBackup(SelecionarAcaoBackup.EDITAR_CLIENTE);
                backup.setDataEvento(LocalDateTime.now());
                backup.setCliente(cliente);
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

    public ResponseEntity<ClienteDTO> AlterarEnderecoCliente(Long id, EnderecoDTO dto) throws Exception
    {
        try{
            if(clienteRepository.existsById(id))
            {
                Cliente cliente = clienteRepository.findById(id).get();
                Endereco endereco = enderecoRepository.findById(cliente.getEndereco().getId()).get();
                endereco.setLogradouro(dto.Logradouro());
                endereco.setNumero(dto.numero());
                endereco.setBairro(dto.bairro());
                endereco.setCep(dto.cep());
                endereco.setCidade(dto.cidade());
                endereco.setEstado(dto.estado());
                enderecoRepository.save(endereco);
                Backup backup = new Backup();
                backup.setAcaoBackup(SelecionarAcaoBackup.EDITAR_CLIENTE);
                backup.setDataEvento(LocalDateTime.now());
                backup.setCliente(cliente);
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

    public ResponseEntity<ClienteDTO> DeletarCliente(Long id) throws Exception
    {
        try
        {
            if(clienteRepository.existsById(id))
            {
                clienteRepository.deleteById(id);
            }
            return new ResponseEntity<>(OK);
        }
        catch (Exception e)
        {
            throw new Exception("erro ao deletar");
        }
    }

}
