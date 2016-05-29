/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicad.Helpers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import minicad.Main;

/**
 *
 * @author Diego
 */
public abstract class Dialog {

    protected Stage stage;
    protected FXMLLoader loader;

    public Dialog(String name, String fxml) {
        stage = new Stage();
        loader = new FXMLLoader(Main.class.getResource("view/" + fxml + ".fxml"));
        //Parent root = (Parent)loader.load();
        Parent root;
        try {
            root = (Parent) loader.load();
            //root = FXMLLoader.load(Main.class.getResource("view/" + fxml + ".fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle(name);
            stage.initModality(Modality.WINDOW_MODAL);
        } catch (Exception e) {
        }
    }

    public void show() {
        stage.showAndWait();
    }
}
