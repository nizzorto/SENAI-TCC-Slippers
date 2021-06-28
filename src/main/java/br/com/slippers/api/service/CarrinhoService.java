package br.com.slippers.api.service;

import java.rmi.AlreadyBoundException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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

     public ResponseEntity<?> listCarrinho(Pageable paginarChinelos) throws NotFoundException {
         Usuario usuario = new Usuario();
         usuario.setIdUsuario(1L);
         usuario.setEmail("ee@email");
         usuario.setTotalCompras(0);
         
         Optional<Carrinho> carrinho = carrinhoR.findByUsuario(usuario);
         if(carrinho.isEmpty()) {
             throw new NotFoundException("Carrinho não encontrado!");
         }
         return ResponseEntity.ok(carrinho.get().toDTO());
     }
    

     public ResponseEntity<?> insertChinelo(CarrinhoForm cForm) 
     throws NotFoundException, AlreadyBoundException {
 
         Usuario usuario = new Usuario();
         usuario.setIdUsuario(1L);
         usuario.setEmail("ee@email");
         usuario.setTotalCompras(0);
 
         Carrinho carrinho = carrinhoR.findByUsuario(usuario).orElseThrow(()
         -> new NotFoundException("Usuário não encontrado!"));
 
         Optional<Chinelo> chinelo = chineloR.findById(cForm.getIdChinelo());
 
         carrinho.addChinelo(chinelo.get(), cForm.getQuantidade());
         
         return ResponseEntity.ok(carrinho.toDTO());
     }
 

     public ResponseEntity<?> deleteChinelo(String idChinelo) throws NotFoundException {
         Usuario usuario = new Usuario();
         usuario.setIdUsuario(1L);
         usuario.setEmail("ee@email");
         usuario.setTotalCompras(0);
 
         Carrinho carrinho = carrinhoR.findByUsuario(usuario).orElseThrow(()
         -> new NotFoundException("Usuário não encontrado!"));
         
         carrinho.deleteChinelo(idChinelo);
         cHasChineloRepository.deleteByChineloId(Long.parseLong(idChinelo));
         
         return ResponseEntity.ok(carrinho.toDTO());
     }


     public ResponseEntity<CarrinhoDTO> updateChineloCarrinho(CarrinhoForm cForm) throws NotFoundException {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setEmail("ee@email");
        usuario.setTotalCompras(0);

        Carrinho carrinho = carrinhoR.findByUsuario(usuario).orElseThrow(()
        -> new NotFoundException("Usuário não encontrado!"));
        
        carrinho.alterarQtdChineloCarrinho(cForm.getIdChinelo(), cForm.getQuantidade());
        return ResponseEntity.ok(carrinho.toDTO());
    }
}
