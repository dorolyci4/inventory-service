package ca.socom.inventoryservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// Entite JPA
@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
class Product {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double price;
}

@RepositoryRestResource
interface ProductRepository extends JpaRepository<Product, Long>{
}

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(ProductRepository productRepository,
			RepositoryRestConfiguration repositoryRestConfiguration) { // pour exposer id de la classe
		return args->{
			//
			repositoryRestConfiguration.exposeIdsFor(Product.class);  //pour exposer id de la classe, on pour utiliser une projection
			productRepository.save(new Product(null, "Ord HP 3590",1000));
			productRepository.save(new Product(null, "Ord MacBook Pro",1500));
			productRepository.save(new Product(null, "Impriamde Canon TS3120",200));
			productRepository.findAll().forEach(System.out::println);
			
	   
		};
	}

}
