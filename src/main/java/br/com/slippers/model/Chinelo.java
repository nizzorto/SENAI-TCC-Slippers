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

@Entity
public class Chinelo
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false)
	private Long id;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=false)
	private String descricao;
	
	@Column(nullable = false)
	private double valor;
	
	@Column(nullable = true)
	private String urlImagem;
	
	@Column(nullable=false)
	private double notaFinal = 0;
	
	@Column(nullable=false)
	private int totalVendas = 0;
	
	@Column(nullable=false)
	private int totalEstrelas = 0;
	
	@ManyToMany
	@JoinTable(
		name = "chinelo_has_tamanho",
		joinColumns = @JoinColumn(name = "chinelo_id"),
		inverseJoinColumns = @JoinColumn(name = "tamanho_id")
	)
	private List<Tamanho> tamanhos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getUrlImagem() {
		return urlImagem;
	}

	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}

	public double getNotaFinal() {
		return notaFinal;
	}

	public void setNotaFinal(double notaFinal) {
		this.notaFinal = notaFinal;
	}

	public int getTotalVendas() {
		return totalVendas;
	}

	public void setTotalVendas(int totalVendas) {
		this.totalVendas = totalVendas;
	}

	public int getTotalEstrelas() {
		return totalEstrelas;
	}

	public void setTotalEstrelas(int totalEstrelas) {
		this.totalEstrelas = totalEstrelas;
	}

	public List<Tamanho> getTamanhos() {
		return tamanhos;
	}

	public void setTamanhos(List<Tamanho> tamanhos) {
		this.tamanhos = tamanhos;
	}
	
	
}
