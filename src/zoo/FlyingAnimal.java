package zoo;

import java.util.ArrayList;

public class FlyingAnimal extends Animal {
    private int airSpace;
    private static ArrayList<Animal> allFlyingAnimals = new ArrayList<>();

    public FlyingAnimal(String name, String species, int assignedPenId, int airSpace) {
        super(name, species, animalType.FLYING, assignedPenId);
        this.airSpace = airSpace;
        allFlyingAnimals.add(this);
        allAnimalsInZooList.add(this);
        animalId = allAnimalsInZooList.indexOf(this);
        setAssignedPen(assignedPenId);
        writeAnimalsToJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/animalData.json", allFlyingAnimals);
    }

    @Override
    public int getAnimalSpace() {
        return airSpace;
    }

    public int getAnimalSpace(String type) {
        return getAnimalSpace();
    }
}
