/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicad.model;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import minicad.Helpers.Point;
import minicad.model.enums.EForms;

/**
 *
 * @author Diego
 */
public class DrawingCanvas {

    private Canvas canvas;
    private GraphicsContext gc;
    private double x, y, width, height = 0;

    private EForms choice;
    public int nClicks = 0;
    private Form actualForm = null;

    private ArrayList<Form> drawList = new ArrayList<>();

    public DrawingCanvas(Canvas canvas) {
        this.canvas = canvas;
        this.width = canvas.getWidth();
        this.height = canvas.getHeight();

        this.gc = canvas.getGraphicsContext2D();

        //The canvas drawning loop
        new AnimationTimer() {
            @Override
            public void handle(long now) {                
                clear();
                cross();
                
                //for each form in drawlist
                for (int i = 0; i < drawList.size(); i++) {
                    Form form = drawList.get(i);//get the form
                    //For each point in the current form
                    for (int k = 0; k < form.plotPoints.size(); k++) {
                        Point p = form.plotPoints.get(k);//get the plot point
                        gc.fillRect(p.x, p.y, 1, 1); //plot the current point
                    }
                }
            }

        }.start();
    }
    private void clear(){
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLACK);
    }
    private void cross(){
        gc.fillRect(x, 0, 1, canvas.getHeight());
        gc.fillRect(0, y, canvas.getWidth(), 1);
    }

    public void AddForm(EForms form) {
        //this.drawList.add(form);
        if (this.choice == form) {
            return;
        }
        this.choice = form;
        switch (this.choice) {
            case LINE:
                this.nClicks = 2;
                this.actualForm = new Line();
                break;
        }
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point getPosition() {
        return new Point(this.x, this.y);
    }

    public void onClick() {
        this.nClicks--;
        if (this.actualForm != null) {
            this.actualForm.AddPoint(getPosition());
            if (this.nClicks <= 0) {
                actualForm.findLine();
                drawList.add(actualForm);
                this.choice = null;
                this.actualForm = null;
            }
        }
    }
}
