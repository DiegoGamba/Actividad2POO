import java.util.ArrayList;
import java.util.Scanner;

class Jugador {
    private int id;
    private String nombre;
    private int edad;

    public Jugador(int id, String nombre, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Jugador [ID=" + id + ", Nombre=" + nombre + ", Edad=" + edad + "]";
    }
}

public class Main {
    private static ArrayList<Jugador> jugadores = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int countID = 0;

    public static void main(String[] args) {
        int opcion;

        do {
            System.out.println("\n--- Menú CRUD de Jugadores ---");
            System.out.println("1. Agregar Jugador");
            System.out.println("2. Eliminar Jugador");
            System.out.println("3. Buscar Jugador");
            System.out.println("4. Editar Jugador");
            System.out.println("5. Mostrar Jugadores");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    agregarJugador();
                    break;
                case 2:
                    eliminarJugador();
                    break;
                case 3:
                    buscarJugador();
                    break;
                case 4:
                    editarJugador();
                    break;
                case 5:
                    mostrarJugadores();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    public static void agregarJugador() {
        countID++;
        int id = countID;
        System.out.print("Ingrese el nombre del jugador: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la edad del jugador: ");
        int edad = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer

        jugadores.add(new Jugador(id, nombre, edad));
        System.out.println("Jugador agregado con éxito.");
        System.out.println("Jugadores en la lista:");
        mostrarJugadores();
    }

    public static void eliminarJugador() {
        System.out.print("Ingrese el Id del jugador a eliminar: ");
        int id = scanner.nextInt();

        Jugador jugadorAEliminar = buscarJugadorPorId(id);
        if (jugadorAEliminar != null) {
            System.out.print("¿Desea eliminar el jugador? Escriba 'si' para confirmar o 'no' para cancelar: ");
            scanner.nextLine(); // limpiar buffer
            String confirmacion = scanner.nextLine();
            if (confirmacion.equalsIgnoreCase("si")) {
                jugadores.remove(jugadorAEliminar);
                System.out.println("Jugador eliminado con éxito.");
            } else {
                System.out.println("Jugador no eliminado.");
            }
        } else {
            System.out.println("Jugador no encontrado.");
        }
    }

    public static void buscarJugador() {
        System.out.print("Ingrese el ID del jugador a buscar: ");
        int id = scanner.nextInt();

        Jugador jugadorEncontrado = buscarJugadorPorId(id);
        if (jugadorEncontrado != null) {
            System.out.println("Jugador encontrado: " + jugadorEncontrado);
        } else {
            System.out.println("Jugador no encontrado.");
        }
    }

    public static Jugador buscarJugadorPorId(int id) {
        for (Jugador jugador : jugadores) {
            if (jugador.getId() == id) {
                return jugador;
            }
        }
        return null;
    }

    public static void editarJugador() {
        System.out.print("Ingrese el id del jugador a editar: ");
        int id = scanner.nextInt();

        Jugador jugadorAEditar = buscarJugadorPorId(id);
        if (jugadorAEditar != null) {
            System.out.println("Jugador encontrado: " + jugadorAEditar);
            
            // Editar nombre
            System.out.print("Ingrese el nuevo nombre (dejar en blanco para no cambiar): ");
            scanner.nextLine(); // Limpiar buffer
            String nuevoNombre = scanner.nextLine();
            if (!nuevoNombre.isEmpty()) {
                jugadorAEditar.setNombre(nuevoNombre);
            }

            // Editar edad
            System.out.print("Ingrese la nueva edad (o 0 para no cambiar): ");
            int nuevaEdad = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer
            if (nuevaEdad > 0) {
                jugadorAEditar.setEdad(nuevaEdad);
            }

            // Confirmación de cambios
            System.out.print("¿Desea guardar los cambios? Escriba 'si' para confirmar o 'no' para cancelar: ");
            String confirmacion = scanner.nextLine();
            if (confirmacion.equalsIgnoreCase("si")) {
                System.out.println("Cambios guardados con éxito.");
                // Mostrar jugadores actualizados
                mostrarJugadores();
            } else {
                System.out.println("Los cambios no fueron guardados.");
            }
        } else {
            System.out.println("Jugador no encontrado.");
        }
    }

    public static void mostrarJugadores() {
        System.out.println("\nLista de Jugadores:");
        for (Jugador jugador : jugadores) {
            System.out.println(jugador);
        }
    }
}
