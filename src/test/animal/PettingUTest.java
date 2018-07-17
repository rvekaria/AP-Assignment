package test.animal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import zoo.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PettingUTest {
    ArrayList<ZooKeeper> keepersList;
    ArrayList<Animal> animalsInPenList;
    Pen pettingPen;
    Animal pettingAnimal;

    @Before
    public void setUp() {
        keepersList = new ArrayList<>();
        animalsInPenList = new ArrayList<>();
        pettingPen = new PettingPen("aquarium", 20, 25, 10, keepersList, animalsInPenList);
        pettingAnimal = new PettingAnimal("Rohandra", "Rabbit", pettingPen, 100);
    }

    @After
    public void tearDown() {
        Animal.getAllAnimalsInZooList().clear();
    }

    @Test
    public void testGetAnimalSpaceString(){
        //act
        int landSpace = pettingAnimal.getAnimalSpace("blah");

        //assert
        assertEquals(100, landSpace);
    }

    @Test
    public void testGetAnimalSpaceNoParam(){
        //act
        int landSpace = pettingAnimal.getAnimalSpace();

        //assert
        assertEquals(100, landSpace);
    }
}
