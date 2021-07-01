package br.com.slippers.api.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.slippers.api.dto.TamanhoDTO;
import br.com.slippers.api.form.TamanhoForm;
import br.com.slippers.api.model.Tamanho;
import br.com.slippers.api.service.TamanhoService;
import javassist.NotFoundException;

@RestController
@RequestMapping("/api/tamanho")
public class TamanhoRest {
    
    //@Autowired injetando dependência na classe
    @Autowired
    TamanhoService tService;
    
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
    @GetMapping("/listaTamanhos")
    @Cacheable(value = "listaChinelos")
	public ResponseEntity<List<TamanhoDTO>> listTamanhos() throws NotFoundException {

        return ResponseEntity.ok(tService.listTamanhos());
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

		return tService.newTamanho(tForm, builder);
	}

    /**
     * @PutMapping mapear requisição put para atualizar um tamanho
     * @Transactional permite que a JPA mantenha o model tamanho no estado Managed
     * e assim permitindo a atualização automática dos dados.
     */
	@PutMapping("alterarTamanho/{id}")
	@Transactional
    @CacheEvict(value = "listaChinelos", allEntries = true)
	public ResponseEntity<TamanhoDTO> updateTamanho(@PathVariable(value = "id") Long id,
			@Valid @RequestBody TamanhoForm tForm) throws NotFoundException {
		return ResponseEntity.ok(tService.updateTamanho(id, tForm));
	}

     /**
     * @DeleteMapping mapear a requisição delete para deletar um tamanho
     */
	@DeleteMapping("deletarTamanho/{id}")
	@Transactional
    @CacheEvict(value = "listaChinelo", allEntries = true)
	public ResponseEntity<?> deleteTamanho(@PathVariable(value = "id") Long id) throws NotFoundException {
        tService.deleteTamanho(id);
        return ResponseEntity.noContent().build();
	}
}
