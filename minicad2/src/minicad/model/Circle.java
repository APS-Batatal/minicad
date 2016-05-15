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
public class Circle extends Form {

    public Circle(Point p1, Point p2) {
        super();
        this.type = EForms.CIRCLE;
        this.points = new ArrayList<>(2);
        this.points.add(p1);
        this.points.add(p2);
    }

    public Circle() {
        this.points = new ArrayList<>(2);
    }

    @Override
    public void setPlot() {
        /*Point p1 = this.points.get(0);
        Point p4 = this.points.get(1);
        Point p2 = new Point(p4.x,p1.y);
        Point p3 = new Point(p1.x,p4.y);
        
        this.plotPoints.addAll(findLine(p1, p2));
        this.plotPoints.addAll(findLine(p2, p4));
        this.plotPoints.addAll(findLine(p4, p3));
        this.plotPoints.addAll(findLine(p3, p1));*/
        System.out.println("aqui a bruxaria que desenha o círculo");
        
    }
}
