package baseAPI.API.Sistema.Service;

import baseAPI.API.Sistema.DTO.OrdemServicoDTO;
import baseAPI.API.Sistema.Enum.*;
import baseAPI.API.Sistema.Exceptions.ClientBlockException;
import baseAPI.API.Sistema.Model.*;
import baseAPI.API.Sistema.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service

public class OrdemServicoService {

    @Autowired
    OrdemServicoRepository ordemServicoRepository;

    @Autowired
    FornecedorRepository fornecedorRepository;


    @Autowired
    BackupRepository backupRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ColaboradorRepository colaboradorRepository;

    public ResponseEntity<List<OrdemServico>> listarOrdemServico() throws Exception
    {
        try
        {
            return new ResponseEntity<>(ordemServicoRepository.findAll(), OK);
        }
        catch (Exception e)
        {
            throw new Exception("erro ao listar");
        }
    }

    public ResponseEntity<OrdemServico> BuscarOrdemServicoPorId(Long id) throws Exception
    {
        try
        {
            if(ordemServicoRepository.existsById(id))
            {
                OrdemServico ordemServico = ordemServicoRepository.findById(id).get();
                return new ResponseEntity<>(ordemServico, OK);
            }

        }
        catch (Exception e)
        {
            throw new Exception("erro ao listar");
        }
        return null;
    }

    public ResponseEntity<OrdemServico> BuscarOrdemServicoPorCodigo(String codigo) throws Exception
    {
        try
        {
            if(ordemServicoRepository.existsBycodigo(codigo))
            {
                OrdemServico ordemServico = ordemServicoRepository.findBycodigo(codigo);
                return new ResponseEntity<>(ordemServico, OK);
            }

        }
        catch (Exception e)
        {
            throw new Exception("erro ao listar");
        }
        return null;
    }

