package test.animal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import zoo.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class FlyingTest {
    ArrayList<ZooKeeper> keepersList;
    ArrayList<Integer> animalsInPenIdList;
    Pen aviary1;
    Animal flyingAnimal;

    @Before
    public void setUp() {
        keepersList = new ArrayList<>();
        animalsInPenIdList = new ArrayList<>();
        aviary1 = new Aviary("aquarium", 20, 25, 10, 400, keepersList, animalsInPenIdList);
        flyingAnimal = new FlyingAnimal("Oleg", "Owl", aviary1.getPenId(), 100);
    }

    @After
    public void tearDown() {
        Animal.getAllAnimalsInZooList().clear();
        Pen.getListOfAllPens().clear();
    }

    @Test
    public void testGetAnimalSpaceString(){
        //act
        double airSpace = flyingAnimal.getAnimalSpace("blah");

        //assert
        assertEquals(100, airSpace);
    }

    @Test
    public void testGetAnimalSpaceNoParam(){
        //act
        double airSpace = flyingAnimal.getAnimalSpace();

        //assert
        assertEquals(100, airSpace);
    }



}
