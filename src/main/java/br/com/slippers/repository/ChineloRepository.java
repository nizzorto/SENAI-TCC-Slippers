package br.com.slippers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.slippers.model.Chinelo;


public interface ChineloRepository extends JpaRepository<Chinelo, Long>{

}
