package zoo;

import java.util.ArrayList;

public class PartDryWaterPen extends Pen {
    private int height;
    private int waterVolume;
    private int landArea;
    private static ArrayList<Pen> listOfAllDryWaterPens = new ArrayList<>();

    public PartDryWaterPen(String name, int length, int width, int height, int landArea, int waterVolume, int temp, ArrayList<ZooKeeper> zooKeepers, ArrayList<Integer> animalIDsInPen) {
        super(name, length, width, temp, PenType.PARTDRYWATER, zooKeepers, animalIDsInPen);
        this.height = height;
        this.waterVolume = waterVolume;
        this.landArea = landArea;
        listOfAllDryWaterPens.add(this);
        listOfAllPens.add(this);
        penId = listOfAllPens.indexOf(this);
        writePensToJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/penData/partDryWaterPensData.json", listOfAllDryWaterPens);
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
