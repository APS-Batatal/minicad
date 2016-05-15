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

    protected Stage dialog;
    protected FXMLLoader loader;

    public Dialog(String name, String fxml) {
        dialog = new Stage();
        loader = new FXMLLoader(Main.class.getResource("view/" + fxml + ".fxml"));
        //Parent root = (Parent)loader.load();
        Parent root;
        try {
            root = (Parent) loader.load();
            //root = FXMLLoader.load(Main.class.getResource("view/" + fxml + ".fxml"));
            dialog.setScene(new Scene(root));
            dialog.setTitle(name);
            dialog.initModality(Modality.WINDOW_MODAL);
        } catch (Exception e) {
        }
    }

    public void show() {
        dialog.showAndWait();
    }
}
