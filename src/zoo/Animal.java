package zoo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public abstract class Animal {
    private String name;
    private String species;
    protected animalType type;
    private Pen assignedPen;
    private static ArrayList<Animal> allAnimalsInZooList = new ArrayList<>();

    public enum animalType {LAND, WATER, AMPHIBIOUS, FLYING, PETTABLE}

    public Animal(String name, String species, animalType type, Pen assignedPen) {
        this.name = name;
        this.species = species;
        this.type = type;
        this.assignedPen = assignedPen;
        assignedPen.assignAnimalToPen(this);
        allAnimalsInZooList.add(this);
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

    public abstract int getAnimalSpace();

    public abstract int getAnimalSpace(String type);

    public Pen getAssignedPen() {
        return assignedPen;
    }

    public static ArrayList<Animal> getAllAnimalsInZooList() {
        return allAnimalsInZooList;
    }

    public void setAssignedPen(Pen pen) {
        String pathname = "/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/animalData.csv";
        File file = new File(pathname);
        setAssignedPen(pen, file);
    }

    public void setAssignedPen(Pen pen, File file) {
        assignedPen.removeAnimalFromPen(this);
        pen.assignAnimalToPen(this);
        this.assignedPen = pen;
        writeAnimalsToFile(file);
    }

    public static void writeAnimalsToFile(File animalData) {
        try {
            PrintWriter printWriter = new PrintWriter(animalData);
            printWriter.println("NAME,SPECIES,TYPE,ASSIGNED_PEN,LAND_REQUIREMENT,WATER_REQUIREMENT,AIR_REQUIREMENT");
            for (Animal animal : allAnimalsInZooList) {
                String name, species, type, assignedPen;
                int landRequirement, waterRequirement, airRequirement;

                name = animal.getName();
                species = animal.getSpecies();
                type = animal.getType().toString();
                assignedPen = animal.getAssignedPen().getName();

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
                        name + "," + species + "," + type + "," + assignedPen
                                + "," + landRequirement + "," + waterRequirement + "," + airRequirement);
            }

            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
