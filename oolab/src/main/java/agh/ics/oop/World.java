package agh.ics.oop;

public class World {
    public static void run(String[] args){
        System.out.println("zwierzak idzie do przodu");
        for(int i = 0; i < args.length; i++){
            if(i != args.length-1) {
                System.out.print(args[i] + ", ");
            }else{
                System.out.println(args[i]);
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("system wystartował");
        run(args);
        System.out.println("system zakończył działanie");
    }

}
