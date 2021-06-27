package br.com.slippers.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.slippers.api.model.Carrinho;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long>{
    
    @Query(value = "SELECT car FROM Carrinho car WHERE car.usuario.id = :p")
    Optional<Carrinho> findByUsuario(@Param("p") Long id);
}

