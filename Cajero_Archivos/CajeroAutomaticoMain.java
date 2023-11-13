package Cajero_Archivos;
import java.util.Scanner;

public class CajeroAutomaticoMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CajeroAutomatico cajero = new CajeroAutomatico();

        int opcion;
        do {
            System.out.println("\nMenú Principal:");
            System.out.println("1. Iniciar sesion");
            System.out.println("2. Salir");
            System.out.print("Ingrese la opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    cajero.iniciarSesion();
                    break;
                case 2:
                    System.out.println("Sesión finalizada. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 2);
    }
}