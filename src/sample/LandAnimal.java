package sample;

public class LandAnimal extends Animal {
    private int landSpace;

    public LandAnimal(String name, String species, Pen assignedPen, int landSpace) {
        super(name, species, animalType.LAND, assignedPen);
        this.landSpace = landSpace;
    }

    public int getLandSpace() {
        return landSpace;
    }

}
