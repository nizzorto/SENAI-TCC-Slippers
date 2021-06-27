package br.com.slippers.api.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.slippers.api.dto.CarrinhoDTO;
import br.com.slippers.api.model.Carrinho;

@Component
public class CarrinhoConverter {

    @Autowired
    ChineloConverter chineloConverter;

    //Convertendo Carrinho para CarrinhoDTO/
    public CarrinhoDTO toDTO(Carrinho carrinho){
        return new CarrinhoDTO(
            carrinho.getId(),
            carrinho.getUsuario().getEmail(),
            chineloConverter.toListChineloDTO(carrinho.getChinelosCarrinho()),
            carrinho.getTotalCarrinho(),
            carrinho.getQtdItensCarrinho()
        );
    }
}
