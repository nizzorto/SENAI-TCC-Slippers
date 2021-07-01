package br.com.slippers.api.service;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.slippers.api.dto.UsuarioDTO;
import br.com.slippers.api.form.UsuarioForm;
import br.com.slippers.api.model.Perfil;
import br.com.slippers.api.model.Usuario;
import br.com.slippers.api.repository.PerfilRepository;
import br.com.slippers.api.repository.UsuarioRepository;
import javassist.NotFoundException;

@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioR;

    @Autowired
    PerfilRepository perfilR;
    
    public ResponseEntity<?> listUsuarios(String email, @RequestParam(required= true)int pagina,
    @RequestParam(required= true) int qtdItensPagina)
    throws NotFoundException {
        if(email == null) {
            Pageable paginacao = PageRequest.of(pagina, qtdItensPagina);
            Page<Usuario> usuarios = usuarioR.findAll(paginacao);
            if(usuarios.isEmpty()) {
                throw new IllegalArgumentException("Retorno vazio, verifique sua paginação (a página começa com 0)");
            }
            return ResponseEntity.ok(Usuario.toPageDTO(usuarios));
        }
        Optional<Usuario> usuario = usuarioR.findByEmail(email);
        if(usuario.isEmpty()) {
            throw new NotFoundException("Usuário não encontrado!");
        }
        return ResponseEntity.ok(usuario.get().toDTO());
    }

    public ResponseEntity<UsuarioDTO> newUsuario(UsuarioForm uForm, UriComponentsBuilder builder) {
        
        Usuario usuario = Usuario.toUsuario(uForm);
        Perfil perfil = perfilR.findByDescricao("Cliente");
        usuario.getPerfis().add(perfil);
        usuarioR.save(usuario);
        
        URI uri = builder.path("/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(usuario.toDTO());
    }
}
