package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap{

    protected  int width;
    protected  int height;
    protected List<Animal> animalList = new ArrayList<>();
    protected List<Grass> grassList = new ArrayList<>();
//    protected HashMap<Vector2d, Animal> animals = new HashMap<>();
//    protected HashMap<Vector2d, Grass> grassFields = new HashMap<>();
    public Animal[][] animalMap;


    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (animal.getPosition() != null && animalMap[animal.getPosition().x][animal.getPosition().y] == null){
            animalMap[animal.getPosition().x][animal.getPosition().y] = animal;
            if(!animalList.contains(animal)) animalList.add(animal);
            return true;
        }
        return false;
    }



    @Override
    public boolean isOccupied(Vector2d position)
    {
        for(Animal a: animalList){

            if (a.getPosition().equals(position)) return true;
        }

        for(Grass g: grassList){
            if (g.getPosition().equals(position)) return true;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        if(isOccupied(position))
        {
            for(Animal a: animalList)
            {
                if(a.getPosition().equals(position)) return a;
            }

            for(Grass g: grassList)
            {
                if(g.getPosition().equals(position)) return g;
            }


        }
        return null;
    }

    @Override
    public String toString(){
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        return mapVisualizer.draw(new Vector2d(0, 0), new Vector2d(this.width, this.height));
    }


}
