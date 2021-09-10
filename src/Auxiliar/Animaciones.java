package Auxiliar;

import animatefx.animation.*;
import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class Animaciones {
    public static void AnimacionTipo1(Node node) {
        if (node instanceof JFXButton) {

            Temblar_Componente(node);
            Undir_Componente(node);
        }
    }

    private static void Temblar_Componente(Node node) {
        Wobble flash = new Wobble(node);
        if (node instanceof JFXButton) {
            node.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    flash.play();
                }
            });
        }
    }

    private static void Undir_Componente(Node node) {
        Tada hinge = new Tada(node);
        node.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                hinge.play();
            }
        });
    }
}
