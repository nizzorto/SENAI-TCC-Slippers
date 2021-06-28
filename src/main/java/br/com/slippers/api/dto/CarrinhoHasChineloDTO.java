package br.com.slippers.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.slippers.api.model.CarrinhoHasChinelo;

public class CarrinhoHasChineloDTO {

    @JsonIgnore
    private CarrinhoDTO carrinho;

    private ChineloDTO chinelo;
    private int quantidade;
    private double valor;

    public CarrinhoHasChineloDTO(CarrinhoHasChinelo cHasChinelo){
        this.carrinho = cHasChinelo.getCarrinho().toDTO();
        this.chinelo = cHasChinelo.getChinelo().toDTO();
        this.quantidade = cHasChinelo.getQuantidade();
        this.valor = cHasChinelo.getValor();
    }

    public CarrinhoHasChineloDTO() {}

    public ChineloDTO getChinelo() {
        return this.chinelo;
    }

    public void setChinelo(ChineloDTO chinelo) {
        this.chinelo = chinelo;
    }

    public CarrinhoDTO getCarrinho() {
        return this.carrinho;
    }

    public void setCarrinho(CarrinhoDTO carrinho) {
        this.carrinho = carrinho;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }


    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }


}
