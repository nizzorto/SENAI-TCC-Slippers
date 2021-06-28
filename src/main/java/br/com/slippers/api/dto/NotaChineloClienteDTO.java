package br.com.slippers.api.dto;

import br.com.slippers.api.model.NotaChineloCliente;

public class NotaChineloClienteDTO {
    
    private Long id;
    private UsuarioDTO usuario;
    private ChineloDTO chinelo;
    private double nota;




    public NotaChineloClienteDTO(NotaChineloCliente notaChineloCliente) {
        this.usuario = notaChineloCliente.getUsuario().toDTO();
        this.chinelo = notaChineloCliente.getChinelo().toDTO();
        this.nota = notaChineloCliente.getNota();
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public UsuarioDTO getUsuario() {
        return this.usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public ChineloDTO getChinelo() {
        return this.chinelo;
    }

    public void setChinelo(ChineloDTO chinelo) {
        this.chinelo = chinelo;
    }

    public double getNota() {
        return this.nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
    
}
