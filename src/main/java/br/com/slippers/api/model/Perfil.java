package br.com.slippers.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

/**
 * GrantedAuthority: Ao implementar esta interface, o spring security irá saber que
 * esta é a classe que representa o perfil de acesso
 * Um perfil poderia ser um cliente, vendedor, administrador, etc. (Exemplo)
 */
@Entity
public class Perfil implements GrantedAuthority{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String getAuthority() {
        return this.descricao;
    }
}
