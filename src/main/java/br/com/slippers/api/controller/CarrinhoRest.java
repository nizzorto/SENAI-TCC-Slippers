package br.com.slippers.api.controller;

import java.rmi.AlreadyBoundException;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
import org.springframework.web.bind.annotation.RestController;

import br.com.slippers.api.dto.CarrinhoDTO;
import br.com.slippers.api.form.CarrinhoForm;
import br.com.slippers.api.service.CarrinhoService;
import javassist.NotFoundException;

@RestController
@RequestMapping("/api/carrinho")
public class CarrinhoRest {
    
    //@Autowired injetando dependência na classe
    @Autowired
    CarrinhoService carrinhoService;
    
    @GetMapping("/listarChinelos")
    @Cacheable(value = "listaCarrinho")
	public ResponseEntity<?> listCarrinho(@PageableDefault(sort = "nome", direction = Direction.ASC, page = 0,
    size = 15) Pageable paginarChinelos) throws NotFoundException {

        return carrinhoService.listCarrinho(paginarChinelos);
    }
    
	@PostMapping("/inserirChinelo")
	@Transactional
    @CacheEvict(value = "listaCarrinho", allEntries = true)
	public ResponseEntity<?> insertChinelo(@Valid @RequestBody CarrinhoForm cForm) 
    throws NotFoundException, AlreadyBoundException {

        return carrinhoService.insertChinelo(cForm);
	}

    @PutMapping("/alterarQtdChineloCarrinho")
    @Transactional
    @CacheEvict(value = "listaCarrinho", allEntries = true)
	public ResponseEntity<CarrinhoDTO> updateQtdChineloCarrinho(@RequestBody @Valid CarrinhoForm cForm) 
    throws NotFoundException {
        return carrinhoService.updateChineloCarrinho(cForm);
	}
 
	@DeleteMapping("/apagarChinelo/{id}")
	@Transactional
    @CacheEvict(value = "listaCarrinho", allEntries = true)
	public ResponseEntity<?> deleteChinelo(@PathVariable(value = "id", required = true) String idChinelo) throws NotFoundException {
        
        return carrinhoService.deleteChinelo(idChinelo);
	}
}
