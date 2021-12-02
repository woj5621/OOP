package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MapTest {
    @Test
    public void placeTest(){

        AbstractWorldMap map = new RectangularMap(10, 5);
        Animal animal = new Animal(map, new Vector2d(2,3));
        map.place(animal);
        assertTrue(map.isOccupied(animal.getPosition()));
    }

    @Test
    public void engineTest(){
        String[] args ={"f", "b", "r", "l", "f", "f", "r", "r","f",  "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        AbstractWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        assertTrue(map.isOccupied(new Vector2d(1,4)));
        assertTrue(map.isOccupied(new Vector2d(4,0)));
    }


}
