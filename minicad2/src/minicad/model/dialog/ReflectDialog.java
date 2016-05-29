/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicad.model.dialog;

import java.util.ArrayList;
import java.util.Optional;
import javafx.scene.control.ChoiceDialog;
import minicad.model.enums.ESides;

/**
 *
 * @author Diego
 */
public class ReflectDialog {

    ChoiceDialog<String> dialog;

    public ReflectDialog() {

        ArrayList<String> choices = new ArrayList<>();
        choices.add(ESides.HORIZONTAL.toString());
        choices.add(ESides.VERTICAL.toString());

        dialog = new ChoiceDialog<>(choices.get(0), choices);
        dialog.setTitle("Reflexo");
        dialog.setHeaderText(null);
        dialog.setContentText("Escolha o sentido");
    }

    public ESides show() {
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            if (result.get().equals(ESides.HORIZONTAL.toString())) {
                return ESides.HORIZONTAL;
            } else if (result.get().equals(ESides.VERTICAL.toString())) {
                return ESides.VERTICAL;
            }
        }
        return null;
    }

}
