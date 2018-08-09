package zoo;

import java.util.ArrayList;

public class PartDryWaterPen extends Pen {
    private int height;
    private int waterVolume;
    private int landArea;


    public PartDryWaterPen(String name, int length, int width, int height, int landArea, int waterVolume, int temp, ArrayList<ZooKeeper> zooKeepers, ArrayList<Integer> animalIDsInPen) {
        super(name, length, width, temp, PenType.PARTDRYWATER, zooKeepers, animalIDsInPen);
        this.height = height;
        this.waterVolume = waterVolume;
        this.landArea = landArea;
        listOfAllDryWaterPens.add(this);
        listOfAllPens.add(this);
        penId = listOfAllPens.indexOf(this);
        updateKeepersAssignedPens(zooKeepers);
        writePartDryWaterToFile();
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String displayInfo() {
        return String.format("name: %s, land area: %s, volume: %s, remaining area: %s, remaining volume: %s, temp: %s, zookeepers: %s, no. of animals: %s", getName(), landArea, waterVolume, getRemainingSpace("land"), getRemainingSpace("water"), getTemp(), getKeeperNames(), getAnimalIDsInPen().size());
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
