package br.com.slippers.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pagamento {
		
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false)
	private Long idPagamento;
	
	@Column(nullable=false)
	private String titular;
	
	@Column(nullable=false)
	private String numeroCartao;
	
	@Column(nullable=false)
	private String codigoSeguranca;
	
	@Column(nullable=false)
	private Date vencimento;
}