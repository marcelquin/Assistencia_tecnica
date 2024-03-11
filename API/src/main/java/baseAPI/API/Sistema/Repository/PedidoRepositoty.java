package baseAPI.API.Sistema.Repository;

import baseAPI.API.Sistema.Model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepositoty extends JpaRepository<Pedido,Long> {
}
