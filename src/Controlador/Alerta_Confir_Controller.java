package Controlador;

import Vista.IDraggedScene;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Alerta_Confir_Controller implements Initializable, IDraggedScene {

    @FXML
    private AnchorPane paneExterior;

    @FXML
    private HBox titlePane;

    @FXML
    private Label titulo;

    @FXML
    private HBox contentPane;

    @FXML
    private Text mensaje;

    @FXML
    private HBox buttonPane;

    @FXML
    private JFXButton botonAceptar;

    @FXML
    private JFXButton botonCancelar;

    //Variables necesarias
    private static int eleccion;
    private String text_titulo;
    private String sms;



    public  static int getEleccion() {
        return eleccion;
    }


    public Alerta_Confir_Controller(String sms, String titulo) {

        this.text_titulo = titulo;
        this.sms = sms;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        onDraggedScene(paneExterior);

        botonAceptar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                eleccion=1;
                FadeTransition transition=new FadeTransition( Duration.seconds(3.5),paneExterior);
                transition.setFromValue(0);
                transition.setToValue(1);
                paneExterior.getScene().getWindow().hide();
            }
        });
        botonCancelar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                eleccion=2;
                FadeTransition transition=new FadeTransition( Duration.seconds(3.5),paneExterior);
                transition.setFromValue(0);
                transition.setToValue(1);
                paneExterior.getScene().getWindow().hide();
            }
        });

        titulo.setText(text_titulo);
        mensaje.setText(sms);

    }
}
