package br.com.slippers.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.slippers.api.model.NotaChineloCliente;

public interface NotaChineloClienteRepository extends JpaRepository<NotaChineloCliente, Long> {
    
}
