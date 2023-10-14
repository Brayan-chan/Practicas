package pokemon;

public class Movimiento {
    private String nombre;
    private int puntosDeAtaque, pp;
    private Tipo tipo;

    public Movimiento(String nombre, int puntosDeAtaque, int pp, Tipo tipo) {
        this.nombre = nombre;
        this.puntosDeAtaque = puntosDeAtaque;
        this.pp = pp;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntosDeAtaque() {
        return puntosDeAtaque;
    }

    public int getPp() {
        return pp;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }

    
    
}
