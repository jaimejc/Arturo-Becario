package usuarios;

import java.time.LocalDate;
import java.util.UUID;

public class Empleado extends Usuario {
    public Empleado(String nombre, String nickName, String contrasenna, String dni, 
        String pasaporte, LocalDate fechaAlta) {
            super(nombre, nickName, contrasenna, dni, pasaporte, fechaAlta, UUID.randomUUID().toString());
        }
}
