package br.com.slippers.api.dto;

import java.util.List;

import br.com.slippers.api.model.Carrinho;
import br.com.slippers.api.model.CarrinhoHasChinelo;

//DTO's : classes "cobaia", servindo para transferir dados entre os models e o usuário
public class CarrinhoDTO {

    private Long id;
	private String emailUsuario;
    private double valorTotalCarrinho;
	private int quantidadeItensCarrinho;
	private List<CarrinhoHasChineloDTO> chinelosCarrinho;


    public CarrinhoDTO(
        Long id,
        String emailUsuario,
        List<CarrinhoHasChineloDTO> carrinhoHasChinelo,
        double totalCarrinho,
        int qtdItensCarrinho
        ) {
        this.id = id;
        this.emailUsuario = emailUsuario;
        this.chinelosCarrinho = carrinhoHasChinelo;
        this.valorTotalCarrinho = totalCarrinho;
        this.quantidadeItensCarrinho = qtdItensCarrinho;
    }

    public CarrinhoDTO(){}

    public CarrinhoDTO(Carrinho carrinho) {
        this.id = carrinho.getId();
        this.emailUsuario = carrinho.getUsuario().getEmail();
        this.valorTotalCarrinho = carrinho.getTotalCarrinho();
        this.quantidadeItensCarrinho = carrinho.getQtdItensCarrinho();
        this.chinelosCarrinho = CarrinhoHasChinelo.toListDTO(carrinho.getChinelosCarrinho(), this);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailUsuario() {
        return this.emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public double getValorTotalCarrinho() {
        return this.valorTotalCarrinho;
    }

    public void setValorTotalCarrinho(double valorTotalCarrinho) {
        this.valorTotalCarrinho = valorTotalCarrinho;
    }

    public int getQuantidadeItensCarrinho() {
        return this.quantidadeItensCarrinho;
    }

    public void setQuantidadeItensCarrinho(int quantidadeItensCarrinho) {
        this.quantidadeItensCarrinho = quantidadeItensCarrinho;
    }

    public List<CarrinhoHasChineloDTO> getChinelosCarrinho() {
        return this.chinelosCarrinho;
    }

    public void setChinelosCarrinho(List<CarrinhoHasChineloDTO> carrinhoHasChinelo) {
        this.chinelosCarrinho = carrinhoHasChinelo;
    }
   
}
