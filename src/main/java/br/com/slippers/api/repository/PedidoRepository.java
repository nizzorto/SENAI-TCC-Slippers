package br.com.slippers.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.slippers.api.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
    
}
