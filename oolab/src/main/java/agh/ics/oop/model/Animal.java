package agh.ics.oop.model;

public class Animal {
    private MapDirection orientation;
    private Vector2d vector;
    public Animal(Vector2d vector){
        this.vector = vector;
        this.orientation = MapDirection.NORTH;
    }

    public Animal(){
        this(new Vector2d(2,2));
    }

    @Override
    public String toString(){
        return super.toString();
    }

    public boolean isAt(Vector2d position){
        return vector.equals(position);
    }
    public Vector2d getVector() {
        return vector;
    }

    public void move(MoveDirection direction){
        if(direction == MoveDirection.RIGHT){
            orientation = orientation.next();
        }else if(direction == MoveDirection.LEFT){
               orientation = orientation.previous();
        }else{
            Vector2d newVectorPosition = vector.add(orientation.toUnitVector());
            if (direction == MoveDirection.BACKWARD){
                newVectorPosition = vector.subtract(orientation.toUnitVector());
            }
            if(Math.min(newVectorPosition.getX(), newVectorPosition.getY()) < 0 || Math.max(newVectorPosition.getX(), newVectorPosition.getY()) > 4){
                return;
            }else{
                vector = newVectorPosition;
            }
        }
    }



}
