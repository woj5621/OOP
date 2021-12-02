package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine{
    public MoveDirection[] moveDirections;
    public AbstractWorldMap map;
    public Vector2d[] positions;
    public List<Animal> animalList = new ArrayList<>();

    public SimulationEngine(MoveDirection[] moveDirections, AbstractWorldMap map, Vector2d[] positions){
        this.moveDirections = moveDirections;
        this.map= map;
        this.positions = positions;

        for(int i=0; i<positions.length; i++){
            Animal animal = new Animal(this.map, this.positions[i]);
            map.place(animal);
            animalList.add(animal);
        }
    }

    @Override
    public void run() {
        int i=0;
        int j=0;
        while(i<this.moveDirections.length){

            Animal a = animalList.get(j);
            a.move(this.moveDirections[i]);
            j++;
            if(j==animalList.size()) j=0;
            i++;

        }
    }
}
