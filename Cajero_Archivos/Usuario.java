package Cajero_Archivos;
import java.io.Serializable;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private int nip;
    private int saldo;

    public Usuario(String nombre, int nip) {
        this.nombre = nombre;
        this.nip = nip;
        // Math.random para generar un saldo aleatorio entre $1,000 y $50,000
        this.saldo = (int) (Math.random() * (50000 - 1000) + 1000);
    }

    public String getNombre() {
        return nombre;
    }

    public int getNip() {
        return nip;
    }

    public int getSaldo() {
        return saldo;
    }

    // Método para retirar saldo
    public void retirarSaldo(int cantidad) {
        saldo -= cantidad;
    }
}