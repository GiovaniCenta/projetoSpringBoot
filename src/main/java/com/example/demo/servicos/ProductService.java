//Aqui é onde serão efetuadas as operações 
package com.example.demo.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Product;
import com.example.demo.repositories.ProductRepository;

//@Component //Quando você tem um objeto que sera injetado pelo mecanismo de dependencia do Spring, a classe desse objeto tem que estar registrada no mecanismo de injeção de dependencia
@Service //o Service é um component mais especifico, assim ficando mais semanticamente especifico

public class ProductService {
	@Autowired //fazer a injeção de dependancia do Repository
	private ProductRepository repository; //tem que ter uma dependencia com o Repository
	
	public List<Product> findAll(){   //fazendo um simples método para retornar todos os usuarios
		return repository.findAll(); //operação findAll que ja vem da interface do JPA, que consequentemente se aplica a interface ProductRepository
	}
	
	public Product findById(int id) {
		Optional<Product> obj = repository.findById(id); //Retorna um optional que é uma variavel que ou retorna o objeto ou retorna um vazio, evitando assim varios erros como NullPointer,etc
		return obj.get(); //retorna o obj optional
	}

}
