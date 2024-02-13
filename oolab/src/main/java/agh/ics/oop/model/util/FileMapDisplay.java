package agh.ics.oop.model.util;

import agh.ics.oop.model.interfaces.MapChangeListener;
import agh.ics.oop.model.interfaces.WorldMap;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;



public class FileMapDisplay implements MapChangeListener {

    public void mapChanged(WorldMap worldMap, String message) {
        String path = "D:\\PO_2023_SR1640_MICHALIK\\oolab\\src\\main\\" + "map_" + worldMap.getId() + ".log";
        //todo jak BurfferWriter działa???
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))){
            writer.write(message);
            writer.newLine();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
