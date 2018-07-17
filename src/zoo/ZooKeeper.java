package zoo;

import java.util.ArrayList;

public class ZooKeeper {
    private String name;
    private ArrayList<Pen.PenType> pensTrainedFor;
    private ArrayList<Pen> assignedPens;
    private static ArrayList<ZooKeeper> listOfAllZooKeepers = new ArrayList<>();

    public ZooKeeper(String name, ArrayList<Pen.PenType> pensTrainedFor, ArrayList<Pen> assignedPens) {
        this.name = name;
        this.pensTrainedFor = pensTrainedFor;
        this.assignedPens = assignedPens;
        listOfAllZooKeepers.add(this);
    }

    public String getName() {
        return name;
    }

    public static ArrayList<ZooKeeper> getListOfAllZooKeepers() {
        return listOfAllZooKeepers;
    }

    public boolean isTrainedFor(Pen.PenType penType) {
        if (pensTrainedFor.contains(penType)) {
            return true;
        } else
            return false;
    }

    public ArrayList<Pen> getAssignedPens() {
        return assignedPens;
    }

    public void addToAssignedPens(Pen pen) {
        assignedPens.add(pen);
    }
}