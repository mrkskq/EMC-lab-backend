package mk.ukim.finki.emclab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// lab2 - 6. za schedule event za materialized view
@EnableScheduling
@SpringBootApplication
public class EmcLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmcLabApplication.class, args);
    }

    // lab3
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
