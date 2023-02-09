module DBClientApp {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;

    opens sample;
    opens Controllers;

}