module ec.edu.espol.mavenproject1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.polinator to javafx.fxml;
    exports ec.edu.espol.polinator;
}
