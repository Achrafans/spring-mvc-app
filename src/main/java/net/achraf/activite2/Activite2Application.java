package net.achraf.activite2;

import net.achraf.activite2.model.Product;
import net.achraf.activite2.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication
public class Activite2Application {

    public static void main(String[] args) {
        SpringApplication.run(Activite2Application.class, args);
    }

    @Bean
   public CommandLineRunner start(ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder()
                            .name("Computer")
                            .description("Computer description")
                            .price(5400)
                            .quantity(12)
                             .build());

            productRepository.save(Product.builder()
                    .name("Printer")
                    .description("Printer description")
                    .price(1200)
                    .quantity(11)
                    .build());

            productRepository.save(Product.builder()
                    .name("Smartphone")
                    .description("Smartphone description")
                    .price(1300)
                    .quantity(33)
                    .build());

            productRepository.findAll().forEach(p-> System.out.println(p.toString()));
        };

    }

}
