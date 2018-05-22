package zoo;

import java.util.ArrayList;

public class DryPen extends Pen {
    private int area;

    public DryPen(String name, int length, int width, int temp, ArrayList<ZooKeeper> zooKeepers, ArrayList<Animal> animalsInPen) {
        super(name, length, width, temp, penType.DRY, zooKeepers, animalsInPen);
        this.area = length * width;
    }

    public int getArea() {
        return area;
    }
}
