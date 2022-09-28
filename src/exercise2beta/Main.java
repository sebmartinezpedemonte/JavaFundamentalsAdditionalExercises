package exercise2beta;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final Scanner input = new Scanner(System.in);

        /*

        String nameTrainer;
        Trainer trainer;
        String namePoke;
        Ability abil = null;
        Pokemonster pokemonster;
        PokemonsterWorld pokeWorld = new PokemonsterWorld("Gold");
        System.out.println("Welcome to Pokemonster's world.");

        for(int i = 1 ; i <3 ; i++) {
            System.out.println("Player " + i + ", what's your name?");
            nameTrainer = input.nextLine();
            trainer = new Trainer(nameTrainer);
            pokeWorld.addTrainer(trainer);
            for(int j = 1; j <7 ; j++) {
                System.out.println(trainer.getName() + " choose your Pokemonster number " + j + ". Write down it's name:");
                namePoke = input.nextLine();
                do {
                    try {
                        System.out.println("Now write down it's ability: WATER, FIRE, GRASS, ELECTRICITY");
                        abil = Ability.valueOf(input.nextLine());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Try again");
                    }
                } while (abil != Ability.FIRE && abil != Ability.GRASS && abil != Ability.WATER && abil != Ability.ELECTRICITY);
                pokemonster = new Pokemonster(namePoke, abil);
                pokeWorld.addPoke(pokemonster);
                trainer.addPokemonster(pokemonster);
            }
        }

        trainer.swapPokemonster(pokemonster1, pokemonster2, trainer2);
        trainer1.swapPokemonster(pokemonster1, pokemonster2, trainer2);
        trainer1.swapPokemonster(null, pokemonster2,  trainer2);
        trainer2.swapPokemonster(pokemonster1, pokemonster2, trainer1);

        System.out.println(pokemonster1.getName() +" will fight against " + pokemonster2.getName());

        while(pokemonster1.getHealth()>0 && pokemonster2.getHealth()>0) {

            System.out.println(trainer1.getName() +  " what attack will you choose (1 or 2):");
            int attack = Integer.parseInt(input.nextLine());
            if(attack == 1){
                pokemonster1.attack1(pokemonster2);
            }else{
                pokemonster1.attack2(pokemonster2);
            }
            if(pokemonster2.getHealth()>0) {
                System.out.println(trainer2.getName() + " what attack will you choose (1 or 2):");
                int attack2 = Integer.parseInt(input.nextLine());
                if (attack2 == 1) {
                    pokemonster2.attack1(pokemonster1);
                } else {
                    pokemonster2.attack2(pokemonster1);
                }
            }
        }
        double pok1 = pokemonster1.getHealth();
        double pok2 = pokemonster2.getHealth();

        if(pok1 > pok2){
            System.out.println(pokemonster1.getName() + " has won");
        }else if (pok1<pok2){
            System.out.println(pokemonster2.getName() + " has won");
        }else{
            System.out.println("It's a tie");
        }*/
    }
}
