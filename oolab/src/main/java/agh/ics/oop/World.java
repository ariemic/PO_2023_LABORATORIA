package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;

public class World {

    public static void run(MoveDirection[] args){
        for(MoveDirection arg: args){
            String message = switch (arg){
                case FORWARD -> "Zwierzak idzie do przodu";
                case BACKWARD -> "Zwierzak idzie do tyłu";
                case LEFT -> "Zwierzak skręca w lewo";
                case RIGHT -> "Zwierzak skręca w prawo";
            };
            System.out.println(message);
        }
    }

    public static void main(String[] args) {
        List<MoveDirection> directions = OptionsParser.parseDirections(args);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        WorldMap map = new RectangularMap(4,4);
        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();
    }

}
