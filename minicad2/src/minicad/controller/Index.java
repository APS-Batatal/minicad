package minicad.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import minicad.model.dialog.InputDialog;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import minicad.Helpers.Point;
import minicad.model.DrawingCanvas;
import minicad.model.FormList;
import minicad.model.enums.EForms;
import minicad.model.enums.ESides;
import minicad.model.dialog.ScaleDialog;

/**
 *
 * @author Diego
 */
public class Index implements Initializable {

    //VARIÁVEIS
    @FXML
    private AnchorPane indexPane;//Container principal

    @FXML
    private Canvas canvas;
    private DrawingCanvas dCanvas;//canvas de desenho
    private EForms choice;//variável para a forma escolhida
    private EForms prevChoice;//variável para a escolha anterior

    @FXML
    private Label status;
    @FXML
    private Label mouseLbl;//label que indicará a posição do mouse

    @FXML
    private Button lineBtn;//botão de linha
    @FXML
    private Button triangleBtn;//Botão de triângulo
    @FXML
    private Button rectBtn;//Botão de retângulo
    @FXML
    private Button circleBtn;//Botão de Círculo

    @FXML
    private Button manualBtn;//botão de entrada manual
    InputDialog manualDialog;//Diálogo de entrada manual

    //TODO: fazer
    @FXML
    private ListView<String> list;//Lista de desenhos
    private FormList formList;//Controlador da lista de desenhos
    
    @FXML
    private ColorPicker colorPicker;
    
    @FXML
    private Button scaleBtnX;
    @FXML
    private Button scaleBtnY;

    //LISTENERS
    //Ao iniciar
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dCanvas = new DrawingCanvas(canvas);//Criar um novo controlador DrawingCanvas
        manualDialog = new InputDialog();//Criar um novo Diálogo (para input por texto) 
        formList = new FormList(list);//Criar um novo controlador da lista

        colorPicker.setValue(Color.BLACK);
        
        manualBtn.setDisable(true);//desabilitar o manualBtn (por default)
        scaleBtnX.setDisable(true);//desabilitar o scaleBtn (por default)
        scaleBtnY.setDisable(true);//desabilitar o scaleBtn (por default)
    }

    //Ao clicar no botão de linha
    @FXML
    private void onLineBtnClick(ActionEvent event) {
        createForm(EForms.LINE);//Criar linha
    }

    //Ao clicar no botão de Triângulo
    @FXML
    private void onTriangleBtnClick(ActionEvent event) {
        createForm(EForms.TRIANGLE);//Criar triângulo
    }

    //Ao clicar no botão de retângulo
    @FXML
    private void onRectBtnClick(ActionEvent event) {
        createForm(EForms.RECTANGLE);//Criar Retângulo
    }

    //Ao clicar no botão de círculo
    @FXML
    private void onCircleBtnClick(ActionEvent event) {
        createForm(EForms.CIRCLE);//Criar Círculo
    }

    //Ao clicar no botão de entrada manual
    @FXML
    private void onManualBtnClick(ActionEvent event) {
        indexPane.setDisable(true);//travar a janela principal        
        dCanvas.clearPoints();//limpar pontos do canvas de desenho
        manualDialog.show(dCanvas.nPoints);//Exibir diálogo de entrada de pontos

        //varificar se o diálogo de entrada manual retornou o número necessário de pontos
        if (dCanvas.nPoints == manualDialog.getPoints().size()) {
            dCanvas.setPoints(manualDialog.getPoints());//setar os pontos para o canvas de desenho
            checkPoints();//chamar a checagem de pontos
            formList.add(dCanvas.getForm());
        }
        indexPane.setDisable(false);//reativar a janela principal
    }
    
    @FXML
    private void onListClick(MouseEvent event) {
        formList.get(list.getSelectionModel().getSelectedIndex());
        scaleBtnX.setDisable(false);
        scaleBtnY.setDisable(false);
    }

    @FXML
    private void onColorSet(ActionEvent event) {
        dCanvas.setColor(colorPicker.getValue());
    }

    @FXML
    private void onScaleBtnClick(ActionEvent event) {
        ESides side;
        if("scaleBtnX".equals(((Control)event.getSource()).getId())){
            side = ESides.HORIZONTAL;
        } else {
            side = ESides.VERTICAL;            
        }
        double factor = new ScaleDialog().show();
        if(factor > 0){;
            formList.scale(side, factor);
        } else {
            status.setText("Escala Inválida");
        }
    }

    //Ao clicar no canvas
    @FXML
    private void onCanvasClick(MouseEvent event) {
        dCanvas.onClick();//chamar evento de click do canvas de desenho
        checkPoints();//verificar se há pontos para desenhar
    }

    //Ao mover o mouse no canvas
    @FXML
    private void onCanvasMove(MouseEvent event) {
        Point p = new Point(event.getX(), event.getY());//criar um ponto com as coordenadas do mouse
        dCanvas.setPosition(p);//Atualizar a posição no canvas de desenho
        mouseLbl.setText("( x: " + ((int) p.x) + " , y: " + ((int) p.y) + " )");//atualizar texto
    }

    //Ao tirar o mouse do canvas
    @FXML
    private void onMouseOut(MouseEvent event) {
        mouseLbl.setText(null);//apagar texto
    }

    //METÓDOS
    //Criar forma
    private void createForm(EForms form) {
        //verificar se há uma escolha
        if (this.choice != form) {
            this.choice = form;//atualizar escolha
            this.prevChoice = form;//atualizar escolha
            dCanvas.AddForm(this.choice);//adicionar forma no canvas de desenho
            manualBtn.setDisable(false);//habilitar entrada manual
        }
    }

    //Checar pontos
    private void checkPoints() {
        //Senão houver mais pontos para desenhar
        if (dCanvas.nPoints <= 0 && this.choice != null) {
            //Se a escolha anterior for a mesma que a atual
            formList.add(dCanvas.getForm());
            //list.getSelectionModel().select(formList.size() - 1);
            dCanvas.clearSelection();
            if (this.prevChoice == this.choice) {
                dCanvas.AddForm(this.choice);//continuar adicionando
                manualBtn.setDisable(false);//reabilitar botão de entrada manual
            } else {
                manualBtn.setDisable(true);//desabilitar botão de entrada manual                
            }
        }
    }
}
