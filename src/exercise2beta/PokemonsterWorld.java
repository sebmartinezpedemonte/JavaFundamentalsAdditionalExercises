package exercise2beta;

import java.util.ArrayList;
import java.util.Scanner;

public class PokemonsterWorld {
    private final Scanner input = new Scanner(System.in);
    private  ArrayList<Pokemonster> pokes;
    private  ArrayList<Trainer> trainers;

    private  ArrayList<Pokemonster> pokeTrainer1;
    private  ArrayList<Pokemonster> pokeTrainer2;
    private  ArrayList<Trainer> trainersChosen;
    private  ArrayList<Pokemonster> availablePokes;
    private String name;

    public PokemonsterWorld(String name) {
        this.name = name;
        this.pokes = new ArrayList<>();
        this.trainers = new ArrayList<>();
        this.pokeTrainer1 = new ArrayList<>();
        this.pokeTrainer2 = new ArrayList<>();
        this.trainersChosen = new ArrayList<>();
        this.availablePokes = new ArrayList<>();
    }
    public String questionValidation(String type) {
        String answer;
        do {
            answer = input.nextLine();
            if (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) {
                System.out.println("That is not a possible answer");
            }
        } while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no"));
        return answer;
    }
    public void addQuestion(String type) {
        System.out.println("Would you like to add a " + type + "? Yes/No");
        String answer = this.questionValidation(type);
        if (answer.equalsIgnoreCase("yes")) {
            if (type.equalsIgnoreCase("trainer")) {
                this.addTrainerConsole();
            } else if (type.equalsIgnoreCase("pokemonster")) {
                this.addPokeConsole();
            } else {
                System.out.println("Error, continue");
            }
            this.addQuestion(type);
        } else {
            System.out.println("Ok");
        }

    }

    public void play() {
        System.out.println("Welcome to Pokemonster's world.");

        this.addQuestion("trainer");
        this.addQuestion("pokemonster");

        trainersChosen.addAll(trainers);
        Trainer trainer1 = this.chooseTrainer();
        this.pokeTrainer1.addAll(trainer1.getPokemonsters());
        this.addPokemonsterToTrainer1(trainer1);
        this.checkEnoughPokemons1(trainer1);
        trainersChosen.remove(trainer1);
        Trainer trainer2 = this.chooseTrainer();
        this.pokeTrainer2.addAll(trainer2.getPokemonsters());
        this.addPokemonsterToTrainer2(trainer2);
        this.checkEnoughPokemons2(trainer2);


        this.fight(trainer1.getName(), trainer2.getName());

    }

    public void checkEnoughPokemons1(Trainer trainer) {
        while (trainer.getPokemonsters().isEmpty()) {
            System.out.println("There are no available Pokemonsters. Add a Pokemonster.");
            this.addPokeConsole();
            do {
                this.addPokemonsterToTrainer1(trainer);
                if(trainer.getPokemonsters().isEmpty()){
                    System.out.println("You must add a Pokemonster to your list.");
                }
            }while (trainer.getPokemonsters().isEmpty());
        }
    }
    public void checkEnoughPokemons2(Trainer trainer) {
        while (trainer.getPokemonsters().isEmpty()) {
            System.out.println("There are no available Pokemonsters. Add a Pokemonster.");
            this.addPokeConsole();
            do {
                this.addPokemonsterToTrainer2(trainer);
                if(trainer.getPokemonsters().isEmpty()){
                    System.out.println("You must add a Pokemonster to your list.");
                }
            }while (trainer.getPokemonsters().isEmpty());
        }
    }
    /*
    public PokemonsterWorld addPoke(String name, Ability ability){
        if(name != null && ability != null) {
            pokes.add(new Pokemonster(name, ability));
        }
        return this;
    }*/

    public void addPokeConsole() {
        //Pokemonster poke = null;
        Pokemonster poke;
        //String name = "";
        String name;
        Ability ability;
        String answer;
        do {
            System.out.println("Insert the name of the Pokemonster");
            answer = input.nextLine();
        } while (answer == null);
        name = answer.substring(0, 1).toUpperCase() + answer.substring(1);
        do {
            System.out.println("Insert the ability of the Pokemonster: WATER, FIRE, ELECTRICITY, GRASS"); //List method in enum in the future
            answer = input.nextLine();
        } while (!answer.equalsIgnoreCase("WATER") && !answer.equalsIgnoreCase("FIRE") &&
                !answer.equalsIgnoreCase("ELECTRICITY") && !answer.equalsIgnoreCase("GRASS"));
        ability = Ability.valueOf(answer.toUpperCase());
        poke = new Pokemonster(name, ability);
        this.availablePokes.add(poke);

    }

