package Cajero_Archivos;
import java.io.Serializable;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Administrador implements Serializable {
    private static final String LOGS_FILE = "logs.txt";

    public void menuAdministrador(CajeroAutomatico cajero) {
        Scanner scanner = new Scanner(System.in);
        Administrador administrador = new Administrador();
        while (cajero.isAdministrador()) { // Utiliza el método isAdministrador()
            System.out.println("Modo Administrador:");
            System.out.println("1. Mostrar listado de acciones");
            System.out.println("2. Mostrar cantidad de billetes disponibles");
            System.out.println("3. Salir del modo administrador");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    administrador.mostrarListadoAcciones();
                    break;
                case 2:
                    administrador.mostrarCantidadBilletesDisponibles(cajero);
                    break;
                case 3:
                    cajero.setAdminMode(false); // Utiliza el método setAdminMode()
                    System.out.println("Saliendo del modo administrador.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
                    break;
            }
        }
    }
    
    public void mostrarListadoAcciones() {
        System.out.println("Listado de acciones:");
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