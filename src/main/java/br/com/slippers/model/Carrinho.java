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
import javax.persistence.OneToOne;

@Entity
public class Carrinho {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false)
	private Long id;
	
	@OneToOne(mappedBy="carrinho")
	@JoinColumn(name = "cliente_id", referencedColumnName = "id")
	private Cliente cliente;
	
	@ManyToMany
	@JoinTable(
		name = "carrinho_has_chinelo",
		joinColumns = @JoinColumn(name = "carrinho_id"),
		inverseJoinColumns = @JoinColumn(name = "chinelo_id")
	)
	private List<Chinelo> chinelosCarrinho;

	private double totalCarrinho = 0;
	
	
	public double calcularTotalCarrinho() {
			return 0;
	}
}