/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicad.Helpers;

/**
 *
 * @author Diego
 */
public class Point {
    
    public double x,y;
    
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public static double distance(Point p1, Point p2){
        return Math.hypot(p2.x - p1.x, p2.y - p1.y);
    }
}
