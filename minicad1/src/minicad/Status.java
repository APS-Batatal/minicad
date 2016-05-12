/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicad;

import javafx.scene.control.Label;
import minicad.enums.Formas;

/**
 *
 * @author Diego
 */
public class Status{
    private Label label;
    public Status(Label label){
        this.label = label;
    };
    public void setText(String string){
        label.setText(string);
    };
    public String getText(){
        return label.getText();
    }
    public void clear(){
        label.setText("");
    }
    public void forma(Formas forma){
        switch(forma){
            case LINHA:
                label.setText("Linha escolhida, são necessários 2 pontos");
                break;
            case TRIANGULO:
                label.setText("Triângulo escolhido, são necessários 3 pontos");
                break;
        }
    }
}
