package br.com.slippers.api.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.slippers.api.dto.ChineloDTO;
import br.com.slippers.api.form.ChineloForm;
import br.com.slippers.api.model.Chinelo;
import br.com.slippers.api.model.Tamanho;
import br.com.slippers.api.repository.CarrinhoHasChineloRepository;
import br.com.slippers.api.repository.CarrinhoRepository;
import br.com.slippers.api.repository.ChineloRepository;
import br.com.slippers.api.repository.TamanhoRepository;
import javassist.NotFoundException;

@Service
public class ChineloService {

    @Autowired
    CarrinhoRepository carrinhoR;

    @Autowired
    CarrinhoHasChineloRepository cHasChineloRepository;

    @Autowired
    ChineloRepository chineloR;

    @Autowired
    TamanhoRepository tamanhoR;

    public Page<ChineloDTO> listChinelosString(String nomeChinelo, int pagina, int qtdItensPagina) throws NotFoundException{
        Pageable paginacao = PageRequest.of(pagina, qtdItensPagina);
        if (nomeChinelo == null) {
            Page<Chinelo> chinelos = chineloR.findAll(paginacao);
            if(chinelos.isEmpty()) {
            throw new NotFoundException("Chinelos não encontrados, verifique sua paginação! (Página começa em 0)");
            }
            return Chinelo.toPageDTO(chinelos);
        } else {
            Page<Chinelo> chinelos = chineloR.findByNome(nomeChinelo, paginacao);
            if(chinelos.isEmpty()) {
                throw new NotFoundException("Chinelo não encontrado!");
            }
            return Chinelo.toPageDTO(chinelos);
        }
    }


    public ResponseEntity<ChineloDTO> newChinelo(ChineloForm cForm, UriComponentsBuilder builder)
        throws NotFoundException {

        List<Tamanho> tamanhos = tamanhoR.findAllById(cForm.getIdTamanhos());
        if(tamanhos.isEmpty()) {
            throw new NotFoundException("Tamanho não encontrado");
        }
		Chinelo chinelo = new Chinelo(cForm, tamanhos);
        
        chineloR.save(chinelo);
        /** 
         * {id} caminho variável, irá receber o id do chinelo criado
         * buildAndExpand irá construir a url e colocar a id do chinelo criado na variável
         */
		URI uri = builder.path("/{id}").buildAndExpand(chinelo.getId()).toUri();

        //irá retornar o código HTTP 201(created), a localização do item criado e o corpo do item criado
		return ResponseEntity.created(uri).body(chinelo.toDTO());
	}


    public ChineloDTO updateChinelo(Long id, ChineloForm cForm) throws NotFoundException {
		// Buscando o chinelo por id no banco de dados. Caso não encontre irá jogar uma NotFoundException
		Chinelo chinelo = chineloR.findById(id).orElseThrow(()
        -> new NotFoundException("Chinelo não encontrado!"));

        List<Tamanho> tamanhos = tamanhoR.findAllById(cForm.getIdTamanhos());
        if(tamanhos.isEmpty()) {
            throw new NotFoundException("Tamanho não encontrado");
        }

        /**
         * Convertendo e atualizando o model com os dados inseridos no form.
         * Como o model chinelo está no estado Managed, ao atualizar
         * seus dados, o JPA detecta e também atualiza no banco.
         */
        chinelo.setNome(cForm.getNome());
		chinelo.setDescricao(cForm.getDescricao());
		chinelo.setValor(Double.parseDouble(cForm.getValor()));
		chinelo.setUrlImagem(cForm.getUrlImagem());
		chinelo.setTamanhos(tamanhos);

		// Convertendo o model atualizado do chinelo para um dto e retornando com o código HTTP 200(ok)
		 return chinelo.toDTO();
    }

    public void deleteChinelo(Long id) throws NotFoundException {
        Chinelo chinelo = chineloR.findById(id).orElseThrow(()
          -> new NotFoundException("Chinelo não encontrado!"));
        chineloR.delete(chinelo);
    }
}