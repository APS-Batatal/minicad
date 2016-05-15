/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicad.model;

import java.util.ArrayList;
import minicad.Helpers.Point;
import minicad.model.enums.EForms;

/**
 *
 * @author Diego
 */
public class Triangle extends Form {

    public Triangle(Point p1, Point p2, Point p3) {
        super();
        this.type = EForms.TRIANGLE;
        this.points = new ArrayList<>(3);
        this.points.add(p1);
        this.points.add(p2);
        this.points.add(p3);
    }

    public Triangle() {
        this.points = new ArrayList<>(3);
    }

    @Override
    public void setPlot() {
        this.plotPoints.addAll(findLine(this.points.get(0), this.points.get(1)));
        this.plotPoints.addAll(findLine(this.points.get(1), this.points.get(2)));
        this.plotPoints.addAll(findLine(this.points.get(2), this.points.get(0)));
    }
}
