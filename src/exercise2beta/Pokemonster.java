package exercise2beta;

public class Pokemonster {

    /*

A Pokemostro has a health bar, and two abilities that deal damage.
In this game, two pokemostros fight each other to death.
•	Create a beta version of the game where 2 pokemostros fight each other.
 You can use the Scanner (https://www.w3schools.com/java/java_user_input.asp )
 to read input from the console and decide what to do in each turn.     */


    private double health;
    private String name;
    private Ability ability;
    private boolean rage;
    private int rageAttack;
    private int attackIncrease;
    private final double DAMAGE_ATTACK_1 = 20;
    private final double DAMAGE_ATTACK_2 = 30;

    public Pokemonster(String name, Ability ability){
        this.name = name;
        this.health = 100;
        this.ability = ability;
        this.rage = false;
        this.rageAttack = 1;
        this.attackIncrease = 1;
    }

    public String getName() {
        return name;
    }

    public void setHealth(double health){
        this.health = health;
    }

    public double getHealth(){
        return this.health;
    }

    public Ability getAbility() {
        return ability;
    }
    /*
    What happens if there are abilities that do not do damage
    directly, but modify a pokemoster attack, defense, etc?
     */

    public void increaseAttack(){
        attackIncrease++;
    }

    public void increaseHealth(){
        this.health += 20;
    }
    public void increaseHealth(double increase){
        this.health += increase;
    }

    /*
   	What happens if now the pokemosters have “modes”? Like if they are enraged
    they do twice the damage but receive twice the damage as well?
     */
    public void enrage(){
        if(!rage) {
            rageAttack = 2;
            rage = true;
            this.health /= 2; //easy solution. Real solution would be to make tournament class and change everything
        }else{
            rageAttack = 1;
            rage = false;
            this.health *=2;
        }
    }

    public void pound(Pokemonster pokemonster){
        if(pokemonster.getHealth() > 0) {
            pokemonster.setHealth(pokemonster.getHealth() - (DAMAGE_ATTACK_1* amountOfDamageMultiplier(pokemonster)*rageAttack*attackIncrease));
            if(pokemonster.getHealth() > 0) {
                System.out.println(this.name +  "'s attack was succesful. " + pokemonster.getName()
                        + "'s health is " + pokemonster.getHealth());
            }else{
                System.out.println(this.name +" has killed " + pokemonster.getName());
            }
        }else {
            System.out.println("The creature is dead");
        }
    }

    public void punch(Pokemonster pokemonster){
        if(pokemonster.getHealth() > 0) {
            pokemonster.setHealth(pokemonster.getHealth() - (DAMAGE_ATTACK_2 * amountOfDamageMultiplier(pokemonster)*rageAttack));
            if(pokemonster.getHealth() > 0) {
                System.out.println(this.name +  "'s attack was succesful. " + pokemonster.getName()
                        + "'s health is " + pokemonster.getHealth());
            }else{
                System.out.println(this.name +" has killed " + pokemonster.getName());
            }
        }else {
            System.out.println("The creature is dead");
        }
    }

    private double amountOfDamageMultiplier(Pokemonster pokemonster){
        double damageMultiplier = 1;
        if(this.ability.equals(Ability.WATER) && pokemonster.getAbility().equals(Ability.FIRE)
        || this.ability.equals(Ability.FIRE) && pokemonster.getAbility().equals(Ability.GRASS)
        || this.ability.equals(Ability.GRASS) && pokemonster.getAbility().equals(Ability.WATER)
        || this.ability.equals(Ability.ELECTRICITY) && pokemonster.getAbility().equals(Ability.WATER)
                || this.ability.equals(Ability.WATER) && pokemonster.getAbility().equals(Ability.ELECTRICITY)){
            damageMultiplier = 2;
        }else if(this.ability.equals(Ability.WATER) && pokemonster.getAbility().equals(Ability.GRASS)
        || this.ability.equals(Ability.FIRE) && pokemonster.getAbility().equals(Ability.WATER)
        || this.ability.equals(Ability.GRASS) && pokemonster.getAbility().equals(Ability.FIRE)
        || this.ability.equals(Ability.ELECTRICITY) && pokemonster.getAbility().equals(Ability.GRASS)){
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

    public String listAttacks(){
        return "Pound \n " + "Punch";
    }

    @Override
    public String toString() {
        return name + ", ability: " + ability;
    }
}
