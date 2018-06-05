package zoo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

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
        //TODO - create a method which is called in this constructor that appends the information of the newly created Pen to the penData file
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

    public  int getHeight(){
        return 0;
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
        if (isPenSuitable(getType(), animal.getType()) && isSpaceFor(animal)) {
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

    public boolean isSpaceFor(Animal animal) {
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

    //TODO - have a separate method which overwrites the file as opposed to appends to file. The method below will only work once with append set to true because of the for loop. You only want to append new pens not the whole list of pens again.
    //TODO - maybe you can get around this by having a boolean parameter for the append?
    //TODO - but if changes are made to an existing pen as opposed to a new pen being created, you don't want a completely new record, you want to overwrite the old data. Although, there are no setters so this is not possible - check if it is a requirement to allow changes to pens.
    //TODO - with regard to the previous TODO, the only requirement is to be able to add new animals to a pen and assign staff to a pen
    public void writePensToFile() {
        File penData = new File("penData.csv");
        try {
            PrintWriter printWriter = new PrintWriter(new FileOutputStream(penData, true));
            //TODO - consider having a normal PrintWriter, without the FileOutputStream. The whole file will be overwritten with changes/additions each time.
            for (Pen pen : listOfAllPens) {
                String name, type;
                int length, width, height, temp, area, volume, remainingArea, remainingVolume, assignedKeepers;
                name = pen.name;
                type = pen.type.toString();
                length = pen.length;
                width = pen.width;
                height = pen.getHeight();
                temp = pen.temp;


                if (pen.type.equals(PenType.DRY) || pen.type.equals(PenType.PETTING)) {
                    area = pen.getCapacity();
                    volume = 0;
                    remainingArea = pen.getRemainingSpace();
                    remainingVolume = 0;
                } else if (pen.type.equals(PenType.PARTDRYWATER)) {
                    area = pen.getCapacity("land");
                    volume = pen.getCapacity("water");
                    remainingArea = pen.getRemainingSpace("land");
                    remainingVolume = pen.getRemainingSpace("water");
                } else {
                    area = 0;
                    remainingArea = 0;
                    volume = pen.getCapacity();
                    remainingVolume = pen.getRemainingSpace();
                }

                printWriter.println(
                        name + ", " + type + ", " + length + ", " + width + ", " + height + ", " + temp + ", " + area
                                + ", " + volume + ", " + remainingArea + ", " + remainingVolume + ", " + zooKeepers + ", " + animalsInPen);
            }


            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
