package Pokemon_Abstraccion.pokemon;
//package pokemon_herencia;

public class PokemonElectrico extends Pokemon {

    public PokemonElectrico(String nombre, int nivel) {
        super(nombre, Tipo.ELECTRICO, nivel);

        ListaMovimientos listaMovimientos = new ListaMovimientos();

        setMovimiento(0, listaMovimientos.buscarMovimientoPorNombre("Impactrueno"));
        setMovimiento(1, listaMovimientos.buscarMovimientoPorNombre("Bola voltio"));
        setMovimiento(2, listaMovimientos.buscarMovimientoPorNombre("Cola de hierro"));
        setMovimiento(3, listaMovimientos.buscarMovimientoPorNombre("Meteoros"));
    }

    @Override
    public double obtenerEfectividad(Pokemon pokemon) {
        double efectividad = 1.0;

        if (pokemon.getTipo() == Tipo.TIERRA)
            efectividad = 0.0;
        if (pokemon.getTipo() == Tipo.AGUA)
            efectividad = 2;

        return efectividad;
    }
}
