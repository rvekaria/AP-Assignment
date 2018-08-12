package test.animal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import zoo.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class LandTest {
    ArrayList<ZooKeeper> keepersList;
    ArrayList<Integer> animalsInPenIdList;
    Pen dryPen;
    Animal landAnimal;

    @Before
    public void setUp() {
        keepersList = new ArrayList<>();
        animalsInPenIdList = new ArrayList<>();
        dryPen = new DryPen("aquarium", 20, 25, 10, keepersList, animalsInPenIdList);
        landAnimal = new LandAnimal("Zidane", "Zebra", dryPen.getPenId(), 100);
    }

    @After
    public void tearDown() {
        Animal.getAllAnimalsInZooList().clear();
        Pen.getListOfAllPens().clear();
    }

    @Test
    public void testGetAnimalSpaceString(){
        //act
        double landSpace = landAnimal.getAnimalSpace("blah");

        //assert
        assertEquals(100, landSpace);
    }

    @Test
    public void testGetAnimalSpaceNoParam(){
        //act
        double landSpace = landAnimal.getAnimalSpace();

        //assert
        assertEquals(100, landSpace);
    }
}
