package br.com.slippers.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cliente extends Pessoa{
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false)
	private Long id;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE)
	private List<Favorito> favoritos;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE)
	private List<NotaChineloCliente> notaChineloCliente;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE)
	private List<Pedido> pedidos;
	
	@ManyToMany
	@JoinTable(
			name = "cliente_has_cartao",
			joinColumns = @JoinColumn(name = "cliente_id"),
			inverseJoinColumns = @JoinColumn(name = "cartao_id")
		)
	private List<Cartao> Cartoes;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "carrinho_id", referencedColumnName = "id")
	private Carrinho carrinho;
	
	@Column(nullable=false)
	private boolean ativo = false;
	
	@Column(nullable=false)
	private int totalCompras = 0;
	
}
