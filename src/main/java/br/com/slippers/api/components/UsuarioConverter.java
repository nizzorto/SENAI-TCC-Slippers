package br.com.slippers.api.components;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.slippers.api.dto.UsuarioDTO;
import br.com.slippers.api.form.UsuarioForm;
import br.com.slippers.api.model.Usuario;

@Component
public class UsuarioConverter {

    @Autowired
    NotaChineloClienteConverter nccConverter;

    @Autowired
    PedidoConverter pedidoConverter;

    @Autowired
    CartaoConverter cartaoConverter;

    @Autowired
    CarrinhoConverter carrinhoConverter;


    public UsuarioDTO toDTO(Usuario usuario) {
        return new UsuarioDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getTotalCompras(),
            nccConverter.toListDTO(usuario.getNotaChineloCliente()),
            pedidoConverter.toListDTO(usuario.getPedidos()),
            cartaoConverter.toListDTO(usuario.getCartoes()),
            carrinhoConverter.toDTO(usuario.getCarrinho())
        );
    }
    
    public Page<UsuarioDTO> toPageUsuarioDTO(Page<Usuario> usuarios){
        return usuarios.map(this::toDTO);
    }

    public Usuario toUsuario(@Valid UsuarioForm uForm, Usuario usuario) {
        usuario.setNome(uForm.getNome());
        usuario.setEmail(uForm.getEmail());
        usuario.setSenha(uForm.getSenha());
        return usuario;
    }
}
