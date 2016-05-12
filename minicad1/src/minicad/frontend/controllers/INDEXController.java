/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicad.frontend.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import minicad.DrawningCanvas;
import minicad.Status;
import minicad.enums.Formas;

/**
 *
 * @author Diego
 */
public class INDEXController implements Initializable {

    @FXML
    private Canvas canvas;
    @FXML
    private Canvas canvasSRD;
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
    @FXML
    private Button polyBtn;

    private ArrayList<Button> buttons;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        status = new Status(statusLabel);
        dCanvas = new DrawningCanvas(canvas,canvasSRD);
        //dCanvas.draw();
        buttons = new ArrayList<>();
        buttons.add(lineBtn);
        buttons.add(triangleBtn);
        buttons.add(polyBtn);
        backgroundColorPicker.setValue(Color.BLACK);
        strokeColorPicker.setValue(Color.BLACK);
    }

    @FXML
    private void onLineBtnClick(MouseEvent event) {
        enableButtons();
        dCanvas.setForma(Formas.LINHA);
        status.forma(Formas.LINHA);
        lineBtn.setDisable(true);
    }

    @FXML
    private void onTriangleBtnClick(ActionEvent event) {
        enableButtons();
        dCanvas.setForma(Formas.TRIANGULO);
        status.forma(Formas.TRIANGULO);
        triangleBtn.setDisable(true);
    }

    @FXML
    private void onPolyBtnClick(ActionEvent event) {
        enableButtons();
        //dCanvas.setForma(Formas.TRIANGULO);
        TextInputDialog dialog = new TextInputDialog("4");
        dialog.setTitle("Polígono");
        dialog.setHeaderText(null);
        dialog.setContentText("Número de vértices:");
        Optional<String> result = dialog.showAndWait();


        if (result.isPresent()) {
            int n;
            try{
                n = Integer.parseInt(result.get());
                if(n <= 0){
                    status.setText("Número de vértices inválido");  
                } else {
                    dCanvas.setForma(Formas.POLYGON);
                    dCanvas.setNClicks(n);
                    polyBtn.setDisable(true);
                }
            } catch(Exception e){
                status.setText("Número de vértices inválido");
            }
        } else {
            enableButtons();
            dCanvas.clear();
        }
    }

    @FXML
    private void onClearBtnClick(ActionEvent event) {
        enableButtons();
        dCanvas.clear();
        status.setText("Tela de desenho limpa.");
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
            if (dCanvas.getNClicks() <= 0) {
                status.clear();
                enableButtons();

            } else {
                status.setText("Faltam " + (dCanvas.getNClicks()) + " pontos");
            }
        } else {
            status.setText("Por favor, escolha uma opção!");
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

    private void enableButtons() {
        status.clear();
        for (Button b : buttons) {
            b.setDisable(false);
        }
    }
}
