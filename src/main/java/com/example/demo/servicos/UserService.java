//Aqui é onde serão efetuadas as operações 
package com.example.demo.servicos;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.servicos.excecoes.DatabaseException;
import com.example.demo.servicos.excecoes.ResourceNotFoundException;

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
		return obj.orElseThrow(()->new ResourceNotFoundException(id) ); //retorna o obj optional
	}

	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public void delete(Integer id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public User update(Integer id, User obj) {
		try {
			User entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}	
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
		
		
		
	}
}
