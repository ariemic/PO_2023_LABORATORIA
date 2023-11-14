package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

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
        System.out.println("Start");
//        MoveDirection[] directions = OptionsParser.parseDirections(args).toArray(new MoveDirection[0]);
//        run(directions);
        System.out.println("Stop");
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));

        MapDirection direction = MapDirection.NORTH;
        System.out.println(direction.previous());
        System.out.println(direction.next());
        System.out.println(direction.toUnitVector());

        Animal animal = new Animal(position1);
        System.out.printf("animal position is %s%n", animal.getPosition());
        animal.move(MoveDirection.BACKWARD);
        System.out.printf("animal position is %s%n",animal.getPosition());

        List<MoveDirection> directions = OptionsParser.parseDirections(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        Simulation simulation = new Simulation(positions, directions);
        simulation.run();

    }

}
