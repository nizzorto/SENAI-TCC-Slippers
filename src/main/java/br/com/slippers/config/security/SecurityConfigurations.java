package br.com.slippers.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
		.anyRequest().authenticated()
		.and().formLogin();
	}
}