package zoo;

public class AmphibiousAnimal extends Animal {
    private int landSpace, waterSpace;

    public AmphibiousAnimal(String name, String species, Pen assignedPen, int landSpace, int waterSpace) {
        super(name, species, animalType.AMPHIBIOUS, assignedPen);
        this.landSpace = landSpace;
        this.waterSpace = waterSpace;
    }

    public int getAnimalSpace(String type) {
        if (type.equals("land")){
            return landSpace;
        } else if (type.equals("water")){
            return waterSpace;
        } else
            System.out.println("land or water must be specified. (returned -1)");
            return -1;
    }
}
