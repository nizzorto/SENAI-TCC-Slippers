package br.com.slippers.api.components;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.slippers.api.dto.TamanhoDTO;
import br.com.slippers.api.form.TamanhoForm;
import br.com.slippers.api.model.Tamanho;

@Component
public class TamanhoConverter {
    
    //Convertendo TamanhoForm para model Tamanho
    public Tamanho toTamanho(TamanhoForm tForm, Tamanho tamanho){
        tamanho.setDescricao(tForm.getDescricao());
        return tamanho;
    }

    //Convertendo Lista de models tamanho para uma lista de tamanhos DTO
    public List<TamanhoDTO> toListTamanhoDTO(List<Tamanho> tamanhos) {
        return tamanhos.stream().map(TamanhoDTO::new).toList();
    }    

    public List<Tamanho> toListTamanho(List<TamanhoDTO> tamanhosDTO) {
        return tamanhosDTO.stream().map(Tamanho::new).toList();
    }
}
