package agh.ics.oop;

import agh.ics.oop.model.enums.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {
    public static List<MoveDirection> parseDirections(String[] args) throws IllegalArgumentException {
        List<MoveDirection> directions = new ArrayList<MoveDirection>();
        for (String arg : args) {
            switch (arg) {
                case "f" -> {
                    directions.add(MoveDirection.FORWARD);
                }
                case "b" -> {
                    directions.add(MoveDirection.BACKWARD);
                }
                case "r" -> {
                    directions.add(MoveDirection.RIGHT);
                }
                case "l" -> {
                    directions.add(MoveDirection.LEFT);
                }
            }
        }
        System.out.println(directions);
        return directions;
    }
}
