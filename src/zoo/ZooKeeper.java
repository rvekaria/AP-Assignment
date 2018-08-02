package zoo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ZooKeeper {
    private String name;
    private ArrayList<Pen.PenType> pensTrainedFor;
    private ArrayList<Integer> assignedPenIds;
    static ArrayList<ZooKeeper> listOfAllZooKeepers = new ArrayList<>();

    public ZooKeeper(String name, ArrayList<Pen.PenType> pensTrainedFor, ArrayList<Integer> assignedPenIds) {
        this.name = name;
        this.pensTrainedFor = pensTrainedFor;
        this.assignedPenIds = assignedPenIds;
        listOfAllZooKeepers.add(this);
        addKeeperToPensListOfKeepers();
        writeKeepersToJsonFile("/Users/rupesh.vekaria/AP-Assignment/src/zoo/data/zooKeeperData/keeperData.json");
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

    public static void writeKeepersToJsonFile(String filePath) {
        //String filePath = "/Users/rupesh.vekaria/AP-Assignment/src/test/zooKeeper/resources/testKeeperData.json";
        File keepersJsonFile = new File(filePath);
        Gson jsonConverter = new Gson();

        try {
            PrintWriter writer = new PrintWriter(keepersJsonFile);
            writer.print(jsonConverter.toJson(listOfAllZooKeepers));
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<ZooKeeper> instantiateKeepersFromJsonFile(String filePath) {
        //String filePath = "/Users/rupesh.vekaria/AP-Assignment/src/test/zooKeeper/resources/testKeeperData.json";
        File keepersJsonFile = new File(filePath);
        Gson jsonConverter = new Gson();
        ArrayList<ZooKeeper> keepersFromFile = new ArrayList<>();
        try {
            String keepersListJsonString = new String(Files.readAllBytes(keepersJsonFile.toPath()));
            keepersFromFile = jsonConverter.fromJson(keepersListJsonString, new TypeToken<List<ZooKeeper>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return keepersFromFile;
    }

    public String toString() {
        String string = String.format("name: %s, trained for: %s, assigned pens: %s", name, pensTrainedFor, assignedPenIds);
        return string;
    }
}