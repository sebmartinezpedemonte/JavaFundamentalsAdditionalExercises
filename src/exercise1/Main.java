package exercise1;

public class Main {
    public static void main(String[] args) {
        /*
        •	Create a main class that generates a zoo and adds
        different animals, makes them make a sound, adds, removes,
        and asks if certain animals exist.
         */

        Zoo zoo1 = new Zoo();
        Dog dog1 = new Dog("Toto");
        zoo1.addAnimal(dog1);
        zoo1.addAnimal(new Dog("Oddie"));
        zoo1.addAnimal(new Cat("Garfield"));
        zoo1.addAnimal(new Parrot("Kellogs"));
        zoo1.jumanji();
        zoo1.animalExists("Coffee");
        zoo1.animalExists("Garfield");
        zoo1.removeAnimal("Kellogs");
        zoo1.animalExists("Kellogs");
        zoo1.returnAnimal("Garfield");
        System.out.println(dog1.makeSound());

    }
}
