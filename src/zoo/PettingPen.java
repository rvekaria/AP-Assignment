package zoo;

import java.util.ArrayList;

public class PettingPen extends Pen {
    private int area;
    private static ArrayList<Pen> listOfAllPettingPens = new ArrayList<>();

    public PettingPen(String name, int length, int width, int temp, ArrayList<ZooKeeper> zooKeepers, ArrayList<Integer> animalIDsInPen) {
        super(name, length, width, temp, PenType.PETTING, zooKeepers, animalIDsInPen);
        this.area = length * width;
        listOfAllPettingPens.add(this);
        listOfAllPens.add(this);
        penId = listOfAllPens.indexOf(this);
        writePensToJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/penData/pettingPensData.json", listOfAllPettingPens);
    }

    @Override
    public int getCapacity() {
        return area;
    }
}
