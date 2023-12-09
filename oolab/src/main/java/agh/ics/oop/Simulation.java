package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.enums.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.interfaces.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<MoveDirection> moves;
    private final List<Animal> animals = new ArrayList<Animal>();
    WorldMap map;
    public Simulation(List<Vector2d> animalPositions, List<MoveDirection> moves, WorldMap map){
        this.moves = moves;
        this.map = map;
        //create new animals on animalPositions
        for(Vector2d position: animalPositions){
            animals.add(new Animal(position));
        }
    }

    public void run(){
        int i=0;
        int animalCnt = animals.size();
        for (Animal animal: animals){
            map.place(animal);
        }
        System.out.println(map);
        for (MoveDirection move: moves){
            map.move(animals.get(i%animalCnt), move);
            System.out.println(map);
            i++;
        }

    }

    public List<MoveDirection> getMoves() {
        return moves;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

}
