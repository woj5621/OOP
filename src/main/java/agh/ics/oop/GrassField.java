package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class GrassField extends AbstractWorldMap implements IWorldMap{

    protected int grassNumber;
    protected int max_width;
    protected int max_height;
    private final MapVisualizer mapVisualizer;


    public GrassField(int grassNumber){
        this.grassNumber=grassNumber;
        this.max_width = 10;
        this.max_height = 5;
        this.animalMap = new Animal[max_width][max_height];
        mapVisualizer = new MapVisualizer(this);

        placeGrass();
    }

    private void placeGrass()
    {

        for(int i=0; i<this.grassNumber; i++)
        {
            int x = (int)(Math.random()*(Math.sqrt(this.grassNumber*10)));
            int y = (int)(Math.random()*(Math.sqrt(this.grassNumber*10)));
            while(isOccupied(new Vector2d(x, y)))
            {
                x = (int)(Math.random()*(Math.sqrt(this.grassNumber*10)));
                y = (int)(Math.random()*(Math.sqrt(this.grassNumber*10)));
            }
            this.max_width = Math.max(this.max_width, x);
            this.max_height = Math.max(this.max_height, y);


            Grass grass = new Grass(new Vector2d(x, y));
            grassList.add(grass);
        }


    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        if(position.follows(new Vector2d(0, 0))){
            if(isOccupied(position) && objectAt(position) instanceof Grass) {
                if (position.precedes(new Vector2d(max_width - 1, max_height - 1))) {
                    if (position.x > max_width) max_width = position.x;
                    if (position.y > max_height) max_height = position.y;
                }
            }
            else return false;
            return true;
        }
        return false;

    }

//    @Override
//    public boolean place(Animal animal) {
//
//        return false;
//    }
//
//    @Override
//    public boolean isOccupied(Vector2d position) {
//        return false;
//    }
//
//    @Override
//    public Object objectAt(Vector2d position) {
//        return null;
//    }

    @Override
    public String toString(){
        return mapVisualizer.draw(new Vector2d(0, 0), new Vector2d(this.max_width, this.max_height));
    }
}
