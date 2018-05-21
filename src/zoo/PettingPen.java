package zoo;

import java.util.ArrayList;

public class PettingPen extends Pen {
    public PettingPen(String name, int length, int width, int temp, ArrayList<ZooKeeper> zooKeepers, ArrayList<Animal> animalsInPen) {
        super(name, length, width, temp, penType.PETTING, zooKeepers, animalsInPen);
    }
}
