package baseDeDatos;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import usuarios.*;

public class RepositorioUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private static RepositorioUsuario instancia;
    private static final String ARCHIVO = "baseDeDatos/usuarios.dat";
    
    private HashMap<String, Usuario> usuarios; // Clave: nickName
    
    private RepositorioUsuario() {
        this.usuarios = new HashMap<>();
        cargarDatos();
    }
    
    public static RepositorioUsuario getInstancia() {
        if (instancia == null) {
            instancia = new RepositorioUsuario();
        }
        return instancia;
    }
    
    private void cargarDatos() {
        File archivo = new File(ARCHIVO);
        
        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
                usuarios = (HashMap<String, Usuario>) ois.readObject();
                System.out.println("Datos cargados correctamente. Total usuarios: " + usuarios.size());
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al cargar datos: " + e.getMessage());
                inicializarDatosIniciales();
            }
        } else {
            System.out.println("Archivo no encontrado. Creando datos iniciales...");
            inicializarDatosIniciales();
        }
    }
    
    private void guardarDatos() {
        try {
            File archivo = new File(ARCHIVO);
            archivo.getParentFile().mkdirs(); 
            
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
                oos.writeObject(usuarios);
                System.out.println("Datos guardados correctamente");
            }
        } catch (IOException e) {
            System.err.println("Error al guardar datos: " + e.getMessage());
        }
    }
    
    private void inicializarDatosIniciales() {
        System.out.println("Creando usuarios iniciales...");
        
        try {
            // Usuario 1
            ClienteRegistrado user1 = new ClienteRegistrado(
                "Juan Pérez", 
                "juanp", 
                "12345", 
                "12345678A", 
                "ABC123456", 
                java.time.LocalDate.of(2024, 1, 15)
            );
            usuarios.put("juanp", user1);
            
            // Usuario 2
            ClienteRegistrado user2 = new ClienteRegistrado(
                "María García", 
                "mariag", 
                "password", 
                "87654321B", 
                "XYZ789012", 
                java.time.LocalDate.of(2024, 2, 10)
            );
            usuarios.put("mariag", user2);
            
            // Usuario 3
            ClienteRegistrado user3 = new ClienteRegistrado(
                "Pedro López", 
                "pedrol", 
                "admin123", 
                "11223344C", 
                "DEF456789", 
                java.time.LocalDate.of(2024, 3, 5)
            );
            usuarios.put("pedrol", user3);

            // Usuario 4
            Gestor gest = new Gestor(
                "Jaime", 
                "Jme", 
                "AELLOS", 
                "452896067A", 
                "OPL562390", 
                java.time.LocalDate.of(2026, 2, 26)
            );
            usuarios.put("Jme", gest);
            
            System.out.println("Base de datos inicializada con " + usuarios.size() + " usuarios de prueba");
        } catch (Exception e) {
            System.err.println("Error al crear usuarios iniciales: " + e.getMessage());
        }
        
        guardarDatos();
    }
    
    public boolean agregarUsuario(Usuario usuario, String nickName, String dni, String pasaporte, String idUnico) {
        if (existeNickName(nickName)) {
            System.err.println("El nickname ya existe");
            return false;
        }
        if (existeDNI(dni)) {
            System.err.println("El DNI ya existe");
            return false;
        }
        if (existePasaporte(pasaporte)) {
            System.err.println("El pasaporte ya existe");
            return false;
        }
        if (existeIdUnico(idUnico)) {
            System.err.println("El ID único ya existe");
            return false;
        }
        
        usuarios.put(nickName, usuario);
        guardarDatos();
        return true;
    }
    
    public Usuario buscarPorNickName(String nickName) {
        return usuarios.get(nickName);
    }
    
    public Usuario validarCredenciales(String nickName, String contrasenna) {
        Usuario usuario = usuarios.get(nickName);
        if (usuario != null && usuario.getContrasenna().equals(contrasenna)) {
            return usuario;
        }
        return null;
    }
    
    public boolean existeNickName(String nickName) {
        return usuarios.containsKey(nickName);
    }
    
    public boolean existeDNI(String dni) {
        if (dni == null || dni.isEmpty()) return false;
        for (Usuario u : usuarios.values()) {
            if (dni.equals(u.getDni())) {
                return true;
            }
        }
        return false;
    }
    
    public boolean existePasaporte(String pasaporte) {
        if (pasaporte == null || pasaporte.isEmpty()) return false;
        for (Usuario u : usuarios.values()) {
            if (pasaporte.equals(u.getPasaporte())) {
                return true;
            }
        }
        return false;
    }
    
    public boolean existeIdUnico(String idUnico) {
        if (idUnico == null || idUnico.isEmpty()) return false;
        for (Usuario u : usuarios.values()) {
            if (idUnico.equals(u.getIdUnico())) {
                return true;
            }
        }
        return false;
    }
    
    public boolean eliminarUsuario(String nickName) {
        if (usuarios.remove(nickName) != null) {
            guardarDatos();
            return true;
        }
        return false;
    }
    
    public Map<String, Usuario> obtenerTodos() {
        return new HashMap<>(usuarios);
    }
    
    public int cantidadUsuarios() {
        return usuarios.size();
    }
}
