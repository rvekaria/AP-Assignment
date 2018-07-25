package zoo;

import java.util.ArrayList;

public class Aquarium extends Pen {
    private int height;
    private int volume;
    private static ArrayList<Pen> listOfAllAquariums = new ArrayList<>();

    public Aquarium(String name, int length, int width, int height, int temp, ArrayList<ZooKeeper> zooKeepers, ArrayList<Integer> animalIDsInPen) {
        super(name, length, width, temp, PenType.AQUARIUM, zooKeepers, animalIDsInPen);
        this.height = height;
        this.volume = length * width * height;
        listOfAllAquariums.add(this);
        listOfAllPens.add(this);
        penId = listOfAllPens.indexOf(this);
        writePensToJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/penData/aquariumsData.json", listOfAllAquariums);
    }

    public int getHeight() {
        return height;
    }

    @Override
    public int getCapacity() {
        return volume;
    }
}
