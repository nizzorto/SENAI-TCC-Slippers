package br.com.slippers.api.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
import br.com.slippers.api.model.Chinelo;
import br.com.slippers.api.model.Tamanho;
import br.com.slippers.api.repository.ChineloRepository;
import br.com.slippers.api.repository.TamanhoRepository;
import javassist.NotFoundException;

/**
 * @RestController: controller que só retorna JSON's
 * @Cacheable: evita que actions da classe façam buscas desnecessárias no banco de dados
 * @RequestMapping: caminho padrão para todas as actions da classe 
 */
@RestController
@RequestMapping("/api/chinelo")
public class ChineloRest {
    
    //@Autowired injetando dependência na classe
    @Autowired
    ChineloRepository chineloR;
    
    @Autowired
    TamanhoRepository tamanhoR;
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
    @GetMapping
    @Cacheable(value = "listaChinelos")
	public ResponseEntity<Page<ChineloDTO>> listChinelos(@RequestParam(required = false) String nomeChinelo,
        @PageableDefault(sort = "dataCriacao", direction = Direction.DESC, page = 0,
        size = 15) Pageable paginacao) throws NotFoundException {
        
        if (nomeChinelo == null) {

            Page<Chinelo> chinelos = chineloR.findAll(paginacao);
            if(chinelos.isEmpty()) {
               throw new NotFoundException("Não há chinelos no BD");
            }
            return ResponseEntity.ok(Chinelo.toPageDTO(chinelos));
        } else {
            
            Page<Chinelo> chinelos = chineloR.findByNome(nomeChinelo, paginacao);
            if(chinelos.isEmpty()) {
                throw new NotFoundException("Chinelo não encontrado!");
            }
            return ResponseEntity.ok(Chinelo.toPageDTO(chinelos));
        }
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

        List<Tamanho> tamanhos = tamanhoR.findAllById(cForm.getIdTamanhos());
        if(tamanhos.isEmpty()) {
            throw new NotFoundException("Tamanho não encontrado");
        }
		Chinelo chinelo = new Chinelo(cForm, tamanhos);
        
        chineloR.save(chinelo);
        /** 
         * {id} caminho variável, irá receber o id do chinelo criado
         * buildAndExpand irá construir a url e colocar a id do chinelo criado na variável
         */
		URI uri = builder.path("/{id}").buildAndExpand(chinelo.getId()).toUri();

        //irá retornar o código HTTP 201(created), a localização do item criado e o corpo do item criado
		return ResponseEntity.created(uri).body(chinelo.toDTO());
	}

    /**
     * @PutMapping mapear requisição put para atualizar um chinelo
     * @Transactional permite que a JPA mantenha o model chinelo no estado Managed
     * e assim permitindo a atualização automática dos dados.
     */
	@PutMapping("/{id}")
	@Transactional
    @CacheEvict(value = "listaChinelos", allEntries = true)
	public ResponseEntity<ChineloDTO> updateChinelo(@PathVariable(value = "id") Long id,
			@Valid @RequestBody ChineloForm cForm) throws NotFoundException {

		// Buscando o chinelo por id no banco de dados. Caso não encontre irá jogar uma NotFoundException
		Chinelo chinelo = chineloR.findById(id).orElseThrow(()
        -> new NotFoundException("Chinelo não encontrado!"));

        List<Tamanho> tamanhos = tamanhoR.findAllById(cForm.getIdTamanhos());
        if(tamanhos.isEmpty()) {
            throw new NotFoundException("Tamanho não encontrado");
        }

        /**
         * Convertendo e atualizando o model com os dados inseridos no form.
         * Como o model chinelo está no estado Managed, ao atualizar
         * seus dados, o JPA detecta e também atualiza no banco.
         */
        chinelo.setNome(cForm.getNome());
		chinelo.setDescricao(cForm.getDescricao());
		chinelo.setValor(Double.parseDouble(cForm.getValor()));
		chinelo.setUrlImagem(cForm.getUrlImagem());
		chinelo.setTamanhos(tamanhos);

		// Convertendo o model atualizado do chinelo para um dto e retornando com o código HTTP 200(ok)
		 return ResponseEntity.ok(chinelo.toDTO());
	}

     /**
     * @DeleteMapping mapear a requisição delete para deletar um chinelo
     */
	@DeleteMapping("/{id}")
	@Transactional
    @CacheEvict(value = "listaChinelos", allEntries = true)
	public ResponseEntity<?> deleteChinelo(@PathVariable(value = "id") Long id) throws NotFoundException {

        Chinelo chinelo = chineloR.findById(id).orElseThrow(()
        -> new NotFoundException("Chinelo não encontrado!"));

        chineloR.delete(chinelo);
        return ResponseEntity.noContent().build();
	}
}
