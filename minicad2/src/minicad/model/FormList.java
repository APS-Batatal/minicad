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
        this.index = forms.size() - 1;
        this.items.add((index+1) + " - " + form.type.toString());
    }

    public void scale(ESides side, double factor) {
        this.forms.get(this.index).scale(side, factor);
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
