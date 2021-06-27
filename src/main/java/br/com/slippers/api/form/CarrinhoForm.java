package br.com.slippers.api.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import br.com.slippers.api.model.Chinelo;

public class CarrinhoForm {
    
    @NotEmpty
    private List<Chinelo> chinelosCarrinho;

    public List<Chinelo> getChinelosCarrinho() {
        return this.chinelosCarrinho;
    }

    public void setChinelosCarrinho(List<Chinelo> chinelosCarrinho) {
        this.chinelosCarrinho = chinelosCarrinho;
    }

}
