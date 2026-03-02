package usuarios;
import java.time.LocalDate;

public class ClienteNoRegistrado {
    private String nombre;
    private String dni;
    private String pasaporte;

    public ClienteNoRegistrado(String nombre, String dni, String pasaporte) {
        this.nombre = nombre;
        this.dni = dni;
        this.pasaporte = pasaporte;
    }

    public ClienteRegistrado registrarCliente(String usuario, String pwd) {
        return new ClienteRegistrado(
                nombre,
                usuario,
                pwd,
                dni,
                pasaporte,
                LocalDate.now()
        );
    }

}
