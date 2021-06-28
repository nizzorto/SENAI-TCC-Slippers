package br.com.slippers.api.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.slippers.api.dto.UsuarioDTO;
import br.com.slippers.api.form.UsuarioForm;

/**
 * @Entity: diz ao jpa que esta classe será uma tabela no banco de dados
 * UserDetails: Ao implementar esta interface, o spring security irá saber que 
 * essa é a classe que representa o usuário do sistema. Também seremos obrigados a implementar
 * alguns métodos.
*/
@Entity
public class Usuario implements UserDetails {


	/** 
	 * @Id: diz ao jpa que este atributo é o id da tabela
	 * @GeneratedValue: diz ao jpa que este id será um valor gerado automaticamente,
	 * usando a estratégia IDENTITY (1 + N).
	 * @Column: atributos da coluna; 
	 * nullable: diz se a coluna pode ser nula
	 */
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false)
	private Long id;
	
	@Column(nullable=false)
	private String nome;

	//unique: sql não deve aceitar usuários com emails iguais
	@Column(nullable=false, unique = true)
	private String email;

	@Column(nullable=false)
	private String senha;

	//Salva quantas compras este cliente fez
	@Column(nullable=true)
	private int totalCompras = 0;
	
	@ManyToMany(cascade = CascadeType.REMOVE)
	@JoinTable(
		name = "usuario_has_endereco",
		joinColumns = @JoinColumn(name = "cliente_id"),
		inverseJoinColumns = @JoinColumn(name = "endereco_id")
	)
	private List<Endereco> enderecos = new ArrayList<Endereco>();
	/**
	 * Entidade com os perfis deste usuário (se é um cliente, administrador, etc.)
	 * Necessário para o funcionamento do spring security.
	 *
	 * @ManyToMany: diz ao jpa que aqui possui um relacionamento Muitos para Muitos
	 * fetchtype.EAGER: diz ao jpa para buscar junto à tabela usuário, a tabela com seus perfis.
	 */
	 @ManyToMany(fetch = FetchType.EAGER)
	 private List<Perfil> perfis = new ArrayList<>();


	/**
	 * Tabela com as notas que o cliente deu para os chinelos que comprou.
	 * A tabela NotaChineloCliente referencia o id do cliente, o id do chinelo, e a
	 *nota que o cliente deu ao chinelo.
	 *
	 * mappedBy: indica que a tabela usuário é a dominante na relação
	 * CascadeType.REMOVE: se o usuário for apagado, as notas que deu também serão apagadas
	 * @OneToMany: um cliente deu notas para muitos chinelos
	 */
	@ManyToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	private List<NotaChineloCliente> notaChineloCliente = new ArrayList<NotaChineloCliente>();
	

	//Todos os pedidos que o cliente fez, desde os em análise até os já entregue
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	private List<Pedido> pedidos = new ArrayList<Pedido>();


	/**
	 * Cartões de crédito do cliente
	 * (Em breve substituído pela API do MercadoPago)
	 * TODO CARTAO
	 * @ManyToMany deve ter uma tabela de relacionamento.
	 * a JPA automaticamente cria uma tabela de relacionamento quando se depara com
	 * esta anotação.
	 * O nome padrão que a JPA coloca para as tabelas de relacionamento é tabela1_has_tabela2 
	 *
	 * @JoinTable: irá colocar uma coluna com o id da tabela de relacionamento usuario_has_cartao na tabela
	 * usuário, definindo a coluna dominante usuario_id com joinColumns e a coluna não-dominante
	 * cartao_id com inverseJoinColumns.
	 * lembrando que por padrão, o nome das colunas id que foram "Joinadas" é coluna_id
	 */
	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinTable(
			name = "usuario_has_cartao",
			joinColumns = @JoinColumn(name = "usuario_id"),
			inverseJoinColumns = @JoinColumn(name = "cartao_id")
		)
	private List<Cartao> cartoes = new ArrayList<Cartao>();
	
	/**
	 * @JoinColumn: irá trazer para a tabela usuario a coluna carrinho_id
	 * CascadeType.ALL: Utiliza-se de todos os CascadeTypes. PERSIST, REFRESH, REMOVE, etc.
	 * referencedColumnName: diz qual é o nome da coluna que referencia o id do carrinho
	 * na tabela Carrinho
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "carrinho_id", referencedColumnName = "id", unique = true)
	private Carrinho carrinho = new Carrinho(this);
	

	// G e t t e r s   e   S e t t e r s
	
	public Long getIdUsuario() {
		return id;	
	}

	public void setIdUsuario(Long id) {
		this.id = id;
	}

	public int getTotalCompras() {
		return totalCompras;
	}

	public void setTotalCompras(int totalCompras) {
		this.totalCompras = totalCompras;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<NotaChineloCliente> getNotaChineloCliente() {
		return this.notaChineloCliente;
	}

	public void setNotaChineloCliente(List<NotaChineloCliente> notaChineloCliente) {
		this.notaChineloCliente = notaChineloCliente;
	}

	public List<Pedido> getPedidos() {
		return this.pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public List<Cartao> getCartoes() {
		return this.cartoes;
	}

	public void setCartoes(List<Cartao> cartoes) {
		this.cartoes = cartoes;
	}

	public Carrinho getCarrinho() {
		return this.carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	public List<Endereco> getEnderecos() {
		return this.enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

    public static List<UsuarioDTO> toListDTO(List<Usuario> usuarios) {
		return usuarios.stream().map(Usuario::toDTO).toList();
    }

	public UsuarioDTO toDTO() {
        return new UsuarioDTO(this);
    }

    public static Usuario toUsuario(UsuarioForm uForm) {
		Usuario usuario = new Usuario();
		usuario.email = uForm.getEmail();
		usuario.nome = uForm.getNome();
		usuario.senha = uForm.getSenha();
		return usuario;
    }


	// /**
	//  * Mostra ao spring que aqui é onde pega a senha
	//  * 
	//  * @Override: Implementa o método requirido pela interface (UserDetails) 
	//  */
	@Override
	public String getPassword() {
		return this.senha;
	}
	//Mostra ao spring que aqui é onde se pega o username (no nosso caso é o email)
	@Override
	public String getUsername() {
		return this.email;
	}

	//Se a conta não foi expirada
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//Se a conta não está bloqueada
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//Se as credenciais não estão expiradas
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//Se o usuário está habilitado
	@Override
	public boolean isEnabled() {
		return true;
	}

	//Para o spring security, é necessário ter o usuário para autenticar
	//E a coleção com os perfis deste usuário para autorizar
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfis;
	}
}
