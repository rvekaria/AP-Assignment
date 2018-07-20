package zoo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public abstract class Pen {
    private String name;
    private int length, width, temp;
    private PenType type;
    protected int penId;
    private ArrayList<ZooKeeper> zooKeepers;
    private ArrayList<Integer> animalIDsInPen;
    static ArrayList<Pen> listOfAllPens = new ArrayList<>();

    public enum PenType {DRY, AQUARIUM, PARTDRYWATER, AVIARY, PETTING}

    public Pen(String name, int length, int width, int temp, PenType type, ArrayList<ZooKeeper> zooKeepers, ArrayList<Integer> animalIDsInPen) {
        this.name = name;
        this.length = length;
        this.width = width;
        this.temp = temp;
        this.type = type;
        this.zooKeepers = zooKeepers;
        this.animalIDsInPen = animalIDsInPen;
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

    public int getPenId() {
        return penId;
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
            if (zooKeepers.isEmpty()) {
                System.out.println("There are no more keepers looking after " + name + ". At least one keeper must be assigned to this pen!");
            }
        } else {
            System.out.println(keeper.getName() + " is not a keeper of this pen. Cannot remove.");
        }
    }

    public void assignZooKeeper(ZooKeeper keeper) {
        if (!zooKeepers.contains(keeper) && keeper.isTrainedFor(getType()) && ZooKeeper.getListOfAllZooKeepers().contains(keeper)) {
            zooKeepers.add(keeper); //update pen's list of zookeeper's that are looking after it
            //keeper.addToAssignedPens(this); //update zookeeper's list of pens that they are looking after
            if (!keeper.getAssignedPenIds().contains(penId)) {
                keeper.getAssignedPenIds().add(penId);
            }
            System.out.println(keeper.getName() + " has been assigned to look after " + name + ".");
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

    public ArrayList<Integer> getAnimalIDsInPen() {
        return animalIDsInPen;
    }

    public boolean assignAnimalToPen(Animal animal) {
        //TODO check that the animal is compatible with other animals in that pen
        if (isPenSuitable(getType(), animal.getType())) {
            if (isSpaceFor(animal)) {
                animalIDsInPen.add(animal.getAnimalId()); //update the pen's list of animals
                System.out.println(animal.getName() + " the " + animal.getSpecies() + " has been added to " + name + ".");
                return true;
            } else {
                System.out.println("There is no space for " + animal.getName() + " in " + name + ".");
                return false;
            }

        } else
            System.out.println("This pen is not suitable for " + animal.getType() + " animals.");
        return false;
    }

    public void removeAnimalFromPen(Animal animal) {
        if (animalIDsInPen.contains(animal.getAnimalId())) {
            animalIDsInPen.remove(animalIDsInPen.indexOf(animal.getAnimalId()));
            System.out.println(animal.getName() + " has been removed from this pen. This animal must be assigned to another suitable pen.");

        } else {
            System.out.println(animal.getName() + " is not in this pen. Cannot remove.");
        }
    }

    private boolean isPenSuitable(PenType penType, Animal.animalType animalType) {
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
    private int spaceOccupiedByAnimals() {
        int occupiedSpace = 0;
        for (int animalId : animalIDsInPen) {
            occupiedSpace += Animal.getAllAnimalsInZooList().get(animalId).getAnimalSpace();
        }
        return occupiedSpace;
    }

    private int spaceOccupiedByAnimals(String type) {
        int occupiedSpace = 0;
        for (int animalId : animalIDsInPen) {
            occupiedSpace += Animal.getAllAnimalsInZooList().get(animalId).getAnimalSpace(type);
        }
        return occupiedSpace;
    }

    private int getRemainingSpace() {
        return getCapacity() - spaceOccupiedByAnimals();
    }

    private int getRemainingSpace(String type) {
        return getCapacity(type) - spaceOccupiedByAnimals(type);
    }

    private boolean isSpaceFor(Animal animal) {
        if (animal.getType() == Animal.animalType.AMPHIBIOUS) {
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

    public static void writePensToJsonFile(String filePath, ArrayList<Pen> penArrayList) {
        String allPensFilePath = "/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/penData/allPensData.json";
        File allPensJsonFile = new File(allPensFilePath);

        File pensJsonFile = new File(filePath);
        Gson jsonConverter = new Gson();

        try {
            PrintWriter writer = new PrintWriter(pensJsonFile);
            writer.print(jsonConverter.toJson(penArrayList));
            writer.close();

            writer = new PrintWriter(allPensJsonFile);
            writer.print(jsonConverter.toJson(listOfAllPens));
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static <T> ArrayList<T> instantiatePensFromJsonFile(String filePath, Class<T> classType) {
        //String filePath = "/Users/rupesh.vekaria/AP-Assignment/src/test/pen/resources/testPenData.json";
        File pensJsonFile = new File(filePath);
        Gson jsonConverter = new Gson();

        ArrayList<T> pensLoadedFromFile = new ArrayList<>();
        try {
            String pensListJsonString = new String(Files.readAllBytes(pensJsonFile.toPath()));
            pensLoadedFromFile = loadArrayListFromJsonForPenType(classType, pensListJsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pensLoadedFromFile;
    }

    private static <T> ArrayList<T> loadArrayListFromJsonForPenType(Class<T> penClassType, String pensListJsonString) {
        Gson jsonConverter = new Gson();
        if (penClassType == Aquarium.class)
            return jsonConverter.fromJson(pensListJsonString, new TypeToken<List<Aquarium>>() {
            }.getType());
        else if (penClassType == Aviary.class)
            return jsonConverter.fromJson(pensListJsonString, new TypeToken<List<Aviary>>() {
            }.getType());
        else if (penClassType == DryPen.class)
            return jsonConverter.fromJson(pensListJsonString, new TypeToken<List<DryPen>>() {
            }.getType());
        else if (penClassType == PartDryWaterPen.class)
            return jsonConverter.fromJson(pensListJsonString, new TypeToken<List<PartDryWaterPen>>() {
            }.getType());
        else if (penClassType == PettingPen.class)
            return jsonConverter.fromJson(pensListJsonString, new TypeToken<List<PettingPen>>() {
            }.getType());
        else return null;
    }

}
