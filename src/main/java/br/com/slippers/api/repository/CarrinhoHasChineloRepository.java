package br.com.slippers.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.slippers.api.model.CarrinhoHasChinelo;

public interface CarrinhoHasChineloRepository extends JpaRepository<CarrinhoHasChinelo, Long>{

    void deleteByChineloId(Long idChinelo);
}
