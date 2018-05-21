package zoo;

import java.util.ArrayList;

public class DryPen extends Pen {
    public DryPen(String name, int length, int width, int temp, ArrayList<ZooKeeper> zooKeepers, ArrayList<Animal> animalsInPen) {
        super(name, length, width, temp, penType.DRY, zooKeepers, animalsInPen);
    }
}
