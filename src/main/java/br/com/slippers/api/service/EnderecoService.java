package br.com.slippers.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.slippers.api.dto.EnderecoDTO;
import br.com.slippers.api.form.CepForm;
import br.com.slippers.api.form.EnderecoForm;
import br.com.slippers.api.model.Endereco;
import br.com.slippers.api.model.Usuario;
import br.com.slippers.api.repository.EnderecoRepository;
import reactor.core.publisher.Mono;

@Service
public class EnderecoService {
    
    @Autowired
    WebClient webClientCep;

    @Autowired
    EnderecoRepository enderecoR;

    public EnderecoDTO obterEnderecoCep(CepForm cepForm) {

      Mono<EnderecoForm> monoEndereco = this.webClientCep
			.method(HttpMethod.GET)
			.uri("/{cep}/json/", cepForm.getCep())
			.retrieve()
			.bodyToMono(EnderecoForm.class);
	
		EnderecoForm eForm = monoEndereco.block();
         List<Usuario> usuarios = new ArrayList<Usuario>();
         Usuario usuario = new Usuario();
         usuario.setIdUsuario(1L);
         usuario.setEmail("ee@email");
         usuario.setTotalCompras(0); 
         usuarios.add(usuario);
        Endereco endereco = eForm.toEndereco();
        endereco.setNumero(cepForm.getNumero());
        endereco.setComplemento(cepForm.getComplemento());
        endereco.setUsuarios(usuarios);

        enderecoR.save(endereco);
        return endereco.toDTO();
	}
}
