package br.com.slippers.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Pedido {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="usuario_id", nullable=false)
	private Usuario usuario;
	
	@Column(nullable=false)
	private double totalPedido = 0;
	
	@ManyToMany
	@JoinTable(
		name = "pedido_has_chinelo",
		joinColumns = @JoinColumn(name = "pedido_id"),
		inverseJoinColumns = @JoinColumn(name = "chinelo_id")
	)
	private List<Chinelo> chinelosPedido;


	@OneToOne
	@JoinColumn(name = "cartao_id", referencedColumnName = "id")
	private Cartao cartaoPagamento;
	
	@Enumerated(EnumType.STRING)
	private StatusPedido statusPedido = StatusPedido.ANALISE;
}