    public void addTrainerConsole() {
        String nameTrainer;
        do {
            System.out.println("Insert the name of the Trainer");
            nameTrainer = input.nextLine();
        } while (nameTrainer == null);
        String name = nameTrainer.substring(0, 1).toUpperCase() + nameTrainer.substring(1);
        Trainer trainer = new Trainer(name);
        this.trainers.add(trainer);
    }


    public PokemonsterWorld addPoke(Pokemonster pokemonster) {
        if (pokemonster != null) {
            pokes.add(pokemonster);
            //System.out.println(pokemonster.getName() +" has been added succesfully");
        }
        return this;
    }

    public PokemonsterWorld addTrainer(Trainer trainer) {
        if (trainer != null) {
            trainers.add(trainer);
        }
        return this;
    }
    /*
    public PokemonsterWorld addTrainer(String name){
        if(name!=null){
            trainers.add(new Trainer(name));
        }
        return this;
    }*/

    private Trainer searchTrainer(String name) {
        return trainers.stream().filter(t -> t.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }


    private Pokemonster searchAvailablePokemonsters(String name) {
        return this.availablePokes.stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
    /*
    private Pokemonster searchPokemonsterTrainer(String namePoke, String nameTrainer) {
        Trainer trainer = searchTrainer(nameTrainer);
        if (trainer != null && !trainer.getPokemonsters().isEmpty()) {
            ArrayList<Pokemonster> pokesTrainer = trainer.getPokemonsters();
            return pokesTrainer.stream().filter(p -> p.getName().equalsIgnoreCase(namePoke)).findFirst().orElse(null);
        } else {
            return null;
        }
    }*/
    private Pokemonster searchPokemonsterTrainer1(String namePoke, String nameTrainer) {
        Trainer trainer = searchTrainer(nameTrainer);
        if (trainer != null && !pokeTrainer1.isEmpty()) {

            return pokeTrainer1.stream().filter(p -> p.getName().equalsIgnoreCase(namePoke)).findFirst().orElse(null);
        } else {
            return null;
        }
    }

    private Pokemonster searchPokemonsterTrainer2(String namePoke, String nameTrainer) {
        Trainer trainer = searchTrainer(nameTrainer);
        if (trainer != null && !pokeTrainer2.isEmpty()) {

            return pokeTrainer2.stream().filter(p -> p.getName().equalsIgnoreCase(namePoke)).findFirst().orElse(null);
        } else {
            return null;
        }
    }
    /*
    public void listAllPokemonsters() {
        System.out.println("Pokemonsters:");
        pokes.forEach(System.out::println);
        //for(int i = 0 ; i < pokes.size(); i++){
        //    System.out.println( (i+1) + "-" + pokes.get(i));
        //}
    }*/
    /*
    public void listTrainersPokemonsters(String name) {
        Trainer trainerPokes = searchTrainer(name);
        if (trainerPokes != null && !trainerPokes.getPokemonsters().isEmpty()) {
            System.out.println("Pokemonsters:");
            trainerPokes.getPokemonsters().forEach(System.out::println);
            //for (int i = 0; i < trainerPokes.getPokemonsters().size(); i++) {
            //    System.out.println((i + 1) + "-" + trainerPokes.getPokemonsters().get(i));
            //}
        }
    }*/

    public void listTrainersPokemonsters1(String name) {
        Trainer trainerPokes = searchTrainer(name);
        if (trainerPokes != null && !pokeTrainer1.isEmpty()) {
            System.out.println("Pokemonsters:");
            pokeTrainer1.forEach(System.out::println);
            //for (int i = 0; i < trainerPokes.getPokemonsters().size(); i++) {
            //    System.out.println((i + 1) + "-" + trainerPokes.getPokemonsters().get(i));
            //}
        }
    }

    public void listTrainersPokemonsters2(String name) {
        Trainer trainerPokes = searchTrainer(name);
        if (trainerPokes != null && !pokeTrainer2.isEmpty()) {
            System.out.println("Pokemonsters:");
            pokeTrainer2.forEach(System.out::println);
            //for (int i = 0; i < trainerPokes.getPokemonsters().size(); i++) {
            //    System.out.println((i + 1) + "-" + trainerPokes.getPokemonsters().get(i));
            //}
        }
    }


    public void listTrainers() {
        System.out.println("Trainers");
        trainersChosen.forEach(System.out::println);
    }

    //if we change many things we could also use this
    public Trainer chooseTrainer() {
        Trainer trainer;
        System.out.println("Choose your Trainer:");
        do {
            listTrainers();
            String trainerName = input.nextLine();
            trainer = searchTrainer(trainerName);
            if (trainer == null) {
                System.out.println("The trainer you wrote is not on the list, choose again");
            }
        } while (trainer == null);

        return trainer;
    }

    public void addPokemonsterToTrainer1(Trainer trainer) {
        System.out.println("Would you like to add a pokemonster to " + trainer.getName() + "? Yes/No");
        String answer = questionValidation(trainer.getName());
        if (answer.equalsIgnoreCase("yes")) {
            Pokemonster pokeToAdd = null;
            do {
                System.out.println("Choose the Pokemonster:");
                this.availablePokes.forEach(System.out::println);
                String answerPoke = input.nextLine();
                pokeToAdd = searchAvailablePokemonsters(answerPoke);
                if (pokeToAdd != null) {
                    trainer.addPokemonster(pokeToAdd);
                    pokeTrainer1.add(pokeToAdd);
                    this.availablePokes.remove(pokeToAdd);
                } else {
                    System.out.println("There is no Pokemonster with that name");
                    this.checkEnoughPokemons1(trainer);
                }
            }while(pokeToAdd==null);

        } else {
            System.out.println("Ok");
        }
    }
    public void addPokemonsterToTrainer2(Trainer trainer) {
        System.out.println("Would you like to add a pokemonster to " + trainer.getName() + "? Yes/No");
        String answer = questionValidation(trainer.getName());
        if (answer.equalsIgnoreCase("yes")) {
            Pokemonster pokeToAdd = null;
            do {
                System.out.println("Choose the Pokemonster:");
                this.availablePokes.forEach(System.out::println);
                String answerPoke = input.nextLine();
                pokeToAdd = searchAvailablePokemonsters(answerPoke);
                if (pokeToAdd != null) {
                    trainer.addPokemonster(pokeToAdd);
                    pokeTrainer2.add(pokeToAdd);
                    this.availablePokes.remove(pokeToAdd);
                } else {
                    System.out.println("There is no Pokemonster with that name");
                    this.checkEnoughPokemons2(trainer);
                }
            }while(pokeToAdd==null);

        } else {
            System.out.println("Ok");
        }
    }


    /*public Pokemonster choosePokemonster(String trainerName) {
        Pokemonster poke = null;
        Trainer trainer = searchTrainer(trainerName);
        if (trainer != null && !trainer.getPokemonsters().isEmpty()) {
            System.out.println(trainer.getName() + " choose your Pokemonster:");
            do {
                listTrainersPokemonsters(trainerName);
                String pokeName = input.nextLine();
                poke = searchPokemonsterTrainer(pokeName, trainerName);
                if (poke == null) {
                    System.out.println("The Pokemonster you wrote is not on your list, choose again");
                }
            } while (poke == null);
        }

        return poke;
    }*/
    public Pokemonster choosePokemonster1(String trainerName) {
        Pokemonster poke = null;
        Trainer trainer = searchTrainer(trainerName);
        if (trainer != null && !pokeTrainer2.isEmpty()) {
            System.out.println(trainer.getName() + " choose your Pokemonster:");
            do {
                listTrainersPokemonsters1(trainerName);
                String pokeName = input.nextLine();
                poke = searchPokemonsterTrainer1(pokeName, trainerName);
                if (poke == null) {
                    System.out.println("The Pokemonster you wrote is not on your list, choose again");
                }
            } while (poke == null);
        }

        return poke;
    }

    public Pokemonster choosePokemonster2(String trainerName) {
        Pokemonster poke = null;
        Trainer trainer = searchTrainer(trainerName);
        if (trainer != null && !pokeTrainer2.isEmpty()) {
            System.out.println(trainer.getName() + " choose your Pokemonster:");
            do {
                listTrainersPokemonsters2(trainerName);
                String pokeName = input.nextLine();
                poke = searchPokemonsterTrainer2(pokeName, trainerName);
                if (poke == null) {
                    System.out.println("The Pokemonster you wrote is not on your list, choose again");
                }
            } while (poke == null);
        }

        return poke;
    }

    public void fight(String nameTrainer1, String nameTrainer2) {
        Trainer trainer1 = searchTrainer(nameTrainer1);
        Trainer trainer2 = searchTrainer(nameTrainer2);
        Pokemonster pokeFightTrainer1;
        Pokemonster pokeFightTrainer2;
        String attackName;

        if (trainer1 != null && trainer2 != null) {
            do {
                pokeFightTrainer1 = this.choosePokemonster1(nameTrainer1);
                pokeFightTrainer2 = this.choosePokemonster2(nameTrainer2);
                // ArrayList<Pokemonster> alivePokemonstersTrainer1 = this.alivePokemonsterList(trainer1);
                //ArrayList<Pokemonster> alivePokemonstersTrainer2 = this.alivePokemonsterList(trainer2);

                do {
                    do {//we could change this later and make an arrayList of names of attacks to be able to scale this
                        System.out.println(nameTrainer1 + " choose " + pokeFightTrainer1.getName() + " attack: " + pokeFightTrainer1.listAttacks());
                        attackName = input.nextLine();
                        if (!attackName.equalsIgnoreCase("punch") && !attackName.equalsIgnoreCase("pound")) {
                            System.out.println("That attack doesn't exist");
                        }
                    } while (!attackName.equalsIgnoreCase("punch") && !attackName.equalsIgnoreCase("pound"));
                    if (attackName.equalsIgnoreCase("punch")) {
                        pokeFightTrainer1.punch(pokeFightTrainer2);
                    } else {
                        pokeFightTrainer1.pound(pokeFightTrainer2);
                    }

                    if (pokeFightTrainer1.getHealth() > 0 && pokeFightTrainer2.getHealth() > 0) {

                        do
                        {//we could change this later and make an arrayList of names of attacks to be able to scale this
                            System.out.println(nameTrainer2 + " choose " + pokeFightTrainer2.getName() + " attack: " + pokeFightTrainer2.listAttacks());
                            attackName = input.nextLine();
                            if (!attackName.equalsIgnoreCase("punch") && !attackName.equalsIgnoreCase("pound")) {
                                System.out.println("That attack doesn't exist");
                            }
                        } while (!attackName.equalsIgnoreCase("punch") && !attackName.equalsIgnoreCase("pound"));
                        if (attackName.equalsIgnoreCase("punch")) {
                            pokeFightTrainer2.punch(pokeFightTrainer1);
                        } else {
                            pokeFightTrainer2.pound(pokeFightTrainer1);
                        }
                    }

                } while (pokeFightTrainer1.getHealth() > 0 && pokeFightTrainer2.getHealth() > 0);
                if (pokeFightTrainer1.getHealth() > pokeFightTrainer2.getHealth()) {
                    System.out.println(pokeFightTrainer1.getName() + " won the battle");
                    pokeTrainer2.remove(pokeFightTrainer2);
                    //this.removePokemonsterFromAliveList(pokeTrainer1, pokeFightTrainer2);
                } else if (pokeFightTrainer2.getHealth() > pokeFightTrainer1.getHealth()) {
                    System.out.println(pokeFightTrainer2.getName() + " won the battle");
                    pokeTrainer1.remove(pokeFightTrainer1);
                    // this.removePokemonsterFromAliveList(pokeTrainer2, pokeFightTrainer1);
                } else {
                    System.out.println("Both have died");
                    pokeTrainer1.remove(pokeFightTrainer1);
                    pokeTrainer2.remove(pokeFightTrainer2);
                    //this.removePokemonsterFromAliveList(pokeTrainer1, pokeFightTrainer1);
                    //this.removePokemonsterFromAliveList(pokeTrainer2, pokeFightTrainer2);
                }
            } while (!pokeTrainer1.isEmpty() && !pokeTrainer2.isEmpty());
            System.out.println("The match has finished");
            if (pokeTrainer1.size() > pokeTrainer2.size()) {
                System.out.println(trainer1.getName() + " has won the match");
            } else if (pokeTrainer2.size() > pokeTrainer1.size()) {
                System.out.println(trainer2.getName() + " has won the match");
            } else {
                System.out.println("It's a tie");
            }
        } else {
            System.out.println("The trainers you chose don't exist");
        }
    }

    /*private ArrayList<Pokemonster> alivePokemonsterList(Trainer trainer) {
        ArrayList<Pokemonster> alivePokes = new ArrayList<>();
        alivePokes.addAll(trainer.getPokemonsters());
        return alivePokes;
    }*/
    /*
    private ArrayList<Pokemonster> alivePokemonsterList1() {
        ArrayList<Pokemonster> alivePokes = new ArrayList<>();
        alivePokes.addAll(pokeTrainer1);
        return alivePokes;
    }
    private ArrayList<Pokemonster> alivePokemonsterList2() {
        ArrayList<Pokemonster> alivePokes = new ArrayList<>();
        alivePokes.addAll(pokeTrainer2);
        return alivePokes;
    }
*/
    /*private void removePokemonsterFromAliveList(ArrayList<Pokemonster> alivePokeList, Pokemonster pokeRemove){
        alivePokeList.remove(pokeRemove);
    }*/
/*
    public void FightChoosingTrainerConsole() {
        listTrainers();
        System.out.println("Player 1, choose your trainer:");
        String nameTrainer1 = input.nextLine();
        System.out.println("Player 2, choose your trainer:");
        String nameTrainer2 = input.nextLine();
        Trainer trainer1 = searchTrainer(nameTrainer1);
        Trainer trainer2 = searchTrainer(nameTrainer2);
        Pokemonster pokeFightTrainer1;
        Pokemonster pokeFightTrainer2;
        String attackName;

        if (trainer1 != null && trainer2 != null) {
            do {
                pokeFightTrainer1 = this.choosePokemonster1(nameTrainer1);
                pokeFightTrainer2 = this.choosePokemonster2(nameTrainer2);
                //ArrayList<Pokemonster> alivePokemonstersTrainer1 = this.alivePokemonsterList(trainer1);
                //ArrayList<Pokemonster> alivePokemonstersTrainer2 = this.alivePokemonsterList(trainer2);

                do {
                    do {//we could change this later and make an arrayList of names of attacks to be able to scale this
                        System.out.println(nameTrainer1 + " choose " + pokeFightTrainer1.getName() + " attack: " + pokeFightTrainer1.listAttacks());
                        attackName = input.nextLine();
                        if (!attackName.equalsIgnoreCase("punch") && !attackName.equalsIgnoreCase("pound")) {
                            System.out.println("That attack doesn't exist");
                        }
                    } while (!attackName.equalsIgnoreCase("punch") && !attackName.equalsIgnoreCase("pound"));
                    if (attackName.equalsIgnoreCase("punch")) {
                        pokeFightTrainer1.punch(pokeFightTrainer2);
                    } else {
                        pokeFightTrainer1.pound(pokeFightTrainer2);
                    }


                    do {//we could change this later and make an arrayList of names of attacks to be able to scale this
                        System.out.println(nameTrainer2 + " choose " + pokeFightTrainer2.getName() + " attack: " + pokeFightTrainer2.listAttacks());
                        attackName = input.nextLine();
                        if (!attackName.equalsIgnoreCase("punch") && !attackName.equalsIgnoreCase("pound")) {
                            System.out.println("That attack doesn't exist");
                        }
                    } while (!attackName.equalsIgnoreCase("punch") && !attackName.equalsIgnoreCase("pound"));
                    if (attackName.equalsIgnoreCase("punch")) {
                        pokeFightTrainer2.punch(pokeFightTrainer1);
                    } else {
                        pokeFightTrainer2.pound(pokeFightTrainer1);
                    }

                } while (pokeFightTrainer1.getHealth() > 0 && pokeFightTrainer2.getHealth() > 0);
                if (pokeFightTrainer1.getHealth() > pokeFightTrainer2.getHealth()) {
                    System.out.println(pokeFightTrainer1.getName() + " won the battle");
                    this.removePokemonsterFromAliveList(pokeTrainer1, pokeFightTrainer1);
                }else if(pokeFightTrainer2.getHealth() > pokeFightTrainer1.getHealth()){
                    System.out.println(pokeFightTrainer2.getName() + " won the battle");
                    this.removePokemonsterFromAliveList(pokeTrainer2, pokeFightTrainer2);
                }else{
                    System.out.println("Both have died");
                    this.removePokemonsterFromAliveList(pokeTrainer1, pokeFightTrainer1);
                    this.removePokemonsterFromAliveList(pokeTrainer2, pokeFightTrainer2);
                }
            } while (!pokeTrainer1.isEmpty() && !pokeTrainer2.isEmpty());
            System.out.println("The match has finished");
            if(pokeTrainer1.size() > pokeTrainer2.size()){
                System.out.println(trainer1.getName() + " has won the match");
            }else if(pokeTrainer2.size() > pokeTrainer1.size()){
                System.out.println(trainer2.getName() + " has won the match");
            }else{
                System.out.println("It's a tie");
            }
        } else {
            System.out.println("The trainers you chose don't exist");
        }
    }
*/

    @Override
    public String toString() {
        return "PokemonsterWorld{" +
                "input=" + input +
                ", pokes=" + pokes +
                ", trainers=" + trainers +
                ", pokeTrainer1=" + pokeTrainer1 +
                ", pokeTrainer2=" + pokeTrainer2 +
                ", trainersChosen=" + trainersChosen +
                ", name='" + name + '\'' +
                '}';
    }
}