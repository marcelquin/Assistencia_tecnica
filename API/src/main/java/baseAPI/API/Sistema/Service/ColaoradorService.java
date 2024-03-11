package baseAPI.API.Sistema.Service;

import baseAPI.API.Sistema.DTO.CargoDTO;
import baseAPI.API.Sistema.DTO.ColaboradorDTO;
import baseAPI.API.Sistema.DTO.EnderecoDTO;
import baseAPI.API.Sistema.DTO.FornecedorDTO;
import baseAPI.API.Sistema.Enum.SelecionarAcaoBackup;
import baseAPI.API.Sistema.Exceptions.EntityNotFoundException;
import baseAPI.API.Sistema.Exceptions.NullargumentsException;
import baseAPI.API.Sistema.Model.*;
import baseAPI.API.Sistema.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
public class ColaoradorService {

    @Autowired
    ColaboradorRepository colaboradorRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    CargoRepository cargoRepository;

    @Autowired
    DocumentosRepository documentosRepository;

    @Autowired
    BackupRepository backupRepository;

    @Value("${App.caminhoImagem}")
    private static String caminhoImagem;

    public ResponseEntity<List<Colaborador>> listarColaboradores() throws Exception
    {
        try
        {
            return new ResponseEntity<>(colaboradorRepository.findAll(), OK);
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    public ResponseEntity<ColaboradorDTO> BuscarColaboradoresPorId(Long id) throws Exception
    {
        try
        {
            if (id != null)
            {
                if(colaboradorRepository.existsById(id))
                {
                    Colaborador colaborador = colaboradorRepository.findById(id).get();
                    ColaboradorDTO response = new ColaboradorDTO(colaborador.getNome(), colaborador.getSobrenome(), colaborador.getCpf(),
                            colaborador.getTelefone(), colaborador.getDataNascimento(),colaborador.getEmail(),colaborador.getEndereco().getLogradouro(),
                            colaborador.getEndereco().getNumero(),colaborador.getEndereco().getBairro(),colaborador.getEndereco().getCep(),
                            colaborador.getEndereco().getCidade(),colaborador.getEndereco().getEstado(),colaborador.getCargo().getDepartamento(),
                            colaborador.getCargo().getNome(),colaborador.getCargo().getDescrisao(),colaborador.getCargo().getSalario(),colaborador.getDataEntrada());
                    return new ResponseEntity<>(response, OK);
                }
                { throw new EntityNotFoundException(); }
            }
            { throw new NullargumentsException(); }
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    public ResponseEntity<ColaboradorDTO> BuscarColaboradoresPorCpf(Long cpf) throws Exception
    {
        try
        {
            if (cpf != null)
            {
                if(colaboradorRepository.existsBycpf(cpf))
                {
                    Colaborador colaborador = colaboradorRepository.findBycpf(cpf);
                    ColaboradorDTO response = new ColaboradorDTO(colaborador.getNome(), colaborador.getSobrenome(), colaborador.getCpf(),
                            colaborador.getTelefone(), colaborador.getDataNascimento(), colaborador.getEmail(), colaborador.getEndereco().getLogradouro(),
                            colaborador.getEndereco().getNumero(), colaborador.getEndereco().getBairro(), colaborador.getEndereco().getCep(),
                            colaborador.getEndereco().getCidade(), colaborador.getEndereco().getEstado(), colaborador.getCargo().getDepartamento(),
                            colaborador.getCargo().getNome(), colaborador.getCargo().getDescrisao(), colaborador.getCargo().getSalario(), colaborador.getDataEntrada());
                    return new ResponseEntity<>(response, OK);
                }
                { throw new EntityNotFoundException(); }
            }
            else
            { throw new NullargumentsException(); }
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    public ResponseEntity<ColaboradorDTO> NovoColaborador(ColaboradorDTO dto, MultipartFile[] files) throws SQLException, IOException
    {
        try{
            if(dto != null && files != null)
            {
                Colaborador colaborador = new Colaborador(dto);
                Cargo cargo = new Cargo(dto);
                Endereco endereco = new Endereco(dto);
                Documentos documentos = new Documentos();
                List<String> arquivos = new ArrayList<>();
                cargoRepository.save(cargo);
                enderecoRepository.save(endereco);
                colaborador.setCargo(cargo);
                colaborador.setEndereco(endereco);
                boolean pasta = new File(caminhoImagem + "\\"+dto.nome()+"_"+dto.cargo()).mkdir();
                for(MultipartFile file: files)
                {
                    byte[] bytes = file.getBytes();
                    Path caminho = Paths.get(caminhoImagem+dto.nome()+"_"+dto.cargo()+"\\"+dto.nome()+"_"+dto.cargo()+"_"+file.getOriginalFilename());
                    Files.write(caminho, bytes);
                    arquivos.add(dto.nome()+"_"+dto.cargo()+"_"+file.getOriginalFilename());
                }
                documentos.setArquivos(arquivos);
                documentos.setDataEnvio(LocalDateTime.now());
                documentosRepository.save(documentos);
                colaborador.setDocumentos(documentos);
                colaboradorRepository.save(colaborador);
                Backup backup = new Backup();
                backup.setAcaoBackup(SelecionarAcaoBackup.NOVO_COLABORADOR);
                backup.setDataEvento(LocalDateTime.now());
                backup.setColaborador(colaborador);
                backupRepository.save(backup);
                ColaboradorDTO response = new ColaboradorDTO(colaborador.getNome(), colaborador.getSobrenome(), colaborador.getCpf(),
                        colaborador.getTelefone(), colaborador.getDataNascimento(),colaborador.getEmail(),colaborador.getEndereco().getLogradouro(),
                        colaborador.getEndereco().getNumero(),colaborador.getEndereco().getBairro(),colaborador.getEndereco().getCep(),
                        colaborador.getEndereco().getCidade(),colaborador.getEndereco().getEstado(),colaborador.getCargo().getDepartamento(),
                        colaborador.getCargo().getNome(),colaborador.getCargo().getDescrisao(),colaborador.getCargo().getSalario(),colaborador.getDataEntrada());
                return new ResponseEntity<>(response, CREATED);
            }
            { throw new NullargumentsException();}
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    public ResponseEntity<ColaboradorDTO> AlterarCargoColaborador(Long id,CargoDTO dto)
    {
        try
        {
            if(id != null && dto != null)
            {
                if (colaboradorRepository.existsById(id))
                {
                    Colaborador colaborador = colaboradorRepository.findById(id).get();
                    if (cargoRepository.existsById(colaborador.getCargo().getId())) {
                        Cargo cargo = cargoRepository.findById(colaborador.getCargo().getId()).get();
                        cargo.setNome(dto.nome());
                        cargo.setDepartamento(dto.departamento());
                        cargo.setDescrisao(dto.descrisao());
                        cargo.setSalario(dto.salario());
                        cargoRepository.save(cargo);
                    }
                    Backup backup = new Backup();
                    backup.setAcaoBackup(SelecionarAcaoBackup.EDITAR_COLABORADOR);
                    backup.setDataEvento(LocalDateTime.now());
                    backup.setColaborador(colaborador);
                    backupRepository.save(backup);
                    ColaboradorDTO response = new ColaboradorDTO(colaborador.getNome(), colaborador.getSobrenome(), colaborador.getCpf(),
                            colaborador.getTelefone(), colaborador.getDataNascimento(), colaborador.getEmail(), colaborador.getEndereco().getLogradouro(),
                            colaborador.getEndereco().getNumero(), colaborador.getEndereco().getBairro(), colaborador.getEndereco().getCep(),
                            colaborador.getEndereco().getCidade(), colaborador.getEndereco().getEstado(), colaborador.getCargo().getDepartamento(),
                            colaborador.getCargo().getNome(), colaborador.getCargo().getDescrisao(), colaborador.getCargo().getSalario(), colaborador.getDataEntrada());
                    return new ResponseEntity<>(response, OK);
                }
                { throw new EntityNotFoundException();}
            }
            else
            { throw new NullargumentsException();}
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    public ResponseEntity<ColaboradorDTO> AdicionarArquivoColaborador(Long id,MultipartFile[] files) throws IOException
    {
        try
        {
            if(id != null && files != null)
            {
                if (colaboradorRepository.existsById(id))
                {
                    Colaborador colaborador = colaboradorRepository.findById(id).get();
                    if (documentosRepository.existsById(colaborador.getDocumentos().getId()))
                    {
                        Documentos documentos = documentosRepository.findById(colaborador.getDocumentos().getId()).get();
                        List<String> arquivos = new ArrayList<>();
                        for (MultipartFile file : files)
                        {
                            byte[] bytes = file.getBytes();
                            Path caminho = Paths.get(caminhoImagem + colaborador.getNome() + "_" + colaborador.getCargo() + "\\" + colaborador.getNome() + "_" + colaborador.getCargo() + "_" + file.getOriginalFilename());
                            Files.write(caminho, bytes);
                            arquivos.add(colaborador.getNome() + "_" + colaborador.getCargo() + "_" + file.getOriginalFilename());
                        }
                        arquivos.forEach(arquivo -> documentos.getArquivos().add(arquivo));
                        documentosRepository.save(documentos);
                        Backup backup = new Backup();
                        backup.setAcaoBackup(SelecionarAcaoBackup.EDITAR_COLABORADOR);
                        backup.setDataEvento(LocalDateTime.now());
                        backup.setColaborador(colaborador);
                        backupRepository.save(backup);
                        ColaboradorDTO response = new ColaboradorDTO(colaborador.getNome(), colaborador.getSobrenome(), colaborador.getCpf(),
                                colaborador.getTelefone(), colaborador.getDataNascimento(), colaborador.getEmail(), colaborador.getEndereco().getLogradouro(),
                                colaborador.getEndereco().getNumero(), colaborador.getEndereco().getBairro(), colaborador.getEndereco().getCep(),
                                colaborador.getEndereco().getCidade(), colaborador.getEndereco().getEstado(), colaborador.getCargo().getDepartamento(),
                                colaborador.getCargo().getNome(), colaborador.getCargo().getDescrisao(), colaborador.getCargo().getSalario(), colaborador.getDataEntrada());
                        return new ResponseEntity<>(response, OK);
                    }
                    else { throw new EntityNotFoundException();}
                }
                else { throw new EntityNotFoundException();}
            }
            else { throw new NullargumentsException();}
        }catch (Exception e)
        {

        }
        return null;
    }

    public ResponseEntity<ColaboradorDTO> AlterarEnderecoColaborador(Long id,EnderecoDTO dto, MultipartFile file) throws SQLException
    {
        try{
            if(id != null && dto != null && file != null)
            {
                if(colaboradorRepository.existsById(id))
                {
                    Colaborador colaborador = colaboradorRepository.findById(id).get();
                    if(enderecoRepository.existsById(colaborador.getEndereco().getId()))
                    {
                        Endereco endereco = enderecoRepository.findById(colaborador.getEndereco().getId()).get();
                        endereco.setLogradouro(dto.Logradouro());
                        endereco.setNumero(dto.numero());
                        endereco.setBairro(dto.bairro());
                        endereco.setCep(dto.cep());
                        endereco.setCidade(dto.cidade());
                        endereco.setEstado(dto.estado());
                        enderecoRepository.save(endereco);
                        Backup backup = new Backup();
                        backup.setAcaoBackup(SelecionarAcaoBackup.EDITAR_COLABORADOR);
                        backup.setDataEvento(LocalDateTime.now());
                        backup.setColaborador(colaborador);
                        backupRepository.save(backup);
                        ColaboradorDTO response = new ColaboradorDTO(colaborador.getNome(), colaborador.getSobrenome(), colaborador.getCpf(),
                                colaborador.getTelefone(), colaborador.getDataNascimento(),colaborador.getEmail(),colaborador.getEndereco().getLogradouro(),
                                colaborador.getEndereco().getNumero(),colaborador.getEndereco().getBairro(),colaborador.getEndereco().getCep(),
                                colaborador.getEndereco().getCidade(),colaborador.getEndereco().getEstado(),colaborador.getCargo().getDepartamento(),
                                colaborador.getCargo().getNome(),colaborador.getCargo().getDescrisao(),colaborador.getCargo().getSalario(),colaborador.getDataEntrada());
                        return new ResponseEntity<>(response,OK);
                    }
                }
                else { throw new EntityNotFoundException();}
            }
            else { throw new NullargumentsException();}
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    public ResponseEntity<ColaboradorDTO> DeletarColaboradores(Long id) throws Exception
    {
        try
        {
            if(id != null)
            {
                if(colaboradorRepository.existsById(id))
                {
                    colaboradorRepository.deleteById(id);
                    return new ResponseEntity<>(OK);
                }
                else { throw new EntityNotFoundException();}
            }
            else { throw new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

}
