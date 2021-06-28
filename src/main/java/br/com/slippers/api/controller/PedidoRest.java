package br.com.slippers.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.slippers.api.dto.PedidoDTO;
import br.com.slippers.api.form.PedidoForm;
import br.com.slippers.api.service.PedidoService;
import javassist.NotFoundException;

@RestController
@RequestMapping("/api/pedido")
public class PedidoRest {
    
    @Autowired
    PedidoService pedidoService;

    //  @GetMapping
    //  public ResponseEntity<List<TamanhoDTO>> listTamanhos(@RequestParam(required = false)
    //  String descricao) throws NotFoundException {
         
    //      List<Tamanho> tamanhos = tamanhoR.findAll();
    //      if(tamanhos.isEmpty()) {
    //          throw new NotFoundException("Não há tamanhos cadastrados no BD");
    //      }
    //      return ResponseEntity.ok(Tamanho.toListDTO(tamanhos));
    //  }
 
     @PostMapping("/realizarPedido")
     @Transactional
     public ResponseEntity<PedidoDTO> newPedido(@RequestBody @Valid PedidoForm pForm,
     UriComponentsBuilder builder) throws NotFoundException {
 
        return pedidoService.newPedido(pForm, builder);
     }
 
     /**
      * @PutMapping mapear requisição put para atualizar um tamanho
      * @Transactional permite que a JPA mantenha o model tamanho no estado Managed
      * e assim permitindo a atualização automática dos dados.
      */
    //  @PutMapping("/{id}")
    //  @Transactional
    //  public ResponseEntity<TamanhoDTO> updateTamanho(@PathVariable(value = "id") Long id,
    //          @Valid @RequestBody TamanhoForm tForm) throws NotFoundException {
 
    //      // Buscando o tamanho por id no banco de dados. Caso não encontre irá jogar uma NotFoundException
    //      Tamanho tamanho = tamanhoR.findById(id).orElseThrow(()
    //      -> new NotFoundException("Tamanho não encontrado!"));
 
    //      /**
    //       * Convertendo e atualizando o model com os dados inseridos no form.
    //       * Como o model tamanho está no estado Managed, ao atualizar
    //       * seus dados, o JPA detecta e também atualiza no banco.
    //       */
    //      Tamanho.toTamanho(tForm);
    //      // Convertendo o model atualizado do tamanho para um dto e retornando com o código HTTP 200(ok)
    //       return ResponseEntity.ok(new TamanhoDTO(tamanho));
    //  }
 
      /**
    //   * @DeleteMapping mapear a requisição delete para deletar um tamanho
    //   */
    //  @DeleteMapping("/{id}")
    //  @Transactional
    //  @CacheEvict(value = "listaChinelo", allEntries = true)
    //  public ResponseEntity<?> deleteTamanho(@PathVariable(value = "id") Long id) throws NotFoundException {
 
    //      Tamanho tamanho = tamanhoR.findById(id).orElseThrow(()
    //      -> new NotFoundException("Tamanho não encontrado!"));
 
    //      tamanhoR.delete(tamanho);
    //      return ResponseEntity.noContent().build();
    //  }
}
