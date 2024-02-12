package agh.ics.oop.model;

import agh.ics.oop.model.util.PositionsGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PositionsGeneratorTest {
    @Test
    public void TestConstructor(){
        PositionsGenerator positions = new PositionsGenerator(5,5,3);
        int counter=0;
        for(Vector2d position : positions){
            counter++;
        }
        Assertions.assertEquals(3,counter);


        PositionsGenerator nextPositions = new PositionsGenerator(2,4,20);
        int counter2=0;
        for(Vector2d position : nextPositions){
            counter2++;
        }
        Assertions.assertEquals(15,counter2);
    }
}
