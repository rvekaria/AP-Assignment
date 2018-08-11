package zoo;

public class AmphibiousAnimal extends Animal {
    private double landSpace, waterSpace;

    public AmphibiousAnimal(String name, String species, int assignedPenId, double landSpace, double waterSpace) {
        super(name, species, animalType.AMPHIBIOUS, assignedPenId);
        this.landSpace = landSpace;
        this.waterSpace = waterSpace;
        getAllAmphibiousAnimals().add(this);
        getAllAnimalsInZooList().add(this);
        animalId = getAllAnimalsInZooList().indexOf(this);
        this.hasAssignedPen = setAssignedPen(assignedPenId);
    }

    public double getAnimalSpace() {
        return getAnimalSpace("must specify land or water");
    }

    public double getAnimalSpace(String type) {
        if (type.equals("land")) {
            return landSpace;
        } else if (type.equals("water")) {
            return waterSpace;
        } else {
            throw new IllegalArgumentException("land or water must be specified.");
        }
    }

    @Override
    public String displayInfo() {
        return String.format("type: %s, species: %s, name: %s, assigned pen: %s, land space: %s, water space: %s", getType(), getSpecies(), getName(), getAssignedPenId() != -1 ? getAssignedPenId() : "NO PEN ASSIGNED!", getAnimalSpace("land"), getAnimalSpace("water"));

    }
}
