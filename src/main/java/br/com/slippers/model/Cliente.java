package br.com.slippers.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cliente {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false)
	private Long idCliente;
	
	@Column(nullable=false)
	private String nomeCompleto;
	
	@Column(unique=true)
	private String cpf;
	
	@Column(unique=true, nullable=false)
	private String email;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE)
	private List<Favorito> favoritos;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE)
	private List<NotaChineloCliente> notaChineloCliente;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE)
	private List<Pedido> pedidos;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "carrinho_id", referencedColumnName = "id")
	private Carrinho carrinho;
	
	@Column(nullable=false)
	private boolean ativo = false;
	
	@Column(nullable=false)
	private int totalCompras = 0;
	
}
