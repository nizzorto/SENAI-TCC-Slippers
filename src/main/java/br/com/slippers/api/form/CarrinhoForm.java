package br.com.slippers.api.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class CarrinhoForm {
    
    @Min(value = 1, message = "Quantidade deve ser maior que 0!")
    @Max(value = 100, message = "Quantidade deve ser menor que 100!")
    private Long idChinelo;

    @Min(value = 1, message = "Quantidade deve ser maior que 0!")
    @Max(value = 100, message = "Quantidade deve ser menor que 100!")
    private int quantidade;

    public Long getIdChinelo() {
        return this.idChinelo;
    }

    public void setIdChinelo(Long idChinelo) {
        this.idChinelo = idChinelo;
    }
    

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
