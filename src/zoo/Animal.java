package zoo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
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

    public int getAnimalSpace() {
        System.out.println("This returns the space requirements for an animal.");
        return 0;
    }

    public int getAnimalSpace(String type) {
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

    public void writeAnimalsToFile() {
        File penData = new File("animalData.csv");
        try {
            PrintWriter printWriter = new PrintWriter(new FileOutputStream(penData, true));
            for (Animal animal : listOfAllAnimals) {
                String name, species, type, assignedPen;
                int landRequirement, waterRequirement, airRequirement;

                name = this.name;
                species = this.species;
                type = this.type.toString();
                assignedPen = this.assignedPen.toString();

                if (animal.type.equals(animalType.LAND) || animal.type.equals(animalType.PETTABLE)) {
                    landRequirement = animal.getAnimalSpace();
                    waterRequirement = 0;
                    airRequirement = 0;
                } else if (animal.type.equals(animalType.AMPHIBIOUS)) {
                    landRequirement = animal.getAnimalSpace("land");
                    waterRequirement = animal.getAnimalSpace("water");
                    airRequirement = 0;
                } else if (animal.type.equals(animalType.FLYING)) {
                    landRequirement = 0;
                    waterRequirement = 0;
                    airRequirement = animal.getAnimalSpace();
                } else {
                    landRequirement = 0;
                    waterRequirement = animal.getAnimalSpace();
                    airRequirement = 0;
                }

                printWriter.println(
                        name + ", " + species + ", " + type + ", " + assignedPen
                                + ", " + landRequirement + ", " + waterRequirement + ", " + airRequirement);
            }

            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
