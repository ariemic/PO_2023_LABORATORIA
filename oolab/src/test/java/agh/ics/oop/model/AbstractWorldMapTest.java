package agh.ics.oop.model;

import agh.ics.oop.model.exceptions.PositionAlreadyOccupiedException;
import org.junit.jupiter.api.Test;

import java.util.*;

public class AbstractWorldMapTest {
    @Test
    public void testOrderedAnimals() throws PositionAlreadyOccupiedException {
        GrassField map = new GrassField(4, 1);
        Random random = new Random();

        int i=0;
        while(i < 10){
            i++;
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            Vector2d position = new Vector2d(x, y);
            if(!map.isOccupied(position)){
                map.place(new Animal(position));
            }
        }

        List<Animal> animals =  map.getOrderedAnimals();
        for(Animal animal:animals){
            System.out.println(animal.getPosition());
        }


    }
}
