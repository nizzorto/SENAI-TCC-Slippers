package br.com.slippers.api.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.slippers.api.dto.CartaoDTO;

@Entity
public class Cartao {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false)
	private Long id;
	
	private String titular;
	
	@Column(nullable=false)
	private String numeroCartao;
	
	@Column(nullable=false)
	private String codigoSeguranca;
	
	@Column(nullable=false)
	private Date vencimento;


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

	public CartaoDTO toDTO() {
        return new CartaoDTO(this);
    }
    
    public static List<CartaoDTO> toListDTO(List<Cartao> cartoes){
        return cartoes.stream().map(Cartao::toDTO).toList();
    }
}