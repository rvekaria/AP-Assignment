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

public class AnimalUTest {
    ArrayList<ZooKeeper> keepersList;
    ArrayList<Animal> animalsInDryWaterPenList;
    Pen dryWaterPen;
    Animal penguin;

    ArrayList<Animal> animalsInDryPenList;
    Pen dryPen;
    Animal dog;

    ArrayList<Animal> animalsInPetPenList;
    Pen pettingPen;
    Animal goat;

    ArrayList<Animal> animalsInAviaryList;
    Pen aviary;
    Animal owl;

    ArrayList<Animal> animalsInAquarium;
    Pen aquarium;
    Animal shark;


    @Before
    public void setUp() {
        keepersList = new ArrayList<>();
        animalsInDryWaterPenList = new ArrayList<>();
        dryWaterPen = new PartDryWaterPen("dryWaterPen", 20, 25, 10, 400, 1000, 18, keepersList, animalsInDryWaterPenList);
        penguin = new AmphibiousAnimal("Penny", "Penguin", dryWaterPen, 2, 4);

        animalsInDryPenList = new ArrayList<>();
        dryPen = new DryPen("dryPen", 40, 50, 10, keepersList, animalsInDryPenList);
        dog = new LandAnimal("Douglas", "Dog", dryPen, 13);


        animalsInPetPenList = new ArrayList<>();
        pettingPen = new PettingPen("pettingPen", 20, 20, 15, keepersList, animalsInPetPenList);
        goat = new PettingAnimal("Gary", "Goat", pettingPen, 11);

        animalsInAviaryList = new ArrayList<>();
        aviary = new Aviary("aviaryPen", 13, 15, 15, 17, keepersList, animalsInAviaryList);
        owl = new FlyingAnimal("Owen", "Owl", aviary, 1000);

        animalsInAquarium = new ArrayList<>();
        aquarium = new Aquarium("aquaPen", 100, 100, 10, 20, keepersList, animalsInAquarium);
        shark = new WaterAnimal("Shaniqua", "Shark", aquarium, 4000);

    }

    @After
    public void tearDown() {
        Animal.getAllAnimalsInZooList().clear();
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
        ArrayList<Animal> animalsInDryPenList = new ArrayList<>();
        Pen dryPen = new DryPen("dryPen", 40, 50, 10, keepersList, animalsInDryPenList);
        Animal giraffe = new LandAnimal("Jeff", "Giraffe", dryPen, 10);

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
        ArrayList<Animal> animalsInPenList2 = new ArrayList<>();
        Pen newPen = new PartDryWaterPen("newPen", 20, 25, 10, 400, 1000, 18, keepersList, animalsInPenList2);
        String pathname = "/Users/rupesh.vekaria/AP-Assignment/src/test/animal/resources/testAnimalData.csv";
        File animalData = new File(pathname);

        //act
        penguin.setAssignedPen(newPen, animalData);
        ArrayList<Animal> animalsInOldPen = dryWaterPen.getAnimalsInPen();
        ArrayList<Animal> animalsInNewPen = newPen.getAnimalsInPen();

        //assert
        assertEquals(newPen, penguin.getAssignedPen());
        assertFalse(animalsInOldPen.contains(penguin));
        assertTrue(animalsInNewPen.contains(penguin));
    }

    @Test
    public void testWriteAnimalToFile() {
        //setup
        String pathname = "/Users/rupesh.vekaria/AP-Assignment/src/test/animal/resources/testAnimalData.csv";
        File animalData = new File(pathname);
        String expectedHeading = "NAME,SPECIES,TYPE,ASSIGNED_PEN,LAND_REQUIREMENT,WATER_REQUIREMENT,AIR_REQUIREMENT";
        String expectedAnimal1 = "Penny,Penguin,AMPHIBIOUS,dryWaterPen,2,4,0";
        String expectedAnimal2 = "Douglas,Dog,LAND,dryPen,13,0,0";
        String expectedAnimal3 = "Gary,Goat,PETTABLE,pettingPen,11,0,0";
        String expectedAnimal4 = "Owen,Owl,FLYING,aviaryPen,0,0,1000";
        String expectedAnimal5 = "Shaniqua,Shark,WATER,aquaPen,0,4000,0";

        //act
        Animal.writeAnimalsToFile(animalData);

        //assert
        try {
            Scanner scanner = new Scanner(animalData);
            String header = scanner.nextLine();
            String animal1 = scanner.nextLine();
            String animal2 = scanner.nextLine();
            String animal3 = scanner.nextLine();
            String animal4 = scanner.nextLine();
            String animal5 = scanner.nextLine();

            assertEquals(expectedHeading, header);
            assertEquals(expectedAnimal1, animal1);
            assertEquals(expectedAnimal2, animal2);
            assertEquals(expectedAnimal3, animal3);
            assertEquals(expectedAnimal4, animal4);
            assertEquals(expectedAnimal5, animal5);

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWriteAnimalToFileAfterChangingPen() {
        //setup
        ArrayList<Animal> animalsInPenList2 = new ArrayList<>();
        Pen newPen = new PartDryWaterPen("newPen", 20, 25, 10, 400, 1000, 18, keepersList, animalsInPenList2);
        String pathname = "/Users/rupesh.vekaria/AP-Assignment/src/test/animal/resources/testAnimalData.csv";
        File animalData = new File(pathname);
        String expectedHeading = "NAME,SPECIES,TYPE,ASSIGNED_PEN,LAND_REQUIREMENT,WATER_REQUIREMENT,AIR_REQUIREMENT";
        String expectedBeforeAssignPen = "Penny,Penguin,AMPHIBIOUS,dryWaterPen,2,4,0";
        String expectedChange = "Penny,Penguin,AMPHIBIOUS,newPen,2,4,0";

        String expectedAnimal2 = "Douglas,Dog,LAND,dryPen,13,0,0";
        String expectedAnimal3 = "Gary,Goat,PETTABLE,pettingPen,11,0,0";
        String expectedAnimal4 = "Owen,Owl,FLYING,aviaryPen,0,0,1000";
        String expectedAnimal5 = "Shaniqua,Shark,WATER,aquaPen,0,4000,0";

        //act
        penguin.setAssignedPen(dryWaterPen, animalData);

        //assert
        try {
            Scanner scanner = new Scanner(animalData);
            String header = scanner.nextLine();
            String animal1 = scanner.nextLine();
            String animal2 = scanner.nextLine();
            String animal3 = scanner.nextLine();
            String animal4 = scanner.nextLine();
            String animal5 = scanner.nextLine();

            scanner.close();
            assertEquals(expectedHeading, header);
            assertEquals(expectedBeforeAssignPen, animal1);

            penguin.setAssignedPen(newPen, animalData);

            Scanner scanner1 = new Scanner(animalData);
            scanner1.nextLine();
            String animal1Changed = scanner1.nextLine();

            assertEquals(expectedHeading, header);
            assertEquals(expectedChange, animal1Changed);
            assertEquals(expectedAnimal2, animal2);
            assertEquals(expectedAnimal3, animal3);
            assertEquals(expectedAnimal4, animal4);
            assertEquals(expectedAnimal5, animal5);

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
