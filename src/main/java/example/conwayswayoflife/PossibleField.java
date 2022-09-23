package example.conwayswayoflife;

import javafx.geometry.Point2D;

public class PossibleField {
    private Point2D location;
    private boolean alreadySet;
    private int countSurroundingSetFields;
    public PossibleField(Point2D location, boolean alreadySet){
        this.location = location;
        this.alreadySet = alreadySet;
    }

    public Point2D getLocation(){return location;}
    public boolean getAlreadySet(){return alreadySet;}
    public int getCountSurroundingSetFields(){return countSurroundingSetFields;}
    public void setCountSurroundingSetFields(int i){countSurroundingSetFields = i;}

    public Point2D addPointToLocation(int x, int y){
        return new Point2D(x + location.getX(), y + location.getY());
    }
}
