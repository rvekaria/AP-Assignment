package test.animal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import zoo.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class WaterUTest {
    ArrayList<ZooKeeper> keepersList;
    ArrayList<Animal> animalsInPenList;
    Pen aquarium;
    Animal waterAnimal;

    @Before
    public void setUp() {
        keepersList = new ArrayList<>();
        animalsInPenList = new ArrayList<>();
        aquarium = new Aquarium("aquarium", 20, 25, 10, 18, keepersList, animalsInPenList);
        waterAnimal = new WaterAnimal("Siobhan", "Shark", aquarium, 200);
        //THIS SHOULD FAIL, THERE IS NO CAPACITY IN THE PEN
    }

    @After
    public void tearDown() {
        Animal.getAllAnimalsInZooList().clear();
    }

    @Test
    public void testGetAnimalSpaceString(){
        //act
        int waterSpace = waterAnimal.getAnimalSpace("blah");

        //assert
        assertEquals(200, waterSpace);
    }

    @Test
    public void testGetAnimalSpaceNoParam(){
        //act
        int waterSpace = waterAnimal.getAnimalSpace();

        //assert
        assertEquals(200, waterSpace);
    }
}
