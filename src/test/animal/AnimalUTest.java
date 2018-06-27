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
    ArrayList<Animal> animalsInPenList;
    Pen dryWaterPen;
    Animal penguin;

    @Before
    public void setUp() {
        keepersList = new ArrayList<>();
        animalsInPenList = new ArrayList<>();
        dryWaterPen = new PartDryWaterPen("dryWaterPen", 20, 25, 10, 400, 1000, 18, keepersList, animalsInPenList);
        penguin = new AmphibiousAnimal("Penny", "Penguin", dryWaterPen, 2, 4);
    }

    @After
    public void tearDown() {
        Animal.getAllAnimalsInZooList().clear();
    }

    @Test
    public void testGetName() {
        //act
        String name = penguin.getName();

        //assert
        assertEquals("Penny", name);
    }

    @Test
    public void testGetSpecies() {
        //act
        String species = penguin.getSpecies();

        //assert
        assertEquals("Penguin", species);
    }

    @Test
    public void testGetAssignedPen() {
        //act
        Pen assignedPen = penguin.getAssignedPen();

        //assert
        assertEquals(dryWaterPen, assignedPen);
    }

    @Test
    public void testGetType() {
        //act
        Animal.animalType type = penguin.getType();

        //assert
        assertEquals(Animal.animalType.AMPHIBIOUS, type);
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
        assertTrue(animalsInZoo.contains(giraffe));
        assertEquals(2, animalsInZoo.size());
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
        animalData.delete();
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
        String expectedLine1 = "NAME,SPECIES,TYPE,ASSIGNED_PEN,LAND_REQUIREMENT,WATER_REQUIREMENT,AIR_REQUIREMENT";
        String expectedLine2 = "Penny,Penguin,AMPHIBIOUS,dryWaterPen,2,4,0";

        //act
        Animal.writeAnimalsToFile(animalData);

        //assert
        try {
            Scanner scanner = new Scanner(animalData);
            String line1 = scanner.nextLine();
            String line2 = scanner.nextLine();

            assertEquals(expectedLine1, line1);
            assertEquals(expectedLine2, line2);
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
        String expectedLine1 = "NAME,SPECIES,TYPE,ASSIGNED_PEN,LAND_REQUIREMENT,WATER_REQUIREMENT,AIR_REQUIREMENT";
        String expectedLine2 = "Penny,Penguin,AMPHIBIOUS,dryWaterPen,2,4,0";
        String expectedChange = "Penny,Penguin,AMPHIBIOUS,newPen,2,4,0";

        //act
        penguin.setAssignedPen(dryWaterPen, animalData);

        //assert
        try {
            Scanner scanner = new Scanner(animalData);
            String line1 = scanner.nextLine();
            String line2 = scanner.nextLine();
            scanner.close();
            assertEquals(expectedLine1, line1);
            assertEquals(expectedLine2, line2);

            penguin.setAssignedPen(newPen, animalData);
            Scanner scanner1 = new Scanner(animalData);
            scanner1.nextLine();
            assertEquals(expectedChange, scanner1.nextLine());
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
