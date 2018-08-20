package test.animal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import zoo.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PettingTest {
    ArrayList<ZooKeeper> keepersList;
    ArrayList<Integer> animalsInPenIdList;
    Pen pettingPen;
    Animal pettingAnimal;

    @Before
    public void setUp() {
        keepersList = new ArrayList<>();
        animalsInPenIdList = new ArrayList<>();
        pettingPen = new PettingPen("aquarium", 20, 25, 10, keepersList, animalsInPenIdList);
        pettingAnimal = new PettingAnimal("Rohandra", "Rabbit", pettingPen.getPenId(), 100);
    }

    @After
    public void tearDown() {
        Animal.getAllAnimalsInZooList().clear();
        Pen.getListOfAllPens().clear();
    }

    @Test
    public void testGetAnimalSpaceString(){
        //act
        double landSpace = pettingAnimal.getAnimalSpace("blah");

        //assert
        assertEquals(100, landSpace, 0);
    }

    @Test
    public void testGetAnimalSpaceNoParam(){
        //act
        double landSpace = pettingAnimal.getAnimalSpace();

        //assert
        assertEquals(100, landSpace, 0);
    }
}
