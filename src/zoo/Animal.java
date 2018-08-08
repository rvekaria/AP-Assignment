package zoo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public abstract class Animal {
    private String name;
    private String species;
    protected animalType type;
    private int assignedPenId;
    boolean hasAssignedPen;
    protected int animalId;
    static ArrayList<Animal> allAnimalsInZooList = new ArrayList<>();
    static ArrayList<LandAnimal> allLandAnimals = new ArrayList<>();
    static ArrayList<PettingAnimal> allPettingAnimals = new ArrayList<>();
    static ArrayList<AmphibiousAnimal> allAmphibiousAnimals = new ArrayList<>();
    static ArrayList<WaterAnimal> allWaterAnimals = new ArrayList<>();
    static ArrayList<FlyingAnimal> allFlyingAnimals = new ArrayList<>();

    public enum animalType {LAND, WATER, AMPHIBIOUS, FLYING, PETTABLE}

    public Animal(String name, String species, animalType type, int assignedPenId) {
        this.name = name;
        this.species = species;
        this.type = type;
        this.assignedPenId = assignedPenId;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public animalType getType() {
        return type;
    }

    public abstract double getAnimalSpace();

    public abstract double getAnimalSpace(String type);

    public int getAssignedPenId() {
        return assignedPenId;
    }

    public int getAnimalId() {
        return animalId;
    }

    public Pen getAssignedPen() {
        return Pen.getListOfAllPens().get(assignedPenId);
    }

    public static ArrayList<Animal> getAllAnimalsInZooList() {
        return allAnimalsInZooList;
    }

    public abstract String displayInfo();

    /*public void setAssignedPen(Pen pen) {
        String pathname = "/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/animalData.json";
        //File file = new File(pathname);
        setAssignedPen(pen, pathname);
    }*/

    public boolean setAssignedPen(int penId) {
        if (!Pen.getPenWithPenId(penId).getAnimalIDsInPen().contains(animalId)) {
            System.out.println(name + " will be removed from its current pen: " + getAssignedPen().getName());
            getAssignedPen().removeAnimalFromPen(this); //remove this animal from its current Pen
            boolean assignSuccessful = Pen.getPenWithPenId(penId).assignAnimalToPen(this); //update the assigned pen's list of animals that are in it
            this.assignedPenId = assignSuccessful ? penId : -1;//set the animal's assigned pen. -1 if it has no assigned pen
            if (this.assignedPenId == -1) {
                System.out.println(name + " was removed from its current pen but could not be assigned to the new pen.");
                System.out.println(name + " has no assigned pen! Please assign an appropriate pen.");
                return false;
            } else {
                return true;
            }
        } else {
            System.out.println(name + " is already in " + Pen.getListOfAllPens().get(penId).getName());
            return true;
        }
    }

    public static ArrayList<Animal> getAnimalsWithoutPens() {
        ArrayList<Animal> animalsWithoutPensList = new ArrayList<>();
        for (Animal animal : allAnimalsInZooList) {
            if (animal.assignedPenId == -1) {
                animalsWithoutPensList.add(animal);
            }
        }
        return animalsWithoutPensList;
    }

    public static Animal getAnimalWithAnimalId(int animalId){
        for (Animal animal : allAnimalsInZooList){
            if (animal.animalId == animalId){
                return animal;
            }
        }
        return null;
    }

    public static void writeAllAnimalsToJsonFile() {
        String allAnimalsFilePath = "/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/animalData/animalData.json";
        File allAnimalsJsonFile = new File(allAnimalsFilePath);
        Gson jsonConverter = new Gson();

        try {
            PrintWriter writer = new PrintWriter(allAnimalsJsonFile);
            writer.print(jsonConverter.toJson(allAnimalsInZooList));
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void writeLandAnimalsToFile() {
        writeAnimalsToJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/animalData/landAnimalData.json", allLandAnimals);
    }

    public static void writePettingAnimalsToFile() {
        writeAnimalsToJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/animalData/pettingAnimalData.json", allPettingAnimals);
    }

    public static void writeAmphibiousAnimalsToFile() {
        writeAnimalsToJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/animalData/amphibiansData.json", allAmphibiousAnimals);
    }

    public static void writeWaterAnimalsToFile() {
        writeAnimalsToJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/animalData/waterAnimalData.json", allWaterAnimals);
    }

    public static void writeFlyingAnimalsToFile() {
        writeAnimalsToJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/animalData/flyingAnimalData.json", allFlyingAnimals);
    }

    public static <T extends Animal> void writeAnimalsToJsonFile(String filePath, ArrayList<T> animalArrayList) {
        File animalsJsonFile = new File(filePath);
        Gson jsonConverter = new Gson();

        try {
            PrintWriter writer = new PrintWriter(animalsJsonFile);
            writer.print(jsonConverter.toJson(animalArrayList));
            writer.close();

            writeAllAnimalsToJsonFile();
        } catch (FileNotFoundException e) {
            System.out.println("File error: " + e.toString());
        }
    }

    public static <T extends Animal> ArrayList<T> instantiateAnimalsFromJsonFile(String filePath, Class<T> classType) {
        //String filePath = "/Users/rupesh.vekaria/AP-Assignment/src/test/animal/resources/testAnimalData.json";
        File animalsJsonFile = new File(filePath);

        ArrayList<T> animalsLoadedFromFile = new ArrayList<>();
        try {
            String animalListJsonString = new String(Files.readAllBytes(animalsJsonFile.toPath()));
            animalsLoadedFromFile = loadArrayListFromJsonForAnimalType(classType, animalListJsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (animalsLoadedFromFile != null) {
            allAnimalsInZooList.addAll(animalsLoadedFromFile);
            return animalsLoadedFromFile;
        } else {
            return new ArrayList<>();
        }
    }

    private static <T> ArrayList<T> loadArrayListFromJsonForAnimalType(Class<T> penClassType, String animalsListJsonString) {
        Gson jsonConverter = new Gson();
        if (penClassType == WaterAnimal.class)
            return jsonConverter.fromJson(animalsListJsonString, new TypeToken<List<WaterAnimal>>() {
            }.getType());
        else if (penClassType == FlyingAnimal.class)
            return jsonConverter.fromJson(animalsListJsonString, new TypeToken<List<FlyingAnimal>>() {
            }.getType());
        else if (penClassType == LandAnimal.class)
            return jsonConverter.fromJson(animalsListJsonString, new TypeToken<List<LandAnimal>>() {
            }.getType());
        else if (penClassType == AmphibiousAnimal.class)
            return jsonConverter.fromJson(animalsListJsonString, new TypeToken<List<AmphibiousAnimal>>() {
            }.getType());
        else if (penClassType == PettingAnimal.class)
            return jsonConverter.fromJson(animalsListJsonString, new TypeToken<List<PettingAnimal>>() {
            }.getType());
        else return null;
    }

}
