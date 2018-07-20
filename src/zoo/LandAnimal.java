package zoo;

import java.util.ArrayList;

public class LandAnimal extends Animal {
    private int landSpace;
    private static ArrayList<Animal> allLandAnimals = new ArrayList<>();

    public LandAnimal(String name, String species, int assignedPenId, int landSpace) {
        super(name, species, animalType.LAND, assignedPenId);
        this.landSpace = landSpace;
        allLandAnimals.add(this);
        allAnimalsInZooList.add(this);
        animalId = allAnimalsInZooList.indexOf(this);
        setAssignedPen(assignedPenId);
        writeAnimalsToJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/animalData.json", allLandAnimals);
    }

    @Override
    public int getAnimalSpace() {
        return landSpace;
    }

    @Override
    public int getAnimalSpace(String type) {
        return getAnimalSpace();
    }
}
