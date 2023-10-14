package pokemon;
import java.util.ArrayList;

public class ListaMovimientos {
    private ArrayList<Movimiento> movimientos = new ArrayList<>();

    public ListaMovimientos() {
        //Movimientos pokemon electrico
        movimientos.add(new Movimiento("Impactrueno", 40, 30, Tipo.ELECTRICO));
        movimientos.add(new Movimiento("Bola voltio", 60, 10, Tipo.ELECTRICO));
        movimientos.add(new Movimiento("Cola de hierro", 100, 15, Tipo.ELECTRICO));
        movimientos.add(new Movimiento("Meteoros", 60, 20, Tipo.ELECTRICO));

        //Movimientos pokemon acero
        movimientos.add(new Movimiento("Foco resplandor", 80, 10, Tipo.ACERO));
        movimientos.add(new Movimiento("Meteoimpacto", 100, 5, Tipo.ACERO));
        movimientos.add(new Movimiento("Cabeza de hierro", 80, 15, Tipo.ACERO));
        movimientos.add(new Movimiento("Metalaser", 140,  5, Tipo.ACERO));
    }

    public Movimiento buscarMovimientoPorNombre(String nombre) {
        for (Movimiento movimiento : movimientos) {
            if (movimiento.getNombre().equals(nombre)) {
                return movimiento;
            }
        }
        return null;
    }

}
