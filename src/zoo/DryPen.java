package zoo;

import java.util.ArrayList;

public class DryPen extends Pen {
    private int area;

    public DryPen(String name, int length, int width, int temp, ArrayList<ZooKeeper> zooKeepers, ArrayList<Integer> animalIDsInPen) {
        super(name, length, width, temp, PenType.DRY, zooKeepers, animalIDsInPen);
        this.area = length * width;
        listOfAllPens.add(this);
        listOfAllDryPens.add(this);
        penId = listOfAllPens.indexOf(this);
        updateKeepersAssignedPens(zooKeepers);
        writeDryPensToFile();
    }

    @Override
    public String displayInfo() {
        return String.format("name: %s, area: %s, remaining area: %s, temp: %s, zookeepers: %s, no. of animals: %s", getName(), area, getRemainingSpace(), getTemp(), getKeeperNames(), getAnimalIDsInPen().size());
    }

    @Override
    public int getCapacity() {
        return area;
    }

}
