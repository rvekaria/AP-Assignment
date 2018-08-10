package zoo;

import java.util.ArrayList;

public class PettingPen extends Pen {
    private int area;


    public PettingPen(String name, int length, int width, int temp, ArrayList<ZooKeeper> zooKeepers, ArrayList<Integer> animalIDsInPen) {
        super(name, length, width, temp, PenType.PETTING, zooKeepers, animalIDsInPen);
        this.area = length * width;
        getListOfAllPettingPens().add(this);
        getListOfAllPens().add(this);
        penId = getListOfAllPens().indexOf(this);
        updateKeepersAssignedPens(zooKeepers);
        writePettingPensToFile();
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
