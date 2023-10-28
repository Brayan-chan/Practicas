package Pokemon;

import java.util.ArrayList;

public class ListaMovimientos {

    ArrayList<Movimiento> movimientos = new ArrayList<>();

    public ListaMovimientos() {

        // Movimientos de tipo agua
        movimientos.add(new Movimiento("Cabezazo", 130, TipoMovimiento.FISICO, 16));
        movimientos.add(new Movimiento("Golpe de cabeza", 70, TipoMovimiento.FISICO, 24));
        movimientos.add(new Movimiento("Placaje", 40, TipoMovimiento.FISICO, 56));
        movimientos.add(new Movimiento("Pistola Agua Water Gun", 40, TipoMovimiento.FISICO, 25));
        movimientos.add(new Movimiento("Hidropulso", 60, TipoMovimiento.ESTADO, 20));
        movimientos.add(new Movimiento("Danza lluvia", 110, TipoMovimiento.ESPECIAL, 5));

        // Movimientos de tipo Ice
        movimientos.add(new Movimiento("Tumba rocas", 15, TipoMovimiento.FISICO, 60));
        movimientos.add(new Movimiento("Terremoto", 10, TipoMovimiento.FISICO, 100));
        movimientos.add(new Movimiento("Bola hielo", 30, TipoMovimiento.ESPECIAL, 30));
        movimientos.add(new Movimiento("Nieve Polvo", 40, TipoMovimiento.ESPECIAL, 40));
        movimientos.add(new Movimiento("Alud", 60, TipoMovimiento.FISICO, 10));
        movimientos.add(new Movimiento("Rayo hielo", 90, TipoMovimiento.ESTADO, 10));

        // movimientos de pidgey
        movimientos.add(new Movimiento("Placaje", 40, TipoMovimiento.FISICO, 56));
        movimientos.add(new Movimiento("Ataque rapido", 40, TipoMovimiento.FISICO, 30));
        movimientos.add(new Movimiento("Ataque ala", 60, TipoMovimiento.ESPECIAL, 35));
        movimientos.add(new Movimiento("Golpe aereo", 60, TipoMovimiento.FISICO, 20));
        movimientos.add(new Movimiento("Vuelo", 90, TipoMovimiento.FISICO, 15));
        movimientos.add(new Movimiento("Doble filo", 120, TipoMovimiento.FISICO, 15));

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
