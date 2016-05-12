/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicad;

import static com.sun.org.apache.bcel.internal.util.SecuritySupport.getResourceAsStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Diego
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/index.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.getIcons().add(
   new Image(Main.class.getResourceAsStream( "resources/icon.png" ))); 
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
