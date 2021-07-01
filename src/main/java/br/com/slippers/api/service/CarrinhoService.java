package br.com.slippers.api.service;

import java.rmi.AlreadyBoundException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.slippers.api.dto.CarrinhoDTO;
import br.com.slippers.api.form.CarrinhoForm;
import br.com.slippers.api.model.Carrinho;
import br.com.slippers.api.model.Chinelo;
import br.com.slippers.api.model.Usuario;
import br.com.slippers.api.repository.CarrinhoHasChineloRepository;
import br.com.slippers.api.repository.CarrinhoRepository;
import br.com.slippers.api.repository.ChineloRepository;
import javassist.NotFoundException;

@Service
public class CarrinhoService {
     //@Autowired injetando dependência na classe
     @Autowired
     CarrinhoRepository carrinhoR;

     @Autowired
     CarrinhoHasChineloRepository cHasChineloRepository;
 
     @Autowired
     ChineloRepository chineloR;

     public CarrinhoDTO listCarrinho(Pageable paginarChinelos) throws NotFoundException {

        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<Carrinho> carrinho = carrinhoR.findByUsuarioEmail(usuario.getEmail());
        if(carrinho.isEmpty()) {
            throw new NotFoundException("Carrinho não encontrado!");
        }
        return carrinho.get().toDTO();
     }
    

     public CarrinhoDTO insertChinelo(CarrinhoForm cForm) 
     throws NotFoundException, AlreadyBoundException {

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String usuario = loggedInUser.getName();
        Carrinho carrinho = carrinhoR.findByUsuarioEmail(usuario).orElseThrow(()
        -> new NotFoundException("Usuário não encontrado!"));

        Chinelo chinelo = chineloR.findById(cForm.getIdChinelo()).orElseThrow(()
        -> new NotFoundException("Chinelo não encontrado!"));

        carrinho.addChinelo(chinelo, cForm.getQuantidade());
        
        return carrinho.toDTO();
     }
 

     public CarrinhoDTO deleteChinelo(String idChinelo) throws NotFoundException {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Carrinho carrinho = carrinhoR.findByUsuarioEmail(usuario.getEmail()).orElseThrow(()
        -> new NotFoundException("Usuário não encontrado!"));
        
        carrinho.deleteChinelo(idChinelo);
        cHasChineloRepository.deleteByChineloId(Long.parseLong(idChinelo));
        
        return carrinho.toDTO();
     }


     public ResponseEntity<CarrinhoDTO> updateChineloCarrinho(CarrinhoForm cForm) throws NotFoundException {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Carrinho carrinho = carrinhoR.findByUsuarioEmail(usuario.getEmail()).orElseThrow(()
        -> new NotFoundException("Usuário não encontrado!"));
        
        carrinho.alterarQtdChineloCarrinho(cForm.getIdChinelo(), cForm.getQuantidade());
        return ResponseEntity.ok(carrinho.toDTO());
    }
}
