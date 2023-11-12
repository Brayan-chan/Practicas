package CajeroAutomatico;
import java.io.Serializable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Administrador implements Serializable {
    private static final String LOGS_FILE = "logs.txt";
    
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
