/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicad.model;

import minicad.model.forms.Rect;
import minicad.model.forms.Triangle;
import minicad.model.forms.Line;
import minicad.model.forms.Form;
import minicad.model.forms.Circle;
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
    private ArrayList<Point> points;
    public int nPoints = 0;
    public int formPoints = 0;
    private Form actualForm = null;

    private Color color = Color.BLACK;

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
                    gc.setFill(form.color);
                    for (int k = 0; k < form.plotPoints.size(); k++) {
                        Point p = form.plotPoints.get(k);//get the plot point
                        //gc.setFill(p);
                        gc.fillRect(p.x, p.y, 1, 1); //plot the current point
                    }
                }
            }

        }.start();
    }

    private void clear() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLACK);
    }

    private void cross() {
        gc.fillRect(x, 0, 1, canvas.getHeight());
        gc.fillRect(0, y, canvas.getWidth(), 1);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void AddForm(EForms form) {
        //this.drawList.add(form);
        if (this.choice == form) {
            return;
        }
        this.choice = form;
        this.points = new ArrayList<>();
        switch (this.choice) {
            case LINE:
                this.nPoints = 2;
                this.formPoints = 2;
                this.actualForm = new Line();
                break;
            case TRIANGLE:
                this.nPoints = 3;
                this.formPoints = 3;
                this.actualForm = new Triangle();
                break;
            case RECTANGLE:
                this.nPoints = 2;
                this.formPoints = 2;
                this.actualForm = new Rect();
                break;
            case CIRCLE:
                this.nPoints = 2;
                this.formPoints = 2;
                this.actualForm = new Circle();
                break;
        }
    }

    public void setPosition(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    public Point getPosition() {
        return new Point(this.x, this.y);
    }

    public void onClick() {
        this.nPoints--;
        if (this.actualForm != null) {
            //this.actualForm.AddPoint(getPosition());
            this.points.add(getPosition());
            if (this.nPoints <= 0) {
                setPoints(this.points);
                create();
            }
        }
    }

    public void setPoints(ArrayList<Point> points) {
        if (this.actualForm != null) {
            for (Point point : points) {
                this.actualForm.AddPoint(point);
            }
            //create();
        }
    }

    public void clearPoints() {
        this.points = new ArrayList<>();
        this.nPoints = this.formPoints;
    }

    private void create() {
        if (actualForm != null) {
            actualForm.color = this.color;
            actualForm.setPlot();
            drawList.add(actualForm);
        }
    }

    public Form getForm() {
        return this.actualForm;
    }

    public void clearSelection() {
        this.choice = null;
        this.actualForm = null;
        this.nPoints = 0;
        this.formPoints = 0;
    }
}
