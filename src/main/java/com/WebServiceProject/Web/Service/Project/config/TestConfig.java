package com.WebServiceProject.Web.Service.Project.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.WebServiceProject.Web.Service.Project.entities.Category;
import com.WebServiceProject.Web.Service.Project.entities.Order;
import com.WebServiceProject.Web.Service.Project.entities.Product;
import com.WebServiceProject.Web.Service.Project.entities.User;
import com.WebServiceProject.Web.Service.Project.entities.enums.OrderStatus;
import com.WebServiceProject.Web.Service.Project.repositories.CategoryRepository;
import com.WebServiceProject.Web.Service.Project.repositories.OrderRepository;
import com.WebServiceProject.Web.Service.Project.repositories.ProductRepository;
import com.WebServiceProject.Web.Service.Project.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Eletrônicos");
		Category cat2 = new Category(null, "Eletrodomésticos");
		Category cat3 = new Category(null, "Computadores");

		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

		Order o1 = new Order(null, Instant.parse("2025-09-29T20:16:00Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2025-09-29T20:16:00Z"), OrderStatus.WAITING_PAYMENT, u2);

		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2));
	}
}
