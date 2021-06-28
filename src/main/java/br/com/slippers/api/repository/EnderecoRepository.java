package br.com.slippers.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.slippers.api.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
    
}
