package zoo;

public class FlyingAnimal extends Animal {
    private int airSpace;

    public FlyingAnimal(String name, String species, int assignedPenId, int airSpace) {
        super(name, species, animalType.FLYING, assignedPenId);
        this.airSpace = airSpace;
        allAnimalsInZooList.add(this);
        animalId = allAnimalsInZooList.indexOf(this);
        setAssignedPen(assignedPenId);
        writeAnimalsToJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/animalData.json");
    }

    @Override
    public int getAnimalSpace() {
        return airSpace;
    }

    public int getAnimalSpace(String type) {
        return getAnimalSpace();
    }
}
