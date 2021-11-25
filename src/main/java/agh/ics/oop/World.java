package agh.ics.oop;

import java.util.Map;
import java.util.Optional;


public class World {

    public static void main(String[] args){


//        Vector2d position1 = new Vector2d(1,2);
//        System.out.println(position1);
//        Vector2d position2 = new Vector2d(-2,1);
//        System.out.println(position2);
//        System.out.println(position1.add(position2));

//        System.out.println(MapDirection.WEST.next());
//        System.out.println(MapDirection.WEST.previous());
//
//        System.out.println(new Vector2d(5,5).toString());

//        Animal animal = new Animal();
//        System.out.println(animal.toString());

        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        System.out.println(map);



//        System.out.println("System wystartował");
//
////        String[] moves = new String[2];
////        moves[0] = "l";
////        moves[1] = "r";
//        MoveDirection[] directions = new MoveDirection[args.length];
//
//        for(int i=0; i <args.length; i++){
//
//            switch(args[i]){
//                case "f" -> {
//                    directions[i] = MoveDirection.FORWARD;
//                }
//                case "b" -> {
//                    directions[i] = MoveDirection.BACKWARD;
//                }
//                case "r" -> {
//                    directions[i] = MoveDirection.RIGHT;
//                }
//                case "l" -> {
//                    directions[i] = MoveDirection.LEFT;
//                }
//            }
//
//        }
//        OptionsParser parser = new OptionsParser();
//        MoveDirection[] directions = parser.parse(args);
//
//        for(MoveDirection d : directions){
//            animal.move(d);
//        }
//        System.out.println(animal.toString());
//
//
//        run(directions);
//
//        System.out.println("System zakończył działanie");
    }

//    public static void run(Direction[] directions){
//
//
//        for (Direction direction : directions) {
////            if(i != moves.length-1) System.out.println(moves[i] + ",");
////            else System.out.println(moves[i]);
//            String message = switch (direction) {
//                case FORWARD -> "Do przodu";
//                case BACKWARD -> "Do tylu";
//                case RIGHT -> "W prawo";
//                case LEFT -> "W lewo";
//            };
//            System.out.println(message);
//        }
//    }
}
