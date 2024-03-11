package baseAPI.API.Sistema.Service;

import baseAPI.API.Sistema.DTO.OrdemServicoDTO;
import baseAPI.API.Sistema.DTO.PedidoDTO;
import baseAPI.API.Sistema.DTO.ReparoDTO;
import baseAPI.API.Sistema.Model.OrdemServico;
import baseAPI.API.Sistema.Model.Pedido;
import baseAPI.API.Sistema.Model.Reparo;
import baseAPI.API.Sistema.Repository.OrdemServicoRepository;
import baseAPI.API.Sistema.Repository.PedidoRepositoty;
import baseAPI.API.Sistema.Repository.ReparoRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.OK;

@Service
public class ReparoService {

    @Autowired
    PedidoRepositoty pedidoRepositoty;
    @Autowired
    ReparoRepositoty reparoRepositoty;

    @Autowired
    OrdemServicoRepository ordemServicoRepository;

    public ResponseEntity<List<Reparo>> listarReparo() throws Exception
    {
        try
        {
            return new ResponseEntity<>(reparoRepositoty.findAll(), OK);
        }
        catch (Exception e)
        {
            throw new Exception("erro ao listar");
        }
    }

    public ResponseEntity<Reparo> BuscarReparoPorId(Long id) throws Exception
    {
        try
        {
            if(reparoRepositoty.existsById(id))
            {
                Reparo reparo = reparoRepositoty.findById(id).get();
                return new ResponseEntity<>(reparo, OK);
            }

        }
        catch (Exception e)
        {
            throw new Exception("erro ao listar");
        }
        return null;
    }

    public ResponseEntity<ReparoDTO> NovoReparo(Long idOrdemServico,ReparoDTO dto)
    {
        try{
            if(dto != null)
            {
                Reparo reparo = new Reparo(dto);
                reparoRepositoty.save(reparo);
                if(ordemServicoRepository.existsById(idOrdemServico))
                {
                    System.out.println("salvando aqui");
                    OrdemServico ordemServico = ordemServicoRepository.findById(idOrdemServico).get();
                    ordemServico.setReparo(reparo);
                    ordemServicoRepository.save(ordemServico);
                    System.out.println("Salvou aqui");
                }
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

    public ResponseEntity<ReparoDTO> NovoPedido(Long idReparo,PedidoDTO dto)
    {
        try{
            if(reparoRepositoty.existsById(idReparo))
            {
                Reparo reparo = reparoRepositoty.findById(idReparo).get();
                if(dto != null)
                {
                    Pedido pedido = new Pedido(dto);
                    pedidoRepositoty.save(pedido);
                    List<Pedido> pedidos = new ArrayList<>();
                    pedidos.add(pedido);
                    reparo.getPedidos().addAll(pedidos);
                    reparo.setValortotalReparo(reparo.CalvalortotalReparo());
                    reparoRepositoty.save(reparo);
                    return new ResponseEntity<>(CREATED);
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

    public ResponseEntity<ReparoDTO> DeletarReparo(Long id) throws Exception
    {
        try
        {
            if(reparoRepositoty.existsById(id))
            {
                reparoRepositoty.deleteById(id);
            }
            return new ResponseEntity<>(OK);
        }
        catch (Exception e)
        {
            throw new Exception("erro ao deletar");
        }
    }
}
