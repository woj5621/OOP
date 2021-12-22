package agh.ics.oop;

import agh.ics.oop.gui.IAnimalChangeObserver;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine, Runnable{
    public MoveDirection[] moveDirections;
    public AbstractWorldMap map;
    public Vector2d[] positions;
    public List<Animal> animalList = new ArrayList<>();
    public int moveDelay;
    protected List<IAnimalChangeObserver> observers = new ArrayList<>();

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

    public SimulationEngine(AbstractWorldMap map, Vector2d[] positions){

        this.map= map;
        this.positions = positions;

        for(int i=0; i<positions.length; i++){
            Animal animal = new Animal(this.map, this.positions[i]);
            map.place(animal);
            animalList.add(animal);
        }
    }

    public void setMoveDelay(int moveDelay){
        this.moveDelay=moveDelay;
    }

    public void addToObservers(IAnimalChangeObserver observer){
        observers.add(observer);
    }

    public void setMoveDirections(MoveDirection[] moveDirections){
        this.moveDirections = moveDirections;
    }

    @Override
    public void run() {
        int i=0;
        int j=0;
        while(i<this.moveDirections.length){

            Animal a = animalList.get(j);
            a.move(this.moveDirections[i]);

            for(IAnimalChangeObserver o: observers){
                o.updateAnimalsOnMap();
            }

            try{
                Thread.sleep(moveDelay);
            }catch (InterruptedException exception){
                System.out.println(exception.getMessage());
            }
            j++;
            if(j==animalList.size()) j=0;
            i++;

        }
    }
}
