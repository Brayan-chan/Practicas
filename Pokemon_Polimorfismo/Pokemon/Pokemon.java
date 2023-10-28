package Pokemon;

public abstract class Pokemon {
    
    public int PS, nivel, ataque, defensa,
    ataqueespecial, defensaespecial, velocidad;
    private String nombre;
    Tipo tipo;
    Tipo tipo2;
    private Movimiento movimientos;
    
    public Pokemon(String nombre, Tipo tipo, int nivel, int PS, int ataque,
        int defensa, int ataqueespecial, int defensaespecial, int velocidad){
        this.nombre= nombre;
        this.tipo= tipo;
        this.nivel= nivel;
        this.PS = PS;
        this.ataque = ataque;
        this.defensa = defensa;
        this.ataqueespecial = ataqueespecial;
        this.defensaespecial = defensaespecial;
        this.velocidad = velocidad;
        
    }
    
    public Pokemon(String nombre, Tipo tipo, Tipo tipo2, int nivel, int PS, int ataque,
        int defensa, int ataqueespecial, int defensaespecial, int velocidad){
        this.nombre= nombre;
        this.tipo= tipo;
        this.tipo2 = tipo2;
        this.nivel= nivel;
        this.PS = PS;
        this.ataque = ataque;
        this.defensa = defensa;
        this.ataqueespecial = ataqueespecial;
        this.defensaespecial = defensaespecial;
        this.velocidad = velocidad;
    }
    
    public int getPS(){
        return PS;
    }

    public void setPS(int PS) {
        this.PS = PS;
    }
    
    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Tipo getTipo(){
        return tipo;
    }
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    
    public int getNivel(){
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Tipo getTipo2() {
        return tipo2;
    }

    public void setTipo2(Tipo tipo2) {
        this.tipo2 = tipo2;
    }
    
    public Movimiento getMovimientos(int pos) {
        return movimientos;
    }

    public void setMovimientos(int pos, Movimiento movimientos) {
        this.movimientos = movimientos;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getAtaqueespecial() {
        return ataqueespecial;
    }

    public void setAtaqueespecial(int ataqueespecial) {
        this.ataqueespecial = ataqueespecial;
    }

    public int getDefensaespecial() {
        return defensaespecial;
    }

    public void setDefensaespecial(int defensaespecial) {
        this.defensaespecial = defensaespecial;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public Movimiento getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(Movimiento movimientos) {
        this.movimientos = movimientos;
    }
    
    
    private void calculaDanio(int danio){
        double puntosRestados = danio;
        this.PS -= puntosRestados;
        System.out.printf("%s recibe %d puntos de danio\n",
        this.getNombre(), danio);
    }
    
    public void recibirAtaque(Movimiento movimiento, double efectividad){
           System.out.printf("%s recibe ATK %s\n", this.getNombre(), movimiento.getNombre());
        
        //La formula se dividio en partes para hacerlo mas simple
        int variacion =  (int) (Math.random()*(100-85)+100);
        double parte1 = (0.2*this.getNivel()+1);
        double parte2 = ((parte1)*(this.getAtaque())*(movimiento.getPuntosDeAtaque()));
        double parte3 = (25*this.getDefensa());
        double parte4 = (0.01*efectividad*variacion);
        double parte5 = ((parte4)*(((parte2)/(parte3))+2));
        int dano = (int) (parte5);
        
        calculaDanio(dano);
        System.out.println(this.getNombre()+" tiene "+this.getPS()+" PS restantes");
        System.out.println("");
    }
    
    protected boolean seHaConcretadoAtaque(Movimiento movimiento,Pokemon pokemon, Tipo Tipo, TipoMovimiento TipoMovimiento){
           System.out.printf("%s ataca a %s\n", this.getNombre(), pokemon.getNombre(), movimiento.getNombre());
        double efectividad = obtenerEfectividad(Tipo, TipoMovimiento);
        
        if(movimiento.getPp()>0){
            pokemon.recibirAtaque(movimiento, efectividad);
            return true;
        }else{
         System.err.println("El movimiento no tiene puntos de pp");
         return false;
        }
    }
    
    public void atacar(int m, Pokemon pokemon, Tipo Tipo, TipoMovimiento TipoMovimiento){
        Movimiento movimientos = getMovimientos(m);
        boolean seHaConcretadoAtaque = seHaConcretadoAtaque(movimientos, pokemon,Tipo, TipoMovimiento);
        if (seHaConcretadoAtaque){
            pokemon.getMovimientos(m).setPp(movimientos.getPp()-1);
        }
    }
    
    public abstract double obtenerEfectividad(Tipo tipo, TipoMovimiento TipoMovimiento);
}
