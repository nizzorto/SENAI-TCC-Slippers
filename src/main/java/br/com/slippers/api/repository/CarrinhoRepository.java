package br.com.slippers.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.slippers.api.model.Carrinho;
import br.com.slippers.api.model.Usuario;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long>{
    
    Optional<Carrinho> findByUsuario(Usuario usuario);
}

