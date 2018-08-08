package zoo;

import java.util.ArrayList;

public class Aviary extends Pen {
    private int height;
    private int volume;


    public Aviary(String name, int length, int width, int height, int temp, ArrayList<ZooKeeper> zooKeepers, ArrayList<Integer> animalIDsInPen) {
        super(name, length, width, temp, PenType.AVIARY, zooKeepers, animalIDsInPen);
        this.height = height;
        this.volume = length * width * height;
        listOfAllAviaries.add(this);
        listOfAllPens.add(this);
        penId = listOfAllPens.indexOf(this);
        writeAviariesToFile();
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String displayInfo() {
        return String.format("name: %s, volume: %s, remaining volume: %s, temp: %s, zookeepers: %s, no. of animals: %s", getName(), volume, getRemainingSpace(), getTemp(), getKeeperNames(), getAnimalIDsInPen().size());
    }

    @Override
    public int getCapacity() {
        return volume;
    }
}
