package usuarios;
import java.time.LocalDate;
import java.io.Serializable;

public abstract class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String nickName;
    private String contrasenna;
    private String dni;
    private String pasaporte;
    private LocalDate fechaAlta;
    private String idUnico;

    public Usuario(String nombre, String nickName, String contrasenna, String dni, 
        String pasaporte, LocalDate fechaAlta, String idUnico) {
            this.nombre = nombre;
            this.nickName = nickName;
            this.contrasenna = contrasenna;
            this.dni = dni;
            this.pasaporte = pasaporte;
            this.fechaAlta = fechaAlta;
            this.idUnico = idUnico;
        }

    public String getNombre() {
        return nombre;
    }

    public String getNickName() {
        return nickName;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public String getDni() {
        return dni;
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public String getIdUnico() {
        return idUnico;
    }


}

