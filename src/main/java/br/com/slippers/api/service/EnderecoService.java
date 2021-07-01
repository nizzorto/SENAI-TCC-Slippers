package br.com.slippers.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.slippers.api.dto.EnderecoDTO;
import br.com.slippers.api.form.CepForm;
import br.com.slippers.api.form.EnderecoForm;
import br.com.slippers.api.model.Endereco;
import br.com.slippers.api.model.Usuario;
import br.com.slippers.api.repository.EnderecoRepository;
import br.com.slippers.api.repository.UsuarioRepository;
import javassist.NotFoundException;
import reactor.core.publisher.Mono;

@Service
public class EnderecoService {
    
    @Autowired
    WebClient webClientCep;

    @Autowired
    EnderecoRepository enderecoR;

    @Autowired
    UsuarioRepository usuarioR;

    public EnderecoDTO obterEnderecoCep(CepForm cepForm) {

      Mono<EnderecoForm> monoEndereco = this.webClientCep
			.method(HttpMethod.GET)
			.uri("/{cep}/json/", cepForm.getCep())
			.retrieve()
			.bodyToMono(EnderecoForm.class);
	
		EnderecoForm eForm = monoEndereco.block();
    
        List<Usuario> usuarios = new ArrayList<Usuario>();
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        usuarios.add(usuario);
        Endereco endereco = eForm.toEndereco();
        endereco.setNumero(cepForm.getNumero());
        endereco.setComplemento(cepForm.getComplemento());
        endereco.setUsuarios(usuarios);

        enderecoR.save(endereco);
        return endereco.toDTO();
	}

    public List<EnderecoDTO> buscarEnderecosUsuario() throws NotFoundException{
      Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       Optional<List<Endereco>> endereco = enderecoR.findByUsuariosId(usuario.getId());
       if(endereco.isEmpty()) {
         throw new NotFoundException("Você não cadastrou nenhum endereço!");
       }
       return Endereco.ToListDTO(endereco.get());
    }
}
