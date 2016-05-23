/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicad.model;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import minicad.model.enums.ESides;
import minicad.model.forms.Form;

/**
 *
 * @author Diego
 */
public class FormList {

    private ListView list;
    private int index;
    private ObservableList<String> items = FXCollections.observableArrayList();
    private ArrayList<Form> forms = new ArrayList<>();

    public FormList(ListView list) {
        this.list = list;
    }

    public void add(Form form) {
        forms.add(form);
        this.refresh();
        this.items.add(index + " - " + form.type.toString());
        this.index = forms.size() - 1;
    }

    public void scale(double factor) {
        this.forms.get(this.index).scale(factor);
    }

    public void reflect(ESides side) {
        this.forms.get(this.index).reflect(side);
    }

    public Form get(int index) {
        this.index = index;
        return this.forms.get(index);
    }

    public int size() {
        return forms.size();
    }

    public void refresh() {
        this.list.setItems(this.items);
    }

}
