package zoo;

import java.util.ArrayList;

public abstract class Pen {
    private String name;
    private int length, width, temp;
    private PenType type;
    private ArrayList<ZooKeeper> zooKeepers;
    private ArrayList<Animal> animalsInPen;
    private static ArrayList<Pen> listOfAllPens = new ArrayList<>();

    public enum PenType {DRY, AQUARIUM, PARTDRYWATER, AVIARY, PETTING}

    public Pen(String name, int length, int width, int temp, PenType type, ArrayList<ZooKeeper> zooKeepers, ArrayList<Animal> animalsInPen) {
        this.name = name;
        this.length = length;
        this.width = width;
        this.temp = temp;
        this.type = type;
        this.zooKeepers = zooKeepers;
        this.animalsInPen = animalsInPen;
        listOfAllPens.add(this);
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getTemp() {
        return temp;
    }

    public PenType getType() {
        return type;
    }

    public int getCapacity() {
        System.out.println("This returns the capacity of the pen.");
        return 0;
    }

    public int getCapacity(String type) {
        System.out.println("This returns the land/water capacity of the pen depending on the type that is passed in.");
        return 0;
    }

    public static ArrayList<Pen> getListOfAllPens() {
        return listOfAllPens;
    }

    public ArrayList<ZooKeeper> getZooKeepers() {
        return zooKeepers;
    }

    public void removeZooKeeper(ZooKeeper keeper) {
        if (zooKeepers.contains(keeper)) {
            zooKeepers.remove(zooKeepers.indexOf(keeper));
            System.out.println(keeper.getName() + " is no longer a keeper of this pen.");
        } else
            System.out.println(keeper.getName() + " is not a keeper of this pen. Cannot remove.");
    }

    public void assignZooKeeper(ZooKeeper keeper) {
        if (!zooKeepers.contains(keeper) && keeper.isTrainedFor(getType()) && ZooKeeper.getListOfAllZooKeepers().contains(keeper)) {
            zooKeepers.add(keeper); //update pen's list of zookeeper's that are looking after it
            keeper.assignToPen(this); //update zookeeper's list of pens that they are looking after
            System.out.println(keeper.getName() + " has been assigned to look after this pen.");
        } else
            assignKeeperErrorMessage(keeper);
    }

    public void assignKeeperErrorMessage(ZooKeeper keeper) {
        if (zooKeepers.contains(keeper)) {
            System.out.println(keeper.getName() + " is already a keeper of this pen.");
        }
        if (!keeper.isTrainedFor(getType())) {
            System.out.println(keeper.getName() + " is not trained to look after " + getType() + " pens.");
        }
        if (!ZooKeeper.getListOfAllZooKeepers().contains(keeper)) {
            System.out.println("This zookeeper does not exist.");
        }
    }

    public ArrayList<Animal> getAnimalsInPen() {
        return animalsInPen;
    }

    public void assignAnimalToPen(Animal animal) {
        //TODO check that the animal is compatible with other animals in that pen
        if (isPenSuitable(getType(), animal.getType()) && canAccommodateAnimal(animal)) {
            animalsInPen.add(animal); //update the pen's list of animals
            animal.setAssignedPen(this); //update the animal's pen attribute
            System.out.println(animal.getName() + " has been added to this pen.");
        } else
            System.out.println("This pen is not suitable for" + animal.getType() + " animals.");
    }

    public void removeAnimalFromPen(Animal animal) {
        if (animalsInPen.contains(animal)) {
            animalsInPen.remove(animalsInPen.indexOf(animal));
            animal.setAssignedPen(null);
            System.out.println(animal.getName() + " has been removed from this pen. This animal must be assigned to another suitable pen.");

        } else {
            System.out.println(animal.getName() + " is not in this pen. Cannot remove.");
        }
    }

    public boolean isPenSuitable(PenType penType, Animal.animalType animalType) {
        if (penType == PenType.DRY && (animalType == Animal.animalType.LAND || animalType == Animal.animalType.PETTABLE)) {
            return true;
        } else if (penType == PenType.AQUARIUM && animalType == Animal.animalType.WATER) {
            return true;
        } else if (penType == PenType.PARTDRYWATER && animalType == Animal.animalType.AMPHIBIOUS) {
            return true;
        } else if (penType == PenType.AVIARY && animalType == Animal.animalType.FLYING) {
            return true;
        } else if (penType == PenType.PETTING && animalType == Animal.animalType.PETTABLE) {
            return true;
        } else
            return false;
    }

    //TODO maybe each of the pen sublcasses need their own version of this method and override it??
    public int spaceOccupiedByAnimals() {
        int occupiedSpace = 0;
        for (Animal animal : animalsInPen) {
            occupiedSpace += animal.getAnimalSpace();
        }
        return occupiedSpace;
    }

    public int spaceOccupiedByAnimals(String type) {
        int occupiedSpace = 0;
        for (Animal animal : animalsInPen) {
            occupiedSpace += animal.getAnimalSpace(type);
        }
        return occupiedSpace;
    }

    public int getRemainingSpace() {
        return getCapacity() - spaceOccupiedByAnimals();
    }

    public int getRemainingSpace(String type) {
        return getCapacity(type) - spaceOccupiedByAnimals(type);
    }

    public boolean canAccommodateAnimal(Animal animal) {
        if (animal.type.equals(Animal.animalType.AMPHIBIOUS)) {
            if (getRemainingSpace("land") - animal.getAnimalSpace("land") >= 0 && getRemainingSpace("water") - animal.getAnimalSpace("water") >= 0) {
                return true;
            } else
                return false;
        } else if (getRemainingSpace() - animal.getAnimalSpace() >= 0) {
            return true;
        } else
            return false;
    }
}
