package example.conwayswayoflife;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Logic {
    private int fieldSize, windowWidth, windowHeight;
    private List<Point2D> setLocations;
    private List<PossibleField> possibleFields;

    Rectangle[][] rectangles;
    public Logic(int fieldSize, int windowWidth, int windowHeight, List<Point2D> initialPositions) {
        this.fieldSize = fieldSize;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        setUpRectangles();
        setLocations = initialPositions;
        for (int i = 0; i < setLocations.size(); i++) {
            rectangles[(int)setLocations.get(i).getX()][(int)setLocations.get(i).getY()].setFill(Color.BLACK);
        }
    }

    private void setUpRectangles(){
        rectangles = new Rectangle[fieldSize][fieldSize];
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                rectangles[i][j] = new Rectangle(windowWidth / fieldSize * i,windowHeight / fieldSize * j,windowWidth / fieldSize,windowHeight / fieldSize);
                rectangles[i][j].setStroke(Color.BLACK);
                rectangles[i][j].setFill(Color.WHITE);
                rectangles[i][j].setStrokeWidth(1);
            }
        }
    }

    private void findAllPointsToCheck(){
        possibleFields = new ArrayList<>();
        for (int i = 0; i < setLocations.size(); i++) {
            for (int j = -1; j < 2; j++) {
                for (int k = -1; k < 2; k++) {
                    Point2D point2D = new Point2D(setLocations.get(i).getX() + j,setLocations.get(i).getX() + k);
                    if (!possibleFields.contains(point2D)){
                        boolean alreadySet = false;
                        if (j+k == 0){
                            alreadySet = true;
                        }
                        possibleFields.add(new PossibleField(point2D,alreadySet));
                    }
                }
            }
        }
    }

    private void countSurroundingFields(){
        int counter = 0;
        for (int i = 0; i < possibleFields.size(); i++) {
            for (int j = -1; j < 2; j++) {
                for (int k = -1; k < 2; k++) {
                    if (j != 0 && k != 0 && setLocations.contains(possibleFields.get(i).addPointToLocation(j,k))){
                        counter++;
                    }
                }
            }
            possibleFields.get(i).setCountSurroundingSetFields(counter);
            counter = 0;
        }
    }

    private List<Point2D> createNextGeneration(){
        List<Point2D> setLocationsNextGen = new ArrayList<>();
        for (PossibleField posF:possibleFields) {
            int amount = posF.getCountSurroundingSetFields();
            if (amount == 2 || amount == 3){
                setLocationsNextGen.add(posF.getLocation());
            } // All other cases end in the death of the field
        }
        return setLocationsNextGen;
    }

    public void turnControl(){
        for (int i = 0; i < setLocations.size(); i++) {
            rectangles[(int)setLocations.get(i).getX()][(int)setLocations.get(i).getY()].setFill(Color.WHITE);
        }
        findAllPointsToCheck();
        countSurroundingFields();
        setLocations = createNextGeneration(); // TODO Fehler finden
        for (int i = 0; i < setLocations.size(); i++) {
            rectangles[(int)setLocations.get(i).getX()][(int)setLocations.get(i).getY()].setFill(Color.BLACK);
        }

    }

    public Rectangle getRectangle(int row, int column){return rectangles[row][column];}
    public Rectangle[][] getRectangles(){return rectangles;}
}
