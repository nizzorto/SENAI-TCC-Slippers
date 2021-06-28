package br.com.slippers.api.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.com.slippers.api.model.Chinelo;
import br.com.slippers.api.model.Tamanho;

//DTO's : classes "cobaia", servindo para transferir dados entre os models e o usuário
public class ChineloDTO {
    
	private Long id;
	private String nome;	
	private String descricao;	
	private double valor;	
	private String urlImagem;	
	private double mediaNota;	
	private int totalVendas;;	
	private int totalEstrelas;
	private LocalDateTime dataCriacao;
	private List<TamanhoDTO> tamanhosDTO;


    public ChineloDTO (Chinelo chinelo) {
        this.id = chinelo.getId();
        this.nome = chinelo.getNome();
        this.descricao = chinelo.getDescricao();
        this.valor = chinelo.getValor();
        this.urlImagem = chinelo.getUrlImagem();
        this.mediaNota = chinelo.getMediaNota();
        this.totalVendas = chinelo.getTotalVendas();
        this.totalEstrelas = chinelo.getTotalEstrelas();
        this.dataCriacao = chinelo.getDataCriacao();
        this.tamanhosDTO = Tamanho.toListDTO(chinelo.getTamanhos());
    }    

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getUrlImagem() {
        return this.urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public double getMediaNota() {
        return this.mediaNota;
    }

    public void setMediaNota(double mediaNota) {
        this.mediaNota = mediaNota;
    }

    public int getTotalVendas() {
        return this.totalVendas;
    }

    public void setTotalVendas(int totalVendas) {
        this.totalVendas = totalVendas;
    }

    public int getTotalEstrelas() {
        return this.totalEstrelas;
    }

    public void setTotalEstrelas(int totalEstrelas) {
        this.totalEstrelas = totalEstrelas;
    }

    public LocalDateTime getDataCriacao() {
        return this.dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<TamanhoDTO> getTamanhosDTO() {
        return this.tamanhosDTO;
    }

    public void setTamanhosDTO(List<TamanhoDTO> tamanhos) {
        this.tamanhosDTO = tamanhos;
    }
}
