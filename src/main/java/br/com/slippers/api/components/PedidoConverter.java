package br.com.slippers.api.components;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.slippers.api.dto.PedidoDTO;
import br.com.slippers.api.model.Pedido;

@Component
public class PedidoConverter {

    public PedidoDTO toDTO(Pedido pedido) {

        return new PedidoDTO(pedido.getId(),
        pedido.getUsuario(),
        pedido.getTotalPedido(),
        pedido.getChinelosPedido(),
        pedido.getCartaoPagamento(),
        pedido.getStatusPedido()
        );
    }
    
    public List<PedidoDTO> toListDTO(List<Pedido> pedidos){
        return pedidos.stream().map(this::toDTO).toList();
    }
}
