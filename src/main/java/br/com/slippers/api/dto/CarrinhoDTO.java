package br.com.slippers.api.dto;

import java.util.List;

//DTO's : classes "cobaia", servindo para transferir dados entre os models e o usuário
public class CarrinhoDTO {

    private Long id;
	private String emailUsuario;
	private List<ChineloDTO> chinelosCarrinho;
	private double totalCarrinho;
	private int qtdItensCarrinho;

    public CarrinhoDTO(
        Long id,
        String emailUsuario,
        List<ChineloDTO> chinelosCarrinho,
        double totalCarrinho,
        int qtdItensCarrinho
        ) {
        this.id = id;
        this.emailUsuario = emailUsuario;
        this.chinelosCarrinho = chinelosCarrinho;
        this.totalCarrinho = totalCarrinho;
        this.qtdItensCarrinho = qtdItensCarrinho;
    }

    public CarrinhoDTO(){}

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

    public List<ChineloDTO> getChinelosCarrinho() {
        return this.chinelosCarrinho;
    }

    public void setChinelosCarrinho(List<ChineloDTO> chinelosCarrinho) {
        this.chinelosCarrinho = chinelosCarrinho;
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
