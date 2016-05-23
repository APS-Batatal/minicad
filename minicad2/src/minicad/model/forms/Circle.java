/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicad.model.forms;

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
        this.type = EForms.CIRCLE;
        this.points = new ArrayList<>(2);
    }

    @Override
    public void setPlot() {
        this.clear();
        Point p1 = this.points.get(0);
        Point p4 = this.points.get(1);
        Point p2 = new Point(p4.x, p1.y);
        Point p3 = new Point(p1.x, p4.y);
        double r = Math.hypot(p4.x - p1.x, p4.y - p1.y);
        double x = -r, y = 0, err = 2 - 2 * r;//II. Quadrant
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
