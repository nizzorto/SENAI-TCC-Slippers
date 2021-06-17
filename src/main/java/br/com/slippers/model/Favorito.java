package br.com.slippers.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Favorito
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false)
	private Long idFavorito;
	
	@ManyToOne
	@JoinColumn(name = "chinelo_id")
	private Chinelo chineloFavoritado;
	
	@ManyToOne
	@JoinColumn(name="cliente_id", nullable=false)
	private Cliente clienteFavoritou;
}