package zoo;

public class PettingAnimal extends Animal {
    private int landSpace;

    public PettingAnimal(String name, String species, Pen assignedPen, int landSpace) {
        super(name, species, animalType.PETTABLE, assignedPen);
        this.landSpace = landSpace;
    }

    @Override
    public int getAnimalSpace() {
        return landSpace;
    }
}
