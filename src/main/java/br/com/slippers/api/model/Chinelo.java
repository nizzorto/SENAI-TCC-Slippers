package br.com.slippers.api.model;

import java.time.LocalDateTime;
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
	
	//Média das notas que os clientes deram ao chinelo
	@Column(nullable=false)
	private double mediaNota = 0;
	
	@Column(nullable=false)
	private int totalVendas = 0;
	
	//Total de estrelas dadas pelos clientes que compraram o chinelo
	@Column(nullable=false)
	private int totalEstrelas = 0;

	/**
	 * Data em que o chinelo foi inserido no banco de dados. É automaticamente definido
	 * na hora em que é criado. Necessário para ordenar chinelos por data
	 */
	@Column(nullable=false)
	private LocalDateTime dataCriacao = LocalDateTime.now();
	

	//Lista de tamanhos disponíveis do chinelo
	@ManyToMany
	@JoinTable(
		name = "chinelo_has_tamanho",
		joinColumns = @JoinColumn(name = "chinelo_id"),
		inverseJoinColumns = @JoinColumn(name = "tamanho_id")
	)
	private List<Tamanho> tamanhos;

	public Chinelo( Long id,
	String nome,
	String descricao,
	double valor,
	String urlImagem,
	double mediaNota,
	int totalVendas,
	int totalEstrelas,
	LocalDateTime dataCriacao,
	List<Tamanho> tamanhos
	) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
		this.urlImagem = urlImagem;
		this.mediaNota = mediaNota;
		this.totalVendas = totalVendas;
		this.totalEstrelas = totalEstrelas;
		this.dataCriacao = dataCriacao;
		this.tamanhos = tamanhos;
	}

	public Chinelo(){}

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

	public double getMediaNota() {
		return mediaNota;
	}

	public void setMediaNota(double mediaNota) {
		this.mediaNota = mediaNota;
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
	
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	
	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
}
