package zoo;

import java.util.ArrayList;

public class Aviary extends Pen {
    private int height;
    private int volume;
    private static ArrayList<Pen> listOfAllAviaries = new ArrayList<>();

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
    public int getCapacity() {
        return volume;
    }
}
