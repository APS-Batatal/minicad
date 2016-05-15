/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicad.model;

import java.util.ArrayList;
import minicad.Helpers.Point;
import minicad.model.enums.EForms;
import minicad.model.interfaces.IBresenham;

/**
 *
 * @author Diego
 */
public abstract class Form implements IBresenham{
    
    public EForms type;
    public ArrayList<Point> points = new ArrayList<>();
    public ArrayList<Point> plotPoints = new ArrayList<>();
    
    public void AddPoint(Point point){
        points.add(point);
    }
    
    @Override
    public ArrayList<Point> findLine(Point pi, Point pf){
        ArrayList<Point> resultPoints = new ArrayList<>();

        int x = (int) pi.x;
        int y = (int) pi.y;
        int x2 = (int) pf.x;
        int y2 = (int) pf.y;

        int w = x2 - x;
        int h = y2 - y;
        int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0;
        if (w < 0) {
            dx1 = -1;
        } else if (w > 0) {
            dx1 = 1;
        }
        if (h < 0) {
            dy1 = -1;
        } else if (h > 0) {
            dy1 = 1;
        }
        if (w < 0) {
            dx2 = -1;
        } else if (w > 0) {
            dx2 = 1;
        }
        int longest = Math.abs(w);
        int shortest = Math.abs(h);
        if (!(longest > shortest)) {
            longest = Math.abs(h);
            shortest = Math.abs(w);
            if (h < 0) {
                dy2 = -1;
            } else if (h > 0) {
                dy2 = 1;
            }
            dx2 = 0;
        }
        int numerator = longest >> 1;
        for (int i = 0; i <= longest; i++) {
            resultPoints.add(new Point(x, y));
            numerator += shortest;
            if (!(numerator < longest)) {
                numerator -= longest;
                x += dx1;
                y += dy1;
            } else {
                x += dx2;
                y += dy2;
            }
        }
        return resultPoints;
    }
}
