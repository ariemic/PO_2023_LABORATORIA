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
        List<MoveDirection> moveDirections = OptionsParser.parseDirections(args);
        List<Vector2d> positions = List.of(new Vector2d(3,3));
        WorldMap map = new RectangularMap(3, 3);
        Simulation simulation = new Simulation(positions, moveDirections, map);
        simulation.run();
    }

}
