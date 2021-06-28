package br.com.slippers.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.slippers.api.dto.CarrinhoDTO;
import br.com.slippers.api.dto.CarrinhoHasChineloDTO;

@Entity
public class CarrinhoHasChinelo {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "chinelo_id")
    private Chinelo chinelo;
    
    @ManyToOne
    @JoinColumn(name = "carrinho_id")
    private Carrinho carrinho;

    private int quantidade;

    private double valor = 1;

    public CarrinhoHasChinelo(Carrinho carrinho, Chinelo chinelo, int quantidade) {
        this.carrinho = carrinho;
        this.chinelo = chinelo;
        this.quantidade = quantidade;
        this.valor = chinelo.getValor() * quantidade;
    }

    public CarrinhoHasChinelo() {}

    public Chinelo getChinelo() {
        return this.chinelo;
    }

    public void setChinelo(Chinelo chinelo) {
        this.chinelo = chinelo;
    }

    public Carrinho getCarrinho() {
        return this.carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        this.valor *= quantidade;
    }

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public static List<CarrinhoHasChineloDTO> toListDTO(List<CarrinhoHasChinelo> cHasChinelos, CarrinhoDTO carrinhoDTO) {
        List<CarrinhoHasChineloDTO> cHasChineloDTOs = new ArrayList<CarrinhoHasChineloDTO>();
        for (CarrinhoHasChinelo carrinhoHasChinelo : cHasChinelos) {
            CarrinhoHasChineloDTO cHasChineloDTO = new CarrinhoHasChineloDTO();
            cHasChineloDTO.setCarrinho(carrinhoDTO);
            cHasChineloDTO.setChinelo(carrinhoHasChinelo.getChinelo().toDTO());
            cHasChineloDTO.setQuantidade(carrinhoHasChinelo.getQuantidade());
            cHasChineloDTO.setValor(carrinhoHasChinelo.getValor());
            cHasChineloDTOs.add(cHasChineloDTO);
        }
        return cHasChineloDTOs;
    }
}
