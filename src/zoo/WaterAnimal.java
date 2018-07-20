package zoo;

public class WaterAnimal extends Animal {
    private int waterSpace;

    public WaterAnimal(String name, String species, int assignedPenId, int waterSpace) {
        super(name, species, animalType.WATER, assignedPenId);
        this.waterSpace = waterSpace;
        allAnimalsInZooList.add(this);
        animalId = allAnimalsInZooList.indexOf(this);
        setAssignedPen(assignedPenId);
        writeAnimalsToJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/animalData.json");
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
