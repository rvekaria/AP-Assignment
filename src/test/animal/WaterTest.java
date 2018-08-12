package test.animal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import zoo.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class WaterTest {
    ArrayList<ZooKeeper> keepersList;
    ArrayList<Integer> animalsInPenIdList;
    Pen aquarium;
    Animal waterAnimal;

    @Before
    public void setUp() {
        keepersList = new ArrayList<>();
        animalsInPenIdList = new ArrayList<>();
        aquarium = new Aquarium("aquarium", 20, 25, 10, 18, keepersList, animalsInPenIdList);
        waterAnimal = new WaterAnimal("Siobhan", "Shark", aquarium.getPenId(), 200);
    }

    @After
    public void tearDown() {
        Animal.getAllAnimalsInZooList().clear();
        Pen.getListOfAllPens().clear();
    }

    @Test
    public void testGetAnimalSpaceString(){
        //act
        double waterSpace = waterAnimal.getAnimalSpace("blah");

        //assert
        assertEquals(200, waterSpace);
    }

    @Test
    public void testGetAnimalSpaceNoParam(){
        //act
        double waterSpace = waterAnimal.getAnimalSpace();

        //assert
        assertEquals(200, waterSpace);
    }
}
