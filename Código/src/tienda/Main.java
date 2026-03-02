package tienda;
import java.util.Scanner;
import servicioAutenticacion.ServicioAutenticacion;
import usuarios.ClienteRegistrado;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ServicioAutenticacion servicio = new ServicioAutenticacion();
        
        System.out.println("-----Bienvenido a la Tienda A ELLOS-----");
        System.out.println("          -----ACCESO A APP-----        ");
        System.out.println("\n1. Iniciar sesión");
        System.out.println("2. Registrarse");
        System.out.print("\nSeleccione una opción: ");
        
        int opcion = scanner.nextInt();
        scanner.nextLine(); 
        
        switch(opcion) {
            case 1:
                System.out.print("Usuario: ");
                String usuario = scanner.nextLine();
                System.out.print("Contraseña: ");
                String pwd = scanner.nextLine();
                
                Boolean exito = servicio.iniciarSesion(usuario, pwd);
                if (exito) {
                    System.out.println("Sesión iniciada correctamente");
                } else {
                    System.out.println("Error al iniciar sesión");
                }
                break;
                
            case 2:
                System.out.print("Nombre completo: ");
                String nombre = scanner.nextLine();
                System.out.print("Nombre de usuario (nickname): ");
                String nuevoUsuario = scanner.nextLine();
                System.out.print("Contraseña: ");
                String nuevoPwd = scanner.nextLine();
                System.out.print("DNI: ");
                String dni = scanner.nextLine();
                System.out.print("Pasaporte: ");
                String pasaporte = scanner.nextLine();
                
                ClienteRegistrado cliente = servicio.registrarse(nombre, nuevoUsuario, nuevoPwd, dni, pasaporte);
                if (cliente != null) {
                    System.out.println("Usuario registrado correctamente");
                } else {
                    System.out.println("Error al registrar usuario");
                }
                break;
                
            default:
                System.out.println("Opción no válida");
                break;
        }
        
        scanner.close();
    }
  
}