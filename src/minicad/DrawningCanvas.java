/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicad;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import minicad.enums.Formas;
import minicad.utils.Point;

/**
 *
 * @author Diego
 */
public class DrawningCanvas {

    public Canvas canvas;
    private GraphicsContext gc;

    private Point position;
    private ArrayList<Point> points;

    private Formas forma;
    private int nClicks = 0;

    public DrawningCanvas(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        this.gc.setFill(Color.BLACK);
        this.gc.setStroke(Color.BLACK);
        this.position = new Point();
        this.points = new ArrayList<>();
    }

    public void setPosition(double x, double y) {
        position.setPosition((int) x, (int) y);
    }

    public Point getPosition() {
        return position;
    }

    public void setForma(Formas forma) {
        this.forma = forma;
        switch (this.forma) {
            case LINHA:
                this.nClicks = 2;
                break;
            case TRIANGULO:
                this.nClicks = 3;
                break;
        }
    }

    public Formas getForma() {
        return this.forma;
    }

    public void clearForma() {
        this.forma = null;
    }

    public int getNClicks() {
        return this.nClicks;
    }
    public void setNClicks(int n){
        this.nClicks = n;
    }

    public void setupPoint() {
        this.points.add(new Point(this.position.x, this.position.y));
        this.nClicks--;
        if(this.nClicks <= 0){
            this.draw();
        }
    }

    public void clearPoints() {
        this.points = new ArrayList<Point>();
    }

    public void setBackgroundColor(Color color) {
        gc.setFill(color);

    }

    public void setStrokeColor(Color color) {
        gc.setStroke(color);
    }

    public void draw() {
        gc.setLineWidth(5);

        ArrayList<Double> xList = new ArrayList<>();
        ArrayList<Double> yList = new ArrayList<>();

        for (Point p : this.points) {
            xList.add((double) p.x);
            yList.add((double) p.y);
        }
        double[] xPoints = xList.stream().mapToDouble(d -> d).toArray();
        double[] yPoints = yList.stream().mapToDouble(d -> d).toArray();

        gc.fillPolygon(xPoints, yPoints, this.points.size());
        gc.strokePolygon(xPoints, yPoints, this.points.size());

        this.clearPoints();
        this.clearForma();
    }

    public void clear() {
        this.clearForma();
        this.clearPoints();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
