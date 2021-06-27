package br.com.slippers.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.slippers.api.dto.TamanhoDTO;

@Entity
public class Tamanho
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false)
	private Long id;
	
	@Column(nullable=false)
	private String descricao;

	public Tamanho(TamanhoDTO tDTO) {
		this.id = tDTO.getId();
		this.descricao = tDTO.getDescricao();
	}

	public Tamanho() {}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


}