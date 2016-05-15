/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicad.model.interfaces;

import java.util.ArrayList;
import minicad.Helpers.Point;

/**
 *
 * @author Diego
 */
public interface IBresenham {

    /**
     *
     * @param pi O ponto inicial da linha
     * @param pf O ponto final da linha
     * @return Um array com os pontos da linha
     */
    public ArrayList<Point> findLine(Point pi, Point pf);
    public void setPlot();
}
