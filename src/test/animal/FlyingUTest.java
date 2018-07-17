package test.animal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import zoo.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class FlyingUTest {
    ArrayList<ZooKeeper> keepersList;
    ArrayList<Animal> animalsInPenList;
    Pen aviary1;
    Animal flyingAnimal;

    @Before
    public void setUp() {
        keepersList = new ArrayList<>();
        animalsInPenList = new ArrayList<>();
        aviary1 = new Aviary("aquarium", 20, 25, 10, 400, keepersList, animalsInPenList);
        flyingAnimal = new FlyingAnimal("Oleg", "Owl", aviary1, 100);
    }

    @After
    public void tearDown() {
        Animal.getAllAnimalsInZooList().clear();
    }

    @Test
    public void testGetAnimalSpaceString(){
        //act
        int airSpace = flyingAnimal.getAnimalSpace("blah");

        //assert
        assertEquals(100, airSpace);
    }

    @Test
    public void testGetAnimalSpaceNoParam(){
        //act
        int airSpace = flyingAnimal.getAnimalSpace();

        //assert
        assertEquals(100, airSpace);
    }



}
