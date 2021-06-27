package br.com.slippers.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.slippers.api.components.CarrinhoConverter;
import br.com.slippers.api.model.Carrinho;
import br.com.slippers.api.model.Usuario;
import br.com.slippers.api.repository.CarrinhoRepository;
import javassist.NotFoundException;

@RestController
@RequestMapping("/api/carrinho")
public class CarrinhoRest {
    
    //@Autowired injetando dependência na classe
    @Autowired
    CarrinhoRepository carrinhoR;

    @Autowired
    CarrinhoConverter carrinhoConverter;
    
    /**
     * @GetMapping mapear requisição get para buscar um ou vários tamanhos
     * @ResponseEntity retorno http, podendo ou não retornar um corpo
     * @RequestParam define um parâmetro de query para a url, e se ele é obrigatório.
     * @PageableDefault caso não defina nenhuma espécie de paginação no parêmetro da url, esta
     * será definida como padrão.
     * sort: ordenar por data de criação do tamanho
     * direction: descendente
     * page: página atual, começa com 0
     * size: quantidade de itens por página, definido 15
     */ 
    @GetMapping
    @Cacheable(value = "listaCarrinho")
	public ResponseEntity<?> listCarrinho(@PageableDefault(sort = "nome", direction = Direction.ASC, page = 0,
    size = 15) Pageable paginarChinelos) throws NotFoundException {
        
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setEmail("ee@email");
        usuario.setTotalCompras(0);
        Optional<Carrinho> carrinho = carrinhoR.findByUsuario(usuario.getId());
        if(carrinho.isEmpty()) {
            throw new NotFoundException("Carrinho não encontrado!");
        }
        return ResponseEntity.ok(carrinhoConverter.toDTO(carrinho.get()));
    }


    /**
     * @PostMapping mapear requisição post para inserir um tamanho
     * @CacheEvict desativa o cache nesta action
     * @RequestBody informações do tamanho serão inseridas no corpo http e transferidas ao TamanhoForm
     * @Valid irá validar os dados transferidos ao TamanhoForm
     * @UriComponentsBuilder irá construir uma nova URL
     */
    // @PostMapping
    // @Transactional
    // @CacheEvict(value = "listaChinelos", allEntries = true)
	// public ResponseEntity<TamanhoDTO> newTamanho(@RequestBody @Valid TamanhoForm cForm,
    // UriComponentsBuilder builder) throws NotFoundException {

	// 	Tamanho tamanho = tamanhoConverter.toTamanho(cForm, new Tamanho());
    //     tamanhoR.save(tamanho);
    //     /** 
    //      * {id} caminho variável, irá receber o id do tamanho criado
    //      * buildAndExpand irá construir a url e colocar a id do tamanho criado na variável
    //      */
	// 	URI uri = builder.path("/{id}").buildAndExpand(tamanho.getId()).toUri();

    //     //irá retornar o código HTTP 201(created), a localização do item criado e o corpo do item criado
	// 	return ResponseEntity.created(uri).body(new TamanhoDTO(tamanho));
	// }

    // /**
    //  * @PutMapping mapear requisição put para atualizar um tamanho
    //  * @Transactional permite que a JPA mantenha o model tamanho no estado Managed
    //  * e assim permitindo a atualização automática dos dados.
    //  */
	// @PutMapping("/{id}")
	// @Transactional
    // @CacheEvict(value = "listaChinelos", allEntries = true)
	// public ResponseEntity<TamanhoDTO> updateTamanho(@PathVariable(value = "id") Long id,
	// 		@Valid @RequestBody TamanhoForm cForm) throws NotFoundException {

	// 	// Buscando o tamanho por id no banco de dados. Caso não encontre irá jogar uma NotFoundException
	// 	Tamanho tamanho = tamanhoR.findById(id).orElseThrow(()
    //     -> new NotFoundException("Tamanho não encontrado!"));

    //     /**
    //      * Convertendo e atualizando o model com os dados inseridos no form.
    //      * Como o model tamanho está no estado Managed, ao atualizar
    //      * seus dados, o JPA detecta e também atualiza no banco.
    //      */
    //     tamanhoConverter.toTamanho(cForm, tamanho);
	// 	// Convertendo o model atualizado do tamanho para um dto e retornando com o código HTTP 200(ok)
	// 	 return ResponseEntity.ok(new TamanhoDTO(tamanho));
	// }

    //  /**
    //  * @DeleteMapping mapear a requisição delete para deletar um tamanho
    //  */
	// @DeleteMapping("/{id}")
	// @Transactional
    // @CacheEvict(value = "listaChinelo", allEntries = true)
	// public ResponseEntity<?> deleteTamanho(@PathVariable(value = "id") Long id) throws NotFoundException {

    //     Tamanho tamanho = tamanhoR.findById(id).orElseThrow(()
    //     -> new NotFoundException("Tamanho não encontrado!"));

    //     tamanhoR.delete(tamanho);
    //     return ResponseEntity.noContent().build();
	// }
}
