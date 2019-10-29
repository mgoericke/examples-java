package de.javamark.cardatabase;

import de.javamark.cardatabase.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CardatabaseApplication {

    public static void main(final String[] args) {
        SpringApplication.run(CardatabaseApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Bean
    CommandLineRunner runner() {
        return args -> {

            final User user = new User("user", "$2a$10$HLQhymbKzHuznpKX4qNpFuSPDEIZZqQIVUS9M5SnVpQCnScZSgAgK", "USER");
            final User admin = new User("admin", "$2a$10$52uRlXENwkZjt2CjJxk1r.tw5pf5lJlt2sW8CX8k2abwB5.yhD85e", "ADMIN");

            if (userRepository.findByUsername("user") == null) {
                userRepository.save(user);
            }
            if (userRepository.findByUsername("admin") == null) {
                userRepository.save(admin);
            }

            final Owner owner1 = new Owner("John", "Müller");
            final Owner owner2 = new Owner("Otto", "Meier");
            ownerRepository.save(owner1);
            ownerRepository.save(owner2);

            carRepository.save(new Car("Mercedes", "C-Klasse", "Metallic", "B-MX1505", 2015, 27000, owner1));
            carRepository.save(new Car("Nissan", "Leaf", "White", "ADF-1234", 2014, 18000, owner1));
            carRepository.save(new Car("Toyota", "Prius", "Silver", "KKO-435", 2018, 39000, owner2));
        };
    }
}
