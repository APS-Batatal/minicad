/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicad.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import minicad.Helpers.Point;

/**
 * FXML Controller class
 *
 * @author Diego
 */
public class Input implements Initializable {

    private Stage stage;
    @FXML
    private VBox pane;

    public ArrayList<Point> points;
    private ArrayList<TextField> xFields;
    private ArrayList<TextField> yFields;
    int nPoints = 0;
    @FXML
    private Label statusLabel;

    public void setupStage(Stage stage) {
        this.stage = stage;
        this.stage.setResizable(false);
        this.stage.initStyle(StageStyle.UTILITY);
        //makeFields(10);
        points = new ArrayList<>();
        xFields = new ArrayList<>();
        yFields = new ArrayList<>();

    }

    public void makeFields(int n) {
        clear();
        nPoints = n;
        for (int i = 0; i < n; i++) {
            Pane nPane = new Pane();
            nPane.setLayoutY(60);

            nPane.getChildren().add(makeLabel("X: ", 25, 8));
            xFields.add(makeTextField(40, 4));
            nPane.getChildren().add(xFields.get(i));

            nPane.getChildren().add(makeLabel("Y: ", 110, 8));
            yFields.add(makeTextField(120, 4));
            nPane.getChildren().add(yFields.get(i));

            pane.getChildren().add(nPane);
        }
    }

    private Label makeLabel(String msg, double x, double y) {
        Label label = new Label(msg);
        label.setLayoutX(x);
        label.setLayoutY(y);
        return label;
    }

    private TextField makeTextField(double x, double y) {
        TextField tField = new TextField();
        tField.setLayoutY(4);
        tField.setPrefWidth(50);
        tField.setPrefHeight(20);
        tField.setLayoutX(x);
        tField.setLayoutY(y);
        return tField;
    }

    private void clear() {
        statusLabel.setText(null);
        xFields = new ArrayList<>();
        yFields = new ArrayList<>();
        pane.getChildren().clear();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //System.out.println("teste");
    }

    @FXML
    private void onConfirmBtnClick(ActionEvent event) {
        points = new ArrayList<>();
        boolean valid = true;

        for (int i = 0; i < nPoints; i++) {
            double x = -1;
            double y = -1;
            try {
                x = Double.parseDouble(xFields.get(i).getText());
                y = Double.parseDouble(yFields.get(i).getText());
            } catch (Exception e) {
            }
            if (x < 0 || y < 0) {
                valid = false;

            } else {
                points.add(new Point(x, y));
                //valid = true;
            }
        }
        if (valid) {
            this.stage.close();
        } else {
            statusLabel.setText("Inválido");
        }
    }

}
