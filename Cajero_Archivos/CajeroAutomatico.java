package Cajero_Archivos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
    private boolean isAdminMode = false;
    private List<Usuario> listaDeUsuarios = new ArrayList<>();

    public CajeroAutomatico() {
        cargarBilletesIniciales();
        iniciarSesion();
        cargarUsuarios();
    }

    private void cargarUsuarios() {
        File file = new File("usuarios.dat");

        if (file.exists() && !file.isDirectory()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                listaDeUsuarios = (List<Usuario>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al guardar usuarios. Por favor, contacte al soporte.");
            }
        }
    }

    private void guardarUsuarios() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("usuarios.dat"))) {
            oos.writeObject(listaDeUsuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarBilletesIniciales() {
        File file = new File(BILLETES_FILE);

        if (file.exists() && !file.isDirectory()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                billetes = (List<Billete>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al cargar billetes iniciales. Por favor, contacte al soporte.");
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
            System.err.println("Error al guardar billetes. Por favor, contacte al soporte.");
        }
    }

    public void iniciarSesion() {
        System.out.print("Ingrese su nombre de usuario: ");
        String nombre = scanner.next();
        System.out.println("");

        System.out.print("Ingrese su NIP de 4 digitos: ");
        int nip = 0;

        try {
            nip = scanner.nextInt();

            if (nip < 1000 || nip > 9999) {
                System.out.println("El NIP debe ser de 4 digitos.");
            }
        } catch (InputMismatchException e) {
            System.out.println("El NIP debe ser entero. ");
        }

        Usuario usuarioExistente = buscarUsuario(nombre, nip);

        //Condicional If para comprobar si los valores son del administrador o de un usuario
        if (nombre.equals("admin") && nip == 3243) {
            isAdminMode = true;
            modoAdministrador();
        } else if (usuarioExistente != null) {
            usuario = usuarioExistente;
            System.out.println("¡Bienvenido de nuevo, " + usuario.getNombre() + "!");
            menuCajero();
            //modoCajero(nombre, nip);
        } else {
            usuario = new Usuario(nombre, nip);
            listaDeUsuarios.add(usuario);
            guardarUsuarios(); 
            System.out.println("\n¡Bienvenido al modo cajero, " + nombre + "!");
            System.out.println("Saldo actual: $" + usuario.getSaldo());
            menuCajero();
        }
    }

    private Usuario buscarUsuario(String nombre, int nip) {
        for (Usuario user : listaDeUsuarios) {
            if (user.getNombre().equals(nombre) && user.getNip() == nip) {
                return user;
            }
        }
        return null;
    }

    public void mostrarMontoMinimoRetiro() {
        int montoMinimo = billetes.stream().mapToInt(billete -> billete.getDenominacion() * billete.getCantidad()).sum();
        System.out.println("\nMonto mínimo para retirar: $" + montoMinimo);
    }

    private void modoCajero(String nombre, int nip) {
        try {
            Usuario usuarioExistente = buscarUsuario(nombre, nip);

            if (usuarioExistente != null) {
                usuario = usuarioExistente;
                System.out.println("¡Bienvenido de nuevo, " + usuario.getNombre() + "!");
            } else {
                usuario = new Usuario(nombre, nip);
                listaDeUsuarios.add(usuario);
                guardarUsuarios();
                System.out.println("¡Bienvenido al modo cajero, " + nombre + "!");
            }

            System.out.println("Saldo actual: $" + usuario.getSaldo());
            menuCajero();
        } catch (Exception e) {
            System.err.println("Error al entrar al modo cajero. Por favor, contacte al soporte.");
        }
        
    }

    private void menuCajero(){
        int opcion;
    
        do {
            //mostrarBilletesDisponibles();
            mostrarMontoMinimoRetiro();
            System.out.println("\nMenú Cajero Automático:");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Retirar efectivo");
            System.out.println("3. Salir");
            System.out.print("Ingrese una opcion: ");
            
            try {
                opcion = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido como opción. Inténtelo de nuevo.");
                scanner.next(); 
                opcion = 0; 
                continue; 
            }

            switch (opcion) {
                case 1:
                    consultarSaldo();
                    break;
                case 2:
                    System.out.print("Ingrese la cantidad a retirar: ");
                    int cantidadRetiro = 0;
                    try {
                        cantidadRetiro = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Ingrese un número válido como cantidad a retirar. Inténtelo de nuevo.");
                        scanner.next(); 
                        continue; 
                    }

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
            int saldoConsultado = usuario.getSaldo();
            System.out.println("Saldo actual: $" + saldoConsultado);
            registrarLog("consultar", usuario.getNombre(), saldoConsultado, true);
        } else {
            System.out.println("No hay usuario registrado. Ingrese al modo cajero primero.");
        }
    }

    //De double a int 
    public void retirarEfectivo(int cantidad) {
        try {
            Integer.parseInt(String.valueOf(cantidad)); 
        } catch (NumberFormatException e) {
            throw new RuntimeException("El monto a retirar debe ser numérico. Inténtelo de nuevo.");
        }

        if (cantidad <= 0) {
            throw new RuntimeException("Saldo a retirar incorrecto. Debe ser mayor que cero. Inténtelo de nuevo.");
        }

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

    //double a int
    private boolean verificarDisponibilidadBilletes(int cantidad) {
        List<Billete> billetesDisponibles = new ArrayList<>(billetes);

        for (Billete billete : billetesDisponibles) {
            int denominacion = billete.getDenominacion();
            int cantidadBilletes = billete.getCantidad();

            if (cantidadBilletes > 0) {
                int billetesNecesarios = cantidad / denominacion;
                int billetesAUsar = Math.min(billetesNecesarios, cantidadBilletes);

                cantidad -= billetesAUsar * denominacion;

                billete.setCantidad(cantidadBilletes - billetesAUsar);
            }
        }

        return cantidad == 0;
    }

    //de double a int 
    private void actualizarBilletesDisponibles(int cantidadRetirada) {
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
    
    public void registrarLog(String accion, String usuario, int saldo, boolean seRealizo) {
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

    public void mostrarBilletesDisponibles() {
        System.out.println("Billetes disponibles:");

        for (Billete billete : billetes) {
            int denominacion = billete.getDenominacion();
            int cantidadBilletes = Math.max(billete.getCantidad(), 0);

            System.out.println("$" + denominacion + ": " + cantidadBilletes + " billetes");
        }
    }

    public boolean isAdministrador() {
        return isAdminMode;
    }

    public void setAdminMode(boolean isAdminMode) {
        this.isAdminMode = isAdminMode;
    }

    public List<Billete> getBilletes() {
        return billetes;
    }
}