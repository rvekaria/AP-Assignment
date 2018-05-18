public class FlyingAnimal extends Animal {
    private int airSpace;

    public FlyingAnimal(String name, String species, animalType type, Pen assignedPen, int airSpace) {
        super(name, species, type, assignedPen);
        this.airSpace = airSpace;
    }

    public int getAirSpace() {
        return airSpace;
    }
}
