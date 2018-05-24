package zoo;

import java.util.ArrayList;

public abstract class Animal {
    private String name;
    private String species;
    protected animalType type;
    private Pen assignedPen;
    private static ArrayList<Animal> listOfAllAnimals = new ArrayList<>();

    public enum animalType {LAND, WATER, AMPHIBIOUS, FLYING, PETTABLE}

    public Animal(String name, String species, animalType type, Pen assignedPen) {
        this.name = name;
        this.species = species;
        this.type = type;
        this.assignedPen = assignedPen;
        listOfAllAnimals.add(this);
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public animalType getType() {
        return type;
    }

    public int getAnimalSpace(){
        System.out.println("This returns the space requirements for an animal.");
        return 0;
    }

    public int getAnimalSpace(String type){
        System.out.println("This returns a space requirement for an animal based on the type that is passed in.");
        return 0;
    }

    public Pen getAssignedPen() {
        return assignedPen;
    }

    public static ArrayList<Animal> getListOfAllAnimals() {
        return listOfAllAnimals;
    }

    public void setAssignedPen(Pen pen) {
        this.assignedPen = pen;
    }

    public void assignToPen(Pen pen) {
        pen.assignAnimalToPen(this);
    }
}
