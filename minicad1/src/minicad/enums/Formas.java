/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicad.enums;

/**
 *
 * @author Diego
 */
public enum Formas {
    LINHA {
        public String toString() {
            return "linha";
        }
    },
    TRIANGULO {
        public String toString() {
            return "triangulo";
        }
    },
    POLYGON {
       public String toString() {
            return "polygon";
        } 
    }
}