    //atendimento
    public ResponseEntity<OrdemServicoDTO> NovaOrdemServico(Long idCLiente, Long idColaborador, Aparelho aparelho, String relatoCliente) throws Exception
     {
        try{
            if(idCLiente != null && idColaborador != null && relatoCliente != null)
            {
                if(clienteRepository.existsById(idCLiente))
                {
                    Cliente cliente = clienteRepository.findById(idCLiente).get();
                    if(cliente.getBloqueado() == false)
                    {
                        if(colaboradorRepository.existsById(idColaborador))
                        {
                            Colaborador colaborador = colaboradorRepository.findById(idColaborador).get();
                            OrdemServico ordemServico = new OrdemServico();
                            int cod = (int) (10000001 + Math.random() * 89999999);;
                            String codigo = "OS_"+cod;
                            ordemServico.setCodigo(codigo);
                            ordemServico.setDataEntrada(LocalDateTime.now());
                            ordemServico.setRelatoCliente(relatoCliente);
                            ordemServico.setCliente(cliente);
                            ordemServico.setColaborador(colaborador);
                            ordemServico.setStatusOrdenServico(StatusOrdenServico.ORCAMENTO);
                            ordemServico.setAparelho(aparelho);
                            ordemServicoRepository.save(ordemServico);
                            Backup backup = new Backup();
                            backup.setAcaoBackup(SelecionarAcaoBackup.NOVO_ORCAMENTO);
                            backup.setOrdemServico(ordemServico);
                            backup.setDataEvento(LocalDateTime.now());
                            backupRepository.save(backup);
                            return new ResponseEntity<>(CREATED);
                        }
                    }
                    else
                    {
                        throw new ClientBlockException("Cliente Boqueado por não finalização de Ordem de serviço anterior");
                    }
                }
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

    //tec
    public ResponseEntity<OrdemServicoDTO> AnaliseOrdemServico(String codigoOrdemServico,String defeito)
    {
        try{
            if(codigoOrdemServico != null)
            {
                if(ordemServicoRepository.existsBycodigo(codigoOrdemServico))
                {
                    OrdemServico ordemServico = ordemServicoRepository.findBycodigo(codigoOrdemServico);
                    if(defeito != null)
                    {
                        ordemServico.setDefeito(defeito);
                        ordemServico.setValorTotal(ordemServico.CalValorTotal());
                        ordemServico.setStatusOrdenServico(StatusOrdenServico.AGUARDANDO_APROVACAO);
                        ordemServico.setDataAnalise(LocalDateTime.now());
                    }
                    ordemServicoRepository.save(ordemServico);
                    return new ResponseEntity<>(OK);
                }

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

    //atendimento
    public ResponseEntity<OrdemServicoDTO> AlterarStatusOrdemServico(String codigoOrdemServico, Boolean OSCOnfirmar)
    {
        try{
            if(codigoOrdemServico != null)
            {
              if(ordemServicoRepository.existsBycodigo(codigoOrdemServico))
              {
                  OrdemServico ordemServico = ordemServicoRepository.findBycodigo(codigoOrdemServico);
                  if(OSCOnfirmar == false)
                  {
                      ordemServico.setStatusOrdenServico(StatusOrdenServico.RECUSADO);
                      ordemServico.setDatarecusado(LocalDateTime.now());
                      ordemServico.setFinalizado(true);
                  }
                  else
                  {
                      ordemServico.setStatusOrdenServico(StatusOrdenServico.APROVADO);
                      ordemServico.setDataAprovacao(LocalDateTime.now());
                  }
                  ordemServicoRepository.save(ordemServico);
                  Backup backup = new Backup();
                  backup.setAcaoBackup(SelecionarAcaoBackup.ATUALIZACAO_ORDEM_SERVICO);
                  backup.setOrdemServico(ordemServico);
                  backup.setDataEvento(LocalDateTime.now());
                  backupRepository.save(backup);
                  return new ResponseEntity<>(OK);
              }

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

    public ResponseEntity<OrdemServicoDTO> FinalizarReparoOrdemServico(Long idOrdemServico)
    {
        try{
            if(idOrdemServico != null)
            {
                if(ordemServicoRepository.existsById(idOrdemServico))
                {
                    OrdemServico ordemServico = ordemServicoRepository.findById(idOrdemServico).get();
                    ordemServico.setDataFinalizacaoReparo(LocalDateTime.now());
                    ordemServico.setStatusOrdenServico(StatusOrdenServico.AGUARDANDO_RETIRADA);
                    ordemServico.setStatusPagamento(StatusPagamento.AGUARDANDO_PAGAMENTO);
                    ordemServicoRepository.save(ordemServico);
                    Backup backup = new Backup();
                    backup.setAcaoBackup(SelecionarAcaoBackup.ATUALIZACAO_ORDEM_SERVICO);
                    backup.setOrdemServico(ordemServico);
                    backup.setDataEvento(LocalDateTime.now());
                    backupRepository.save(backup);
                    return new ResponseEntity<>(OK);
                }

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

    //atendimento
    public ResponseEntity<OrdemServicoDTO> FinalizarOrdemServico(String codigo, SelecionarPagamento selecionarPagamento)
    {
        try{
            if(codigo != null)
            {
                if(ordemServicoRepository.existsBycodigo(codigo))
                {
                    OrdemServico ordemServico = ordemServicoRepository.findBycodigo(codigo);
                    ordemServico.setDataEntrega(LocalDateTime.now());
                    ordemServico.setStatusPagamento(StatusPagamento.PAGAMENTO_CONFIRMADO);
                    ordemServico.setStatusOrdenServico(StatusOrdenServico.PRODUTO_ENTREGUE);
                    ordemServico.setSelecionarPagamento(selecionarPagamento);
                    ordemServico.setFinalizado(Boolean.TRUE);
                    ordemServicoRepository.save(ordemServico);
                    Backup backup = new Backup();
                    backup.setAcaoBackup(SelecionarAcaoBackup.ATUALIZACAO_ORDEM_SERVICO);
                    backup.setOrdemServico(ordemServico);
                    backup.setDataEvento(LocalDateTime.now());
                    backupRepository.save(backup);
                    return new ResponseEntity<>(OK);


                }

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

    //atendimento
    public ResponseEntity<OrdemServicoDTO> ProdutoNaoRetirado(String codigo) throws Exception
    {
        try
        {
            if(ordemServicoRepository.existsBycodigo(codigo))
            {
                OrdemServico ordemServico = ordemServicoRepository.findBycodigo(codigo);
                ordemServico.setStatusOrdenServico(StatusOrdenServico.PRODUTO_NAO_ENTREGUE);
                if(clienteRepository.existsById(ordemServico.getCliente().getId()))
                {
                    Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId()).get();
                    cliente.setBloqueado(true);
                    clienteRepository.save(cliente);
                    ordemServicoRepository.save(ordemServico);
                }
            }
            return new ResponseEntity<>(OK);
        }
        catch (Exception e)
        {
            throw new Exception("erro ao deletar");
        }
    }

    public ResponseEntity<OrdemServicoDTO> DeletarOrdemServico(Long id) throws Exception
    {
        try
        {
            if(ordemServicoRepository.existsById(id))
            {
                ordemServicoRepository.deleteById(id);
            }
            return new ResponseEntity<>(OK);
        }
        catch (Exception e)
        {
            throw new Exception("erro ao deletar");
        }
    }

}
