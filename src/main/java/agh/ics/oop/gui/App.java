package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;



public class App extends Application {

    private AbstractWorldMap map;
    private GridPane grid;
    private final int width = 20;
    private final int height = 20;

    @Override
    public void start(Stage primaryStage) throws Exception {
        draw();
        Scene scene = new Scene(grid, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void init(){
        try{
            String[] args = getParameters().getRaw().toArray(String[]::new);
            MoveDirection[] directions = new OptionsParser().parse(args);
            this.map = new GrassField(10);
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
            System.out.println(map);
        } catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
            System.exit(0);
        }
    }

    private void setGridConstraints(){
        Vector2d upperRight = this.map.getUpperRight();
        Vector2d lowerLeft = this.map.getLowerLeft();

        for(int i=upperRight.x+1; i>=lowerLeft.x; i--)
            this.grid.getColumnConstraints().add(new ColumnConstraints(width));

        for(int j=upperRight.y+1; j>=lowerLeft.y; j--)
            this.grid.getRowConstraints().add(new RowConstraints(height));

    }

    private void drawObject(){
        Vector2d upperRight = this.map.getUpperRight();
        Vector2d lowerLeft = this.map.getLowerLeft();

        for(int i=upperRight.x; i>=lowerLeft.x; i--){
            for(int j=upperRight.y; j>=lowerLeft.y; j--){
                if(this.map.isOccupied(new Vector2d(i, j))){
                    Label l = new Label(this.map.objectAt(new Vector2d(i, j)).toString());
                    this.grid.add(l, i-lowerLeft.x+1,upperRight.y-j+1);
                    GridPane.setHalignment(l, HPos.CENTER);
                }
                else{
                    Label l = new Label("  ");
                    this.grid.add(l, i-lowerLeft.x+1,upperRight.y-j+1);
                    GridPane.setHalignment(l,HPos.CENTER);
                }
            }
        }
    }

    private void drawCoordinates(){
        Vector2d upperRight = this.map.getUpperRight();
        Vector2d lowerLeft = this.map.getLowerLeft();

        for(int i=upperRight.y; i>=lowerLeft.y; i--){
            Label l = new Label(""+i);
            this.grid.add(l, 0, upperRight.y-i+1);
            GridPane.setHalignment(l, HPos.CENTER);
        }

        for(int i=upperRight.x; i>=lowerLeft.x; i--){
            Label l = new Label(""+i);
            this.grid.add(l,i-lowerLeft.x+1, 0);
            GridPane.setHalignment(l, HPos.CENTER);
        }
    }

    public void draw(){
        this.grid = new GridPane();
        Label label = new Label("y/x");
        grid.add(label, 0,0);
        GridPane.setHalignment(label, HPos.CENTER);

        drawCoordinates();

        drawObject();

        setGridConstraints();

        this.grid.setGridLinesVisible(true);

    }
}
