package br.com.slippers.api.form;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;


/**
 * Classe para receber dados do usuário e validá-los
 */
public class ChineloForm {
    
    /**
     * @NotBlank: campo não pode ser nulo nem vazio
     * @Length: tamanho limite do campo
     */
    @NotBlank(message = "Campo nome não pode estar vazio!") @Length(max = 100)
    private String nome;	

    @NotBlank(message = "Campo descrição não pode estar vazio!")  @Length(max = 255)
	private String descricao;	

    /**
     * @Pattern: define o padrão requisitado para inserção do valor. Exemplo:
     * aceito: 200.00, 200;
     * não aceito: .200, 200., -120.00
     */
    @Pattern(regexp = "^\\d+(\\.\\d+{2})?$", message = "Valor inválido!")
	@NotBlank
	private String valor;	

    @NotBlank @Length(max = 255)
	private String urlImagem;	

    @NotEmpty
    private List<Long> idTamanhos;
    
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValor() {
        return this.valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getUrlImagem() {
        return this.urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public List<Long> getIdTamanhos() {
        return idTamanhos;
    }

    public void setIdTamanhos(List<Long> idTamanhos) {
        this.idTamanhos = idTamanhos;
    }
}
