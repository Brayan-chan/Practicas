package Pokemon;

public class Movimiento {

    private String nombre;
    private int puntosDeAtaque;
    private TipoMovimiento TipoMovimiento;
    private int pp;

    private Movimiento[] movimientos;

    public Movimiento(String nombre, int puntosDeAtaque, TipoMovimiento TipoMovimiento, int pp) {
        this.nombre = nombre;
        this.puntosDeAtaque = puntosDeAtaque;
        this.TipoMovimiento = TipoMovimiento;
        this.pp = pp;
        this.movimientos = new Movimiento[4];
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntosDeAtaque() {
        return puntosDeAtaque;
    }

    public void setPuntosDeAtaque(int puntosDeAtaque) {
        this.puntosDeAtaque = puntosDeAtaque;
    }

    public int getPp() {
        return pp;
    }

    public void setPp(int Pp) {
        this.pp = Pp;
    }

    public Movimiento[] getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(Movimiento[] movimientos) {
        this.movimientos = movimientos;
    }

    public TipoMovimiento getTipoMovimiento() {
        return TipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimiento TipoMovimiento) {
        this.TipoMovimiento = TipoMovimiento;
    }

}