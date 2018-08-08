package test.pen;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import zoo.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PenUTest {
    ArrayList<ZooKeeper> dryWaterKeepersList;
    ArrayList<Integer> animalsInDryWaterPenIdList;
    PartDryWaterPen dryWaterPen;
    Animal penguin;

    ArrayList<ZooKeeper> dryKeepersList;
    ArrayList<Integer> animalsInDryPenIdList;
    Pen dryPen;
    Animal dog;

    ArrayList<ZooKeeper> pettingKeepersList;
    ArrayList<Integer> animalsInPetPenIdList;
    Pen pettingPen;
    Animal goat;

    ArrayList<ZooKeeper> aviaryKeepersList;
    ArrayList<Integer> animalsInAviaryIdList;
    Aviary aviary;
    Animal owl;

    ArrayList<ZooKeeper> aquaKeepersList;
    ArrayList<Integer> animalsInAquariumIdList;
    Aquarium aquarium;
    Animal shark;


    @Before
    public void setUp() {
        dryWaterKeepersList = new ArrayList<>();
        animalsInDryWaterPenIdList = new ArrayList<>();
        dryWaterPen = new PartDryWaterPen("dryWaterPen", 20, 25, 10, 400, 1000, 18, dryWaterKeepersList, animalsInDryWaterPenIdList);
        penguin = new AmphibiousAnimal("Penny", "Penguin", dryWaterPen.getPenId(), 2, 4);

        dryKeepersList = new ArrayList<>();
        animalsInDryPenIdList = new ArrayList<>();
        dryPen = new DryPen("dryPen", 40, 50, 10, dryKeepersList, animalsInDryPenIdList);
        dog = new LandAnimal("Douglas", "Dog", dryPen.getPenId(), 13);

        pettingKeepersList = new ArrayList<>();
        animalsInPetPenIdList = new ArrayList<>();
        pettingPen = new PettingPen("pettingPen", 20, 20, 15, pettingKeepersList, animalsInPetPenIdList);
        goat = new PettingAnimal("Gary", "Goat", pettingPen.getPenId(), 11);

        aviaryKeepersList = new ArrayList<>();
        animalsInAviaryIdList = new ArrayList<>();
        aviary = new Aviary("aviaryPen", 13, 15, 15, 17, aviaryKeepersList, animalsInAviaryIdList);
        owl = new FlyingAnimal("Owen", "Owl", aviary.getPenId(), 1000);

        aquaKeepersList = new ArrayList<>();
        animalsInAquariumIdList = new ArrayList<>();
        aquarium = new Aquarium("aquaPen", 100, 100, 10, 20, aquaKeepersList, animalsInAquariumIdList);
        shark = new WaterAnimal("Shaniqua", "Shark", aquarium.getPenId(), 4000);

    }

    @After
    public void tearDown() {
        Animal.getAllAnimalsInZooList().clear();
        Pen.getListOfAllPens().clear();
    }

    @Test
    public void testGetName() {
        //act
        String dryWaterPenName = dryWaterPen.getName();
        String dryPenName = dryPen.getName();
        String petPenName = pettingPen.getName();
        String aviaryName = aviary.getName();
        String aquariumName = aquarium.getName();

        //assert
        assertEquals("dryWaterPen", dryWaterPenName);
        assertEquals("dryPen", dryPenName);
        assertEquals("pettingPen", petPenName);
        assertEquals("aviaryPen", aviaryName);
        assertEquals("aquaPen", aquariumName);
    }

    @Test
    public void testGetLength() {
        //act
        int dryWaterPenLength = dryWaterPen.getLength();
        int dryPenLength = dryPen.getLength();
        int petPenLength = pettingPen.getLength();
        int aviaryLength = aviary.getLength();
        int aquariumLength = aquarium.getLength();

        //assert
        assertEquals(20, dryWaterPenLength);
        assertEquals(40, dryPenLength);
        assertEquals(20, petPenLength);
        assertEquals(13, aviaryLength);
        assertEquals(100, aquariumLength);
    }

    @Test
    public void testGetWidth() {
        //act
        int dryWaterPenWidth = dryWaterPen.getWidth();
        int dryPenWidth = dryPen.getWidth();
        int petPenWidth = pettingPen.getWidth();
        int aviaryWidth = aviary.getWidth();
        int aquariumWidth = aquarium.getWidth();

        //assert
        assertEquals(25, dryWaterPenWidth);
        assertEquals(50, dryPenWidth);
        assertEquals(20, petPenWidth);
        assertEquals(15, aviaryWidth);
        assertEquals(100, aquariumWidth);
    }

    @Test
    public void testGetHeight() {
        //act
        int dryWaterPenHeight = dryWaterPen.getHeight();
        int aviaryHeight = aviary.getHeight();
        int aquariumHeight = aquarium.getHeight();

        //assert
        assertEquals(10, dryWaterPenHeight);
        assertEquals(15, aviaryHeight);
        assertEquals(10, aquariumHeight);
    }

    @Test
    public void testGetTemp() {
        //act
        int dryWaterPenTemp = dryWaterPen.getTemp();
        int dryPenTemp = dryPen.getTemp();
        int petPenTemp = pettingPen.getTemp();
        int aviaryTemp = aviary.getTemp();
        int aquariumTemp = aquarium.getTemp();

        //assert
        assertEquals(18, dryWaterPenTemp);
        assertEquals(10, dryPenTemp);
        assertEquals(15, petPenTemp);
        assertEquals(17, aviaryTemp);
        assertEquals(20, aquariumTemp);
    }

    @Test
    public void testGetPenId() {
        //act
        int dryWaterPenId = dryWaterPen.getPenId();
        int dryPenId = dryPen.getPenId();
        int petPenId = pettingPen.getPenId();
        int aviaryId = aviary.getPenId();
        int aquariumId = aquarium.getPenId();

        //assert
        assertEquals(0, dryWaterPenId);
        assertEquals(1, dryPenId);
        assertEquals(2, petPenId);
        assertEquals(3, aviaryId);
        assertEquals(4, aquariumId);
    }

    @Test
    public void testGetCapacity() {
        //act
        int dryWaterPenLandCapacity = dryWaterPen.getCapacity("land");
        int dryWaterPenWaterCapacity = dryWaterPen.getCapacity("water");
        int dryPenCapacity = dryPen.getCapacity();
        int petPenCapacity = pettingPen.getCapacity();
        int aviaryCapacity = aviary.getCapacity();
        int aquariumCapacity = aquarium.getCapacity();

        //assert
        assertEquals(400, dryWaterPenLandCapacity);
        assertEquals(1000, dryWaterPenWaterCapacity);
        assertEquals(2000, dryPenCapacity);
        assertEquals(400, petPenCapacity);
        assertEquals(2925, aviaryCapacity);
        assertEquals(100000, aquariumCapacity);
    }

    @Test
    public void testGetListOfAllPens() {
        //act
        ArrayList<Pen> listOfPens = Pen.getListOfAllPens();
        int numberOfPens = listOfPens.size();

        //assert
        assertEquals(5, numberOfPens);
        assertTrue(listOfPens.contains(dryWaterPen));
        assertTrue(listOfPens.contains(dryPen));
        assertTrue(listOfPens.contains(pettingPen));
        assertTrue(listOfPens.contains(aviary));
        assertTrue(listOfPens.contains(aquarium));
    }

    @Test
    public void testGetZooKeepers() {
        //setup
        ZooKeeper hardip = createZooKeeper(Pen.PenType.DRY, Pen.PenType.AVIARY, dryPen.getPenId(), "Hardip");
        ZooKeeper alex = createZooKeeper(Pen.PenType.AQUARIUM, Pen.PenType.PARTDRYWATER, aquarium.getPenId(), "Alex");
        ZooKeeper farhad = createZooKeeper(Pen.PenType.AVIARY, Pen.PenType.AQUARIUM, aviary.getPenId(), "Farhad");
        ZooKeeper alan = createZooKeeper(Pen.PenType.DRY, Pen.PenType.PETTING, pettingPen.getPenId(), "Alan");

        //act
        ArrayList<ZooKeeper> listOfDryWaterPenKeepers = dryWaterPen.getZooKeepers();
        ArrayList<ZooKeeper> listOfDryPenKeepers = dryPen.getZooKeepers();
        ArrayList<ZooKeeper> listOfPettingPenKeepers = pettingPen.getZooKeepers();
        ArrayList<ZooKeeper> listOfAviaryKeepers = aviary.getZooKeepers();
        ArrayList<ZooKeeper> listOfAquariumKeepers = aquarium.getZooKeepers();

        aviary.assignZooKeeper(hardip);

        //assert
        assertEquals(hardip, listOfDryPenKeepers.get(0));
        assertEquals(alex, listOfAquariumKeepers.get(0));
        assertEquals(farhad, listOfAviaryKeepers.get(0));
        assertEquals(hardip, listOfAviaryKeepers.get(1));
        assertEquals(alan, listOfPettingPenKeepers.get(0));
        assertEquals(0, listOfDryWaterPenKeepers.size());
    }

    @Test
    public void testAssignZooKeeperFailsWhenKeeperisNotTrainedFor() {
        //setup
        ZooKeeper farhad = createZooKeeper(Pen.PenType.AVIARY, Pen.PenType.AQUARIUM, aviary.getPenId(), "Farhad");

        String expectedOutput = "Farhad is not trained to look after DRY pens.\n";

        //act
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        dryPen.assignZooKeeper(farhad);

        //assert
        assertEquals(expectedOutput, outContent.toString());
        assertEquals(0, dryPen.getZooKeepers().size());
        assertEquals(1, aviary.getZooKeepers().size());

    }

    @Test
    public void testAssignZooKeeperWhenKeeperAlreadyAssigned() {
        //setup
        ZooKeeper farhad = createZooKeeper(Pen.PenType.AVIARY, Pen.PenType.AQUARIUM, aviary.getPenId(), "Farhad");

        String expectedOutput = "Farhad is already a keeper of this pen.\n";

        //act
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        aviary.assignZooKeeper(farhad);

        //assert
        assertEquals(expectedOutput, outContent.toString());
        assertEquals(1, aviary.getZooKeepers().size());

    }

    @Test
    public void testAssignAnimalToPen() {
        //act
        dryPen.assignAnimalToPen(goat);

        //assert
        assertEquals(2, dryPen.getAnimalIDsInPen().size());
        assertEquals((Integer) 1, dryPen.getAnimalIDsInPen().get(0));
        assertEquals((Integer) 2, dryPen.getAnimalIDsInPen().get(1));
    }

    @Test
    public void testAssignAnimalToPenFailsForUnsuitablePenType() {
        //setup
        String expectedOutput = "This pen is not suitable for AMPHIBIOUS animals.\n";

        //act
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        dryPen.assignAnimalToPen(penguin);

        //assert
        assertEquals(expectedOutput, outContent.toString());
        assertEquals(1, dryPen.getAnimalIDsInPen().size());
        assertEquals(1, dryWaterPen.getAnimalIDsInPen().size());
        assertEquals("Douglas", Animal.getAllAnimalsInZooList().get(dryPen.getAnimalIDsInPen().get(0)).getName());
        assertEquals("Penny", Animal.getAllAnimalsInZooList().get(dryWaterPen.getAnimalIDsInPen().get(0)).getName());
    }

    @Test
    public void testAssignAnimalToPenFailsWhenNoSpace() {
        //setup
        String expectedOutput = "Obese will be removed from its current pen: aviaryPen\n" +
                "Obese is not in this pen. Cannot remove.\n" +
                "There is no space for Obese in aviaryPen.\n" +
                "Obese was removed from its current pen but could not be assigned to the new pen.\n" +
                "Obese has no assigned pen! Please assign an appropriate pen.\n";

        //act
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Animal owl2 = new FlyingAnimal("Obese", "Owl", aviary.getPenId(), 1926);

        //assert
        assertEquals(expectedOutput, outContent.toString());
        assertEquals(1, aviary.getAnimalIDsInPen().size());
        assertEquals(6, Animal.getAllAnimalsInZooList().size());
        assertEquals(-1, owl2.getAssignedPenId());
        assertEquals("Owen", Animal.getAllAnimalsInZooList().get(aviary.getAnimalIDsInPen().get(0)).getName());
    }

    @Test
    public void testAssignAnimalToPenFailsWhenNoSpaceForAmphibousAnimal() {
        //setup
        String expectedOutput = "Big P will be removed from its current pen: dryWaterPen\n" +
                "Big P is not in this pen. Cannot remove.\n" +
                "There is no space for Big P in dryWaterPen.\n" +
                "Big P was removed from its current pen but could not be assigned to the new pen.\n" +
                "Big P has no assigned pen! Please assign an appropriate pen.\n";

        //act
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Animal penguin2 = new AmphibiousAnimal("Big P", "Penguin", dryWaterPen.getPenId(), 3, 997);

        //assert
        assertEquals(expectedOutput, outContent.toString());
        assertEquals(1, dryWaterPen.getAnimalIDsInPen().size());
        assertEquals(6, Animal.getAllAnimalsInZooList().size());
        assertEquals(-1, penguin2.getAssignedPenId());
        assertEquals("Penny", Animal.getAllAnimalsInZooList().get(dryWaterPen.getAnimalIDsInPen().get(0)).getName());
    }

    @Test
    public void testRemoveAnimalFromPen() {
        //act
        dryPen.removeAnimalFromPen(dog);

        //assert
        assertEquals(0, dryPen.getAnimalIDsInPen().size());
    }

    @Test
    public void testRemoveAnimalFromPenThatIsNotThere() {
        //setup
        String expectedOutput = "Gary is not in this pen. Cannot remove.\n";

        //act
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        dryPen.removeAnimalFromPen(goat);

        //assert
        assertEquals(expectedOutput, outContent.toString());
        assertEquals(1, dryPen.getAnimalIDsInPen().size());
    }

    @Test
    public void testWritePensToFile() {
        //setup
        String pathname = "/Users/rupesh.vekaria/AP-Assignment/src/test/pen/resources/testPenData.json";
        File penData = new File(pathname);
        String expectedJson = "[{\"height\":10,\"waterVolume\":1000,\"landArea\":400,\"name\":\"dryWaterPen\",\"length\":20,\"width\":25,\"temp\":18,\"type\":\"PARTDRYWATER\",\"penId\":0,\"zooKeepers\":[],\"animalIDsInPen\":[0]},{\"area\":2000,\"name\":\"dryPen\",\"length\":40,\"width\":50,\"temp\":10,\"type\":\"DRY\",\"penId\":1,\"zooKeepers\":[],\"animalIDsInPen\":[1]},{\"area\":400,\"name\":\"pettingPen\",\"length\":20,\"width\":20,\"temp\":15,\"type\":\"PETTING\",\"penId\":2,\"zooKeepers\":[],\"animalIDsInPen\":[2]},{\"height\":15,\"volume\":2925,\"name\":\"aviaryPen\",\"length\":13,\"width\":15,\"temp\":17,\"type\":\"AVIARY\",\"penId\":3,\"zooKeepers\":[],\"animalIDsInPen\":[3]},{\"height\":10,\"volume\":100000,\"name\":\"aquaPen\",\"length\":100,\"width\":100,\"temp\":20,\"type\":\"AQUARIUM\",\"penId\":4,\"zooKeepers\":[],\"animalIDsInPen\":[4]}]";

        //act
        Pen.writePensToJsonFile(pathname, Pen.getListOfAllPens());

        //assert
        try {
            Scanner scanner = new Scanner(penData);
            String readFromJsonFile = scanner.nextLine();

            assertEquals(expectedJson, readFromJsonFile);

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWritePensToFileAfterChangingPen() {
        //setup
        ArrayList<Integer> animalsInPenList2 = new ArrayList<>();
        String pathname = "/Users/rupesh.vekaria/AP-Assignment/src/test/pen/resources/testPenData.json";
        String expectedJsonBefore = "[{\"height\":10,\"waterVolume\":1000,\"landArea\":400,\"name\":\"dryWaterPen\",\"length\":20,\"width\":25,\"temp\":18,\"type\":\"PARTDRYWATER\",\"penId\":0,\"zooKeepers\":[],\"animalIDsInPen\":[0]},{\"area\":2000,\"name\":\"dryPen\",\"length\":40,\"width\":50,\"temp\":10,\"type\":\"DRY\",\"penId\":1,\"zooKeepers\":[],\"animalIDsInPen\":[1]},{\"area\":400,\"name\":\"pettingPen\",\"length\":20,\"width\":20,\"temp\":15,\"type\":\"PETTING\",\"penId\":2,\"zooKeepers\":[],\"animalIDsInPen\":[2]},{\"height\":15,\"volume\":2925,\"name\":\"aviaryPen\",\"length\":13,\"width\":15,\"temp\":17,\"type\":\"AVIARY\",\"penId\":3,\"zooKeepers\":[],\"animalIDsInPen\":[3]},{\"height\":10,\"volume\":100000,\"name\":\"aquaPen\",\"length\":100,\"width\":100,\"temp\":20,\"type\":\"AQUARIUM\",\"penId\":4,\"zooKeepers\":[],\"animalIDsInPen\":[4]}]";
        String expectedJsonAfter = "[{\"height\":10,\"waterVolume\":1000,\"landArea\":400,\"name\":\"dryWaterPen\",\"length\":20,\"width\":25,\"temp\":18,\"type\":\"PARTDRYWATER\",\"penId\":0,\"zooKeepers\":[],\"animalIDsInPen\":[]},{\"area\":2000,\"name\":\"dryPen\",\"length\":40,\"width\":50,\"temp\":10,\"type\":\"DRY\",\"penId\":1,\"zooKeepers\":[],\"animalIDsInPen\":[1]},{\"area\":400,\"name\":\"pettingPen\",\"length\":20,\"width\":20,\"temp\":15,\"type\":\"PETTING\",\"penId\":2,\"zooKeepers\":[],\"animalIDsInPen\":[2]},{\"height\":15,\"volume\":2925,\"name\":\"aviaryPen\",\"length\":13,\"width\":15,\"temp\":17,\"type\":\"AVIARY\",\"penId\":3,\"zooKeepers\":[],\"animalIDsInPen\":[3]},{\"height\":10,\"volume\":100000,\"name\":\"aquaPen\",\"length\":100,\"width\":100,\"temp\":20,\"type\":\"AQUARIUM\",\"penId\":4,\"zooKeepers\":[],\"animalIDsInPen\":[4]},{\"height\":10,\"waterVolume\":1000,\"landArea\":400,\"name\":\"newPen\",\"length\":20,\"width\":25,\"temp\":18,\"type\":\"PARTDRYWATER\",\"penId\":5,\"zooKeepers\":[],\"animalIDsInPen\":[0]}]";

        //act
        Pen.writePensToJsonFile(pathname, Pen.getListOfAllPens());

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
        assertEquals(expectedJsonBefore, readFromJsonFile);

        Pen newPen = new PartDryWaterPen("newPen", 20, 25, 10, 400, 1000, 18, dryWaterKeepersList, animalsInPenList2);
        penguin.setAssignedPen(newPen.getPenId());
        Pen.writePensToJsonFile(pathname, Pen.getListOfAllPens());

        Scanner scanner1 = null;
        String readFromJsonFileAfter = new String();
        try {
            scanner1 = new Scanner(new File((pathname)));
            readFromJsonFileAfter = scanner1.nextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scanner1.close();

        assertEquals(expectedJsonAfter, readFromJsonFileAfter);
    }

    @Test
    public void testLoadPenFromFile() {
        //setup
        String pathname = "/Users/rupesh.vekaria/AP-Assignment/src/test/pen/resources/testInstantiatePenObject.json";

        ZooKeeper alex = createZooKeeper(Pen.PenType.AQUARIUM, Pen.PenType.PARTDRYWATER, aquarium.getPenId(), "Alex");
        dryWaterPen.assignZooKeeper(alex);

        //act
        ArrayList<PartDryWaterPen> pensLoadedFromFile = Pen.instantiatePensFromJsonFile(pathname, PartDryWaterPen.class);
        PartDryWaterPen penFromFile = pensLoadedFromFile.get(0);

        //assert
        assertEquals(dryWaterPen.getPenId(), penFromFile.getPenId());
        assertEquals(dryWaterPen.getZooKeepers().get(0).getName(), penFromFile.getZooKeepers().get(0).getName());
        assertEquals(dryWaterPen.getHeight(), penFromFile.getHeight());
        assertEquals(dryWaterPen.getCapacity("land"), penFromFile.getCapacity("land"));
        assertEquals(dryWaterPen.getCapacity("water"), penFromFile.getCapacity("water"));
        assertEquals(dryWaterPen.getLength(), penFromFile.getLength());
        assertEquals(dryWaterPen.getAnimalIDsInPen(), penFromFile.getAnimalIDsInPen());
        assertEquals(dryWaterPen.getName(), penFromFile.getName());
        assertEquals(dryWaterPen.getTemp(), penFromFile.getTemp());
        assertEquals(dryWaterPen.getWidth(), penFromFile.getWidth());
        assertEquals(dryWaterPen.getType(), penFromFile.getType());
    }

    private ZooKeeper createZooKeeper(Pen.PenType type1, Pen.PenType type2, int assignedPenId, String keeperName) {
        Pen.PenType[] pensTrainedFor = {type1, type2};
        ArrayList<Pen.PenType> trainedForPensList = createTrainedForPensList(pensTrainedFor);
        ArrayList<Integer> assignedPens = new ArrayList<>();
        assignedPens.add(assignedPenId);
        return new ZooKeeper(keeperName, trainedForPensList, assignedPens);
    }

    private ArrayList<Pen.PenType> createTrainedForPensList(Pen.PenType[] pensList) {
        ArrayList<Pen.PenType> trainedForPensList = new ArrayList<>();
        trainedForPensList.addAll(Arrays.asList(pensList));
        return trainedForPensList;
    }

}
