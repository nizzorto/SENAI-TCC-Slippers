package br.com.slippers.api.form;
import javax.validation.constraints.NotBlank;

public class TamanhoForm {
    
    @NotBlank
    private String descricao;

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
