package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal implements IMapElement {
    private Vector2d position;
    private MapDirection orientation;
    private AbstractWorldMap map;
    protected List<IPositionChangeObserver> observers = new ArrayList<>();

    public Animal() {
        this.position = new Vector2d(2, 2);
        this.orientation = MapDirection.NORTH;


    }

    public Animal(AbstractWorldMap map){
        this.map = map;
        this.addObserver(this.map);
    }

    public Animal(AbstractWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.position = initialPosition;
        this.orientation = MapDirection.NORTH;
        this.addObserver(this.map);
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
                if(this.map.canMoveTo(newPosition))
                {

                    this.positionChanged(this.position, newPosition);

                    this.position = newPosition;
                }
            }
            case BACKWARD ->
                    {
                        Vector2d newPosition = position.subtract(this.orientation.toUnitVector());

                        if(map.canMoveTo(newPosition))
                        {

                            this.positionChanged(this.position, newPosition);
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

    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for(IPositionChangeObserver o:observers){

            o.positionChange(oldPosition, newPosition);

        }
    }

    @Override
    public String getUrl() {
        String url = "";
        switch(this.toString()){
            case "N" -> url = "src/main/resources/up.png";
            case "E" -> url = "src/main/resources/right.png";
            case "S" -> url = "src/main/resources/down.png";
            case "W" -> url = "src/main/resources/left.png";
        }
        return url;
    }

}
