package mk.ukim.finki.emclab;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenerateHashes {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        System.out.println("admin: " + encoder.encode("admin"));
        System.out.println("user: " + encoder.encode("user"));
    }
}