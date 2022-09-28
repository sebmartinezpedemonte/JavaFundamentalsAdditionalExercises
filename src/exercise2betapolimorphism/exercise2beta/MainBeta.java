package exercise2betapolimorphism.exercise2beta;

public class MainBeta {
    public static void main(String[] args) {


        PokemonsterWorld pokWorld = new PokemonsterWorld("Gold");
        Pokemonster poke1 = new PokemonsterElectricity("Pikachu");
        Pokemonster poke2 = new PokemonsterWater("Squirtle");
        Pokemonster poke3 = new PokemonsterGrass("Bulbasur");
        Pokemonster poke4 = new PokemonsterFire("Charmander");
        Trainer trainer1 = new Trainer("Ash");
        Trainer trainer2 = new Trainer("Max");
        pokWorld.addPoke(poke1)
                .addPoke(poke2)
                .addPoke(poke3);
        pokWorld.addTrainer(trainer1)
                .addTrainer(trainer2);
        trainer1.addPokemonster(poke1);
        trainer1.addPokemonster(poke2);
        trainer2.addPokemonster(poke3);
        trainer2.addPokemonster(poke4);
        pokWorld.play();

    }
}
