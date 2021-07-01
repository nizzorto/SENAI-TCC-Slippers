package br.com.slippers.api.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.slippers.api.dto.TamanhoDTO;
import br.com.slippers.api.form.TamanhoForm;
import br.com.slippers.api.model.Tamanho;
import br.com.slippers.api.repository.TamanhoRepository;
import javassist.NotFoundException;

@Service
public class TamanhoService {
    @Autowired
    TamanhoRepository tamanhoR;
    
	public List<TamanhoDTO> listTamanhos() throws NotFoundException {
        
        List<Tamanho> tamanhos = tamanhoR.findAll();
        if(tamanhos.isEmpty()) {
            throw new NotFoundException("Não há tamanhos cadastrados no BD");
        }
        return Tamanho.toListDTO(tamanhos);
    }

	public ResponseEntity<TamanhoDTO> newTamanho(TamanhoForm tForm, UriComponentsBuilder builder) 
    throws NotFoundException {

		Tamanho tamanho = Tamanho.toTamanho(tForm);
        tamanhoR.save(tamanho);
        /** 
         * {id} caminho variável, irá receber o id do tamanho criado
         * buildAndExpand irá construir a url e colocar a id do tamanho criado na variável
         */
		URI uri = builder.path("/{id}").buildAndExpand(tamanho.getId()).toUri();

        //irá retornar o código HTTP 201(created), a localização do item criado e o corpo do item criado
		return ResponseEntity.created(uri).body(new TamanhoDTO(tamanho));
	}

   
	public TamanhoDTO updateTamanho(Long id, TamanhoForm tForm) throws NotFoundException {

		// Buscando o tamanho por id no banco de dados. Caso não encontre irá jogar uma NotFoundException
		Tamanho tamanho = tamanhoR.findById(id).orElseThrow(()
        -> new NotFoundException("Tamanho não encontrado!"));
        tamanho.setDescricao(tForm.getDescricao());

        /**
         * Convertendo e atualizando o model com os dados inseridos no form.
         * Como o model tamanho está no estado Managed, ao atualizar
         * seus dados, o JPA detecta e também atualiza no banco.
         */
        Tamanho.toTamanho(tForm);
		// Convertendo o model atualizado do tamanho para um dto e retornando com o código HTTP 200(ok)
		 return new TamanhoDTO(tamanho);
	}

     /**
     * @DeleteMapping mapear a requisição delete para deletar um tamanho
     */
	
	public void deleteTamanho(Long id) throws NotFoundException {

        Tamanho tamanho = tamanhoR.findById(id).orElseThrow(()
        -> new NotFoundException("Tamanho não encontrado!"));
        tamanhoR.delete(tamanho);
	}
}
