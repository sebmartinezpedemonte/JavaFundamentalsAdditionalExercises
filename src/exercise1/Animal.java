package exercise1;

public abstract class Animal {

    private String name;

    public Animal(String name){
        this.name = name;
    }


    public abstract String makeSound();

    protected String getName(){
        return this.name;
    }

}
