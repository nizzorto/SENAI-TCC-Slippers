package br.com.slippers.api.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.slippers.api.dto.UsuarioDTO;
import br.com.slippers.api.form.UsuarioForm;
import br.com.slippers.api.model.Usuario;
import br.com.slippers.api.repository.CarrinhoRepository;
import br.com.slippers.api.repository.CartaoRepository;
import br.com.slippers.api.repository.NotaChineloClienteRepository;
import br.com.slippers.api.repository.PedidoRepository;
import br.com.slippers.api.repository.UsuarioRepository;
import javassist.NotFoundException;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioRest {

        @Autowired
        UsuarioRepository usuarioR;

        @Autowired
        NotaChineloClienteRepository nccRepository;

        @Autowired
        PedidoRepository pedidosR;

        @Autowired
        CartaoRepository cartaoR;

        @Autowired
        CarrinhoRepository carrinhoRepository;

        @GetMapping
        @Cacheable(value = "listaUsuario")
        public ResponseEntity<?> listUsuarios(@RequestParam(required = true) String email,
        @PageableDefault(sort = "nome", direction = Direction.DESC, page = 0, size = 15) Pageable paginacao) 
        throws NotFoundException {
            Optional<Usuario> usuario = usuarioR.findByEmail(email);
            if(usuario.isEmpty()) {
                throw new NotFoundException("Usuário não encontrado!");
            }
            return ResponseEntity.ok(usuario.get().toDTO());
        }
    
    

        @PostMapping
        @Transactional
        @CacheEvict(value = "listaUsuario", allEntries = true)
        public ResponseEntity<UsuarioDTO> newUsuario(@RequestBody @Valid UsuarioForm uForm,
        UriComponentsBuilder builder) {
    
            Usuario usuario = Usuario.toUsuario(uForm);
            usuarioR.save(usuario);
            
            URI uri = builder.path("/{id}").buildAndExpand(usuario.getId()).toUri();
    
            return ResponseEntity.created(uri).body(usuario.toDTO());
        }


        // @PutMapping("/{id}")
        // @Transactional
        // @CacheEvict(value = "listaUsuario", allEntries = true)
        // public ResponseEntity<TamanhoDTO> updateTamanho(@PathVariable(value = "id") Long id,
        //         @Valid @RequestBody TamanhoForm cForm) throws NotFoundException {
    
        //     // Buscando o tamanho por id no banco de dados. Caso não encontre irá jogar uma NotFoundException
        //     Tamanho tamanho = tamanhoR.findById(id).orElseThrow(()
        //     -> new NotFoundException("Tamanho não encontrado!"));
    
        //     /**
        //      * Convertendo e atualizando o model com os dados inseridos no form.
        //      * Como o model tamanho está no estado Managed, ao atualizar
        //      * seus dados, o JPA detecta e também atualiza no banco.
        //      */
        //     tamanhoConverter.toTamanho(cForm, tamanho);
        //     // Convertendo o model atualizado do tamanho para um dto e retornando com o código HTTP 200(ok)
        //      return ResponseEntity.ok(new TamanhoDTO(tamanho));
        // }
    
        //  /**
        //  * @DeleteMapping mapear a requisição delete para deletar um tamanho
        //  */
        // @DeleteMapping("/{id}")
        // @Transactional
        // @CacheEvict(value = "listaUsuario", allEntries = true)
        // public ResponseEntity<?> deleteTamanho(@PathVariable(value = "id") Long id) throws NotFoundException {
    
        //     Tamanho tamanho = tamanhoR.findById(id).orElseThrow(()
        //     -> new NotFoundException("Tamanho não encontrado!"));
    
        //     tamanhoR.delete(tamanho);
        //     return ResponseEntity.noContent().build();
        // }
}
