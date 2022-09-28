package exercise1;

public class Parrot extends Animal{


    public Parrot(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return this.getName() + " says: Hello!";
    }
}
