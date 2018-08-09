package test.zooKeeper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import zoo.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ZooKeeperUTest {
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

    ZooKeeper hardip;
    ZooKeeper alex;
    ZooKeeper farhad;
    ZooKeeper alan;

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

        hardip = createZooKeeper(Pen.PenType.DRY, Pen.PenType.AVIARY, dryPen.getPenId(), "Hardip");
        alex = createZooKeeper(Pen.PenType.AQUARIUM, Pen.PenType.PARTDRYWATER, aquarium.getPenId(), "Alex");
        farhad = createZooKeeper(Pen.PenType.AVIARY, Pen.PenType.AQUARIUM, aviary.getPenId(), "Farhad");
        alan = createZooKeeper(Pen.PenType.DRY, Pen.PenType.PETTING, pettingPen.getPenId(), "Alan");
    }

    @After
    public void tearDown() {
        Animal.getAllAnimalsInZooList().clear();
        Pen.getListOfAllPens().clear();
        ZooKeeper.getListOfAllZooKeepers().clear();
    }

    @Test
    public void testGetName(){
        //act
        String hardipName = hardip.getName();
        String alexName = alex.getName();
        String farhadName = farhad.getName();
        String alanName = alan.getName();

        //assert
        assertEquals("Hardip", hardipName);
        assertEquals("Alex", alexName);
        assertEquals("Farhad", farhadName);
        assertEquals("Alan", alanName);
    }

    @Test
    public void testGetListOfKeepers(){
        //setup
        ArrayList<ZooKeeper> expectedListOfKeepers = new ArrayList<>();
        expectedListOfKeepers.add(hardip);
        expectedListOfKeepers.add(alex);
        expectedListOfKeepers.add(farhad);
        expectedListOfKeepers.add(alan);

        //act
        ArrayList<ZooKeeper> keeperArrayList = ZooKeeper.getListOfAllZooKeepers();

        //assert
        assertEquals(expectedListOfKeepers, keeperArrayList);
    }

    @Test
    public void testIsTrainedFor(){
        assertTrue(hardip.isTrainedFor(Pen.PenType.DRY));
        assertTrue(hardip.isTrainedFor(Pen.PenType.AVIARY));
        assertFalse(hardip.isTrainedFor(Pen.PenType.AQUARIUM));

        assertTrue(alex.isTrainedFor(Pen.PenType.AQUARIUM));
        assertTrue(alex.isTrainedFor(Pen.PenType.PARTDRYWATER));
        assertFalse(alex.isTrainedFor(Pen.PenType.PETTING));

        assertTrue(farhad.isTrainedFor(Pen.PenType.AVIARY));
        assertTrue(farhad.isTrainedFor(Pen.PenType.AQUARIUM));
        assertFalse(farhad.isTrainedFor(Pen.PenType.PARTDRYWATER));

        assertTrue(alan.isTrainedFor(Pen.PenType.DRY));
        assertTrue(alan.isTrainedFor(Pen.PenType.PETTING));
        assertFalse(alan.isTrainedFor(Pen.PenType.AVIARY));
    }

    @Test
    public void testGetAssignedPenIds(){
        //act
        ArrayList<Integer> hardipAssignedPenIds = hardip.getAssignedPenIds();
        ArrayList<Integer> alexAssignedPenIds = alex.getAssignedPenIds();
        ArrayList<Integer> farhadAssignedPenIds = farhad.getAssignedPenIds();
        ArrayList<Integer> alanAssignedPenIds = alan.getAssignedPenIds();

        //assert
        assertEquals((Integer) 1, hardipAssignedPenIds.get(0));
        assertEquals((Integer) 4, alexAssignedPenIds.get(0));
        assertEquals((Integer) 3, farhadAssignedPenIds.get(0));
        assertEquals((Integer) 2, alanAssignedPenIds.get(0));
    }

    @Test
    public void testAddKeeperToPensListOfKeepers(){
        //assert
        assertTrue(dryPen.getAssignedKeepers().contains(hardip));
        assertTrue(aquarium.getAssignedKeepers().contains(alex));
        assertTrue(aviary.getAssignedKeepers().contains(farhad));
        assertTrue(pettingPen.getAssignedKeepers().contains(alan));
    }

    @Test
    public void testWriteKeepersToFile() {
        //setup
        String pathname = "/Users/rupesh.vekaria/AP-Assignment/src/test/zooKeeper/resources/testKeeperData.json";
        File keepersData = new File(pathname);
        String expectedJson = "[{\"name\":\"Hardip\",\"pensTrainedFor\":[\"DRY\",\"AVIARY\"],\"assignedPenIds\":[1]},{\"name\":\"Alex\",\"pensTrainedFor\":[\"AQUARIUM\",\"PARTDRYWATER\"],\"assignedPenIds\":[4]},{\"name\":\"Farhad\",\"pensTrainedFor\":[\"AVIARY\",\"AQUARIUM\"],\"assignedPenIds\":[3]},{\"name\":\"Alan\",\"pensTrainedFor\":[\"DRY\",\"PETTING\"],\"assignedPenIds\":[2]}]";

        //act
        ZooKeeper.writeKeepersToJsonFile(pathname);

        //assert
        try {
            Scanner scanner = new Scanner(keepersData);
            String readFromJsonFile = scanner.nextLine();

            assertEquals(expectedJson, readFromJsonFile);

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAssignZookeeper(){
        //act
        aviary.assignZooKeeper(hardip);
        dryWaterPen.assignZooKeeper(alex);
        aquarium.assignZooKeeper(farhad);
        dryPen.assignZooKeeper(alan);

        //assert
        assertEquals((Integer) 1, hardip.getAssignedPenIds().get(0));
        assertEquals((Integer) 3, hardip.getAssignedPenIds().get(1));

        assertEquals((Integer) 4, alex.getAssignedPenIds().get(0));
        assertEquals((Integer) 0, alex.getAssignedPenIds().get(1));

        assertEquals((Integer) 3, farhad.getAssignedPenIds().get(0));
        assertEquals((Integer) 4, farhad.getAssignedPenIds().get(1));

        assertEquals((Integer) 2, alan.getAssignedPenIds().get(0));
        assertEquals((Integer) 1, alan.getAssignedPenIds().get(1));
    }

    @Test
    public void testLoadKeepersFromFile() {
        //setup
        String pathname = "/Users/rupesh.vekaria/AP-Assignment/src/test/zooKeeper/resources/testInstantiateKeeperObject.json";

        //act
        ArrayList<ZooKeeper> keepersLoadedFromFile = ZooKeeper.instantiateKeepersFromJsonFile(pathname);
        ZooKeeper alexFromFile = keepersLoadedFromFile.get(0);

        //assert
        assertEquals(alex.getName(), alexFromFile.getName());
        assertEquals(alex.getAssignedPenIds(), alexFromFile.getAssignedPenIds());
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
