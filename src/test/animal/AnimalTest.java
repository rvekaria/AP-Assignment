package test.animal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import zoo.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

public class AnimalTest {
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
    public void testGetName() {
        //act
        String amphName = penguin.getName();
        String landName = dog.getName();
        String petName = goat.getName();
        String flyName = owl.getName();
        String waterName = shark.getName();

        //assert
        assertEquals("Penny", amphName);
        assertEquals("Douglas", landName);
        assertEquals("Gary", petName);
        assertEquals("Owen", flyName);
        assertEquals("Shaniqua", waterName);
    }

    @Test
    public void testGetSpecies() {
        //act
        String amphSpecies = penguin.getSpecies();
        String landSpecies = dog.getSpecies();
        String petSpecies = goat.getSpecies();
        String flySpecies = owl.getSpecies();
        String waterSpecies = shark.getSpecies();

        //assert
        assertEquals("Penguin", amphSpecies);
        assertEquals("Dog", landSpecies);
        assertEquals("Goat", petSpecies);
        assertEquals("Owl", flySpecies);
        assertEquals("Shark", waterSpecies);
    }

    @Test
    public void testGetAssignedPen() {
        //act
        Pen amphPen = penguin.getAssignedPen();
        Pen landPen = dog.getAssignedPen();
        Pen petPen = goat.getAssignedPen();
        Pen flyPen = owl.getAssignedPen();
        Pen waterPen = shark.getAssignedPen();

        //assert
        assertEquals(dryWaterPen, amphPen);
        assertEquals(dryPen, landPen);
        assertEquals(pettingPen, petPen);
        assertEquals(aviary, flyPen);
        assertEquals(aquarium, waterPen);
    }

    @Test
    public void testGetType() {
        //act
        Animal.animalType amphType = penguin.getType();
        Animal.animalType landType = dog.getType();
        Animal.animalType petType = goat.getType();
        Animal.animalType flyType = owl.getType();
        Animal.animalType waterType = shark.getType();

        //assert
        assertEquals(Animal.animalType.AMPHIBIOUS, amphType);
        assertEquals(Animal.animalType.LAND, landType);
        assertEquals(Animal.animalType.PETTABLE, petType);
        assertEquals(Animal.animalType.FLYING, flyType);
        assertEquals(Animal.animalType.WATER, waterType);
    }

    @Test
    public void testAnimalIsInZooAnimalsList() {
        //setup
        ArrayList<Integer> animalsInDryPenIdList = new ArrayList<>();
        Pen dryPen = new DryPen("dryPen", 40, 50, 10, keepersList, animalsInDryPenIdList);
        Animal giraffe = new LandAnimal("Jeff", "Giraffe", dryPen.getPenId(), 10);

        //act
        ArrayList<Animal> animalsInZoo = Animal.getAllAnimalsInZooList();

        //assert
        assertTrue(animalsInZoo.contains(penguin));
        assertTrue(animalsInZoo.contains(dog));
        assertTrue(animalsInZoo.contains(goat));
        assertTrue(animalsInZoo.contains(owl));
        assertTrue(animalsInZoo.contains(shark));
        assertTrue(animalsInZoo.contains(giraffe));
        assertEquals(6, animalsInZoo.size());
    }

    @Test
    public void testSetAssignedPen() {
        //setUp
        ArrayList<Integer> animalsInPenIdList2 = new ArrayList<>();
        Pen newPen = new PartDryWaterPen("newPen", 20, 25, 10, 400, 1000, 18, keepersList, animalsInPenIdList2);
        String pathname = "/Users/rupesh.vekaria/AP-Assignment/src/test/animal/resources/testAnimalData.csv";

        //act
        penguin.setAssignedPen(newPen.getPenId());
        ArrayList<Integer> animalsInOldPen = dryWaterPen.getAnimalIDsInPen();
        ArrayList<Integer> animalsInNewPen = newPen.getAnimalIDsInPen();

        //assert
        assertEquals(newPen, penguin.getAssignedPen());
        assertFalse(animalsInOldPen.contains(penguin.getAnimalId()));
        assertTrue(animalsInNewPen.contains(penguin.getAnimalId()));
    }

    @Test
    public void testGetAnimalsWithoutPens(){
        //setup
        penguin.setAssignedPen(1);
        owl.setAssignedPen(4);

        //act
        ArrayList<Animal> unassignedAnimals = Animal.getAnimalsWithoutPens();

        //assert
        assertEquals(2, unassignedAnimals.size());
        assertEquals(penguin, unassignedAnimals.get(0));
        assertEquals(owl, unassignedAnimals.get(1));
        assertEquals(-1, penguin.getAssignedPenId());
        assertEquals(-1, owl.getAssignedPenId());
    }

}
