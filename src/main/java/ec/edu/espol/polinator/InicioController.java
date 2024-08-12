/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.polinator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PERSONAL
 */
public class InicioController implements Initializable {
    Button buttton;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void abrirPrimary(ActionEvent event) {
        try {
            // Cargar el archivo FXML para la ventana secundaria
            FXMLLoader loader = new FXMLLoader(getClass().getResource("primary_1.fxml"));
            Parent root = loader.load();
           
            // Crear una nueva ventana
            Stage stage = new Stage();
            stage.setTitle("Principal");
            stage.setScene(new Scene(root));
            stage.show();
            Stage s = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
