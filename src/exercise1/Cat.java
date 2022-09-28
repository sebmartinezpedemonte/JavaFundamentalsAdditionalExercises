package exercise1;

public class Cat extends Animal{


    public Cat(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return this.getName() + " says: Meow!";
    }
}
