public class AmphibiousAnimal extends Animal {
    private int landSpace, waterSpace;

    public AmphibiousAnimal(String name, String species, animalType type, Pen assignedPen, int landSpace, int waterSpace) {
        super(name, species, type, assignedPen);
        this.landSpace = landSpace;
        this.waterSpace = waterSpace;
    }

    public int getLandSpace() {
        return landSpace;
    }

    public int getWaterSpace() {
        return waterSpace;
    }
}
