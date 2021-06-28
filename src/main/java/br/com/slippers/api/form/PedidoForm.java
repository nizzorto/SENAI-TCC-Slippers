package br.com.slippers.api.form;

import javax.validation.constraints.NotBlank;

public class PedidoForm {
    
    @NotBlank
    private String idEndereco;
    
    @NotBlank
    private String idCartao;


    public String getIdEndereco() {
        return this.idEndereco;
    }

    public void setIdEndereco(String idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getIdCartao() {
        return this.idCartao;
    }

    public void setIdCartao(String idCartao) {
        this.idCartao = idCartao;
    }
}
