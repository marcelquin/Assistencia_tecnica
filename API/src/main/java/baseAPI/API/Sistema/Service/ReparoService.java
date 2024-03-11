package baseAPI.API.Sistema.Service;

import baseAPI.API.Sistema.DTO.OrdemServicoDTO;
import baseAPI.API.Sistema.DTO.PedidoDTO;
import baseAPI.API.Sistema.DTO.ReparoDTO;
import baseAPI.API.Sistema.Exceptions.EntityNotFoundException;
import baseAPI.API.Sistema.Exceptions.NullargumentsException;
import baseAPI.API.Sistema.Model.OrdemServico;
import baseAPI.API.Sistema.Model.Pedido;
import baseAPI.API.Sistema.Model.Reparo;
import baseAPI.API.Sistema.Repository.OrdemServicoRepository;
import baseAPI.API.Sistema.Repository.PedidoRepositoty;
import baseAPI.API.Sistema.Repository.ReparoRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
            e.getMessage();
        }
        return null;
    }

    public ResponseEntity<ReparoDTO> BuscarReparoPorId(Long id) throws Exception
    {
        try
        {
            if(id != null)
            {
                if(reparoRepositoty.existsById(id))
                {
                    Reparo reparo = reparoRepositoty.findById(id).get();
                    ReparoDTO response = new ReparoDTO(reparo.getDescrisao(),reparo.getValortotalReparo());
                    return new ResponseEntity<>(response, OK);
                }
                else{throw  new EntityNotFoundException();}
            }
            else{throw new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    public ResponseEntity<ReparoDTO> NovoReparo(Long idOrdemServico,ReparoDTO dto)
    {
        try{
            if(idOrdemServico != null && dto != null)
            {
                if(ordemServicoRepository.existsById(idOrdemServico))
                {
                    Reparo reparo = new Reparo(dto);
                    OrdemServico ordemServico = ordemServicoRepository.findById(idOrdemServico).get();
                    ordemServico.setReparo(reparo);
                    ordemServicoRepository.save(ordemServico);
                    ReparoDTO response = new ReparoDTO(reparo.getDescrisao(),reparo.getValortotalReparo());
                    return new ResponseEntity<>(response, CREATED);
                }
                else{throw  new EntityNotFoundException();}
            }
            else{throw new NullargumentsException();}
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    public ResponseEntity<ReparoDTO> NovoPedido(Long idReparo,PedidoDTO dto)
    {
        try{
            if(idReparo != null && dto != null)
            {
                if(reparoRepositoty.existsById(idReparo))
                {
                    Reparo reparo = reparoRepositoty.findById(idReparo).get();
                    Pedido pedido = new Pedido(dto);
                    pedidoRepositoty.save(pedido);
                    List<Pedido> pedidos = new ArrayList<>();
                    pedidos.add(pedido);
                    reparo.getPedidos().addAll(pedidos);
                    reparo.setValortotalReparo(reparo.CalvalortotalReparo());
                    reparoRepositoty.save(reparo);
                    ReparoDTO response = new ReparoDTO(reparo.getDescrisao(),reparo.getValortotalReparo());
                    return new ResponseEntity<>(response, OK);
                }
                else{throw  new EntityNotFoundException();}
            }
            else{throw  new NullargumentsException();}
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    public ResponseEntity<ReparoDTO> DeletarReparo(Long id) throws Exception
    {
        try
        {
            if(id != null)
            {
                if(reparoRepositoty.existsById(id))
                {
                    reparoRepositoty.deleteById(id);
                    return new ResponseEntity<>(OK);
                }
                else{throw  new EntityNotFoundException();}
            }
            else{throw  new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }
}
