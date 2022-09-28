package exercise2beta;

import java.util.ArrayList;

public class Trainer {

    private ArrayList<Pokemonster> pokemonsters;
    private String name;
    private final int MAX_POKEMONSTER = 6;

    public Trainer(String name){
        this.pokemonsters = new ArrayList<>();
        this.name = name;
    }

    public void addPokemonster(Pokemonster pokemonster){
        if(pokemonster != null && !this.pokemonsters.contains(pokemonster)  && pokemonsters.size()<MAX_POKEMONSTER){
            this.pokemonsters.add(pokemonster);
        }
    }

    public void swapPokemonster(Pokemonster pok1 , Pokemonster pok2, Trainer trainer){
        if(pok1 != null && pok2 != null && trainer.getPokemonsters().size() <6
                && this.pokemonsters.size()<6 && trainer.getPokemonsters().contains(pok2)
        &&this.pokemonsters.contains(pok1)){
            trainer.getPokemonsters().add(pok1);
            trainer.getPokemonsters().remove(pok2);
            this.pokemonsters.remove(pok1);
            this.pokemonsters.add(pok2);
            System.out.println("The swap between " + this.name + " and " + trainer.getName()
                    + " was successful. Now " + trainer.getName() + " owns " + pok1.getName() +
                    " and " + this.name + " owns " + pok2.getName());
        }else{
            System.out.println("The swap was not successful");

        }

    }

    public void removePokemonster(ArrayList<Pokemonster> pokemonsters, Pokemonster pokemonster){
        pokemonsters.remove(pokemonster);
    }

    public ArrayList<Pokemonster> getPokemonsters() {
        return this.pokemonsters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
