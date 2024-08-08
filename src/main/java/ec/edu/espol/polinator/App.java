package ec.edu.espol.polinator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Game game = new Game();  // Shared game instance
    
    @Override
    public void start(Stage stage) throws IOException {
        
        scene = new Scene(repeat("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();        
    }

    
    static void setRoot(String fxml) throws IOException {
        //scene.setRoot(loadFXML(fxml));
        scene.setRoot(repeat(fxml));
    }

    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
                                       
        launch();
        
    }

    private static Parent repeat(String fxml) throws IOException {
    FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    Parent root = loader.load();

    // Retrieve the controller and ensure it implements the GameController interface
    Object controller = loader.getController();
    if (controller instanceof GameController) {
        ((GameController) controller).setGame(getGame());
    } else {
        throw new IllegalStateException("Controller must implement GameController");
    }

    return root;
}


    private static Game getGame() {
        return game; 
    }
    
  
}
