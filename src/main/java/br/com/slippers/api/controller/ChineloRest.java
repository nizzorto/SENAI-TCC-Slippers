package br.com.slippers.api.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
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

import br.com.slippers.api.dto.ChineloDTO;
import br.com.slippers.api.form.ChineloForm;
import br.com.slippers.api.service.ChineloService;
import javassist.NotFoundException;

/**
 * @RestController: controller que só retorna JSON's
 * @Cacheable: evita que actions da classe façam buscas desnecessárias no banco de dados
 * @RequestMapping: caminho padrão para todas as actions da classe 
 */
@RestController
@RequestMapping("/api/chinelo")
public class ChineloRest {
    
    @Autowired
    ChineloService cService;
    /**
     * @GetMapping mapear requisição get para buscar um ou vários chinelos
     * @ResponseEntity retorno http, podendo ou não retornar um corpo
     * @RequestParam define um parâmetro de query para a url, e se ele é obrigatório.
     * @PageableDefault caso não defina nenhuma espécie de paginação no parêmetro da url, esta
     * será definida como padrão.
     * sort: ordenar por data de criação do chinelo
     * direction: descendente
     * page: página atual, começa com 0
     * size: quantidade de itens por página, definido 15
     */ 
    @GetMapping("/listaChinelos")
    @Cacheable(value = "listaChinelos")
	public ResponseEntity<Page<ChineloDTO>> listChinelos(@RequestParam(required = false) String nomeChinelo,
    @RequestParam(required = true) int pagina, @RequestParam(required = true) int qtdItensPagina) throws NotFoundException {
        
        return ResponseEntity.ok(cService.listChinelosString(nomeChinelo, pagina, qtdItensPagina));
	}


    /**
     * @PostMapping mapear requisição post para inserir um chinelo
     * @CacheEvict desativa o cache nesta action
     * @RequestBody informações do chinelo serão inseridas no corpo http e transferidas ao ChineloForm
     * @Valid irá validar os dados transferidos ao ChineloForm
     * @UriComponentsBuilder irá construir uma nova URL
     */
    @PostMapping("/inserirChinelo")
    @Transactional
    @CacheEvict(value = "listaChinelos", allEntries = true)
	public ResponseEntity<ChineloDTO> newChinelo(@RequestBody @Valid ChineloForm cForm,
    UriComponentsBuilder builder) throws NotFoundException {

		return cService.newChinelo(cForm, builder);
	}

    /**
     * @PutMapping mapear requisição put para atualizar um chinelo
     * @Transactional permite que a JPA mantenha o model chinelo no estado Managed
     * e assim permitindo a atualização automática dos dados.
     */
	@PutMapping("/atualizarChinelo/{id}")
	@Transactional
    @CacheEvict(value = "listaChinelos", allEntries = true)
	public ResponseEntity<ChineloDTO> updateChinelo(@PathVariable(value = "id") Long id,
        @Valid @RequestBody ChineloForm cForm) throws NotFoundException {

        return ResponseEntity.ok(cService.updateChinelo(id, cForm));
	}

     /**
     * @DeleteMapping mapear a requisição delete para deletar um chinelo
     */
	@DeleteMapping("/deletarChinelo/{id}")
	@Transactional
    @CacheEvict(value = "listaChinelos", allEntries = true)
	public ResponseEntity<?> deleteChinelo(@PathVariable(value = "id") Long id) throws NotFoundException {

        cService.deleteChinelo(id);
        return ResponseEntity.noContent().build();
	}
}
