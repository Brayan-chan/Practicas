import Pokemon.Pidgey;
import Pokemon.Blastoise;
import Pokemon.Pidgey2T;
import Pokemon.Tipo;
import Pokemon.TipoMovimiento;

public class BatallaPokemon {

    public static void main(String[] args) {

        Blastoise Blastoise = new Blastoise("Blastoise", 1, 79, 83, 100, 85, 105, 78);

        Pidgey pidgey = new Pidgey("Pidgey", Tipo.NORMAL, 1, 40, 45, 40, 35, 35, 56);

        Pidgey2T pidgey2 = new Pidgey2T("Pidgey", Tipo.NORMAL, Tipo.VOLADOR, 1, 40, 45, 40, 35, 35, 56);

        pidgey.atacar(0, Blastoise, Tipo.AGUA, TipoMovimiento.FISICO);

        Blastoise.atacar(0, pidgey, Tipo.NORMAL, TipoMovimiento.FISICO);

        pidgey2.atacar(0, Blastoise, Tipo.AGUA, TipoMovimiento.FISICO);
        ;

    }
}
