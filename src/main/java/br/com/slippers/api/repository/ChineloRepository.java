package br.com.slippers.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.slippers.api.model.Chinelo;


public interface ChineloRepository extends JpaRepository<Chinelo, Long>{

    @Query(value = "SELECT c FROM Chinelo c WHERE c.nome LIKE :nome%")
    Page<Chinelo> findByNome(@Param("nome") String nome, Pageable pag);
}
