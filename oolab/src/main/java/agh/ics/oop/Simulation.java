package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private List<MoveDirection> moves;
    private List<Animal> animals = new ArrayList<Animal>();

    public List<MoveDirection> getMoves() {
        return moves;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public Simulation(List<Vector2d> animalPositions, List<MoveDirection> moves){
        this.moves = moves;
        //create new animals on animalPositions
        for(Vector2d position: animalPositions){
            animals.add(new Animal(position));
        }
    }

    public void run(){
        int i=0;
        int animalCnt = animals.size();
        for (MoveDirection move: moves){
            animals.get(i%animalCnt).move(move);
            System.out.printf("Zwięrzę %d : %s%n", i%animalCnt, animals.get(i%animalCnt).getPosition().toString());
            i++;
        }

    }

}
