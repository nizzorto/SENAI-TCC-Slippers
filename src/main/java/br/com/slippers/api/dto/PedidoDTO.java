package br.com.slippers.api.dto;

import br.com.slippers.api.model.Pedido;
import br.com.slippers.api.model.StatusPedido;
import net.minidev.json.annotate.JsonIgnore;

public class PedidoDTO {

    private Long id;
    @JsonIgnore
    private String emailUsuario;
    private double totalPedido;
    private CarrinhoDTO carrinhoPedido;
    private CartaoDTO cartaoPagamento;
    private EnderecoDTO enderecoEntrega;
    private StatusPedido statusPedido;

    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.emailUsuario = pedido.getUsuario().getEmail();
        this.totalPedido = pedido.getTotalPedido();
        this.carrinhoPedido = pedido.getCarrinhoPedido().toDTO();
        this.cartaoPagamento = pedido.getCartaoPagamento().toDTO();
        this.enderecoEntrega = pedido.getEnderecoEntrega().toDTO();
        this.statusPedido = pedido.getStatusPedido();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotalPedido() {
        return this.totalPedido;
    }

    public void setTotalPedido(double totalPedido) {
        this.totalPedido = totalPedido;
    }

    public StatusPedido getStatusPedido() {
        return this.statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public String getEmailUsuario() {
        return this.emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public CarrinhoDTO getCarrinhoPedido() {
        return this.carrinhoPedido;
    }

    public void setCarrinhoPedido(CarrinhoDTO carrinhoPedido) {
        this.carrinhoPedido = carrinhoPedido;
    }

    public CartaoDTO getCartaoPagamento() {
        return this.cartaoPagamento;
    }

    public void setCartaoPagamento(CartaoDTO cartaoPagamento) {
        this.cartaoPagamento = cartaoPagamento;
    }


    public EnderecoDTO getEnderecoEntrega() {
        return this.enderecoEntrega;
    }

    public void setEnderecoEntrega(EnderecoDTO enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    
}
