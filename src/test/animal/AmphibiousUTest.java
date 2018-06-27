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

public class AmphibiousUTest {
    ArrayList<ZooKeeper> keepersList;
    ArrayList<Animal> animalsInPenList;
    Pen dryWaterPen1;
    Animal amphAnimal;

    @Before
    public void setUp() {
        keepersList = new ArrayList<>();
        animalsInPenList = new ArrayList<>();
        dryWaterPen1 = new PartDryWaterPen("dryWaterPen", 20, 25, 10, 400, 1000, 18, keepersList, animalsInPenList);
        amphAnimal = new AmphibiousAnimal("Penny", "Penguin", dryWaterPen1, 2, 4);
    }

    @After
    public void tearDown(){
        Animal.getAllAnimalsInZooList().clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAnimalSpaceString() {
        //act
        int landSpace = amphAnimal.getAnimalSpace("land");
        int waterSpace = amphAnimal.getAnimalSpace("water");

        //assert
        assertEquals(2, landSpace);
        assertEquals(4, waterSpace);
        int animalSpace = amphAnimal.getAnimalSpace("blah");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAnimalSpaceNoParam() {
        //act
        int animalSpace = amphAnimal.getAnimalSpace();

    }

    @Test
    public void testGetName() {
        //act
        String name = amphAnimal.getName();

        //assert
        assertEquals("Penny", name);
    }

    @Test
    public void testGetSpecies() {
        //act
        String species = amphAnimal.getSpecies();

        //assert
        assertEquals("Penguin", species);
    }

    @Test
    public void testGetAssignedPen() {
        //act
        Pen assignedPen = amphAnimal.getAssignedPen();

        //assert
        assertEquals(dryWaterPen1, assignedPen);
    }

    @Test
    public void testGetType() {
        //act
        Animal.animalType type = amphAnimal.getType();

        //assert
        assertEquals(Animal.animalType.AMPHIBIOUS, type);
    }

    @Test
    public void testAnimalIsInZooAnimalsList() {
        //act
        ArrayList<Animal> animalsInZoo = Animal.getAllAnimalsInZooList();

        //assert
        assertTrue(animalsInZoo.contains(amphAnimal));
    }
    //@Ignore
    @Test
    public void testSetAssignedPen() {
        //setUp
        ArrayList<Animal> animalsInPenList2 = new ArrayList<>();
        Pen newPen = new PartDryWaterPen("newPen", 20, 25, 10, 400, 1000, 18, keepersList, animalsInPenList2);
        String pathname = "/Users/rupesh.vekaria/AP-Assignment/src/test/animal/resources/testAnimalData.csv";
        File animalData = new File(pathname);

        //act
        amphAnimal.setAssignedPen(newPen, animalData);
        animalData.delete();
        ArrayList<Animal> animalsInOldPen = dryWaterPen1.getAnimalsInPen();
        ArrayList<Animal> animalsInNewPen = newPen.getAnimalsInPen();

        //assert
        assertEquals(newPen, amphAnimal.getAssignedPen());
        assertFalse(animalsInOldPen.contains(amphAnimal));
        assertTrue(animalsInNewPen.contains(amphAnimal));
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
        String expectedLine2 = "Penny,Penguin,AMPHIBIOUS,newPen,2,4,0";

        //act
        amphAnimal.setAssignedPen(newPen, animalData);

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

}
