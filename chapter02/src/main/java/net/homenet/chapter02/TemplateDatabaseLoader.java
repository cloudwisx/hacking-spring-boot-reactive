package net.homenet.chapter02;

import net.homenet.chapter02.domain.Item;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

@Component
public class TemplateDatabaseLoader {

    @Bean
    public CommandLineRunner initialize(MongoOperations mongoOperations) {
        return args -> {
            mongoOperations.save(new Item("Alf alarm clock", 19.99));
            mongoOperations.save(new Item("Smurf TV tray", 24.99));
        };
    }

}
