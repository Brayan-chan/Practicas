//package pokemon_herencia;
import pokemon.PokemonAcero;
import pokemon.PokemonElectrico;

public class BatallaPokemon {
    //PokemonElectrico pokemonElectrico = new PokemonElectrico("Pikachu", 10);
    //PokemonAcero pokemonAcero = new PokemonAcero("Solgaleo", 10);

    public static void main(String[] args) {

    PokemonElectrico pokemonElectrico = new PokemonElectrico("Pikachu", 10);
    PokemonAcero pokemonAcero = new PokemonAcero("Solgaleo", 10);

    pokemonElectrico.atacar(2, pokemonAcero);
    //pokemonAcero.recibirAtaque("Cola de hierro");
    System.out.println("Hp = " + pokemonAcero.getHP());
    pokemonAcero.atacar(1, pokemonElectrico);
    //pokemonElectrico.recibirAtaque("Puño meteoro");
    System.out.println("Hp = " + pokemonElectrico.getHP());   
    }
}