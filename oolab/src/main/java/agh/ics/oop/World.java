package agh.ics.oop;

public class World {
    public static void run(String[] args){
        System.out.println("Start");
//        for(int i=0; i < args.length; i++){
//            String message = args[i];
//            switch(message){
//                case "f" -> System.out.println("Zwierzak idzie do przodu");
//                case "b" -> System.out.println("Zwierzak idzie do tyłu");
//                case "r" -> System.out.println("Zwierzak skręca w prawo");
//                case "l" -> System.out.println("Zwierzak skręca w lewo");
//            }
//        }
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
