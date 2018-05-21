package zoo;

import java.util.ArrayList;

public class PartDryWaterPen extends Pen {
    private int height;
    private int volume;

    public PartDryWaterPen(String name, int length, int width, int height, int volume, int temp, ArrayList<ZooKeeper> zooKeepers, ArrayList<Animal> animalsInPen) {
        super(name, length, width, temp, penType.PARTDRYWATER, zooKeepers, animalsInPen);
        this.height = height;
        this.volume = volume;
    }

    public int getHeight() {
        return height;
    }

    public int getVolume() {
        return volume;
    }
}
