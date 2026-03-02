package servicioAutenticacion;

import baseDeDatos.RepositorioUsuario;
import java.time.LocalDate;
import usuarios.*;

public class ServicioAutenticacion {
    private RepositorioUsuario repositorio;
    private Usuario usuarioActual;

    public ServicioAutenticacion() {
        this.repositorio = RepositorioUsuario.getInstancia();
        this.usuarioActual = null;
    }

    public Boolean iniciarSesion(String nickName, String pwd) {
        Usuario usuario = repositorio.validarCredenciales(nickName, pwd);
        if (usuario != null) {
            usuarioActual = usuario;
            System.out.println("Bienvenido, " + usuario.getNombre());
            return true;
        }
        return false;
    }

    public ClienteRegistrado registrarse(String nombre, String nickName, String pwd, String dni, String pasaporte) {
        ClienteRegistrado nuevoCliente = new ClienteRegistrado(nombre, nickName, pwd, dni, pasaporte, LocalDate.now());
        
        boolean exito = repositorio.agregarUsuario(nuevoCliente, nickName, dni, pasaporte, nuevoCliente.getIdUnico());
        
        if (exito) {
            System.out.println("Usuario registrado exitosamente");
            return nuevoCliente;
        } else {
            System.out.println("No se pudo registrar el usuario");
            return null;
        }
    }

    public Boolean cerrarSesion() {
        if (usuarioActual != null) {
            usuarioActual = null;
            System.out.println("Sesión cerrada");
            return true;
        }
        return false;
    }
    
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
    
}
