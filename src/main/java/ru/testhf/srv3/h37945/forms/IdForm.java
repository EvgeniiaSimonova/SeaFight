package ru.testhf.srv3.h37945.forms;

public class IdForm {
    String id;
    String state;

    public IdForm() {
    }

    public IdForm(String id, String state) {
        this.id = id;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
