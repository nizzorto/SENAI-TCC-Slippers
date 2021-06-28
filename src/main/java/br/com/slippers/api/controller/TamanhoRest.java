package br.com.slippers.api.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.slippers.api.dto.TamanhoDTO;
import br.com.slippers.api.form.TamanhoForm;
import br.com.slippers.api.model.Tamanho;
import br.com.slippers.api.repository.TamanhoRepository;
import javassist.NotFoundException;

@RestController
@RequestMapping("/api/tamanho")
public class TamanhoRest {
    
    //@Autowired injetando dependência na classe
    @Autowired
    TamanhoRepository tamanhoR;
    
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
    @Cacheable(value = "listaChinelos")
	public ResponseEntity<List<TamanhoDTO>> listTamanhos(@RequestParam(required = false)
    String descricao) throws NotFoundException {
        
        List<Tamanho> tamanhos = tamanhoR.findAll();
        if(tamanhos.isEmpty()) {
            throw new NotFoundException("Não há tamanhos cadastrados no BD");
        }
        return ResponseEntity.ok(Tamanho.toListDTO(tamanhos));
    }


    /**
     * @PostMapping mapear requisição post para inserir um tamanho
     * @CacheEvict desativa o cache nesta action
     * @RequestBody informações do tamanho serão inseridas no corpo http e transferidas ao TamanhoForm
     * @Valid irá validar os dados transferidos ao TamanhoForm
     * @UriComponentsBuilder irá construir uma nova URL
     */
    @PostMapping("/inserirTamanho")
    @Transactional
    @CacheEvict(value = "listaChinelos", allEntries = true)
	public ResponseEntity<TamanhoDTO> newTamanho(@RequestBody @Valid TamanhoForm tForm,
    UriComponentsBuilder builder) throws NotFoundException {

		Tamanho tamanho = Tamanho.toTamanho(tForm);
        tamanhoR.save(tamanho);
        /** 
         * {id} caminho variável, irá receber o id do tamanho criado
         * buildAndExpand irá construir a url e colocar a id do tamanho criado na variável
         */
		URI uri = builder.path("/{id}").buildAndExpand(tamanho.getId()).toUri();

        //irá retornar o código HTTP 201(created), a localização do item criado e o corpo do item criado
		return ResponseEntity.created(uri).body(new TamanhoDTO(tamanho));
	}

    /**
     * @PutMapping mapear requisição put para atualizar um tamanho
     * @Transactional permite que a JPA mantenha o model tamanho no estado Managed
     * e assim permitindo a atualização automática dos dados.
     */
	@PutMapping("/{id}")
	@Transactional
    @CacheEvict(value = "listaChinelos", allEntries = true)
	public ResponseEntity<TamanhoDTO> updateTamanho(@PathVariable(value = "id") Long id,
			@Valid @RequestBody TamanhoForm tForm) throws NotFoundException {

		// Buscando o tamanho por id no banco de dados. Caso não encontre irá jogar uma NotFoundException
		Tamanho tamanho = tamanhoR.findById(id).orElseThrow(()
        -> new NotFoundException("Tamanho não encontrado!"));

        /**
         * Convertendo e atualizando o model com os dados inseridos no form.
         * Como o model tamanho está no estado Managed, ao atualizar
         * seus dados, o JPA detecta e também atualiza no banco.
         */
        Tamanho.toTamanho(tForm);
		// Convertendo o model atualizado do tamanho para um dto e retornando com o código HTTP 200(ok)
		 return ResponseEntity.ok(new TamanhoDTO(tamanho));
	}

     /**
     * @DeleteMapping mapear a requisição delete para deletar um tamanho
     */
	@DeleteMapping("/{id}")
	@Transactional
    @CacheEvict(value = "listaChinelo", allEntries = true)
	public ResponseEntity<?> deleteTamanho(@PathVariable(value = "id") Long id) throws NotFoundException {

        Tamanho tamanho = tamanhoR.findById(id).orElseThrow(()
        -> new NotFoundException("Tamanho não encontrado!"));

        tamanhoR.delete(tamanho);
        return ResponseEntity.noContent().build();
	}
}
