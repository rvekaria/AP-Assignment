package zoo;

public class PettingAnimal extends Animal {
    private double landSpace;

    public PettingAnimal(String name, String species, int assignedPenId, double landSpace) {
        super(name, species, animalType.PETTABLE, assignedPenId);
        this.landSpace = landSpace;
        getAllPettingAnimals().add(this);
        getAllAnimalsInZooList().add(this);
        animalId = getAllAnimalsInZooList().indexOf(this);
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
