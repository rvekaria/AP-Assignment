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
    private static ArrayList<ZooKeeper> listOfAllZooKeepers = new ArrayList<>();

    public ZooKeeper(String name, ArrayList<Pen.PenType> pensTrainedFor, ArrayList<Integer> assignedPenIds) {
        this.name = name;
        this.pensTrainedFor = pensTrainedFor;
        this.assignedPenIds = assignedPenIds;
        listOfAllZooKeepers.add(this);
        addKeeperToPensListOfKeepers();
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
        ArrayList<Pen> assignedPens = new ArrayList<>();
        for (int penId : assignedPenIds){
            assignedPens.add(Pen.getListOfAllPens().get(penId));
        }
        return assignedPens;
    }

    public void addToAssignedPenIds(Pen pen) {
        assignedPenIds.add(pen.getPenId());
    }

    private void addKeeperToPensListOfKeepers(){
        for (int penId : assignedPenIds){
            Pen.getListOfAllPens().get(penId).assignZooKeeper(this);
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

    public static void instantiateKeepersFromJsonFile(String filePath) {
        //String filePath = "/Users/rupesh.vekaria/AP-Assignment/src/test/zooKeeper/resources/testKeeperData.json";
        File keepersJsonFile = new File(filePath);
        Gson jsonConverter = new Gson();

        try {
            String keepersListJsonString = new String(Files.readAllBytes(keepersJsonFile.toPath()));
            listOfAllZooKeepers = jsonConverter.fromJson(keepersListJsonString, new TypeToken<List<Pen>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}