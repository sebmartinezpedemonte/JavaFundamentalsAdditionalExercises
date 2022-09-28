package exercise1;

import java.util.ArrayList;

public class Zoo {

    private ArrayList<Animal> animals;

    public Zoo() {
        this.animals = new ArrayList<>();
    }


    public void addAnimal(Animal animal) {
        if (animal != null && searchAnimal(animal.getName()) == null) {
            this.animals.add(animal);
            System.out.println("The " + animal.getClass().getSimpleName() + " with name " + animal.getName() + " has been added to the zoo");
        } else if (searchAnimal(animal.getName()) == null) {
            System.out.println("The " + animal.getClass().getSimpleName() + " with name " + animal.getName() + " is already in the zoo");
        } else {
            System.out.println("The animal can't be null");
        }
    }

    //The zoo should have a method to remove an animal from the zoo based on its name

    public void removeAnimal(String name) {
        Animal animalToRemove = searchAnimal(name);
        if (animalToRemove != null) {
            animals.remove(animalToRemove);
            System.out.println("The " + animalToRemove.getClass().getSimpleName() + " with name " + animalToRemove.getName() + " has been removed from the zoo");
        } else {
            System.out.println("There is no animal with that name in the zoo.");
        }
    }

    private Animal searchAnimal(String name) {
        Animal animalSearched = null;
        int i = 0;
        while (i < this.animals.size() && animalSearched == null) {
            if (this.animals.get(i).getName().equals(name)) {
                animalSearched = this.animals.get(i);
            } else {
                i++;
            }
        }
        return animalSearched;
    }

    private Animal searchAnimalStream(String name) {
        return animals.stream().filter(a -> a.getName().equals(name)).
                findFirst().orElseGet(null);
    }

    public void jumanji() {
        for (Animal animal : animals) {
            System.out.println(animal.makeSound());

        }
    }

    //The zoo should have a method to return an animal by its name if it exists
    public Animal returnAnimal(String name) {
        //if it exists it returns the animal, if not, it returns null as it has to return something
        Animal animalReturned = searchAnimal(name);
        if (animalReturned == null) {
            System.out.println("The animal with name " + name + " is not in this zoo");
        } else {
            System.out.println("The " + animalReturned.getClass().getSimpleName() + " with name " + animalReturned.getName() + " is in this zoo.");
        }
        return animalReturned;
    }

    public void animalExists(String name) {
        Animal animalReturned = searchAnimal(name);
        if (animalReturned == null) {
            System.out.println("The animal with name " + name + " is not in this zoo");
        } else {
            System.out.println("The " + animalReturned.getClass().getSimpleName() + " with name " + animalReturned.getName() + " is in this zoo.");
        }
    }

}
