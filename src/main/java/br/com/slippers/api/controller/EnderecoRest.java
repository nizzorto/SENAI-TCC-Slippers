package br.com.slippers.api.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.slippers.api.dto.EnderecoDTO;
import br.com.slippers.api.form.CepForm;
import br.com.slippers.api.service.EnderecoService;
import javassist.NotFoundException;

@RestController
@RequestMapping("/api/endereco")
public class EnderecoRest {
    
    @Autowired
	private EnderecoService enderecoService;

    @GetMapping("/buscarEnderecos")
    public ResponseEntity<List<EnderecoDTO>> findEnderecos() throws NotFoundException {
		return ResponseEntity.ok(enderecoService.buscarEnderecosUsuario());
	}

    @PostMapping("/inserirEndereco")
    @Transactional
	public ResponseEntity<EnderecoDTO> newEndereco(@RequestBody @Valid CepForm cepForm,
    UriComponentsBuilder builder) throws NotFoundException {
		return ResponseEntity.ok(enderecoService.obterEnderecoCep(cepForm));
	}
}
