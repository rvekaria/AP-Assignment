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

}
