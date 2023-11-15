package Pokemon_Abstraccion;

import Pokemon_Abstraccion.pokemon.PokemonAcero;
import Pokemon_Abstraccion.pokemon.PokemonElectrico;

public class BatallaPokemon {

    public static void main(String[] args) {

    PokemonElectrico pokemonElectrico = new PokemonElectrico("Pikachu", 10);
    PokemonAcero pokemonAcero = new PokemonAcero("Solgaleo", 10);

    pokemonElectrico.atacar(1, pokemonAcero);
    System.out.println("Hp = " + pokemonAcero.getHP());
    pokemonAcero.atacar(3, pokemonElectrico);
    System.out.println("Hp = " + pokemonElectrico.getHP());   
    }
}