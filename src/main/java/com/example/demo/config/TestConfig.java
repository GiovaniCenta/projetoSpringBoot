//É uma classe especifica de configuração para teste
package com.example.demo.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner; //serve para executar quando o programa for rodado
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.demo.entidades.Order;
import com.example.demo.entidades.User;
import com.example.demo.entidades.enums.OrderStatus;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.UserRepository;

@Configuration //necessário para ser uma configuração de teste
@Profile("test")
public class TestConfig implements CommandLineRunner { //nesse momento essa classe sera para popular nosso database com testes
	@Autowired //pro Spring conseguir resolver a dependencia e associar o userRepository ao TestConfig
	private UserRepository userRepository; //aqui tem uma injeção de dependencia, pois essa classe precisa acessar o repositorio para acessar o DB
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); 
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"),OrderStatus.AGUARDANDO_PAGAMENTO, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"),OrderStatus.AGUARDANDO_PAGAMENTO, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.PAGO,u1); 
		
		userRepository.saveAll(Arrays.asList(u1,u2));
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));//salvar essa lista no banco de dados
		
		 
	}
}
