//Aqui é onde serão efetuadas as operações 
package com.example.demo.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.User;
import com.example.demo.repositories.UserRepository;

//@Component //Quando você tem um objeto que sera injetado pelo mecanismo de dependencia do Spring, a classe desse objeto tem que estar registrada no mecanismo de injeção de dependencia
@Service //o Service é um component mais especifico, assim ficando mais semanticamente especifico

public class UserService {
	@Autowired //fazer a injeção de dependancia do Repository
	private UserRepository repository; //tem que ter uma dependencia com o Repository
	
	public List<User> findAll(){   //fazendo um simples método para retornar todos os usuarios
		return repository.findAll(); //operação findAll que ja vem da interface do JPA, que consequentemente se aplica a interface UserRepository
	}
	
	public User findById(int id) {
		Optional<User> obj = repository.findById(id); //Retorna um optional que é uma variavel que ou retorna o objeto ou retorna um vazio, evitando assim varios erros como NullPointer,etc
		return obj.get(); //retorna o obj optional
	}

}
