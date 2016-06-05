/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicad.model.forms;

import java.util.ArrayList;
import minicad.Helpers.Point;
import minicad.model.enums.EForms;
import minicad.model.enums.ESides;

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
        this.type = EForms.CIRCLE;
        this.points = new ArrayList<>(2);
    }

    @Override
    public void setPlot() {
        this.clear();

        Point p1 = this.points.get(0);
        Point p4 = this.points.get(1);

        double r = Point.distance(p4, p1);
        double x = -r, y = 0, err = 2 - 2 * r;//II. Quadrant

        //this.plotPoints.addAll(this.findLine(p1, p4));

        do {
            this.plotPoints.add(new Point(p1.x - x, p1.y + y));//I. Quadrant
            this.plotPoints.add(new Point(p1.x - y, p1.y - x));//II. Quadrant
            this.plotPoints.add(new Point(p1.x + x, p1.y - y));//III. Quadrant
            this.plotPoints.add(new Point(p1.x + y, p1.y + x));//IV. Quadrant
            r = err;
            if (r <= y) {
                err += ++y * 2 + 1;//e_xy+e_y < 0
            }
            if (r > x || err > y) {
                err += ++x * 2 + 1;//e_xy+e_x > 0 or no 2nd y-step
            }
        } while (x < 0);
    }

    @Override
    public void scale(ESides side, double factor) {
        //TODO: Arrumar
        //super.scale(side, factor);
        this.clear();

        Point p1 = this.points.get(0);
        Point p4 = this.points.get(1);
        double r = Point.distance(p4, p1) * factor;
        double x = -r, y = 0, err = 2 - 2 * r;//II. Quadrant
        this.plotPoints.addAll(this.findLine(p1, p4));
        do {
            this.plotPoints.add(new Point(p1.x - x, p1.y + y));//I. Quadrant
            this.plotPoints.add(new Point(p1.x - y, p1.y - x));//II. Quadrant
            this.plotPoints.add(new Point(p1.x + x, p1.y - y));//III. Quadrant
            this.plotPoints.add(new Point(p1.x + y, p1.y + x));//IV. Quadrant
            r = err;
            if (r <= y) {
                err += ++y * 2 + 1;//e_xy+e_y < 0
            }
            if (r > x || err > y) {
                err += ++x * 2 + 1;//e_xy+e_x > 0 or no 2nd y-step
            }
        } while (x < 0);
    }

}
