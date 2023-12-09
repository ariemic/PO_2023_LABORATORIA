package agh.ics.oop.model;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;
    public Animal(Vector2d position){
        this.position = position;
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
        return this.position.equals(position);
    }
    public Vector2d getPosition() {
        return this.position;
    }

    public void move(MoveDirection direction){
        if(direction == MoveDirection.RIGHT){
            orientation = orientation.next();
        }else if(direction == MoveDirection.LEFT){
               orientation = orientation.previous();
        }else{
            Vector2d newVectorPosition = position.add(orientation.toUnitVector());
            if (direction == MoveDirection.BACKWARD){
                newVectorPosition = position.subtract(orientation.toUnitVector());
            }
            if(Math.min(newVectorPosition.getX(), newVectorPosition.getY()) < 0 || Math.max(newVectorPosition.getX(), newVectorPosition.getY()) > 4){
                return;
            }else{
                position = newVectorPosition;
            }
        }
    }

    public MapDirection getOrientation() {
        return this.orientation;
    }

}
