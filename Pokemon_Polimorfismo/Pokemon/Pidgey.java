package Pokemon;

import static Pokemon.Tipo.ACERO;
import static Pokemon.Tipo.AGUA;
import static Pokemon.Tipo.BICHO;
import static Pokemon.Tipo.ELECTRICO;
import static Pokemon.Tipo.FUEGO;
import static Pokemon.Tipo.HADA;
import static Pokemon.Tipo.LUCHA;
import static Pokemon.Tipo.PLANTA;
import static Pokemon.Tipo.ROCA;
import static Pokemon.Tipo.TIERRA;

public class Pidgey extends Pokemon
        implements ClaseInterfaz {

    public Pidgey(String nombre, Tipo Tipo, int nivel, int PS, int ataque,
        int defensa, int ataqueespecial, int defensaespecial, int velocidad) {
        super(nombre, Tipo, nivel, PS, ataque, defensa, ataqueespecial, defensaespecial, velocidad);

        ListaMovimientos listaMovimientos = new ListaMovimientos();

        setMovimientos(0, listaMovimientos.buscarMovimientoPorNombre("Placaje"));
        setMovimientos(1, listaMovimientos.buscarMovimientoPorNombre("Cabezazo"));
        setMovimientos(2, listaMovimientos.buscarMovimientoPorNombre("Ataque rapido"));
        setMovimientos(3, listaMovimientos.buscarMovimientoPorNombre("Golpe de cabeza"));
    }

    @Override
    public void CalcularDanio(int danio) {

        double puntosRestados = danio;
        this.PS -= puntosRestados;
        System.out.printf("%s recibe %d puntos de danio\n",
        this.getNombre(), danio);
    }

    @Override
    public void RecibirAtaque(Movimiento movimiento, double efectividad) {
        System.out.printf("%s recibe ATKespecial%s\n", this.getNombre(), movimiento.getNombre());

        // Partes de la formula para calcular el danio inflijido
        int variacion = (int) (Math.random() * (100 - 85) + 100);
        double parte1 = (0.2 * this.getNivel() + 1);
        double parte2 = ((parte1) * (this.getAtaque()) * (movimiento.getPuntosDeAtaque()));
        double parte3 = (25 * this.getDefensa());
        double parte4 = (0.01 * efectividad * variacion);
        double parte5 = ((parte4) * (((parte2) / (parte3)) + 2));
        int dannio = (int) (parte5);

        CalcularDanio(dannio);
        System.out.println(this.getNombre() + " tiene " + this.getPS() + " PS restantes ");
        System.out.println("");
    }

    @Override
    public boolean ConcretarAtaque(Movimiento movimiento, Pokemon pokemon, Tipo Tipo, TipoMovimiento TipoMovimiento) {
        System.out.printf("%s ataca a %s con ataque especial \n", this.getNombre(), pokemon.getNombre(),
        movimiento.getNombre());
        double efectividads = obtenerEfectividad(Tipo, TipoMovimiento);

        if (movimiento.getPp() > 0) {
            pokemon.recibirAtaque(movimiento, efectividads);
            return true;
        } else {
            System.err.println("El movimiento no tiene puntos de pp");
            return false;
        }
    }

    @Override
    public void Ataque(int m, Pokemon pokemon, Tipo Tipo, TipoMovimiento TipoMovimiento) {
        Movimiento movimientos = getMovimientos(m);
        boolean ConcretarAtaque = ConcretarAtaque(movimientos, pokemon, Tipo, TipoMovimiento);
        if (ConcretarAtaque) {
            pokemon.getMovimientos(m).setPp(movimientos.getPp() - 1);
        }
    }

    @Override
    public double obtenerEfectividad(Tipo tipo, TipoMovimiento TipoMovimiento) {
        double efectividadcase = 1;
        switch (tipo) {
            case ACERO:
                efectividadcase *= 0.5;
                break;
            case FUEGO:
                efectividadcase *= 0.5;
                break;
            case HADA:
                efectividadcase *= 0.5;
                break;
            case LUCHA:
                efectividadcase *= 0.5;
                break;
            case AGUA:
                efectividadcase *= 2;
                break;
            case ELECTRICO:
                efectividadcase *= 2;
                break;
            case BICHO:
                efectividadcase *= 0.25;
                break;
            case PLANTA:
                efectividadcase *= 0.25;
                break;
            case ROCA:
                efectividadcase *= 4;
                break;
            case TIERRA:
                efectividadcase *= 0;
                break;
        }
        return efectividadcase;
    }
}
