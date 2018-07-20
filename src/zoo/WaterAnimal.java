package zoo;

import java.util.ArrayList;

public class WaterAnimal extends Animal {
    private int waterSpace;
    private static ArrayList<Animal> allWaterAnimals = new ArrayList<>();

    public WaterAnimal(String name, String species, int assignedPenId, int waterSpace) {
        super(name, species, animalType.WATER, assignedPenId);
        this.waterSpace = waterSpace;
        allWaterAnimals.add(this);
        allAnimalsInZooList.add(this);
        animalId = allAnimalsInZooList.indexOf(this);
        setAssignedPen(assignedPenId);
        writeAnimalsToJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/animalData.json", allWaterAnimals);
    }

    @Override
    public int getAnimalSpace() {
        return waterSpace;
    }

    @Override
    public int getAnimalSpace(String type) {
        return getAnimalSpace();
    }
}
