package exercise2betapolimorphism.exercise2beta;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class Pokemonster {

    private double health;
    private String name;
    private Ability ability;
    private boolean rage;
    private int rageAttack;
    private int attackIncrease;
    private final double DAMAGE_ATTACK_1 = 20;
    private final double DAMAGE_ATTACK_2 = 30;
    private final double DAMAGE_ATTACK_3 = 40;
    private ArrayList<String> attacksNames;
    List<Consumer<Pokemonster>> methodList;

    public Pokemonster(String name, Ability ability) {
        this.name = name;
        this.health = 100;
        this.ability = ability;
        this.rage = false;
        this.rageAttack = 1;
        this.attackIncrease = 1;
        this.attacksNames = new ArrayList<>();
        addAttackToList("Punch");
        addAttackToList("Pound");
        methodList = new ArrayList<>();
        methodList.add(this::punch);
        methodList.add(this::pound);
        methodList.add(this::special);
    }

    public void addMethodsToList(Consumer<Pokemonster> poke) {
        methodList.add(poke);
    }

    //I would love to make this work
    public void callAttack(String name, Pokemonster poke) {
        for (Consumer<Pokemonster> method : methodList) {
            if (method.toString().equalsIgnoreCase(name)) {
                method.accept(poke);
            }
        }
    }

    public boolean isRage() {
        return rage;
    }

    public int getRageAttack() {
        return rageAttack;
    }

    public int getAttackIncrease() {
        return attackIncrease;
    }

    public String getName() {
        return name;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getHealth() {
        return this.health;
    }

    public Ability getAbility() {
        return ability;
    }
    /*
    What happens if there are abilities that do not do damage
    directly, but modify a pokemoster attack, defense, etc?
     */

    public void increaseAttack() {
        attackIncrease++;
    }

    public void increaseHealth() {
        this.health += 20;
    }

    public void increaseHealth(double increase) {
        this.health += increase;
    }

    /*
   	What happens if now the pokemosters have “modes”? Like if they are enraged
    they do twice the damage but receive twice the damage as well?
     */
    public void enrage() {
        if (!rage) {
            rageAttack = 2;
            rage = true;
            this.health /= 2; //easy solution. Real solution would be to make tournament class and change everything
        } else {
            rageAttack = 1;
            rage = false;
            this.health *= 2;
        }
    }

    public void pound(Pokemonster pokemonster) {
        if (pokemonster.getHealth() > 0) {
            pokemonster.setHealth(pokemonster.getHealth() - (DAMAGE_ATTACK_1 * rageAttack * attackIncrease));
            if (pokemonster.getHealth() > 0) {
                System.out.println(this.name + "'s attack was succesful. " + pokemonster.getName()
                        + "'s health is " + pokemonster.getHealth());
            } else {
                System.out.println(this.name + " has killed " + pokemonster.getName());
            }
        } else {
            System.out.println("The creature is dead");
        }
    }

    public void punch(Pokemonster pokemonster) {
        if (pokemonster.getHealth() > 0) {
            pokemonster.setHealth(pokemonster.getHealth() - (DAMAGE_ATTACK_2 * rageAttack));
            if (pokemonster.getHealth() > 0) {
                System.out.println(this.name + "'s attack was succesful. " + pokemonster.getName()
                        + "'s health is " + pokemonster.getHealth());
            } else {
                System.out.println(this.name + " has killed " + pokemonster.getName());
            }
        } else {
            System.out.println("The creature is dead");
        }

    }

    protected double amountOfDamageMultiplier(Pokemonster pokemonster) {
        double damageMultiplier = 1;
        if (this.ability.equals(Ability.WATER) && pokemonster.getAbility().equals(Ability.FIRE)
                || this.ability.equals(Ability.FIRE) && pokemonster.getAbility().equals(Ability.GRASS)
                || this.ability.equals(Ability.GRASS) && pokemonster.getAbility().equals(Ability.WATER)
                || this.ability.equals(Ability.ELECTRICITY) && pokemonster.getAbility().equals(Ability.WATER)
                || this.ability.equals(Ability.WATER) && pokemonster.getAbility().equals(Ability.ELECTRICITY)) {
            damageMultiplier = 2;
        } else if (this.ability.equals(Ability.WATER) && pokemonster.getAbility().equals(Ability.GRASS)
                || this.ability.equals(Ability.FIRE) && pokemonster.getAbility().equals(Ability.WATER)
                || this.ability.equals(Ability.GRASS) && pokemonster.getAbility().equals(Ability.FIRE)
                || this.ability.equals(Ability.ELECTRICITY) && pokemonster.getAbility().equals(Ability.GRASS)) {
            damageMultiplier = 0.5;
        }
        return damageMultiplier;
    }
    /*
    Hit by / I am	Water	Fire	Grass  Electricity
        Water       1x 	     2x	    0.5x        2x
        Fire	    0.5x	1x	    2x          1X
        Grass	    2x	    0.5x	1x          0.5x
        Electricity   2x     1X      1X         1x
     */

    public abstract void special(Pokemonster poke);


    public void addAttackToList(String attack) {
        this.attacksNames.add(attack);
    }

    public ArrayList<String> getAttacksNames() {
        return attacksNames;
    }

    public void listAttacks() {
        for (String attack : attacksNames) {
            System.out.println(attack);
        }
    }

    public String searchAttack(String name) {
        int i = 0;
        String nameAttack = null;
        while (i < attacksNames.size() && nameAttack == null) {
            if (attacksNames.get(i).equalsIgnoreCase(name)) {
                nameAttack = attacksNames.get(i);
            } else {
                i++;
            }
        }
        return nameAttack;
    }

}

