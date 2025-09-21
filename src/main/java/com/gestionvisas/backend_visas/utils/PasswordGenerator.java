package com.gestionvisas.backend_visas.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordGenerator implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        /*BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "passf2"; // Aquí la contraseña que quieres encriptar
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println("====================================");
        System.out.println("Contraseña original: " + rawPassword);
        System.out.println("Contraseña encriptada: " + encodedPassword);
        System.out.println("====================================");

        String rawPassword2 = "passa1"; // Aquí la contraseña que quieres encriptar
        String encodedPassword2 = encoder.encode(rawPassword2);

        System.out.println("====================================");
        System.out.println("Contraseña original: " + rawPassword2);
        System.out.println("Contraseña encriptada: " + encodedPassword2);
        System.out.println("====================================");

        String rawPassword3 = "passa2"; // Aquí la contraseña que quieres encriptar
        String encodedPassword3 = encoder.encode(rawPassword3);

        System.out.println("====================================");
        System.out.println("Contraseña original: " + rawPassword3);
        System.out.println("Contraseña encriptada: " + encodedPassword3);
        System.out.println("====================================");*/
    }
}
