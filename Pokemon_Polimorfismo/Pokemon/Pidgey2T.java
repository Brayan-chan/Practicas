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

public class Pidgey2T extends Pidgey implements ClaseInterfaz {

    private Tipo Tipo2;

    public Pidgey2T(String nombre, Tipo Tipo, Tipo Tipo2, int nivel, int PS, int ataque,
            int defensa, int ataqueespecial, int defensaespecial, int velocidad) {
        super(nombre, Tipo, nivel, PS, ataque, defensa, ataqueespecial, defensaespecial, velocidad);

        this.Tipo2 = Tipo2;

        ListaMovimientos listaMovimientos = new ListaMovimientos();

        setMovimientos(0, listaMovimientos.buscarMovimientoPorNombre("Cabezazo"));
        setMovimientos(1, listaMovimientos.buscarMovimientoPorNombre("Golpe de cabeza"));
        setMovimientos(2, listaMovimientos.buscarMovimientoPorNombre("Placaje"));
        setMovimientos(3, listaMovimientos.buscarMovimientoPorNombre("Pistola Agua Water Gun"));
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

        // Partes de la formula para calcular el daÃ±o inflijido
        int variacion = (int) (Math.random() * (100 - 85) + 100);
        double parte1 = (0.2 * this.getNivel() + 1);
        double parte2 = ((parte1) * (this.getAtaque()) * (movimiento.getPuntosDeAtaque()));
        double parte3 = (25 * this.getDefensa());
        double parte4 = (0.01 * efectividad * variacion);
        double parte5 = ((parte4) * (((parte2) / (parte3)) + 2));
        int dano = (int) (parte5);

        CalcularDanio(dano);
        System.out.println(this.getNombre() + " tiene " + this.getPS() + " PS restantes");
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
        double efectividads = 1;
        switch (tipo) {
            case ACERO:
                efectividads *= 0.5;
                break;
            case FUEGO:
                efectividads *= 0.5;
                break;
            case HADA:
                efectividads *= 0.5;
                break;
            case LUCHA:
                efectividads *= 0.5;
                break;
            case AGUA:
                efectividads *= 2;
                break;
            case ELECTRICO:
                efectividads *= 2;
                break;
            case BICHO:
                efectividads *= 0.25;
                break;
            case PLANTA:
                efectividads *= 0.25;
                break;
            case ROCA:
                efectividads *= 4;
                break;
            case TIERRA:
                efectividads *= 0;
                break;
        }
        return efectividads;
    }
}
