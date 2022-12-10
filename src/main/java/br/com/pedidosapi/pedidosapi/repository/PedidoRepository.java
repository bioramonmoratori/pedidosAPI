package br.com.pedidosapi.pedidosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.pedidosapi.pedidosapi.enums.Status;
import br.com.pedidosapi.pedidosapi.models.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
    @Transactional
    @Modifying(clearAutomatically = true)
	@Query("update Pedido p set p.status = :status where p = :pedido")
	void alteraStatus(Status status, Pedido pedido);

}
