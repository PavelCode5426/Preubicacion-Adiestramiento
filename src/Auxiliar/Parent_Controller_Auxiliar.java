package Auxiliar;

import javafx.scene.Parent;

public class Parent_Controller_Auxiliar<T>
{
    Parent parent;
    T Controller;

    public Parent_Controller_Auxiliar(Parent parent, T controller) {
        this.parent = parent;
        Controller = controller;
    }

    public Parent getParent() {
        return parent;
    }

    public T getController() {
        return Controller;
    }
}
