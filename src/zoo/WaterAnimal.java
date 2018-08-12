package zoo;

public class WaterAnimal extends Animal {
    private double waterSpace;

    public WaterAnimal(String name, String species, int assignedPenId, double waterSpace) {
        super(name, species, animalType.WATER, assignedPenId);
        this.waterSpace = waterSpace;
        getAllWaterAnimals().add(this);
        getAllAnimalsInZooList().add(this);
        animalId = getAllAnimalsInZooList().indexOf(this);
        this.hasAssignedPen = setAssignedPen(assignedPenId);
    }

    @Override
    public double getAnimalSpace() {
        return waterSpace;
    }

    @Override
    public double getAnimalSpace(String type) {
        return getAnimalSpace();
    }

    @Override
    public String displayInfo() {
        return String.format("type: %s, species: %s, name: %s, assigned pen: %s, water space: %s", getType(), getSpecies(), getName(), getAssignedPenId() != -1 ? getAssignedPenId() : "NO PEN ASSIGNED!", getAnimalSpace());

    }
}
