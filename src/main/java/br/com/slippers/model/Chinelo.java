package br.com.slippers.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Chinelo
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false)
	private Long id;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=false)
	private String descricao;
	
	@Column(nullable=false)
	private double notaFinal = 0;
	
	@Column(nullable=false)
	private int totalVendas = 0;
	
	@Column(nullable=false)
	private int totalEstrelas = 0;
	
	@ManyToMany
	@JoinTable(
		name = "chinelo_has_tamanho",
		joinColumns = @JoinColumn(name = "chinelo_id"),
		inverseJoinColumns = @JoinColumn(name = "tamanho_id")
	)
	private List<Tamanho> tamanhos;
}
