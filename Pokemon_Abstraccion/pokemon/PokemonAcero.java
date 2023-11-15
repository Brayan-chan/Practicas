package Pokemon_Abstraccion.pokemon;
//package pokemon_herencia;

public class PokemonAcero extends Pokemon {
    public PokemonAcero(String nombre, int nivel) {
        super(nombre, Tipo.ACERO, nivel);

        ListaMovimientos listaMovimientos = new ListaMovimientos();

        setMovimiento(0, listaMovimientos.buscarMovimientoPorNombre("Foco resplandor"));
        setMovimiento(1, listaMovimientos.buscarMovimientoPorNombre("Meteoimpacto"));
        setMovimiento(2, listaMovimientos.buscarMovimientoPorNombre("Cabeza de hierro"));
        setMovimiento(3, listaMovimientos.buscarMovimientoPorNombre("Metalaser"));
    }

    @Override
    public double obtenerEfectividad(Pokemon pokemon) {
        return 1.0;
    }
    
}
