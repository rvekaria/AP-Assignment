package zoo;

import java.util.ArrayList;

public class PartDryWaterPen extends Pen {
    private int height;
    private int waterVolume;
    private int landArea;

    public PartDryWaterPen(String name, int length, int width, int height, int landArea, int waterVolume, int temp, ArrayList<ZooKeeper> zooKeepers, ArrayList<Animal> animalsInPen) {
        super(name, length, width, temp, penType.PARTDRYWATER, zooKeepers, animalsInPen);
        this.height = height;
        this.waterVolume = waterVolume;
        this.landArea = landArea;
    }

    public int getHeight() {
        return height;
    }

    public int getWaterVolume() {
        return waterVolume;
    }

    public int getLandArea() {
        return landArea;
    }
}
