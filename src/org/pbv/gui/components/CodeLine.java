package org.pbv.gui.components;

import javax.swing.text.Document;

public class CodeLine {

    private int num;

    private String data;

    private Document notes;

    public CodeLine(int num, String data) {
        this.num = num;
        this.data = data;
    }

    public int getNum() {
        return num;
    }

    public String getData() {
        return data;
    }

    public void setNotes(Document doc) {
        this.notes = doc;
    }

    public Document getNotes() {
        return notes;
    }
}
