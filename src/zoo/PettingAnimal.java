package zoo;

import java.util.ArrayList;

public class PettingAnimal extends Animal {
    private int landSpace;
    private static ArrayList<Animal> allPettingAnimals = new ArrayList<>();

    public PettingAnimal(String name, String species, int assignedPenId, int landSpace) {
        super(name, species, animalType.PETTABLE, assignedPenId);
        this.landSpace = landSpace;
        allPettingAnimals.add(this);
        allAnimalsInZooList.add(this);
        animalId = allAnimalsInZooList.indexOf(this);
        setAssignedPen(assignedPenId);
        writeAnimalsToJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/animalData/pettingAnimalData.json", allPettingAnimals);
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
