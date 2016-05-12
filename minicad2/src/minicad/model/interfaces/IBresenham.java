/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicad.model.interfaces;

import java.util.ArrayList;

/**
 *
 * @author Diego
 */
public interface IBresenham {

    /**
     *
     * @param xi O x inicial para a linha
     * @param yi O i inicial para a linha
     * @param xf O x final para a linha
     * @param yf O y final para a linha
     * @return Um array com os pontos da linha
     */
    public ArrayList<Integer> findLine();
}
