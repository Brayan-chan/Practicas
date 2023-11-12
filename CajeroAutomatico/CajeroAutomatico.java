package CajeroAutomatico;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;

public class CajeroAutomatico {
    static Scanner scanner = new Scanner(System.in);
    private static final String BILLETES_FILE = "billetes.dat";
    private static final String LOGS_FILE = "logs.txt";

    private Usuario usuario;
    private List<Billete> billetes;

    public CajeroAutomatico() {
        cargarBilletesIniciales();
        iniciarSesion();
    }

    public void cargarBilletesIniciales() {
        File file = new File(BILLETES_FILE);

        if (file.exists() && !file.isDirectory()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                billetes = (List<Billete>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            // Si el archivo no existe, crea billetes iniciales y guárdalos
            billetes = new ArrayList<>();
            billetes.add(new Billete(100, 100));
            billetes.add(new Billete(200, 100));
            billetes.add(new Billete(500, 20));
            billetes.add(new Billete(1000, 10));

            guardarBilletes();
        }
    }

    public void guardarBilletes() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BILLETES_FILE))) {
            oos.writeObject(billetes);
        } catch (IOException e) {
            //Stacktracem: Permite mostrar el nombre de una excepción junto con el mensaje que devuelve getMessage()
            e.printStackTrace();
        }
    }

    public void iniciarSesion() {
        System.out.print("Ingrese su nombre de usuario: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese su NIP de 4 digitos: ");
        int nip = scanner.nextInt();

        //Condicional If para comprobar si los valores son del administrador o de un usuario
        if (nombre.equals("admin") && nip == 3243) {
            //isAdminMode = true;
            //modoAdministrador();
        } else {
            //Agregar NIP usuario
            modoCajero(nombre, nip);
        }
    }

    public void modoCajero(String nombre, int nip) {
        usuario = new Usuario(nombre, nip);
        System.out.println("¡Bienvenido al modo cajero, " + nombre + "!");
        System.out.println("Saldo actual: $" + usuario.getSaldo());
        //Agregar metodos del anterior proyecto
        menuCajero();
    }

    private void menuCajero(){
        int opcion;
        //Do While para el menu de opciones
        do {
            System.out.println("\nMenú Cajero Automático:");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Retirar efectivo");
            System.out.println("3. Salir");
            System.out.print("Ingrese una opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    consultarSaldo();
                    break;
                case 2:
                    System.out.print("Ingrese la cantidad a retirar: ");
                    double cantidadRetiro = scanner.nextDouble();
                    retirarEfectivo(cantidadRetiro);
                    break;
                case 3:
                    System.out.println("Sesión finalizada.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }

        } while (opcion != 3);
    }

    private void modoAdministrador() {
        Administrador administrador = new Administrador();
        administrador.menuAdministrador(this); 
    }

    public void consultarSaldo() {
        if (usuario != null) {
            //Getter de usuario.getSaldo
            System.out.println("Saldo actual: $" + usuario.getSaldo());
        } else {
            System.out.println("No hay usuario registrado. Ingrese al modo cajero primero.");
        }
    }

    public void retirarEfectivo(double cantidad) {
        if (usuario != null) {
            if (cantidad > 0 && cantidad <= usuario.getSaldo()) {
                if (verificarDisponibilidadBilletes(cantidad)) {
                    // Realizar la operación de retiro
                    usuario.retirarSaldo(cantidad);
                    actualizarBilletesDisponibles(cantidad);

                    System.out.println("Retiro exitoso. Nuevo saldo: $" + usuario.getSaldo());
                    registrarLog("retirar", usuario.getNombre(), cantidad, true);
                } else {
                    System.out.println("No hay suficientes billetes disponibles para el monto solicitado.");
                    registrarLog("retirar", usuario.getNombre(), cantidad, false);
                }
            } else {
                System.out.println("No se puede realizar el retiro. Verifique el monto ingresado.");
                registrarLog("retirar", usuario.getNombre(), cantidad, false);
            }
        } else {
            System.out.println("No hay usuario registrado. Ingrese al modo cajero primero.");
        }
    }

    private boolean verificarDisponibilidadBilletes(double cantidad) {
        int montoRestante = (int) cantidad;

        for (Billete billete : billetes) {
            int denominacion = billete.getDenominacion();
            int cantidadBilletes = billete.getCantidad();

            int billetesNecesarios = montoRestante / denominacion;

            int billetesAUsar = Math.min(billetesNecesarios, cantidadBilletes);

            if (billetesAUsar > 0) {
                montoRestante -= billetesAUsar * denominacion;
            }

            if (montoRestante == 0) {
                // Combinación de billetes 
                billete.setCantidad(cantidadBilletes - billetesAUsar);
                return true;
            }
        }
        return false;
    }

    private void actualizarBilletesDisponibles(double cantidadRetirada) {
        for (Billete billete : billetes) {
            int denominacion = billete.getDenominacion();
            int cantidadBilletes = billete.getCantidad();

            int billetesUtilizados = (int) (cantidadRetirada / denominacion);
            cantidadRetirada -= billetesUtilizados * denominacion;

            // Actualizar la cantidad de billetes disponibles
            billete.setCantidad(cantidadBilletes - billetesUtilizados);
        }
        //Guardar los nevos billetes disponibles
        guardarBilletes(); 
    }
    public void registrarLog(String accion, String usuario, double saldo, boolean seRealizo) {
        //BufferedWriter: Permite escribir en un archivo de texto
        //Se puede utilizar igual PrintWriter 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOGS_FILE, true))) {
            String logEntry = accion + "," + usuario + "," + saldo + "," + (seRealizo ? "SI" : "NO");
            writer.write(logEntry);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public void mostrarLogs() {
        //BufferedReader: Permite leer un archivo de texto linea a linea
        //FileReader: Permite leer un archivo de texto completo 
        try (BufferedReader reader = new BufferedReader(new FileReader(LOGS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */

    public void mostrarBilletesDisponibles() {
        System.out.println("Billetes disponibles:");

        for (Billete billete : billetes) {
            int denominacion = billete.getDenominacion();
            int cantidadBilletes = billete.getCantidad();

            System.out.println("$" + denominacion + ": " + cantidadBilletes + " billetes");
        }
    }
}