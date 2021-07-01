package br.com.slippers.api.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
import br.com.slippers.api.service.UsuarioService;
import javassist.NotFoundException;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioRest {

        @Autowired
        UsuarioService uService;

        @GetMapping("/buscarEmail")
        @Cacheable(value = "listaUsuario")
        public ResponseEntity<?> listUsuarios(@RequestParam(required = false) String email,
        @RequestParam(required = true) int pagina, @RequestParam(required = true) int qtdItensPorPagina) throws NotFoundException {
            
            return uService.listUsuarios(email, pagina, qtdItensPorPagina);
        }

        @PostMapping("/inserirUsuario")
        @Transactional
        @CacheEvict(value = "listaUsuario", allEntries = true)
        public ResponseEntity<UsuarioDTO> newUsuario(@RequestBody @Valid UsuarioForm uForm,
        UriComponentsBuilder builder) {

            return uService.newUsuario(uForm, builder);
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
