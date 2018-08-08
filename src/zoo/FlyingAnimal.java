package zoo;

public class FlyingAnimal extends Animal {
    private double airSpace;

    public FlyingAnimal(String name, String species, int assignedPenId, double airSpace) {
        super(name, species, animalType.FLYING, assignedPenId);
        this.airSpace = airSpace;
        allFlyingAnimals.add(this);
        allAnimalsInZooList.add(this);
        animalId = allAnimalsInZooList.indexOf(this);
        this.hasAssignedPen = setAssignedPen(assignedPenId);
        writeFlyingAnimalsToFile();
    }

    @Override
    public double getAnimalSpace() {
        return airSpace;
    }

    public double getAnimalSpace(String type) {
        return getAnimalSpace();
    }

    @Override
    public String displayInfo() {
        return String.format("type: %s, species: %s, name: %s, assigned pen: %s, air space: %s", getType(), getSpecies(), getName(), getAssignedPenId() != -1 ? getAssignedPenId() : "NO PEN ASSIGNED!", getAnimalSpace());

    }
}
