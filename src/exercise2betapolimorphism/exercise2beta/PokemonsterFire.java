package exercise2betapolimorphism.exercise2beta;

public class PokemonsterFire extends Pokemonster{
    private final double DAMAGE_ATTACK_3 = 40;


    public PokemonsterFire(String name) {
        super(name, Ability.FIRE);
        addAttackToList("Fire Blast");
    }
    @Override
    public void special(Pokemonster pokemonster) {
        if (pokemonster.getHealth() > 0) {
            pokemonster.setHealth(pokemonster.getHealth() - (DAMAGE_ATTACK_3 * this.amountOfDamageMultiplier(pokemonster) * getRageAttack() * getAttackIncrease()));
            if (pokemonster.getHealth() > 0) {
                System.out.println(this.getName() + "'s Fire Blast attack was successful. " + pokemonster.getName()
                        + "'s health is " + pokemonster.getHealth());
                if (this.amountOfDamageMultiplier(pokemonster) == 2) {
                    System.out.println("The attack was super effective");
                }

            } else {
                if (this.amountOfDamageMultiplier(pokemonster) == 2) {
                    System.out.println("The attack was super effective");
                }
                System.out.println(this.getName() + " has killed " + pokemonster.getName());
            }
        } else {
            System.out.println("The creature is dead");
        }
    }
}
