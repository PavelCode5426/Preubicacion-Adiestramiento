package Controlador;

import Vista.IDraggedScene;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Desarrolladores_Controller implements Initializable, IDraggedScene {

    @FXML
    private AnchorPane PaneExterior;

    @FXML
    private AnchorPane paneTitulo;

    @FXML
    private AnchorPane hbox;

    @FXML
    private JFXButton botonCerrarVentana;


    public static void setStage(Stage stage) {
        Desarrolladores_Controller.stage = stage;
    }
     private static Stage stage;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        onDraggedScene(PaneExterior);

        //Poner Pantalla linda
       ConfigurarMinimizarMaximizar(PaneExterior,null,botonCerrarVentana);
    }


}
