/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicad;

import javafx.scene.control.Label;

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
}
