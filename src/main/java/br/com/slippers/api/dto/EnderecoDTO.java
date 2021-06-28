package br.com.slippers.api.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.slippers.api.model.Endereco;

public class EnderecoDTO {
    private Long id;
    private List<String> emailUsuarios;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
    private String numero;
    private String complemento;


    public EnderecoDTO(Endereco endereco) {
        this.id = endereco.getId();
        this.logradouro = endereco.getLogradouro();
        this.bairro = endereco.getBairro();
        this.cidade = endereco.getCidade();
        this.uf = endereco.getUf();
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();

        var it = endereco.getUsuarios().iterator();
        List<String> emails = new ArrayList<String>();
        while(it.hasNext()){
            String email;
            email = it.next().getEmail();
            emails.add(email);
        }
        this.emailUsuarios = emails;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<String> getEmailUsuarios() {
        return this.emailUsuarios;
    }

    public void setEmailUsuarios(List<String> emailUsuarios) {
        this.emailUsuarios = emailUsuarios;
    }


    public String getLogradouro() {
        return this.logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return this.uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
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
