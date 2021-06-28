package br.com.slippers.api.dto;

import java.util.List;

import br.com.slippers.api.model.Cartao;
import br.com.slippers.api.model.Chinelo;
import br.com.slippers.api.model.Pedido;
import br.com.slippers.api.model.StatusPedido;
import br.com.slippers.api.model.Usuario;

public class PedidoDTO {

    private Long id;
    private Usuario usuario;
    private double totalPedido;
    private List<Chinelo> chinelosPedido;
    private Cartao cartaoPagamento;
    private StatusPedido statusPedido;

    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.usuario = pedido.getUsuario();
        this.totalPedido = pedido.getTotalPedido();
        this.chinelosPedido = pedido.getChinelosPedido();
        this.cartaoPagamento = pedido.getCartaoPagamento();
        this.statusPedido = pedido.getStatusPedido();
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

    public List<Chinelo> getChinelosPedido() {
        return this.chinelosPedido;
    }

    public void setChinelosPedido(List<Chinelo> chinelosPedido) {
        this.chinelosPedido = chinelosPedido;
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

    
}
