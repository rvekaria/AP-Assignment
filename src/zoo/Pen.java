package zoo;

import java.util.ArrayList;

public abstract class Pen {
    private String name;
    private int length, width, temp;
    private penType type;
    private ArrayList<ZooKeeper> zooKeepers;
    private ArrayList<Animal> animalsInPen;
    private static ArrayList<Pen> listOfAllPens;

    public enum penType {DRY, AQUARIUM, PARTDRYWATER, AVIARY, PETTING}

    public Pen(String name, int length, int width, int temp, penType type, ArrayList<ZooKeeper> zooKeepers, ArrayList<Animal> animalsInPen) {
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

    public penType getType() {
        return type;
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
        //TODO check that the capacity of the pen is not exceeded by adding the animal
        //TODO check that the animal is compatible with other animals in that pen
        if (isPenSuitable(getType(), animal.getType())) {
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

    public boolean isPenSuitable(penType penType, Animal.animalType animalType) {
        if (penType == penType.DRY && (animalType == Animal.animalType.LAND || animalType == Animal.animalType.PETTABLE)) {
            return true;
        } else if (penType == penType.AQUARIUM && animalType == Animal.animalType.WATER) {
            return true;
        } else if (penType == penType.PARTDRYWATER && animalType == Animal.animalType.AMPHIBIOUS) {
            return true;
        } else if (penType == penType.AVIARY && animalType == Animal.animalType.FLYING) {
            return true;
        } else if (penType == penType.PETTING && animalType == Animal.animalType.PETTABLE) {
            return true;
        } else
            return false;
    }
}
