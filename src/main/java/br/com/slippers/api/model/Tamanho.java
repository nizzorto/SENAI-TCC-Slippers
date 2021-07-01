package br.com.slippers.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import br.com.slippers.api.dto.TamanhoDTO;
import br.com.slippers.api.form.TamanhoForm;

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

	@ManyToMany(mappedBy = "tamanhos", cascade = CascadeType.MERGE)
	private List<Chinelo> chinelos;

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

    public static List<TamanhoDTO> toListDTO(List<Tamanho> tamanhos) {
		return tamanhos.stream().map(TamanhoDTO::new).toList();
    }

    public static Tamanho toTamanho(TamanhoForm tForm) {
        Tamanho tamanho = new Tamanho();
		tamanho.descricao = tForm.getDescricao();
		return tamanho;
    }
}