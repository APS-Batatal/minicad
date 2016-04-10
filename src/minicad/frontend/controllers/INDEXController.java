/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicad.frontend.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import minicad.DrawningCanvas;
import minicad.Status;
import minicad.enums.Formas;
import minicad.utils.Point;

/**
 *
 * @author Diego
 */
public class INDEXController implements Initializable {

    @FXML
    private Canvas canvas;
    private DrawningCanvas dCanvas;
    @FXML
    private Label statusLabel;
    private Status status;
    @FXML
    private Button lineBtn;
    @FXML
    private ColorPicker backgroundColorPicker;
    @FXML
    private ColorPicker strokeColorPicker;
    @FXML
    private Label labelMousePos;
    @FXML
    private Button triangleBtn;
    @FXML
    private Button clearBtn;
    
    private ArrayList<Button> buttons;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        status = new Status(statusLabel);
        dCanvas = new DrawningCanvas(canvas);
        //dCanvas.draw();
        buttons = new ArrayList<>();
        buttons.add(lineBtn);
        buttons.add(triangleBtn);
        
    }

    @FXML
    private void onLineBtnClick(MouseEvent event) {
        dCanvas.setForma(Formas.LINHA);
        lineBtn.setDisable(true);
    }

    @FXML
    private void onTriangleBtnClick(ActionEvent event) {
        dCanvas.setForma(Formas.TRIANGULO);
        triangleBtn.setDisable(true);
    }

    @FXML
    private void onClearBtnClick(ActionEvent event) {
        dCanvas.clear();
    }

    @FXML
    private void onCanvasMouseMove(MouseEvent event) {
        dCanvas.setPosition(event.getX(), event.getY());
        labelMousePos.setText("(X: " + dCanvas.getPosition().x + "; Y: " + dCanvas.getPosition().y + ")");
    }

    @FXML
    private void onCanvasMouseClick(MouseEvent event) {
        if (dCanvas.getForma() != null) {
            dCanvas.setupPoint(); 
            if(dCanvas.getNClicks()<= 0){
                for(Button b: buttons){
                    b.setDisable(false);
                }
            }
        }
    }

    @FXML
    private void onCanvasMouseExit(MouseEvent event) {
        labelMousePos.setText("");
    }

    @FXML
    private void onBackgroundColorPicker(ActionEvent event) {
        dCanvas.setBackgroundColor(backgroundColorPicker.getValue());
    }

    @FXML
    private void onStrokeColorPicker(ActionEvent event) {
        dCanvas.setStrokeColor(strokeColorPicker.getValue());
    }
}
