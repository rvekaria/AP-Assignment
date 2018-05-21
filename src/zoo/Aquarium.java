package zoo;

import java.util.ArrayList;

public class Aquarium extends Pen {
    private int height;
    private int volume;

    public Aquarium(String name, int length, int width, int height, int temp, ArrayList<ZooKeeper> zooKeepers, ArrayList<Animal> animalsInPen) {
        super(name, length, width, temp, penType.AQUARIUM, zooKeepers, animalsInPen);
        this.height = height;
        this.volume = length * width * height;
    }

    public int getHeight() {
        return height;
    }

    public int getVolume() {
        return volume;
    }
}
