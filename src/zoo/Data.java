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

public class Data {
    //TODO - change file paths to relative paths otherwise they may not work on other computers
    private static final String KEEPER_DATA = "/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/zooKeeperData/keeperData.json";

    private static final String ALL_PENS_FILE_PATH = "/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/penData/allPensData.json";
    private static final String DRY_PEN_DATA = "/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/penData/dryPensData.json";
    private static final String PETTING_PEN_DATA = "/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/penData/pettingPensData.json";
    private static final String PART_DRY_WATER_PEN_DATA = "/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/penData/partDryWaterPensData.json";
    private static final String AQUARIUM_DATA = "/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/penData/aquariumsData.json";
    private static final String AVIARY_DATA = "/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/penData/aviariesData.json";

    private static final String LAND_ANIMAL_DATA = "/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/animalData/landAnimalData.json";
    private static final String PETTING_ANIMAL_DATA = "/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/animalData/pettingAnimalData.json";
    private static final String AMPHIBIANS_DATA = "/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/animalData/amphibiansData.json";
    private static final String WATER_ANIMAL_DATA = "/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/animalData/waterAnimalData.json";
    private static final String FLYING_ANIMAL_DATA = "/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/animalData/flyingAnimalData.json";
    private static final String ALL_ANIMAL_DATA = "/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/animalData/animalData.json";


    private static void writeAllAnimalsToJsonFile() {
        File allAnimalsJsonFile = new File(ALL_ANIMAL_DATA);
        Gson jsonConverter = new Gson();

        try {
            PrintWriter writer = new PrintWriter(allAnimalsJsonFile);
            writer.print(jsonConverter.toJson(Animal.getAllAnimalsInZooList()));
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void writeLandAnimalsToFile() {
        writeAnimalsToJsonFile(LAND_ANIMAL_DATA, Animal.getAllLandAnimals());
    }

    public static void writePettingAnimalsToFile() {
        writeAnimalsToJsonFile(PETTING_ANIMAL_DATA, Animal.getAllPettingAnimals());
    }

    public static void writeAmphibiousAnimalsToFile() {
        writeAnimalsToJsonFile(AMPHIBIANS_DATA, Animal.getAllAmphibiousAnimals());
    }

    public static void writeWaterAnimalsToFile() {
        writeAnimalsToJsonFile(WATER_ANIMAL_DATA, Animal.getAllWaterAnimals());
    }

    public static void writeFlyingAnimalsToFile() {
        writeAnimalsToJsonFile(FLYING_ANIMAL_DATA, Animal.getAllFlyingAnimals());
    }

    private static <T extends Animal> void writeAnimalsToJsonFile(String filePath, ArrayList<T> animalArrayList) {
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
        File animalsJsonFile = new File(filePath);

        ArrayList<T> animalsLoadedFromFile = new ArrayList<>();
        try {
            String animalListJsonString = new String(Files.readAllBytes(animalsJsonFile.toPath()));
            animalsLoadedFromFile = loadArrayListFromJsonForAnimalType(classType, animalListJsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (animalsLoadedFromFile != null) {
            Animal.getAllAnimalsInZooList().addAll(animalsLoadedFromFile);
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

    static void writeAllPensListToJsonFile() {
        File allPensJsonFile = new File(ALL_PENS_FILE_PATH);
        Gson jsonConverter = new Gson();
        try {
            PrintWriter writer = new PrintWriter(allPensJsonFile);
            writer.print(jsonConverter.toJson(Pen.getListOfAllPens()));
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void writeDryPensToFile() {
        writePensToJsonFile(DRY_PEN_DATA, Pen.getListOfAllDryPens());
    }

    public static void writePettingPensToFile() {
        writePensToJsonFile(PETTING_PEN_DATA, Pen.getListOfAllPettingPens());
    }

    public static void writePartDryWaterToFile() {
        writePensToJsonFile(PART_DRY_WATER_PEN_DATA, Pen.getListOfAllDryWaterPens());
    }

    public static void writeAquariumsToFile() {
        writePensToJsonFile(AQUARIUM_DATA, Pen.getListOfAllAquariums());
    }

    public static void writeAviariesToFile() {
        writePensToJsonFile(AVIARY_DATA, Pen.getListOfAllAviaries());
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
        File pensJsonFile = new File(filePath);

        ArrayList<T> pensLoadedFromFile = new ArrayList<>();
        try {
            String pensListJsonString = new String(Files.readAllBytes(pensJsonFile.toPath()));
            pensLoadedFromFile = loadArrayListFromJsonForPenType(classType, pensListJsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (pensLoadedFromFile != null) {
            Pen.getListOfAllPens().addAll(pensLoadedFromFile);
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

    public static void writeKeepersToJsonFile(String filePath) {
        File keepersJsonFile = new File(filePath);
        Gson jsonConverter = new Gson();

        try {
            PrintWriter writer = new PrintWriter(keepersJsonFile);
            writer.print(jsonConverter.toJson(ZooKeeper.getListOfAllZooKeepers()));
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<ZooKeeper> instantiateKeepersFromJsonFile(String filePath) {
        File keepersJsonFile = new File(filePath);
        Gson jsonConverter = new Gson();
        ArrayList<ZooKeeper> keepersFromFile = new ArrayList<>();
        try {
            String keepersListJsonString = new String(Files.readAllBytes(keepersJsonFile.toPath()));
            keepersFromFile = jsonConverter.fromJson(keepersListJsonString, new TypeToken<List<ZooKeeper>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return keepersFromFile;
    }
}
