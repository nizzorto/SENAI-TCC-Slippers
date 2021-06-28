package br.com.slippers.api.dto;

import java.util.List;

import br.com.slippers.api.model.Carrinho;
import br.com.slippers.api.model.CarrinhoHasChinelo;

//DTO's : classes "cobaia", servindo para transferir dados entre os models e o usuário
public class CarrinhoDTO {

    private Long id;
	private String emailUsuario;
	private List<CarrinhoHasChineloDTO> carrinhoHasChinelo;
	private double totalCarrinho;
	private int qtdItensCarrinho;

    public CarrinhoDTO(
        Long id,
        String emailUsuario,
        List<CarrinhoHasChineloDTO> carrinhoHasChinelo,
        double totalCarrinho,
        int qtdItensCarrinho
        ) {
        this.id = id;
        this.emailUsuario = emailUsuario;
        this.carrinhoHasChinelo = carrinhoHasChinelo;
        this.totalCarrinho = totalCarrinho;
        this.qtdItensCarrinho = qtdItensCarrinho;
    }

    public CarrinhoDTO(){}

    public CarrinhoDTO(Carrinho carrinho) {
        this.id = carrinho.getId();
        this.emailUsuario = carrinho.getUsuario().getEmail();
        this.totalCarrinho = carrinho.getTotalCarrinho();
        this.qtdItensCarrinho = carrinho.getQtdItensCarrinho();
        this.carrinhoHasChinelo = CarrinhoHasChinelo.toListDTO(carrinho.getChinelosCarrinho(), this);
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

    public List<CarrinhoHasChineloDTO> getChinelosCarrinhoDTO() {
        return this.carrinhoHasChinelo;
    }

    public void setChinelosCarrinhoDTO(List<CarrinhoHasChineloDTO> carrinhoHasChinelo) {
        this.carrinhoHasChinelo = carrinhoHasChinelo;
    }   

    public double getTotalCarrinho() {
        return this.totalCarrinho;
    }

    public void setTotalCarrinho(double totalCarrinho) {
        this.totalCarrinho = totalCarrinho;
    }

    public int getQtdItensCarrinho() {
        return this.qtdItensCarrinho;
    }

    public void setQtdItensCarrinho(int qtdItensCarrinho) {
        this.qtdItensCarrinho = qtdItensCarrinho;
    }

}
