//É uma classe especifica de configuração para teste
package com.example.demo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner; //serve para executar quando o programa for rodado
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.demo.entidades.User;
import com.example.demo.repositories.UserRepository;

@Configuration //necessário para ser uma configuração de teste
@Profile("test")
public class TestConfig implements CommandLineRunner { //nesse momento essa classe sera para popular nosso database com testes
	@Autowired //pro Spring conseguir resolver a dependencia e associar o userRepository ao TestConfig
	private UserRepository userRepository; //aqui tem uma injeção de dependencia, pois essa classe precisa acessar o repositorio para acessar o DB

	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); 
		
		userRepository.saveAll(Arrays.asList(u1,u2)); //salvar essa lista no banco de dados
	}
}
