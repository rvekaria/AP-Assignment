package zoo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Pen {
    private String name;
    private int length, width, temp;
    private PenType type;
    protected int penId;
    private ArrayList<ZooKeeper> assignedKeepers;
    private ArrayList<Integer> animalIDsInPen;
    private static ArrayList<Pen> listOfAllPens = new ArrayList<>();
    private static ArrayList<Aquarium> listOfAllAquariums = new ArrayList<>();
    private static ArrayList<PettingPen> listOfAllPettingPens = new ArrayList<>();
    private static ArrayList<Aviary> listOfAllAviaries = new ArrayList<>();
    private static ArrayList<PartDryWaterPen> listOfAllDryWaterPens = new ArrayList<>();
    private static ArrayList<DryPen> listOfAllDryPens = new ArrayList<>();

    public static void setListOfAllPens(ArrayList<Pen> listOfAllPens) {
        Pen.listOfAllPens = listOfAllPens;
    }

    public static ArrayList<Aquarium> getListOfAllAquariums() {
        return listOfAllAquariums;
    }

    public static void setListOfAllAquariums(ArrayList<Aquarium> listOfAllAquariums) {
        Pen.listOfAllAquariums = listOfAllAquariums;
    }

    public static ArrayList<PettingPen> getListOfAllPettingPens() {
        return listOfAllPettingPens;
    }

    public static void setListOfAllPettingPens(ArrayList<PettingPen> listOfAllPettingPens) {
        Pen.listOfAllPettingPens = listOfAllPettingPens;
    }

    public static ArrayList<Aviary> getListOfAllAviaries() {
        return listOfAllAviaries;
    }

    public static void setListOfAllAviaries(ArrayList<Aviary> listOfAllAviaries) {
        Pen.listOfAllAviaries = listOfAllAviaries;
    }

    public static ArrayList<PartDryWaterPen> getListOfAllDryWaterPens() {
        return listOfAllDryWaterPens;
    }

    public static void setListOfAllDryWaterPens(ArrayList<PartDryWaterPen> listOfAllDryWaterPens) {
        Pen.listOfAllDryWaterPens = listOfAllDryWaterPens;
    }

    public static ArrayList<DryPen> getListOfAllDryPens() {
        return listOfAllDryPens;
    }

    public static void setListOfAllDryPens(ArrayList<DryPen> listOfAllDryPens) {
        Pen.listOfAllDryPens = listOfAllDryPens;
    }

    public enum PenType {DRY, AQUARIUM, PARTDRYWATER, AVIARY, PETTING}

    public Pen(String name, int length, int width, int temp, PenType type, ArrayList<ZooKeeper> assignedKeepers, ArrayList<Integer> animalIDsInPen) {
        this.name = name;
        this.length = length;
        this.width = width;
        this.temp = temp;
        this.type = type;
        this.assignedKeepers = assignedKeepers;
        this.animalIDsInPen = animalIDsInPen;
    }

    protected void updateKeepersAssignedPens(ArrayList<ZooKeeper> assignedKeepers) {
        for (ZooKeeper keeper : assignedKeepers) {
            keeper.addPenToAssignedPens(penId);
            ZooKeeper.writeKeepersToJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/zooKeeperData/keeperData.json");
        }
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

    public abstract String displayInfo();

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

    public static Pen getPenWithPenId(int penId){
        for (Pen pen : getListOfAllPens()){
            if (pen.penId == penId){
                return pen;
            }
        }
        return null;
    }

    public ArrayList<ZooKeeper> getAssignedKeepers() {
        return assignedKeepers;
    }

    public String getKeeperNames() {
        ArrayList<String> keeperNames = new ArrayList<>();
        for (ZooKeeper keeper : assignedKeepers) {
            keeperNames.add(keeper.getName());
        }
        String[] keeperNamesArray = new String[keeperNames.size()];
        keeperNamesArray = keeperNames.toArray(keeperNamesArray);
        return Arrays.toString(keeperNamesArray);
    }

    public void removeZooKeeper(ZooKeeper keeper) {
        if (assignedKeepers.contains(keeper)) {
            assignedKeepers.remove(assignedKeepers.indexOf(keeper));
            System.out.println(keeper.getName() + " is no longer a keeper of this pen.");
            if (assignedKeepers.isEmpty()) {
                System.out.println("There are no more keepers looking after " + name + ". At least one keeper must be assigned to this pen!");
            }
        } else {
            System.out.println(keeper.getName() + " is not a keeper of this pen. Cannot remove.");
        }
    }

    public void assignZooKeeper(ZooKeeper keeper) {
        if (!assignedKeepers.contains(keeper) && keeper.isTrainedFor(getType()) && ZooKeeper.getListOfAllZooKeepers().contains(keeper)) {
            assignedKeepers.add(keeper); //update pen's list of zookeeper's that are looking after it
            writeAllPensListToJsonFile();
            keeper.addPenToAssignedPens(penId);//update keeper's list of pens they are looking after
            System.out.println(keeper.getName() + " has been assigned to look after " + name + ".");
        } else
            assignKeeperErrorMessage(keeper);
    }

    public void assignKeeperErrorMessage(ZooKeeper keeper) {
        if (assignedKeepers.contains(keeper)) {
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

    private double spaceOccupiedByAnimals() {
        double occupiedSpace = 0;
        for (int animalId : animalIDsInPen) {
            occupiedSpace += Animal.getAnimalWithAnimalId(animalId).getAnimalSpace();
        }
        return occupiedSpace;
    }

    private double spaceOccupiedByAnimals(String type) {
        double occupiedSpace = 0;
        for (int animalId : animalIDsInPen) {
            occupiedSpace += Animal.getAnimalWithAnimalId(animalId).getAnimalSpace(type);
        }
        return occupiedSpace;
    }


    public double getRemainingSpace() {
        return getCapacity() - spaceOccupiedByAnimals();
    }

    public double getRemainingSpace(String type) {
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

    static void writeAllPensListToJsonFile() {
        String allPensFilePath = "/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/penData/allPensData.json";
        File allPensJsonFile = new File(allPensFilePath);
        Gson jsonConverter = new Gson();
        try {
            PrintWriter writer = new PrintWriter(allPensJsonFile);
            writer.print(jsonConverter.toJson(getListOfAllPens()));
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void writeDryPensToFile() {
        writePensToJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/penData/dryPensData.json", getListOfAllDryPens());
    }

    public static void writePettingPensToFile() {
        writePensToJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/penData/pettingPensData.json", getListOfAllPettingPens());
    }

    public static void writePartDryWaterToFile() {
        writePensToJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/penData/partDryWaterPensData.json", getListOfAllDryWaterPens());
    }

    public static void writeAquariumsToFile() {
        writePensToJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/penData/aquariumsData.json", getListOfAllAquariums());
    }

    public static void writeAviariesToFile() {
        writePensToJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/penData/aviariesData.json", getListOfAllAviaries());
    }

    public static <T extends Pen> void writePensToJsonFile(String filePath, ArrayList<T> penArrayList) {
        File pensJsonFile = new File(filePath);
        Gson jsonConverter = new Gson();

        try {
            PrintWriter writer = new PrintWriter(pensJsonFile);
            writer.print(jsonConverter.toJson(penArrayList));
            writer.close();

            writeAllPensListToJsonFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static <T extends Pen> ArrayList<T> instantiatePensFromJsonFile(String filePath, Class<T> classType) {
        //String filePath = "/Users/rupesh.vekaria/AP-Assignment/src/test/pen/resources/testPenData.json";
        File pensJsonFile = new File(filePath);

        ArrayList<T> pensLoadedFromFile = new ArrayList<>();
        try {
            String pensListJsonString = new String(Files.readAllBytes(pensJsonFile.toPath()));
            pensLoadedFromFile = loadArrayListFromJsonForPenType(classType, pensListJsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (pensLoadedFromFile != null) {
            getListOfAllPens().addAll(pensLoadedFromFile);
            return pensLoadedFromFile;
        } else {
            return new ArrayList<>();
        }

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
