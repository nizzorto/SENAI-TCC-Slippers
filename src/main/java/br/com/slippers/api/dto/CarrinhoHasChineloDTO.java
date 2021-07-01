package br.com.slippers.api.dto;

import br.com.slippers.api.model.CarrinhoHasChinelo;

public class CarrinhoHasChineloDTO {

    private ChineloDTO chinelo;
    private int quantidade;
    private double valor;

    public CarrinhoHasChineloDTO(CarrinhoHasChinelo cHasChinelo){
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
