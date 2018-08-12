package zoo;

import java.util.ArrayList;
import java.util.Objects;

public class ZooKeeper {
    private String name;
    private ArrayList<Pen.PenType> pensTrainedFor;
    private ArrayList<Integer> assignedPenIds;
    private static ArrayList<ZooKeeper> listOfAllZooKeepers = new ArrayList<>();

    public ZooKeeper(String name, ArrayList<Pen.PenType> pensTrainedFor, ArrayList<Integer> assignedPenIds) {
        this.name = name;
        this.pensTrainedFor = pensTrainedFor;
        this.assignedPenIds = assignedPenIds;
        getListOfAllZooKeepers().add(this);
        addKeeperToPensListOfKeepers();
    }

    public String getName() {
        return name;
    }

    public static ArrayList<ZooKeeper> getListOfAllZooKeepers() {
        return listOfAllZooKeepers;
    }

    public static void setListOfAllZooKeepers(ArrayList<ZooKeeper> listOfAllZooKeepers) {
         ZooKeeper.listOfAllZooKeepers = listOfAllZooKeepers;
    }

    public boolean isTrainedFor(Pen.PenType penType) {
        if (pensTrainedFor.contains(penType)) {
            return true;
        } else
            return false;
    }

    public ArrayList<Integer> getAssignedPenIds() {
        return assignedPenIds;
    }

    private void addKeeperToPensListOfKeepers() {
        for (int penId : assignedPenIds) {
            Pen.getListOfAllPens().get(penId).assignZooKeeper(this);
        }
    }

    void addPenToAssignedPens(int penId) {
        if (!assignedPenIds.contains(penId)) {
            assignedPenIds.add(penId);
        }
    }

    public String toString() {
        String string = String.format("name: %s, trained for: %s, assigned pens: %s", name, pensTrainedFor, assignedPenIds);
        return string;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZooKeeper keeper = (ZooKeeper) o;
        return Objects.equals(name, keeper.name) &&
                Objects.equals(pensTrainedFor, keeper.pensTrainedFor) &&
                Objects.equals(assignedPenIds, keeper.assignedPenIds);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, pensTrainedFor, assignedPenIds);
    }
}