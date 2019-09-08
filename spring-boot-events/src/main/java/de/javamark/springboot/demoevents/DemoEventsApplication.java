package de.javamark.springboot.demoevents;

import de.javamark.springboot.demoevents.domain.Lieferung;
import de.javamark.springboot.demoevents.domain.LieferungRepository;
import de.javamark.springboot.demoevents.service.LieferungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoEventsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoEventsApplication.class, args);
    }



    @Bean
    CommandLineRunner runner(LieferungService lieferungService) {
        return args -> {
            Lieferung created = lieferungService.createLieferung(Lieferung.builder().name("Roggenbrot").build());
            sleep(1000);
            lieferungService.deleteLieferung(created);
        };
    }
    private void sleep(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
        }
    }
}
