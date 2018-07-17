package zoo;

public class WaterAnimal extends Animal {
    private int waterSpace;

    public WaterAnimal(String name, String species, Pen assignedPen, int waterSpace) {
        super(name, species, animalType.WATER, assignedPen);
        this.waterSpace = waterSpace;
        assignedPen.assignAnimalToPen(this);
        allAnimalsInZooList.add(this);
    }

    @Override
    public int getAnimalSpace() {
        return waterSpace;
    }

    @Override
    public int getAnimalSpace(String type) {
        return getAnimalSpace();
    }
}
