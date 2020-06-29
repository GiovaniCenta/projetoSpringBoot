//É uma classe especifica de configuração para teste
package com.example.demo.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner; //serve para executar quando o programa for rodado
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.demo.entidades.Category;
import com.example.demo.entidades.Order;
import com.example.demo.entidades.Product;
import com.example.demo.entidades.User;
import com.example.demo.entidades.enums.OrderStatus;
import com.example.demo.entidades.pk.OrderItem;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.OrderItemRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;

@Configuration //necessário para ser uma configuração de teste
@Profile("test")
public class TestConfig implements CommandLineRunner { //nesse momento essa classe sera para popular nosso database com testes
	@Autowired //pro Spring conseguir resolver a dependencia e associar o userRepository ao TestConfig
	private UserRepository userRepository; //aqui tem uma injeção de dependencia, pois essa classe precisa acessar o repositorio para acessar o DB
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderitemRepository;
	
	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); 
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"),OrderStatus.AGUARDANDO_PAGAMENTO, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"),OrderStatus.AGUARDANDO_PAGAMENTO, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.PAGO,u1); 
		
		Category c1= new Category(null,"Computador");
		Category c2 = new Category(null,"Video-game");
		Category c3 = new Category(null,"Livro");
		Category c4 = new Category(null,"Eletronico");
		
		Product p1 = new Product(null,"PS4","Playstation 4",2500.00,"sony.com/ps4.png");
		Product p2 = new Product(null,"Revolucao dos bixos","psdjsuhda dsahudashudsa",50.00,"livros.com/rvdb.png");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, ""); 
		
		categoryRepository.saveAll(Arrays.asList(c1,c2,c3,c4));
		userRepository.saveAll(Arrays.asList(u1,u2));
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));//salvar essa lista no banco de dados
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		 
		p1.getCategories().add(c1); //adicionar categorias aos produtos
		p1.getCategories().add(c4);
		p2.getCategories().add(c3);
		p3.getCategories().add(c1);
		p3.getCategories().add(c4);
		p4.getCategories().add(c4);
		p4.getCategories().add(c1);
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4));
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice()); 
		orderitemRepository.saveAll(Arrays.asList(oi1,oi2,oi3,oi4));
		
	}
}
