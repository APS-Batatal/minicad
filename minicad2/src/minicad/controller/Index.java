package minicad.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import minicad.model.InputDialog;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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

    private EForms choice;
    private EForms prevChoice;

    @FXML
    private Label status;
    @FXML
    private Button lineBtn;
    @FXML
    private Button manualBtn;
    @FXML
    private Label mouseLbl;

    //InputDialog manualDialog;
    InputDialog manualDialog;
    @FXML
    private AnchorPane indexPane;
    @FXML
    private Button triangleBtn;
    @FXML
    private Button rectBtn;
    @FXML
    private Button circleBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //criar um novo DrawingCanvas
        dCanvas = new DrawingCanvas(canvas);

        //manualDialog = new Dialog("Entrada Manual", "Entre com valor de (x,y)");
        manualDialog = new InputDialog();

        //desabilitar o manualBtn (por default)
        manualBtn.setDisable(true);
    }

    @FXML
    private void onLineBtnClick(ActionEvent event) {
        createForm(EForms.LINE);
    }

    @FXML
    private void onTriangleBtnClick(ActionEvent event) {
        createForm(EForms.TRIANGLE);
    }

    @FXML
    private void onRectBtnClick(ActionEvent event) {
        createForm(EForms.RECTANGLE);
    }

    @FXML
    private void onCircleBtnClick(ActionEvent event) {
        System.out.println("Aqui será o círculo");
    }

    @FXML
    private void onManualBtnClick(ActionEvent event) {
        //TODO: abrir menu para entar com os valores por meio de texto
        indexPane.setDisable(true);
        dCanvas.clearPoints();
        manualDialog.show(dCanvas.nPoints);
        if (dCanvas.nPoints == manualDialog.getPoints().size()) {
            dCanvas.setPoints(manualDialog.getPoints());
            check();
        }
        indexPane.setDisable(false);
    }

    @FXML
    private void onCanvasClick(MouseEvent event) {
        //Ao clicar no canvas
        dCanvas.onClick();

        //verificar se há pontos para desenhar
        check();
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

    private void createForm(EForms form) {
        if (this.choice != form) {

            this.choice = form;
            this.prevChoice = form;

            dCanvas.AddForm(this.choice);
            manualBtn.setDisable(false);//habilitar entrada manual
        }
    }

    private void check() {
        if (dCanvas.nPoints <= 0) {
            //senão houver
            //desabilitar botões de ação
            //lineBtn.setDisable(false);

            manualBtn.setDisable(true);//desabilitar botão de entrada manual

            if (this.prevChoice == this.choice) {
                dCanvas.AddForm(this.choice);
                manualBtn.setDisable(false);
            }
        }
    }
}
