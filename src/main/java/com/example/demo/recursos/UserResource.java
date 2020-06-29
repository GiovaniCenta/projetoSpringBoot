package com.example.demo.recursos;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entidades.User;
import com.example.demo.servicos.UserService;


@RestController //Serve para dizer que é um recurso web controlado por um rest controller(resource)
@RequestMapping(value = "/users",method=RequestMethod.GET) //É o caminho do meu recurso
public class UserResource { //disponibiliza um recurso WEB correspondente a classe User
	
	@Autowired 
	private UserService service; //injetar a dependencia do service, que é classe que tem os métodos desejados
	
	@GetMapping //pra instanciar que responde a uma requisição do tipo get do HTTP
	public ResponseEntity<List<User>> findAll(){ //Response entity é um recurso do Spring para retornar as requisições web feitas
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list); //retorna o body da requisição web que foi feita
	}
	
	@GetMapping(value="/{id}") //isso indica que minha requisição vai aceitar um Id dentro da url
	public ResponseEntity<User> findById(@PathVariable Integer id){ //pra receber o parametro Id do controlador rest tem que declarar e pro spring aceitar tem que se usar o PathVariable
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping //pré processamento pro compilador entender um método de inserção
	public ResponseEntity<User> insert(@RequestBody User obj){  //precisa da anotação para fazer a serialização e desserialização do objeto
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).body(obj); //created espera a localizacao do objeto, por tanto tem que pegar o endereço dela pelo URI
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();  //retorna uma resposta no content, uma resposta vazia, o codigo de eero que nao existe é 204
		//Erro 500 é quando tem pedidos associados a esse usuario, o que faz o banco de dados nao permitir a deleçao
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<User> update (@PathVariable Integer id,@RequestBody User obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	

}
