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
            productRepository.save(Product.builder()
                    .name("Tablet")
                    .description("Tablet description")
                    .price(2300)
                    .quantity(25)
                    .build());

            productRepository.save(Product.builder()
                    .name("Monitor")
                    .description("Full HD monitor 24 inches")
                    .price(1500)
                    .quantity(20)
                    .build());

            productRepository.save(Product.builder()
                    .name("Keyboard")
                    .description("Mechanical keyboard with RGB")
                    .price(500)
                    .quantity(40)
                    .build());

            productRepository.save(Product.builder()
                    .name("Mouse")
                    .description("Wireless ergonomic mouse")
                    .price(300)
                    .quantity(35)
                    .build());

            productRepository.save(Product.builder()
                    .name("Headphones")
                    .description("Noise-cancelling headphones")
                    .price(800)
                    .quantity(18)
                    .build());

            productRepository.save(Product.builder()
                    .name("Webcam")
                    .description("HD webcam with microphone")
                    .price(600)
                    .quantity(22)
                    .build());


            productRepository.findAll().forEach(p-> System.out.println(p.toString()));
        };

    }

}
