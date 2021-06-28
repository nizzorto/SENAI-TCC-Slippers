package br.com.slippers.api.dto;

import java.sql.Date;

import br.com.slippers.api.model.Cartao;

public class CartaoDTO {

    private Long id;
    private String titular;
    private String numeroCartao;
    private Date vencimento;

    public CartaoDTO(Cartao cartao) {
        this.id = cartao.getId();
        this.titular = cartao.getTitular();
        this.numeroCartao = cartao.getNumeroCartao().substring(0, 3).concat("*******");
        this.vencimento = cartao.getVencimento();
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitular() {
        return this.titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getNumeroCartao() {
        return this.numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }
    public Date getVencimento() {
        return this.vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    
}
