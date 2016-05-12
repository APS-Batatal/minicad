/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicad.controller;

import java.util.Optional;
import javafx.scene.control.TextInputDialog;

/**
 *
 * @author Diego
 */
public class InputDialog {
    private TextInputDialog  dialog;
    public String InputDialog(String name, String message){
        this.dialog = new TextInputDialog(name);
        dialog.setTitle(name);
        dialog.setHeaderText(null);
        dialog.setContentText(message);
        
        Optional<String> result = dialog.showAndWait();
        if(result.isPresent()){
            return result.get();
        }
        return null;
    }
}
