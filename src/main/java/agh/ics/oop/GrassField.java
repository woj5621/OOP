package agh.ics.oop;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class GrassField extends AbstractWorldMap implements IWorldMap, IPositionChangeObserver{

    protected int grassNumber;
    protected int maxWidth;
    protected int maxHeight;
    private final MapVisualizer mapVisualizer;
    //protected List<Grass> grassList = new ArrayList<>();
    LinkedHashMap<Vector2d, Grass> grassHashMap = new LinkedHashMap<>();

    public GrassField(int grassNumber){
        this.grassNumber=grassNumber;
        this.maxWidth = 10;
        this.maxHeight =5;
        this.animalMap = new Animal[maxWidth][maxHeight];
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

            Grass grass = new Grass(new Vector2d(x, y));
            grassHashMap.put(grass.getPosition(), grass);

            this.mapBoundary.xSorted.add(grass.getPosition());
            this.mapBoundary.ySorted.add(grass.getPosition());

            this.maxWidth = this.mapBoundary.xSorted.last().x;
            this.maxHeight =this.mapBoundary.ySorted.last().y;
            //grassList.add(grass);
        }


    }

    @Override
    public boolean isOccupied(Vector2d position)
    {
//        for(Grass g: grassList){
//            if (g.getPosition().equals(position)) return true;
//        }
//        return false;
        return grassHashMap.containsKey(position) || animalHashMap.containsKey(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        if(isOccupied(position))
        {


//            for(Grass g: grassList)
//            {
//                if(g.getPosition().equals(position)) return g;
//            }

            if (grassHashMap.containsKey(position)) return grassHashMap.get(position);
            else if (animalHashMap.containsKey(position)) return animalHashMap.get(position);
        }
        return null;
    }




    @Override
    public boolean canMoveTo(Vector2d position) {
        if(position.follows(new Vector2d(0, 0))){
            if(isOccupied(position) && objectAt(position) instanceof Grass) {
                this.grassHashMap.remove(position);
            }
            else return !isOccupied(position) || !(objectAt(position) instanceof Animal);
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
        return mapVisualizer.draw(new Vector2d(0, 0), new Vector2d(this.maxWidth, this.maxHeight));
    }

    public Vector2d getLowerLeft(){
        return this.mapBoundary.lowerLeft();
    }

    public Vector2d getUpperRight(){
        return this.mapBoundary.upperRight();

    }


}
