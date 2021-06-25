package br.com.slippers.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.slippers.model.Usuario;
import br.com.slippers.repository.UsuarioRepository;

/**
 * @Service: Indica que é uma classe gerenciada pelo Spring, uma service, é onde fica a lógica do sistema.
 * UserDetailsService: diz ao spring que esta é a classe service onde acontece a lógica de autenticação
 */
@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UsuarioRepository uRepository;

    /**
     * Aqui é onde receberemos o email que o usuário colocar no login.
     * Iremos buscar a senha no banco de dados filtrando pelo email.
     * A validação da senha é feita em memória, não necessita de nenhuma lógica feita por nós.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = uRepository.findByEmail(username);
        if(usuario.isPresent()) {
            return usuario.get();
        }
        throw new UsernameNotFoundException("Dados inválidos!");
    }
}
