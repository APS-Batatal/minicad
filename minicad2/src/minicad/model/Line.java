/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicad.model;

import java.util.ArrayList;
import minicad.Helpers.Point;
import static minicad.model.enums.EForms.LINE;

/**
 *
 * @author Diego
 */
public class Line extends Form{

    public Line(Point pi, Point pf) {
        super();
        this.type = LINE;
        this.points = new ArrayList<>(2);
        this.points.add(pi);
        this.points.add(pf);
    }
    public Line() {
        this.points = new ArrayList<>(2);
    }

    @Override
    public ArrayList<Integer> findLine() {
        double xi = this.points.get(0).x;
        double yi = this.points.get(0).y;
        double xf = this.points.get(this.points.size()-1).x;
        double yf = this.points.get(this.points.size()-1).y;
        
        double dX = xf-xi;
        double dY = yf-yi;
        
        double x = xi;
        double y = yi;
        
        double p = 2*dY - dX;
        
        while(x <= xf){
            this.plotPoints.add(new Point(x,y));
            if(p < 0){
                x = x+1;
                p = p + 2*dY;
            } else if( p >= 0){
                x = x+1;
                y = y+1;
                p = p*(2*dY) - 2*dX;
            }
        }
        return null;
    }
    
}
