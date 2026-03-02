package usuarios;
import java.time.LocalDate;
import java.util.UUID;

public class ClienteRegistrado extends Usuario {

    public ClienteRegistrado(String nombre, String nickName, String contrasenna, String dni, 
        String pasaporte, LocalDate fechaAlta) {
            super(nombre, nickName, contrasenna, dni, pasaporte, fechaAlta, UUID.randomUUID().toString());
        }

    
    

    
}

