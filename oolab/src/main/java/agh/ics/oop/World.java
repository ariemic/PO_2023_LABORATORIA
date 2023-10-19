package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

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
        MoveDirection[] directions = OptionsParser.parseDirections(args);
        run(directions);
        System.out.println("Stop");
    }

}
