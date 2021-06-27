package br.com.slippers.api.dto;

import java.sql.Date;

public class CartaoDTO {

    private Long id;
    private String titular;
    private String numeroCartao;
    private String codigoSeguranca;
    private Date vencimento;

    public CartaoDTO(
    Long id,
    String titular,
    String numeroCartao,
    String codigoSeguranca,
    Date vencimento
    ) {
        this.id = id;
        this.titular = titular;
        this.numeroCartao = numeroCartao;
        this.codigoSeguranca = codigoSeguranca;
        this.vencimento = vencimento;
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

    public String getCodigoSeguranca() {
        return this.codigoSeguranca;
    }

    public void setCodigoSeguranca(String codigoSeguranca) {
        this.codigoSeguranca = codigoSeguranca;
    }

    public Date getVencimento() {
        return this.vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    
}
