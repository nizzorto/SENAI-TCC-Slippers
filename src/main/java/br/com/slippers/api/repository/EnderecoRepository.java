package br.com.slippers.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.slippers.api.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
    
    Optional<List<Endereco>> findByUsuariosId(Long id);
}
