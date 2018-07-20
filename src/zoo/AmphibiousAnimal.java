package zoo;

public class AmphibiousAnimal extends Animal {
    private int landSpace, waterSpace;

    public AmphibiousAnimal(String name, String species, int assignedPenId, int landSpace, int waterSpace) {
        super(name, species, animalType.AMPHIBIOUS, assignedPenId);
        this.landSpace = landSpace;
        this.waterSpace = waterSpace;
        allAnimalsInZooList.add(this);
        animalId = allAnimalsInZooList.indexOf(this);
        setAssignedPen(assignedPenId);
        writeAnimalsToJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/animalData.json");
    }

    public int getAnimalSpace() {
        return getAnimalSpace("must specify land or water");
    }

    public int getAnimalSpace(String type) {
        if (type.equals("land")) {
            return landSpace;
        } else if (type.equals("water")) {
            return waterSpace;
        } else {
            throw new IllegalArgumentException("land or water must be specified.");
        }
    }
}
