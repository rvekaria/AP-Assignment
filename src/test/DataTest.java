package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import zoo.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class DataTest {

    ArrayList<ZooKeeper> keepersList;
    ArrayList<Integer> animalsInDryWaterPenIdList;
    Pen dryWaterPen;
    Animal penguin;

    ArrayList<Integer> animalsInDryPenIdList;
    Pen dryPen;
    Animal dog;

    ArrayList<Integer> animalsInPetPenIdList;
    Pen pettingPen;
    Animal goat;

    ArrayList<Integer> animalsInAviaryIdList;
    Pen aviary;
    Animal owl;

    ArrayList<Integer> animalsInAquariumIdList;
    Pen aquarium;
    Animal shark;


    @Before
    public void setUp() {
        keepersList = new ArrayList<>();
        animalsInDryWaterPenIdList = new ArrayList<>();
        dryWaterPen = new PartDryWaterPen("dryWaterPen", 20, 25, 10, 400, 1000, 18, keepersList, animalsInDryWaterPenIdList);
        penguin = new AmphibiousAnimal("Penny", "Penguin", dryWaterPen.getPenId(), 2, 4);

        animalsInDryPenIdList = new ArrayList<>();
        dryPen = new DryPen("dryPen", 40, 50, 10, keepersList, animalsInDryPenIdList);
        dog = new LandAnimal("Douglas", "Dog", dryPen.getPenId(), 13);

        animalsInPetPenIdList = new ArrayList<>();
        pettingPen = new PettingPen("pettingPen", 20, 20, 15, keepersList, animalsInPetPenIdList);
        goat = new PettingAnimal("Gary", "Goat", pettingPen.getPenId(), 11);

        animalsInAviaryIdList = new ArrayList<>();
        aviary = new Aviary("aviaryPen", 13, 15, 15, 17, keepersList, animalsInAviaryIdList);
        owl = new FlyingAnimal("Owen", "Owl", aviary.getPenId(), 1000);

        animalsInAquariumIdList = new ArrayList<>();
        aquarium = new Aquarium("aquaPen", 100, 100, 10, 20, keepersList, animalsInAquariumIdList);
        shark = new WaterAnimal("Shaniqua", "Shark", aquarium.getPenId(), 4000);

    }

    @After
    public void tearDown() {
        Animal.getAllAnimalsInZooList().clear();
        Pen.getListOfAllPens().clear();
    }

    @Test
    public void testWriteAnimalToFile() {
        //setup
        String pathname = "/Users/rupesh.vekaria/AP-Assignment/src/test/animal/resources/testAnimalData.json";
        File animalData = new File(pathname);
        String expectedJson = "[{\"landSpace\":2.0,\"waterSpace\":4.0,\"name\":\"Penny\",\"species\":\"Penguin\",\"type\":\"AMPHIBIOUS\",\"assignedPenId\":0,\"hasAssignedPen\":false,\"animalId\":0},{\"landSpace\":13.0,\"name\":\"Douglas\",\"species\":\"Dog\",\"type\":\"LAND\",\"assignedPenId\":1,\"hasAssignedPen\":false,\"animalId\":1},{\"landSpace\":11.0,\"name\":\"Gary\",\"species\":\"Goat\",\"type\":\"PETTABLE\",\"assignedPenId\":2,\"hasAssignedPen\":false,\"animalId\":2},{\"airSpace\":1000.0,\"name\":\"Owen\",\"species\":\"Owl\",\"type\":\"FLYING\",\"assignedPenId\":3,\"hasAssignedPen\":false,\"animalId\":3},{\"waterSpace\":4000.0,\"name\":\"Shaniqua\",\"species\":\"Shark\",\"type\":\"WATER\",\"assignedPenId\":4,\"hasAssignedPen\":false,\"animalId\":4}]";

        //act
        Data.writeAnimalsToJsonFile(pathname, Animal.getAllAnimalsInZooList());

        //assert
        try {
            Scanner scanner = new Scanner(animalData);
            String readFromJsonFile = scanner.nextLine();

            assertEquals(expectedJson, readFromJsonFile);

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWriteAnimalToFileAfterChangingPen() {
        //setup
        ArrayList<Integer> animalsInPenIdList2 = new ArrayList<>();
        Pen newPen = new PartDryWaterPen("newPen", 20, 25, 10, 400, 1000, 18, keepersList, animalsInPenIdList2);
        String pathname = "/Users/rupesh.vekaria/AP-Assignment/src/test/animal/resources/testAnimalData.json";
        //File animalData = new File(pathname);
        String expectedJsonBeforeChange = "[{\"landSpace\":2.0,\"waterSpace\":4.0,\"name\":\"Penny\",\"species\":\"Penguin\",\"type\":\"AMPHIBIOUS\",\"assignedPenId\":0,\"hasAssignedPen\":false,\"animalId\":0},{\"landSpace\":13.0,\"name\":\"Douglas\",\"species\":\"Dog\",\"type\":\"LAND\",\"assignedPenId\":1,\"hasAssignedPen\":false,\"animalId\":1},{\"landSpace\":11.0,\"name\":\"Gary\",\"species\":\"Goat\",\"type\":\"PETTABLE\",\"assignedPenId\":2,\"hasAssignedPen\":false,\"animalId\":2},{\"airSpace\":1000.0,\"name\":\"Owen\",\"species\":\"Owl\",\"type\":\"FLYING\",\"assignedPenId\":3,\"hasAssignedPen\":false,\"animalId\":3},{\"waterSpace\":4000.0,\"name\":\"Shaniqua\",\"species\":\"Shark\",\"type\":\"WATER\",\"assignedPenId\":4,\"hasAssignedPen\":false,\"animalId\":4}]";
        String expectedJsonAfterChange = "[{\"landSpace\":2.0,\"waterSpace\":4.0,\"name\":\"Penny\",\"species\":\"Penguin\",\"type\":\"AMPHIBIOUS\",\"assignedPenId\":5,\"hasAssignedPen\":false,\"animalId\":0},{\"landSpace\":13.0,\"name\":\"Douglas\",\"species\":\"Dog\",\"type\":\"LAND\",\"assignedPenId\":1,\"hasAssignedPen\":false,\"animalId\":1},{\"landSpace\":11.0,\"name\":\"Gary\",\"species\":\"Goat\",\"type\":\"PETTABLE\",\"assignedPenId\":2,\"hasAssignedPen\":false,\"animalId\":2},{\"airSpace\":1000.0,\"name\":\"Owen\",\"species\":\"Owl\",\"type\":\"FLYING\",\"assignedPenId\":3,\"hasAssignedPen\":false,\"animalId\":3},{\"waterSpace\":4000.0,\"name\":\"Shaniqua\",\"species\":\"Shark\",\"type\":\"WATER\",\"assignedPenId\":4,\"hasAssignedPen\":false,\"animalId\":4}]";

        //act
        Data.writeAnimalsToJsonFile(pathname, Animal.getAllAnimalsInZooList());

        //assert
        Scanner scanner = null;
        String readFromJsonFile = new String();
        try {
            scanner = new Scanner(new File(pathname));
            readFromJsonFile = scanner.nextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scanner.close();
        assertEquals(expectedJsonBeforeChange, readFromJsonFile);

        penguin.setAssignedPen(newPen.getPenId());
        Data.writeAnimalsToJsonFile(pathname, Animal.getAllAnimalsInZooList());

        Scanner scanner1 = null;
        String readFromJsonFileAfter = new String();
        try {
            scanner1 = new Scanner(new File((pathname)));
            readFromJsonFileAfter = scanner1.nextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scanner1.close();

        assertEquals(expectedJsonAfterChange, readFromJsonFileAfter);
    }

    @Test
    public void testLoadAnimalFromFile() {
        //setup
        String pathname = "/Users/rupesh.vekaria/AP-Assignment/src/test/animal/resources/testInstantiateAnimalObject.json";

        //act
        ArrayList<AmphibiousAnimal> animalsLoadedFromFile = Data.instantiateAnimalsFromJsonFile(pathname, AmphibiousAnimal.class);
        AmphibiousAnimal animalFromFile = animalsLoadedFromFile.get(0);

        //assert
        assertEquals(penguin.getAnimalId(), animalFromFile.getAnimalId());
        assertEquals(penguin.getAssignedPenId(), animalFromFile.getAssignedPenId());
        assertEquals(penguin.getSpecies(), animalFromFile.getSpecies());
        assertEquals(penguin.getAnimalSpace("land"), animalFromFile.getAnimalSpace("land"),0);
        assertEquals(penguin.getAnimalSpace("water"), animalFromFile.getAnimalSpace("water"), 0);
        assertEquals(penguin.getName(), animalFromFile.getName());
        assertEquals(penguin.getType(), animalFromFile.getType());
    }

}
