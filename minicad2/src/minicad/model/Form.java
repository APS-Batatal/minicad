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
}
