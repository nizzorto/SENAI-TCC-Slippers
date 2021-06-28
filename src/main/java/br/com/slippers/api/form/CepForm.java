package br.com.slippers.api.form;
import javax.validation.constraints.NotBlank;

public class CepForm {
    
    @NotBlank
    private String cep;

    @NotBlank
    private String numero;

    @NotBlank
    private String complemento;


    public String getCep() {
        return this.cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    
}
