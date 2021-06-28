package br.com.slippers.api.components;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.slippers.api.dto.NotaChineloClienteDTO;
import br.com.slippers.api.model.Chinelo;
import br.com.slippers.api.model.NotaChineloCliente;

@Component
public class NotaChineloClienteConverter {
    
    @Autowired
    UsuarioConverter usuarioConverter;


    public NotaChineloClienteDTO toDTO(NotaChineloCliente ncc) {

        return new NotaChineloClienteDTO(
            usuarioConverter.toDTO(ncc.getUsuario()),
            ncc.getChinelo().toDTO(),
            ncc.getNota()
        );
    }
    
    public List<NotaChineloClienteDTO> toListDTO(List<NotaChineloCliente> ncc){
        return ncc.stream().map(this::toDTO).toList();
    }
}
