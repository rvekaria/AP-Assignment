package zoo;

import java.util.ArrayList;

public class Aquarium extends Pen {
    private int height;
    private int volume;


    public Aquarium(String name, int length, int width, int height, int temp, ArrayList<ZooKeeper> zooKeepers, ArrayList<Integer> animalIDsInPen) {
        super(name, length, width, temp, PenType.AQUARIUM, zooKeepers, animalIDsInPen);
        this.height = height;
        this.volume = length * width * height;
        getListOfAllAquariums().add(this);
        getListOfAllPens().add(this);
        penId = getListOfAllPens().indexOf(this);
        updateKeepersAssignedPens(zooKeepers);
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String displayInfo() {
        return String.format("name: %s, volume: %s, remaining volum: %s, temp: %s, zookeepers: %s, no. of animals: %s", getName(), volume, getRemainingSpace(), getTemp(), getKeeperNames(), getAnimalIDsInPen().size());
    }

    @Override
    public int getCapacity() {
        return volume;
    }
}
