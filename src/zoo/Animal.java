package zoo;

import java.util.ArrayList;

public abstract class Animal {
    private String name;
    private String species;
    protected animalType type;
    private int assignedPenId;
    boolean hasAssignedPen;
    protected int animalId;
    private static ArrayList<Animal> allAnimalsInZooList = new ArrayList<>();
    private static ArrayList<LandAnimal> allLandAnimals = new ArrayList<>();
    private static ArrayList<PettingAnimal> allPettingAnimals = new ArrayList<>();
    private static ArrayList<AmphibiousAnimal> allAmphibiousAnimals = new ArrayList<>();
    private static ArrayList<WaterAnimal> allWaterAnimals = new ArrayList<>();
    private static ArrayList<FlyingAnimal> allFlyingAnimals = new ArrayList<>();

    public static ArrayList<LandAnimal> getAllLandAnimals() {
        return allLandAnimals;
    }

    public static void setAllLandAnimals(ArrayList<LandAnimal> allLandAnimals) {
        Animal.allLandAnimals = allLandAnimals;
    }

    public static ArrayList<PettingAnimal> getAllPettingAnimals() {
        return allPettingAnimals;
    }

    public static void setAllPettingAnimals(ArrayList<PettingAnimal> allPettingAnimals) {
        Animal.allPettingAnimals = allPettingAnimals;
    }

    public static ArrayList<AmphibiousAnimal> getAllAmphibiousAnimals() {
        return allAmphibiousAnimals;
    }

    public static void setAllAmphibiousAnimals(ArrayList<AmphibiousAnimal> allAmphibiousAnimals) {
        Animal.allAmphibiousAnimals = allAmphibiousAnimals;
    }

    public static ArrayList<WaterAnimal> getAllWaterAnimals() {
        return allWaterAnimals;
    }

    public static void setAllWaterAnimals(ArrayList<WaterAnimal> allWaterAnimals) {
        Animal.allWaterAnimals = allWaterAnimals;
    }

    public static ArrayList<FlyingAnimal> getAllFlyingAnimals() {
        return allFlyingAnimals;
    }

    public static void setAllFlyingAnimals(ArrayList<FlyingAnimal> allFlyingAnimals) {
        Animal.allFlyingAnimals = allFlyingAnimals;
    }

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

    public boolean setAssignedPen(int penId) {
        if (!Pen.getPenWithPenId(penId).getAnimalIDsInPen().contains(animalId)) {
            if (assignedPenId != -1) {
                System.out.println(name + " will be removed from its current pen: " + getAssignedPen().getName());
                getAssignedPen().removeAnimalFromPen(this); //remove this animal from its current Pen
            }
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
        for (Animal animal : getAllAnimalsInZooList()) {
            if (animal.assignedPenId == -1) {
                animalsWithoutPensList.add(animal);
            }
        }
        return animalsWithoutPensList;
    }

    public static Animal getAnimalWithAnimalId(int animalId) {
        for (Animal animal : getAllAnimalsInZooList()) {
            if (animal.animalId == animalId) {
                return animal;
            }
        }
        return null;
    }

}
