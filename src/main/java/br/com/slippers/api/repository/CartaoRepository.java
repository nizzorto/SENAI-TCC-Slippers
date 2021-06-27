package br.com.slippers.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.slippers.api.model.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, Long>{
    
}
