package br.com.slippers.api.model;

import java.rmi.AlreadyBoundException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.slippers.api.dto.CarrinhoDTO;

@Entity
public class Carrinho {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false)
	private Long id;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false, unique = true)
	private Usuario usuario;
	
	@OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL)
	private List<CarrinhoHasChinelo> carrinhoHasChinelo = new ArrayList<CarrinhoHasChinelo>();

	private double totalCarrinho = 0;
	
	private int qtdItensCarrinho = 0;

	//No momento da criação do usuário, iremos pegar este usuário e salvar o id dele no banco de dados,
	//Juntamente com um novo carrinho criado
	public Carrinho(Usuario usuario) {
		this.usuario = usuario;
	}

	public Carrinho(){}
	// G e t t e r s   e   S e t t e r s

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<CarrinhoHasChinelo> getChinelosCarrinho() {
		return this.carrinhoHasChinelo;
	}

	public void setChinelosCarrinho(List<CarrinhoHasChinelo> carrinhoHasChinelo) {
		this.carrinhoHasChinelo = carrinhoHasChinelo;
	}

	public double getTotalCarrinho() {
		return this.totalCarrinho;
	}

	public void setTotalCarrinho(double totalCarrinho) {
		this.totalCarrinho = totalCarrinho;
	}

	public int getQtdItensCarrinho() {
		return this.qtdItensCarrinho;
	}

	public void setQtdItensCarrinho(int qtdItensCarrinho) {
		this.qtdItensCarrinho = qtdItensCarrinho;
	}

	public void addChinelo(Chinelo chinelo, int quantidade) throws AlreadyBoundException {
		var it = this.carrinhoHasChinelo.iterator();
		while(it.hasNext()){
			var chinelosCarrinho = it.next();
			if(chinelosCarrinho.getChinelo().getId().equals(chinelo.getId())){
				throw new AlreadyBoundException("Chinelo já inserido! Para colocar mais, altere" +
				" sua quantidade em: api/carrinho/alterarQtdChineloCarrinho");
			}
		}
		this.carrinhoHasChinelo.add(new CarrinhoHasChinelo(this, chinelo, quantidade));
		this.calcularCarrinho();
	}

	public void deleteChinelo(String idChineloDelete) {
		var it = this.carrinhoHasChinelo.iterator();
		while(it.hasNext()){
			var chinelosCarrinho = it.next();
			if(chinelosCarrinho.getChinelo().getId().equals(Long.parseLong(idChineloDelete))){
				it.remove();
				this.calcularCarrinho();
			}
		}
	}

	public void alterarQtdChineloCarrinho(Long idChineloAlt, int quantidade) {
		var it = this.carrinhoHasChinelo.iterator();
		while(it.hasNext()){
			var chinelosCarrinho = it.next();
			if(chinelosCarrinho.getChinelo().getId().equals(idChineloAlt)){
				chinelosCarrinho.setQuantidade(quantidade);
				this.calcularCarrinho();
			}
		}
	}

	public void calcularCarrinho() {
		this.totalCarrinho = 0;
		this.qtdItensCarrinho = 0;
		for (CarrinhoHasChinelo cHasChinelo : this.carrinhoHasChinelo) {
			this.totalCarrinho += cHasChinelo.getValor();
			this.qtdItensCarrinho += cHasChinelo.getQuantidade();
		}
	}

	public CarrinhoDTO toDTO() {
		return new CarrinhoDTO(this);
	}
}