package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;


public class App extends Application implements IAnimalChangeObserver{

    private AbstractWorldMap map;
    private GridPane grid;
    private SimulationEngine engine;
    private final int width = 40;
    private final int height = 40;
    private final int moveDelay = 300;

    @Override
    public void start(Stage primaryStage) throws Exception {
        TextField textField = new TextField();
        Button button = new Button("Start");
        VBox vBox = new VBox(textField, button);
        VBox box = new VBox(grid, vBox);

        button.setOnAction(event -> {
            String[] args = textField.getText().split(" ");
            MoveDirection[] directions = new OptionsParser().parse(args);
            engine.setMoveDirections(directions);
            Thread engineThread = new Thread(engine);
            engineThread.start();

        });

        draw();
        setGridConstraints();
        Scene scene = new Scene(box, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void init(){
        try{
            this.map = new GrassField(10);
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
            engine = new SimulationEngine(map, positions);

            engine.setMoveDelay(moveDelay);
            engine.addToObservers(this);
            this.grid = new GridPane();
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

    private void drawObject() throws FileNotFoundException {
        Vector2d upperRight = this.map.getUpperRight();
        Vector2d lowerLeft = this.map.getLowerLeft();

        for(int i=upperRight.x; i>=lowerLeft.x; i--){
            for(int j=upperRight.y; j>=lowerLeft.y; j--){
                if(this.map.isOccupied(new Vector2d(i, j))){
                    GuiElementBox guiElementBox = new GuiElementBox((IMapElement) this.map.objectAt(new Vector2d(i, j)));
                    Label l = new Label(this.map.objectAt(new Vector2d(i, j)).toString());
                    this.grid.add(guiElementBox.vbox, i-lowerLeft.x+1,upperRight.y-j+1);
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

    public void draw() throws FileNotFoundException {
       // this.grid = new GridPane();
        Label label = new Label("y/x");
        grid.add(label, 0,0);
        GridPane.setHalignment(label, HPos.CENTER);

        drawCoordinates();

        drawObject();


        grid.setGridLinesVisible(false);
        grid.setGridLinesVisible(true);

    }

    @Override
    public void updateAnimalsOnMap() {
        Platform.runLater(() ->{
            this.grid.getChildren().clear();
            try {
                draw();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
