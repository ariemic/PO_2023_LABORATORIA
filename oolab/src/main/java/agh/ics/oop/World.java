package agh.ics.oop;

public class World {
    public static void run(String[] args){
        System.out.println("Start");

        for(String arg: args){
            String message = switch(arg){
                case "f" -> "Zwierzak idzie do przodu";
                case "b" -> "Zwierzak idzie do tyłu";
                case "r" -> "Zwierzak skręca w prawo";
                case "l" -> "Zwierzak skręca w lewo";
                default -> "";
            };
            if (!message.isEmpty()){
                System.out.println(message);
            }
        }
        System.out.println("Stop");
    }

    public static void main(String[] args) {
        System.out.println("system wystartował");
        run(args);
        System.out.println("system zakończył działanie");
    }

}
