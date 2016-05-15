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
public class Line extends Form {

    public Line(Point pi, Point pf) {
        super();
        this.type = EForms.LINE;
        this.points = new ArrayList<>(2);
        this.points.add(pi);
        this.points.add(pf);
    }

    public Line() {
        this.points = new ArrayList<>(2);
    }

    @Override
    public void setPlot() {
        this.plotPoints = findLine(this.points.get(0), this.points.get(1));
    }
}
