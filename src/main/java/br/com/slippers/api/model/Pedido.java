package br.com.slippers.api.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.com.slippers.api.dto.PedidoDTO;

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
	
	@OneToOne
	@JoinColumn(name = "carrinho_id", referencedColumnName = "id")
	private Carrinho carrinhoPedido;

	@OneToOne
	@JoinColumn(name = "endereco_id", referencedColumnName = "id")
	private Endereco enderecoEntrega;

	@OneToOne
	@JoinColumn(name = "cartao_id", referencedColumnName = "id")
	private Cartao cartaoPagamento;
	
	@Enumerated(EnumType.STRING)
	private StatusPedido statusPedido = StatusPedido.ANALISE;


	public Pedido(Endereco endereco, Cartao cartao, Usuario usuario, Carrinho carrinho) {
		this.enderecoEntrega = endereco;
		this.cartaoPagamento = cartao;
		this.usuario = usuario;
		this.carrinhoPedido = carrinho;
		this.totalPedido = carrinho.getTotalCarrinho();
	}

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

	public double getTotalPedido() {
		return this.totalPedido;
	}

	public void setTotalPedido(double totalPedido) {
		this.totalPedido = totalPedido;
	}

	public Cartao getCartaoPagamento() {
		return this.cartaoPagamento;
	}

	public void setCartaoPagamento(Cartao cartaoPagamento) {
		this.cartaoPagamento = cartaoPagamento;
	}

	public StatusPedido getStatusPedido() {
		return this.statusPedido;
	}

	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}
	
	public PedidoDTO toDTO() {
        return new PedidoDTO(this);
    }
    
    public static List<PedidoDTO> toListDTO(List<Pedido> pedidos){
        return pedidos.stream().map(Pedido::toDTO).toList();
    }


	public Carrinho getCarrinhoPedido() {
		return this.carrinhoPedido;
	}

	public void setCarrinhoPedido(Carrinho carrinhoPedido) {
		this.carrinhoPedido = carrinhoPedido;
	}

	public Endereco getEnderecoEntrega() {
		return this.enderecoEntrega;
	}

	public void setEnderecoEntrega(Endereco enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}


}