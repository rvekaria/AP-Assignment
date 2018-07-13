package zoo;

public class LandAnimal extends Animal {
    private int landSpace;

    public LandAnimal(String name, String species, Pen assignedPen, int landSpace) {
        super(name, species, animalType.LAND, assignedPen);
        this.landSpace = landSpace;
        assignedPen.assignAnimalToPen(this);
        allAnimalsInZooList.add(this);
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
