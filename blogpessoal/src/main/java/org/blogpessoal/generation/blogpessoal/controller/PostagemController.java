package org.blogpessoal.generation.blogpessoal.controller;

import java.util.List; 
import org.blogpessoal.generation.blogpessoal.model.Postagem;	
import org.blogpessoal.generation.blogpessoal.repository.PostagemRepository; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RestController; 

@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagemController {
	
	@Autowired
	private PostagemRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Postagem>> buscaTodasAsPostagens(){
		return ResponseEntity.ok(repository.findAll());
	}
	@GetMapping("/id")
	public ResponseEntity<Postagem> getById(@PathVariable Long id){
		return repository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<Postagem> getByTitulo(@PathVariable Long titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
}
