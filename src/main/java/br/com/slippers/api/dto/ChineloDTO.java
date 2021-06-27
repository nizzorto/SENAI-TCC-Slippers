package br.com.slippers.api.dto;

import java.time.LocalDateTime;
import java.util.List;

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


    public ChineloDTO (Long id,
        String nome,
        String descricao,
        double valor,
        String urlImagem,
        double mediaNota,
        int totalVendas,
        int totalEstrelas,
        LocalDateTime localDateTime,
        List<TamanhoDTO> tamanhosDTO
        ) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.urlImagem = urlImagem;
        this.mediaNota = mediaNota;
        this.totalVendas = totalVendas;
        this.totalEstrelas = totalEstrelas;
        this.dataCriacao = localDateTime;
        this.tamanhosDTO = tamanhosDTO;
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
