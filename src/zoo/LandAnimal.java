package zoo;

public class LandAnimal extends Animal {
    private int landSpace;

    public LandAnimal(String name, String species, int assignedPenId, int landSpace) {
        super(name, species, animalType.LAND, assignedPenId);
        this.landSpace = landSpace;
        allAnimalsInZooList.add(this);
        animalId = allAnimalsInZooList.indexOf(this);
        setAssignedPen(assignedPenId);
        writeAnimalsToJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/animalData.json");
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
