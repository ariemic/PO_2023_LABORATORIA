package agh.ics.oop.model.util;

import agh.ics.oop.model.interfaces.MapChangeListener;
import agh.ics.oop.model.interfaces.WorldMap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



public class FileMapDisplay {


    public void mapChanged(WorldMap worldMap, String message) throws IOException {
//     nazwa pliku map_id.log, dopisuje message na koniec pliku
        System.out.print(worldMap);

        String fileName = "map_" + worldMap.getId() + ".log";
        File file = new File(fileName);
        file.createNewFile();
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
            writer.write(message);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
