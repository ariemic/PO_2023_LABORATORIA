package agh.ics.oop.model;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;


    public Animal(Vector2d position, MapDirection orientation){
        this.position = position;
        this.orientation = orientation;
    }

    public Animal(Vector2d position){
        this(new Vector2d(2,2), MapDirection.NORTH);
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

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }
    public Vector2d getPosition() {
        return this.position;
    }


    public void move(MoveDirection direction, MoveValidator validator){
        if(direction == MoveDirection.RIGHT){
            orientation = orientation.next();
        }else if(direction == MoveDirection.LEFT){
               orientation = orientation.previous();
        }else{
            Vector2d newVectorPosition = position.add(orientation.toUnitVector());
            if (direction == MoveDirection.BACKWARD){
                newVectorPosition = position.subtract(orientation.toUnitVector());
            }
//            if(Math.min(newVectorPosition.getX(), newVectorPosition.getY()) < 0 || Math.max(newVectorPosition.getX(), newVectorPosition.getY()) > 4){
//                return;
            if(validator.canMoveTo(newVectorPosition)) {
                position = newVectorPosition;
            }
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return position == animal.getPosition() && orientation == animal.getOrientation();
    }

    public MapDirection getOrientation() {
        return this.orientation;
    }

}
