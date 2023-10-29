package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class OptionsParser {
    public static MoveDirection[] parseDirections(String[] args){
        int leng = 0;
        for(String x: args){
            if (x.equals("f") || x.equals("b") || x.equals("r") || x.equals("l")){
                leng ++;
            }
        }
        MoveDirection[] directions = new MoveDirection[leng];
        int j = 0;
        for(String arg: args){
            switch (arg){
                case "f" -> {
                    directions[j] = MoveDirection.FORWARD;
                    j++;
                }
                case "b" -> {
                    directions[j] = MoveDirection.BACKWARD;
                    j++;
                }
                case "r" -> {
                    directions[j] = MoveDirection.RIGHT;
                    j++;
                }
                case "l" -> {
                    directions[j] = MoveDirection.LEFT;
                    j++;
                }
            }
        }
//        System.out.println(Arrays.toString(directions));
        return directions;

    }
}
