package zoo;

import java.util.ArrayList;

public class DryPen extends Pen {
    private int area;
    private static ArrayList<Pen> listOfAllDryPens = new ArrayList<>();

    public DryPen(String name, int length, int width, int temp, ArrayList<ZooKeeper> zooKeepers, ArrayList<Integer> animalIDsInPen) {
        super(name, length, width, temp, PenType.DRY, zooKeepers, animalIDsInPen);
        this.area = length * width;
        listOfAllDryPens.add(this);
        listOfAllPens.add(this);
        penId = listOfAllPens.indexOf(this);
        writePensToJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/penData/dryPensData.json", listOfAllDryPens);
    }

    @Override
    public int getCapacity() {
        return area;
    }
}
