package br.com.slippers.api.dto;

import java.util.List;

import br.com.slippers.api.model.Cartao;
import br.com.slippers.api.model.Chinelo;
import br.com.slippers.api.model.StatusPedido;
import br.com.slippers.api.model.Usuario;

public class PedidoDTO {

    private Long id;
    private Usuario usuario;
    private double totalPedido;
    private List<Chinelo> chinelosPedido;
    private Cartao cartaoPagamento;
    private StatusPedido statusPedido;

    public PedidoDTO(Long id,
    Usuario usuario,
    double totalPedido,
    List<Chinelo> chinelosPedido,
    Cartao cartaoPagamento,
    StatusPedido statusPedido) {
        this.id = id;
        this.usuario = usuario;
        this.totalPedido = totalPedido;
        this.chinelosPedido = chinelosPedido;
        this.cartaoPagamento = cartaoPagamento;
        this.statusPedido = statusPedido;
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
