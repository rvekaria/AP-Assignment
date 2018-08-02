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
        writePensToJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/penData/aviariesData.json", listOfAllAviaries);
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return String.format("name: %s, volume: %s, remaining area: %s, temp: %s, zookeepers: %s, no. of animals: %s", getName(), volume, getRemainingSpace(), getTemp(), getKeeperNames(), getAnimalIDsInPen().size());
    }

    @Override
    public int getCapacity() {
        return volume;
    }
}
