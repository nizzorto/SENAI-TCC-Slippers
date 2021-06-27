package br.com.slippers.api.components;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.slippers.api.dto.ChineloDTO;
import br.com.slippers.api.form.ChineloForm;
import br.com.slippers.api.model.Chinelo;
import br.com.slippers.api.model.Tamanho;

/**
 * @Component: definindo a classe como um componente instanciado pelo spring
 * Classe para realizar as conversões entre as classes de transferência relacionadas a chinelo
 */
@Component
public class ChineloConverter {
    
    @Autowired
    TamanhoConverter tConverter;

        //Convertendo ChineloForm para model Chinelo
        public Chinelo toChinelo(ChineloForm cForm, List<Tamanho> tamanhos, Chinelo chinelo){
            chinelo.setNome(cForm.getNome());
            chinelo.setDescricao(cForm.getDescricao());
            chinelo.setValor(Double.parseDouble(cForm.getValor()));
            chinelo.setUrlImagem(cForm.getUrlImagem());
            chinelo.setTamanhos(tamanhos);
            return chinelo;
        }
    
    //Convertendo model Chinelo para ChineloDTO
    public ChineloDTO toDTO(Chinelo chinelo) {
        return new ChineloDTO(
        chinelo.getId(),
        chinelo.getNome(),
        chinelo.getDescricao(),
        chinelo.getValor(),
        chinelo.getUrlImagem(),
        chinelo.getMediaNota(),
        chinelo.getTotalVendas(),
        chinelo.getTotalEstrelas(),
        chinelo.getDataCriacao(),
        tConverter.toListTamanhoDTO(chinelo.getTamanhos())
        );
    }

    //Convertendo Page de models chinelo para uma lista de chinelos DTO
    public Page<ChineloDTO> toPageChineloDTO(Page<Chinelo> chinelos) {
        return chinelos.map(this::toDTO);
    }    

    public List<ChineloDTO> toListChineloDTO(List<Chinelo> chinelos) {
        return chinelos.stream().map(this::toDTO).toList();
    }
}
