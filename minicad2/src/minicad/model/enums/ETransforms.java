/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicad.model.enums;

/**
 *
 * @author Diego
 */
public enum ETransforms {
    REFLECT {
        @Override
        public String toString() {
            return "REFLECT";
        }
    },
    SCALE {
        @Override
        public String toString() {
            return "SCALE";
        }
    }
}
