package baseDeDatos;

import usuarios.*;
import java.util.Map;
import java.util.Scanner;

public class VerDatos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RepositorioUsuario repo = RepositorioUsuario.getInstancia();
        
        // Autenticación de gestor
        System.out.println("=== ACCESO RESTRINGIDO - SOLO GESTORES ===");
        System.out.print("Usuario: ");
        String nickName = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasenna = scanner.nextLine();
        
        // Validar credenciales
        Usuario usuario = repo.validarCredenciales(nickName, contrasenna);
        
        if (usuario == null) {
            System.out.println("\nError: Credenciales incorrectas.");
            scanner.close();
            return;
        }
        
        // Verificar que sea un Gestor
        if (!(usuario instanceof Gestor)) {
            System.out.println("\nAcceso denegado: Solo los gestores pueden ver esta información.");
            scanner.close();
            return;
        }
        
        // Si es gestor, mostrar datos
        System.out.println("\nAcceso concedido. Bienvenido, " + usuario.getNombre());
        System.out.println("\n=== CONTENIDO DEL ARCHIVO usuarios.dat ===\n");
        System.out.println("Total de usuarios: " + repo.cantidadUsuarios());
        System.out.println("\n" + "=".repeat(100) + "\n");
        
        Map<String, Usuario> usuarios = repo.obtenerTodos();
        
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios en la base de datos.");
        } else {
            int contador = 1;
            for (Map.Entry<String, Usuario> entry : usuarios.entrySet()) {
                Usuario u = entry.getValue();
                System.out.println("Usuario #" + contador++);
                System.out.println("  Nombre:      " + u.getNombre());
                System.out.println("  NickName:    " + u.getNickName());
                System.out.println("  Contraseña:  *****");
                System.out.println("  DNI:         " + u.getDni());
                System.out.println("  Pasaporte:   " + u.getPasaporte());
                System.out.println("  Fecha Alta:  " + u.getFechaAlta());
                System.out.println("  ID Único:    " + u.getIdUnico());
                System.out.println("  Tipo:        " + u.getClass().getSimpleName());
                System.out.println("-".repeat(80));
            }
        }
        
        System.out.println("\n=== FIN DEL REPORTE ===");
        scanner.close();
    }
}
