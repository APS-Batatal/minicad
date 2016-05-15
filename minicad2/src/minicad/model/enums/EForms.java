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
public enum EForms {
    LINE {
        @Override
        public String toString() {
            return "LINE";
        }
    },
    TRIANGLE {
        @Override
        public String toString() {
            return "TRIANGLE";
        }
    },
    RECTANGLE {
        @Override
        public String toString() {
            return "RECTANGLE";
        }
    },
    CIRCLE {
        @Override
        public String toString() {
            return "CIRCLE";
        }
    }
}
