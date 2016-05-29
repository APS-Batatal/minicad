/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicad.model.dialog;

import java.util.Optional;
import javafx.scene.control.TextInputDialog;

/**
 *
 * @author Diego
 */
public class ScaleDialog {

    TextInputDialog dialog;

    public ScaleDialog() {
        dialog = new TextInputDialog();
        dialog.setTitle("Escala");
        dialog.setHeaderText(null);
        dialog.setContentText("Entre com o fator de escalonamento");
    }

    public double show() {
        Optional<String> num = dialog.showAndWait();
        double result = -1;
        if (num.isPresent()) {
            try {
                result = Double.parseDouble(num.get());
                if (result > 0) {
                    return result;
                } else {
                    return -1;
                }
            } catch (Exception e) {
                return -1;
            }
        } else {
            return result;
        }
    }

}
