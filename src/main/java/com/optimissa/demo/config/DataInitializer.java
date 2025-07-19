package com.optimissa.demo.config;

import com.optimissa.demo.model.AuthUser;
import com.optimissa.demo.model.CatItem;
import com.optimissa.demo.repository.AuthUserRepository;
import com.optimissa.demo.repository.CatItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(AuthUserRepository userRepository, CatItemRepository catItemRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.count() == 0) {
                AuthUser user1 = new AuthUser(null, "admin@example.com", passwordEncoder.encode("admin123"));
                AuthUser user2 = new AuthUser(null, "test@example.com", passwordEncoder.encode("test123"));
                userRepository.save(user1);
                userRepository.save(user2);

                System.out.println("Usuarios de prueba creados.");
            }
            if (catItemRepository.count() == 0) {
                catItemRepository.save(new CatItem(null, "Vainilla", "Descripción prueba Helado de Vainilla"));
                catItemRepository.save(new CatItem(null, "Chocolate", "Descripción prueba Helado de Chocolate"));
                catItemRepository.save(new CatItem(null, "Fresa", "Descripción prueba Helado de Fresa"));
                System.out.println("Catalogos de prueba creados.");
            }
        };
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
