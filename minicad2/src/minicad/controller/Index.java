package minicad.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import minicad.model.DrawingCanvas;
import minicad.model.enums.EForms;

/**
 *
 * @author Diego
 */
public class Index implements Initializable {    

    @FXML
    private Canvas canvas;
    private DrawingCanvas dCanvas;
    
    @FXML
    private Label status;
    
    @FXML
    private Button lineBtn;
    @FXML
    private Button manualBtn;
    @FXML
    private Label mouseLbl;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dCanvas = new DrawingCanvas(canvas);
    }    

    @FXML
    private void onLineBtnClick(ActionEvent event) {
        dCanvas.AddForm(EForms.LINE);
        lineBtn.setDisable(true);
    }

    @FXML
    private void onManualBtnClick(ActionEvent event) {
        //TODO: abrir menu para entar com os valores por meio de texto
    }

    @FXML
    private void onCanvasClick(MouseEvent event) {
        //Ao clicar no canvas
        dCanvas.onClick();
        
        if(dCanvas.nClicks <= 0){
            lineBtn.setDisable(false);
        }
    }

    @FXML
    private void onCanvasMove(MouseEvent event) {
        //ao movimentar o canvas
        dCanvas.setPosition(event.getX(), event.getY());
        mouseLbl.setText("( x: " + Math.round(event.getX()) + " , y: " + Math.round(event.getY()) + " )");
    }

    @FXML
    private void onMouseOut(MouseEvent event) {
        mouseLbl.setText(null);
    }
    
}
