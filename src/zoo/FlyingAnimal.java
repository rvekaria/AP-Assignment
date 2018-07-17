package zoo;

public class FlyingAnimal extends Animal {
    private int airSpace;

    public FlyingAnimal(String name, String species, Pen assignedPen, int airSpace) {
        super(name, species, animalType.FLYING, assignedPen);
        this.airSpace = airSpace;
        assignedPen.assignAnimalToPen(this);
        allAnimalsInZooList.add(this);
    }

    @Override
    public int getAnimalSpace() {
        return airSpace;
    }

    public int getAnimalSpace(String type) {
        return getAnimalSpace();
    }
}
