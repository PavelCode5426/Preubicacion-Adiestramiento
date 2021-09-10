package Controlador;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class Alert_Controller implements Initializable
{
    @FXML
    private VBox pane;
    @FXML
    private VBox barra;
    @FXML
    private Label titulo;
    @FXML
    private Label sms;
    @FXML
    private ImageView img;
    @FXML
    private ImageView imgarriba;

    //Declaracion de Variables Necesarias
    private String color;
    private String tit;
    private String mens;
    private File foto;
    private File fotoArriba;
    private Stage stage;

    public Alert_Controller(String color, String tit, String mens, File foto, Stage stage,File fotoArriba)
    {
        this.stage=stage;
        this.color = color;
        this.tit = tit;
        this.mens = mens;
        this.foto = foto;
        this.fotoArriba=fotoArriba;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        if(color.equals("darkgreen") || color.equals("DARKGREEN"))
            color= "#065325";

        pane.setOpacity(0);
        FadeTransition transition=new FadeTransition( Duration.seconds(3.5),pane);
        transition.setFromValue(0);
        transition.setToValue(1);

        titulo.setText(tit);
        sms.setText(mens);
        pane.setStyle("-fx-background-radius: 20px;-fx-background-color: #FFF;");
        barra.setStyle("-fx-background-color: "+color+ "; -fx-background-radius: 20px");
        try
        {
            img.setImage(new Image(new FileInputStream(foto)));
            imgarriba.setImage(new Image(new FileInputStream(fotoArriba)));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



        transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                transition.setFromValue(1);
                transition.setToValue(0);
                transition.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        stage.close();
                    }
                });
                transition.play();
            }
        });

        transition.play();


    }



}
