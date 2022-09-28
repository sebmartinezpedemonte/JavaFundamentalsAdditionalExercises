package exercise2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Pokemonster's world.");
        System.out.println("Player 1, what's your name?");
        String nameTrainer1 = input.nextLine();
        Trainer trainer1 = new Trainer(nameTrainer1);
        System.out.println(trainer1.getName() + " choose your Pokemonster, write down it's name");
        String namePoke = input.nextLine();
        Ability abil = null;
        do {
            try {
                System.out.println("Now write down it's ability: WATER, FIRE, GRASS");
                abil = Ability.valueOf(input.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Try again");
            }
        }while(abil != Ability.FIRE && abil != Ability.GRASS && abil!= Ability.WATER);
        Pokemonster pokemonster1 = new Pokemonster(namePoke,abil);
        trainer1.addPokemonster(pokemonster1);


        System.out.println("Player 2, what's your name?");
        String nameTrainer2 = input.nextLine();
        Trainer trainer2 = new Trainer(nameTrainer2);
        System.out.println(trainer2.getName() + " choose your Pokemonster, write down it's name");
        String namePoke2 = input.nextLine();
        Ability abil2 = null;
        do {
            try {
                System.out.println("Now write down it's ability: WATER, FIRE, GRASS");
                abil2 = Ability.valueOf(input.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Try again");
            }
        }while(abil2 != Ability.FIRE && abil2 != Ability.GRASS && abil2!= Ability.WATER);
        Pokemonster pokemonster2 = new Pokemonster(namePoke2,abil2);
        trainer2.addPokemonster(pokemonster2);

        trainer1.swapPokemonster(pokemonster1, pokemonster2, trainer2);
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
        }
    }
}
