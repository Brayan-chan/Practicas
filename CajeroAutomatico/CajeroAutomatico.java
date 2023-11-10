package CajeroAutomatico;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class CajeroAutomatico {
    private static final String BILLETES_FILE = "billetes.dat";
    private static final String LOGS_FILE = "logs.txt";

    private Usuario usuario;
    private List<Billete> billetes;

    public CajeroAutomatico() {
        cargarBilletesIniciales();
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
            e.printStackTrace();
        }
    }

    public void consultarSaldo() {
    }

    public void retirarEfectivo(double cantidad) {
    }

    public void registrarLog(String accion, String usuario, double saldo, boolean seRealizo) {
    }

    public void mostrarLogs() {
    }

    public void mostrarBilletesDisponibles() {
    }
}
