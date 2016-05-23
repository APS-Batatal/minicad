/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicad.model.dialog;

import java.util.ArrayList;
import minicad.Helpers.Dialog;
import minicad.Helpers.Point;
import minicad.controller.Input;

/**
 *
 * @author Diego
 */
public class InputDialog extends Dialog {

    ArrayList<Point> points;
    Input controller;

    public InputDialog() {
        super("Entrada Manual", "input");
        controller = (Input) loader.getController();
        controller.setupStage(stage);
    }

    public void show(int npoints) {
        controller.makeFields(npoints);
        super.show();
        this.points = controller.points;
    }
    public ArrayList<Point> getPoints(){
        return this.points;
    }
}
