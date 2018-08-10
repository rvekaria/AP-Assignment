package zoo;

public class LandAnimal extends Animal {
    private double landSpace;


    public LandAnimal(String name, String species, int assignedPenId, double landSpace) {
        super(name, species, animalType.LAND, assignedPenId);
        this.landSpace = landSpace;
        getAllLandAnimals().add(this);
        getAllAnimalsInZooList().add(this);
        animalId = getAllAnimalsInZooList().indexOf(this);
        this.hasAssignedPen = setAssignedPen(assignedPenId);
        writeLandAnimalsToFile();
    }

    @Override
    public double getAnimalSpace() {
        return landSpace;
    }

    @Override
    public double getAnimalSpace(String type) {
        return getAnimalSpace();
    }

    @Override
    public String displayInfo() {
        return String.format("type: %s, species: %s, name: %s, assigned pen: %s, land space: %s", getType(), getSpecies(), getName(), getAssignedPenId() != -1 ? getAssignedPenId() : "NO PEN ASSIGNED!", getAnimalSpace());

    }
}
