package test.animal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import zoo.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class LandUTest {
    ArrayList<ZooKeeper> keepersList;
    ArrayList<Animal> animalsInPenList;
    Pen dryPen;
    Animal landAnimal;

    @Before
    public void setUp() {
        keepersList = new ArrayList<>();
        animalsInPenList = new ArrayList<>();
        dryPen = new DryPen("aquarium", 20, 25, 10, keepersList, animalsInPenList);
        landAnimal = new LandAnimal("Zidane", "Zebra", dryPen, 100);
    }

    @After
    public void tearDown() {
        Animal.getAllAnimalsInZooList().clear();
    }

    @Test
    public void testGetAnimalSpaceString(){
        //act
        int landSpace = landAnimal.getAnimalSpace("blah");

        //assert
        assertEquals(100, landSpace);
    }

    @Test
    public void testGetAnimalSpaceNoParam(){
        //act
        int landSpace = landAnimal.getAnimalSpace();

        //assert
        assertEquals(100, landSpace);
    }
}
