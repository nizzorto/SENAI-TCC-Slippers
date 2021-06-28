package br.com.slippers.api.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.slippers.api.repository.UsuarioRepository;

/**
 * Classe de configuração do Spring Security
 * @EnableWebSecurity: Ativa
 * @Configuration: Avisa ao spring que é uma classe de configuração
 * extends WebSecurityConfigurerAdapter herda métodos necessários
 */
@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthService authService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	
	//Config de autenticação
	/**
	 * 
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.userDetailsService(authService).passwordEncoder(new BCryptPasswordEncoder());
	}

	/**
	 * Execute o main abaixo e insira uma senha dentro de .encode("") para 
	 * codificar uma senha.
	 * a resposta retornará no console
	 */
//	public static void main(String[] args) {
//		System.out.println(new BCryptPasswordEncoder().encode("senha aleatoria"));
//	}

	//Config de autorização
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/** 
		 *	.authorizeRequests: Quais requests irá gerenciar
		 * .antMatchers(HttpMethod, "/caminho"): qual o método e url é para filtrar
		 * 
		 * .permitAll(): libera para todos
		 * || 
		 * .authenticated(): deve estar autenticado
		 *
		 * .anyRequest(): para qualquer outra requisição não configurada
		 * 
		 * .and(): define mais outras configurações
		 */
		http.authorizeRequests()
		.antMatchers("/", "/css/**", "/images/**", "/img/**", "/js/**", "/h2-console/**").permitAll()
		.antMatchers(HttpMethod.GET, "/api/chinelo").permitAll()
        .antMatchers("/auth").permitAll()
		.anyRequest().authenticated()
        .and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
	
	}
}