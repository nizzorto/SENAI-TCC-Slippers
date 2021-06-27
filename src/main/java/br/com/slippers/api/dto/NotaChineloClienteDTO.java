package br.com.slippers.api.dto;

public class NotaChineloClienteDTO {
    
    private UsuarioDTO usuario;
    private ChineloDTO chinelo;
    private double nota;



    public NotaChineloClienteDTO(UsuarioDTO usuario, ChineloDTO chinelo, double nota) {
        this.usuario = usuario;
        this.chinelo = chinelo;
        this.nota = nota;
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
