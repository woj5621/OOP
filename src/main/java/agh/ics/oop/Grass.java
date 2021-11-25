package agh.ics.oop;

public class Grass {
    private Vector2d position;

    public Grass(Vector2d position){
        this.position = position;
    }

    Vector2d getPosition(){
        return this.position;
    }

    @Override
    public String toString(){
        return "*";
    }


}
