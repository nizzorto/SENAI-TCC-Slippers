package br.com.slippers.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Carrinho {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false, unique = true)
	private Usuario usuario;
	
	@ManyToMany
	@JoinTable(
		name = "carrinho_has_chinelo",
		joinColumns = @JoinColumn(name = "carrinho_id"),
		inverseJoinColumns = @JoinColumn(name = "chinelo_id")
	)
	private List<Chinelo> chinelosCarrinho;

	private double totalCarrinho = 0;
	
	private int qtdItensCarrinho = 0;


	// G e t t e r s   e   S e t t e r s

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Chinelo> getChinelosCarrinho() {
		return this.chinelosCarrinho;
	}

	public void setChinelosCarrinho(List<Chinelo> chinelosCarrinho) {
		this.chinelosCarrinho = chinelosCarrinho;
	}

	public double getTotalCarrinho() {
		return this.totalCarrinho;
	}

	public void setTotalCarrinho(double totalCarrinho) {
		this.totalCarrinho = totalCarrinho;
	}

	public int getQtdItensCarrinho() {
		return this.qtdItensCarrinho;
	}

	public void setQtdItensCarrinho(int qtdItensCarrinho) {
		this.qtdItensCarrinho = qtdItensCarrinho;
	}
	
	public double calcularTotalCarrinho() {
			return 0;
	}
}