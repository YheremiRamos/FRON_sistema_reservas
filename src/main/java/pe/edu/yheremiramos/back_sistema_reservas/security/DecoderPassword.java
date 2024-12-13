package pe.edu.yheremiramos.back_sistema_reservas.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class DecoderPassword {

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String password = "1234";
        String encodedPassword = "$2a$10$1DyzGkJ0ufqMHDgdTXSTtOXMU/URNLvihfFIPZm1uEl0QJXKlDNpS";

        boolean isPasswordMatch = passwordEncoder.matches(password, encodedPassword);
        System.out.println("Password : " + password + "   isPasswordMatch    : " + isPasswordMatch);

    }

}
