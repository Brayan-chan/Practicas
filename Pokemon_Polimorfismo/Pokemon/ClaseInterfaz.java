package Pokemon;

public interface ClaseInterfaz {

    public void CalcularDanio(int danio);

    public boolean ConcretarAtaque(Movimiento movimiento, Pokemon pokemon, Tipo Tipo, TipoMovimiento TipoMovimiento);

    public void Ataque(int m, Pokemon pokemon, Tipo Tipo, TipoMovimiento TipoMovmiento);

    public void RecibirAtaque(Movimiento movimiento, double efectividad);

}
