package zoo;

import java.util.ArrayList;

public class PartDryWaterPen extends Pen {
    private int height;
    private int waterVolume;
    private int landArea;

    public PartDryWaterPen(String name, int length, int width, int height, int landArea, int waterVolume, int temp, ArrayList<ZooKeeper> zooKeepers, ArrayList<Animal> animalsInPen) {
        super(name, length, width, temp, PenType.PARTDRYWATER, zooKeepers, animalsInPen);
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

    @Override
    public int getCapacity(String type) {
        if (type.equals("land")) {
            return landArea;
        } else if (type.equals("water")) {
            return waterVolume;
        } else {
            System.out.println("land or water must be specified. (returned -1)");
            return -1;
        }

    }
}
