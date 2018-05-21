package zoo;

public class WaterAnimal extends Animal {
    private int waterSpace;

    public WaterAnimal(String name, String species, Pen assignedPen, int waterSpace) {
        super(name, species, animalType.WATER, assignedPen);
        this.waterSpace = waterSpace;
    }

    public int getWaterSpace() {
        return waterSpace;
    }
}
