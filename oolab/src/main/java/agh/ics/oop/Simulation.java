package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.enums.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.exceptions.PositionAlreadyOccupiedException;
import agh.ics.oop.model.interfaces.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<MoveDirection> moves;
    private final List<Animal> animals = new ArrayList<Animal>();
    private final WorldMap map;
    public Simulation(List<Vector2d> animalPositions, List<MoveDirection> moves, WorldMap map){
        this.moves = moves;
        this.map = map;
        //create new animals on animalPositions
        for(Vector2d position: animalPositions){
            animals.add(new Animal(position));
        }
    }

    public void run() throws PositionAlreadyOccupiedException {
        int i=0;
        int animalCnt = animals.size();
        for (Animal animal: animals){
            try{
                map.place(animal);
            }
            catch (PositionAlreadyOccupiedException e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println(map);
        for (MoveDirection move: moves){
            map.move(animals.get(i%animalCnt), move);
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
