package Controlador;

import animatefx.animation.Tada;
import animatefx.animation.Wobble;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Notificacion_Pane_Controller implements Initializable {
    @FXML
    HBox parent;
    @FXML
    private Label notif;
    @FXML
    private JFXCheckBox selc;

    private String tex;

    public void setTex(String tex) {
        this.tex = tex;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Undir_Componente(parent);
        Temblar_Componente(parent);
        notif.setText(tex);
    }

    private void Temblar_Componente(Node node) {
        Wobble flash = new Wobble(node);
        node.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                flash.play();

            }
        });

    }

    private void Undir_Componente(Node node) {
        Tada hinge = new Tada(node);
        node.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                hinge.play();
                selc.setSelected(!selc.isSelected());
            }
        });
    }


    public boolean isSelected()
    {
        return selc.isSelected();
    }
}