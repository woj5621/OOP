package agh.ics.oop;

public class Animal {
    private Vector2d position;
    private MapDirection orientation;
    private IWorldMap map;

    public Animal() {
        this.position = new Vector2d(2, 2);
        this.orientation = MapDirection.NORTH;
    }

    public Animal(IWorldMap map){
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.position = initialPosition;
        this.orientation = MapDirection.NORTH;
    }

    @Override
    public String toString() {
        return orientation.toString();
    }

    public boolean isAt(Vector2d position){
        return this.position == position;
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> this.orientation = orientation.next();
            case LEFT -> this.orientation = orientation.previous();
            case FORWARD -> {
                Vector2d newPosition = position.add(this.orientation.toUnitVector());

                if(map.canMoveTo(newPosition))
                {

                    this.position = position.add(this.orientation.toUnitVector());
                }
            }
            case BACKWARD ->
                    {
                        Vector2d newPosition = position.subtract(this.orientation.toUnitVector());
                        if(map.canMoveTo(newPosition))
                        {
                            this.position = position.subtract(this.orientation.toUnitVector());
                        }
                    }

        }
    }

    public Vector2d getPosition(){
        return this.position;
    }

    public MapDirection getOrientation(){
        return this.orientation;
    }


}
