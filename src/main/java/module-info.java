module example.conwayswayoflife {
    requires javafx.controls;
    requires javafx.fxml;


    opens example.conwayswayoflife to javafx.fxml;
    exports example.conwayswayoflife;
}