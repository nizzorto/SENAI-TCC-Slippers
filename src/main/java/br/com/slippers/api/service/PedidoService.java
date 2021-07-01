package br.com.slippers.api.service;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.slippers.api.dto.PedidoDTO;
import br.com.slippers.api.form.PedidoForm;
import br.com.slippers.api.model.Carrinho;
import br.com.slippers.api.model.Cartao;
import br.com.slippers.api.model.Endereco;
import br.com.slippers.api.model.Pedido;
import br.com.slippers.api.model.Usuario;
import br.com.slippers.api.repository.CarrinhoRepository;
import br.com.slippers.api.repository.CartaoRepository;
import br.com.slippers.api.repository.EnderecoRepository;
import br.com.slippers.api.repository.PedidoRepository;
import javassist.NotFoundException;

@Service
public class PedidoService {
    @Autowired
    PedidoRepository pedidoR;
    
    @Autowired
    CartaoRepository cartaoR;

    @Autowired
    EnderecoRepository enderecoR;

    @Autowired
    CarrinhoRepository carrinhoR;

    public ResponseEntity<PedidoDTO> newPedido(PedidoForm pForm, UriComponentsBuilder builder)
    throws NotFoundException {
        
        Optional<Endereco> enderecoPedido = enderecoR.findById(Long.parseLong(pForm.getIdEndereco()));
        Optional<Cartao> cartaoPedido = cartaoR.findById(Long.parseLong(pForm.getIdCartao()));
        
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<Carrinho> carrinho = carrinhoR.findByUsuarioEmail(usuario.getEmail());
         if(carrinho.isEmpty()) {
             throw new NotFoundException("Carrinho não encontrado!");
         }
        Pedido pedido = new Pedido(enderecoPedido.get(), cartaoPedido.get(), usuario, carrinho.get());
        pedidoR.save(pedido);
        /** 
         * {id} caminho variável, irá receber o id do tamanho criado
         * buildAndExpand irá construir a url e colocar a id do tamanho criado na variável
         */
        URI uri = builder.path("/{id}").buildAndExpand(pedido.getId()).toUri();

        //irá retornar o código HTTP 201(created), a localização do item criado e o corpo do item criado
        return ResponseEntity.created(uri).body(pedido.toDTO());
     }
}
