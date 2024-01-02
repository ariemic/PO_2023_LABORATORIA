package agh.ics.oop.model;

import agh.ics.oop.model.enums.MapDirection;
import agh.ics.oop.model.enums.MoveDirection;
import agh.ics.oop.model.interfaces.MoveValidator;
import agh.ics.oop.model.interfaces.WorldElement;

import java.util.Objects;

public class Animal implements WorldElement {
    private MapDirection orientation;
    private Vector2d position;


    public Animal(Vector2d position, MapDirection orientation){
        this.position = position;
        this.orientation = orientation;
    }

    public Animal(Vector2d position){
        this(new Vector2d(position.getX(), position.getY()), MapDirection.NORTH);
    }

    public Animal(){
        this(new Vector2d(2,2));
    }

    @Override
    public String toString(){
        return switch(orientation){
            case NORTH -> "N";
            case WEST -> "W";
            case EAST -> "E";
            case SOUTH -> "S";
        };
    }
    @Override
    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }
    @Override
    public Vector2d getPosition() {
        return this.position;
    }


    public void move(MoveDirection direction, MoveValidator validator){
        if(direction == MoveDirection.RIGHT){
            orientation = orientation.next();
        }else if(direction == MoveDirection.LEFT){
               orientation = orientation.previous();
        }else{
            Vector2d newVectorPosition;
            if (direction == MoveDirection.FORWARD) {
                newVectorPosition = position.add(orientation.toUnitVector());
                if(validator.canMoveTo(newVectorPosition)) {
                    position = newVectorPosition;
                }
            }
            if (direction == MoveDirection.BACKWARD){
                newVectorPosition = position.subtract(orientation.toUnitVector());
                if(validator.canMoveTo(newVectorPosition)) {
                    position = newVectorPosition;
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return position == animal.getPosition() && orientation == animal.getOrientation();
    }

    @Override
    public int hashCode() {
        return Objects.hash(orientation, position);
    }

    public MapDirection getOrientation() {
        return this.orientation;
    }

}
