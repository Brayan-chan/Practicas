package CajeroAutomatico;
import java.io.Serializable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Administrador implements Serializable {
    private static final String LOGS_FILE = "logs.txt";

    public void menuAdministrador(CajeroAutomatico cajero) {
        int opcion;
        do {
            System.out.println("\nMenú Administrador:");
            System.out.println("1. Mostrar listado de acciones");
            System.out.println("2. Mostrar cantidad de billetes disponibles");
            System.out.println("3. Salir");
            System.out.print("Ingrese la opción: ");
            opcion = CajeroAutomatico.scanner.nextInt();

            switch (opcion) {
                case 1:
                    mostrarListadoAcciones();
                    break;
                case 2:
                    mostrarCantidadBilletesDisponibles(cajero);
                    break;
                case 3:
                    System.out.println("Sesión de administrador finalizada.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 3);
    }
    
    public void mostrarListadoAcciones() {
        System.out.println("Listado de acciones:");
        //Agregar los metodos
        mostrarLogs();
    }

    public void mostrarCantidadBilletesDisponibles(CajeroAutomatico cajero) {
        System.out.println("Cantidad de billetes disponibles:");
        cajero.mostrarBilletesDisponibles();
    }

    private void mostrarLogs() {
        try (BufferedReader reader = new BufferedReader(new FileReader(LOGS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}