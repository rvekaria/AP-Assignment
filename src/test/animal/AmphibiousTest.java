package test.animal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import zoo.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AmphibiousTest {
    ArrayList<ZooKeeper> keepersList;
    ArrayList<Integer> animalsInPenIdList;
    Pen dryWaterPen1;
    Animal amphAnimal;

    @Before
    public void setUp() {
        keepersList = new ArrayList<>();
        animalsInPenIdList = new ArrayList<>();
        dryWaterPen1 = new PartDryWaterPen("dryWaterPen", 20, 25, 10, 400, 1000, 18, keepersList, animalsInPenIdList);
        amphAnimal = new AmphibiousAnimal("Penny", "Penguin", dryWaterPen1.getPenId(), 2, 4);
    }

    @After
    public void tearDown(){
        Animal.getAllAnimalsInZooList().clear();
        Pen.getListOfAllPens().clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAnimalSpaceString() {
        //act
        double landSpace = amphAnimal.getAnimalSpace("land");
        double waterSpace = amphAnimal.getAnimalSpace("water");

        //assert
        assertEquals(2, landSpace);
        assertEquals(4, waterSpace);
        double animalSpace = amphAnimal.getAnimalSpace("blah");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAnimalSpaceNoParam() {
        //act
        double animalSpace = amphAnimal.getAnimalSpace();

    }

}
