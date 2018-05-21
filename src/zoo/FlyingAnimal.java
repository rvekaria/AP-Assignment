package zoo;

public class FlyingAnimal extends Animal {
    private int airSpace;

    public FlyingAnimal(String name, String species, Pen assignedPen, int airSpace) {
        super(name, species, animalType.FLYING, assignedPen);
        this.airSpace = airSpace;
    }

    public int getAirSpace() {
        return airSpace;
    }
}
