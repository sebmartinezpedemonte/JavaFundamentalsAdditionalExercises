package exercise1;

public class Dog extends Animal{


    public Dog(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return this.getName() + " says: Woof!";
    }
}
