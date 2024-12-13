package pe.edu.yheremiramos.back_sistema_reservas;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@CommonsLog
@SpringBootApplication
public class BackSistemaReservasApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackSistemaReservasApplication.class, args);

        log.info("¡Terminó de cargar el proyecto Yheremi ... Fuerza!");

    }

}
