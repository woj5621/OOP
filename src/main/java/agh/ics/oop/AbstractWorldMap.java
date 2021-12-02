package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{

    protected  int width;
    protected  int height;
   // protected List<Animal> animalList = new ArrayList<>();
    //protected List<Grass> grassList = new ArrayList<>();
//    protected HashMap<Vector2d, Animal> animals = new HashMap<>();
//    protected HashMap<Vector2d, Grass> grassFields = new HashMap<>();
    public Animal[][] animalMap;
    LinkedHashMap<Vector2d, Animal> animalHashMap = new LinkedHashMap<>();

    @Override
    public boolean canMoveTo(Vector2d position){
        return !(objectAt(position) instanceof Animal);
    }



    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())){

            //animalMap[animal.getPosition().x][animal.getPosition().y] = animal;

            animalHashMap.put(animal.getPosition(), animal);

            return true;
        }
        return false;
    }



    @Override
    public boolean isOccupied(Vector2d position)
    {
//        for(Animal a: animalList){
//
//            if (a.getPosition().equals(position)){
//
//                return true;
//            }
//        }
        return this.animalHashMap.get(position)!=null;

    }

    @Override
    public Object objectAt(Vector2d position) {
        if(isOccupied(position))
        {
//           for(Animal a: animalList)
//            {
//                if(a.getPosition().equals(position)) return a;
//            }
            return this.animalHashMap.get(position);




        }
    return null;
    }

    @Override
    public String toString(){
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        return mapVisualizer.draw(new Vector2d(0, 0), new Vector2d(this.width, this.height));
    }

    @Override
    public void positionChange(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = animalHashMap.remove(oldPosition);
        animalHashMap.put(newPosition, animal);
    }


}
