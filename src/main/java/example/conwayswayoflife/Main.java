package example.conwayswayoflife;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    public static Group root;
    public static Scene scene;
    public static Stage primaryStage;
    public static Logic logic;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        root = new Group();
        scene = new Scene(root);
        setUpStage();
        logic = new Logic(100,1500,1000, setInitialFields());
        setUpField(logic.getRectangles());
    }

    public static void main(String[] args) {
        launch();
    }

    private static void setUpStage(){
        primaryStage.show();
        primaryStage.setTitle("Conways way of life");
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, (event) -> primaryStageKeyPressed(event.getCode()));
        scene.setRoot(root);
        primaryStage.setWidth(1500);
        primaryStage.setHeight(1000);
        primaryStage.setScene(scene);
    }

    private static List<Point2D> setInitialFields(){
        List<Point2D> initialPositions = new ArrayList<>();
        initialPositions.add(new Point2D(15,20));
        initialPositions.add(new Point2D(15,21));
        initialPositions.add(new Point2D(15,22));
        return  initialPositions;
    }

    private static void setUpField(Rectangle[][] rectangle){
        for (int i = 0; i < rectangle.length; i++) {
            for (int j = 0; j < rectangle[i].length; j++) {
                root.getChildren().add(rectangle[i][j]);
            }
        }
    }
    public static void primaryStageKeyPressed(KeyCode keyCode){
        if (keyCode == KeyCode.ENTER){
            logic.turnControl();
        }
    }
}