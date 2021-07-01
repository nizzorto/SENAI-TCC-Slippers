package br.com.slippers.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.slippers.api.model.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long>{
    
    Perfil findByDescricao(String descricao);
}
