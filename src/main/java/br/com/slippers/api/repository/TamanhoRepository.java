package br.com.slippers.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.slippers.api.model.Tamanho;



public interface TamanhoRepository extends JpaRepository<Tamanho, Long>{

    List<Tamanho> findByDescricao(String descricao);
}
