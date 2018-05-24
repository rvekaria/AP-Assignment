package zoo;

import java.util.ArrayList;

public class PettingPen extends Pen {
    private int area;

    public PettingPen(String name, int length, int width, int temp, ArrayList<ZooKeeper> zooKeepers, ArrayList<Animal> animalsInPen) {
        super(name, length, width, temp, PenType.PETTING, zooKeepers, animalsInPen);
        this.area = length * width;
    }

    @Override
    public int getCapacity() {
        return area;
    }
}
