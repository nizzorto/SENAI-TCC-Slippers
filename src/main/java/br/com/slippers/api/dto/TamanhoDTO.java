package br.com.slippers.api.dto;

import br.com.slippers.api.model.Tamanho;

public class TamanhoDTO {

    private Long id;
    private String descricao;


    public TamanhoDTO(Tamanho tamanho) {
        this.id = tamanho.getId();
        this.descricao = tamanho.getDescricao();
    }
    
    public TamanhoDTO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
