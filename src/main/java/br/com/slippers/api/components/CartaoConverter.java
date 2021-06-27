package br.com.slippers.api.components;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.slippers.api.dto.CartaoDTO;
import br.com.slippers.api.model.Cartao;

@Component
public class CartaoConverter {

    public CartaoDTO toDTO(Cartao cartao) {

        return new CartaoDTO(
            cartao.getId(),
            cartao.getTitular(),
            cartao.getNumeroCartao(),
            cartao.getCodigoSeguranca(),
            cartao.getVencimento()
        );
    }
    
    public List<CartaoDTO> toListDTO(List<Cartao> cartoes){
        return cartoes.stream().map(this::toDTO).toList();
    }
    
}
