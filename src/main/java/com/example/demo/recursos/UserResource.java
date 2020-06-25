package com.example.demo.recursos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	

	

}
