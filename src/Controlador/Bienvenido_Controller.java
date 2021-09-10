package Controlador;

import Vista.GetPantalla;
import com.jfoenix.controls.JFXProgressBar;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;


public class Bienvenido_Controller implements Initializable
{
    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView img;
    @FXML
    private JFXProgressBar bar;


    private static Stage stage;

    public static void setStage(Stage stage) {
        Bienvenido_Controller.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        FadeTransition paneTransition=new FadeTransition(Duration.seconds(2),pane);
        FadeTransition paneTransition2=new FadeTransition(Duration.seconds(2),pane);
        FadeTransition barTransition=new FadeTransition(Duration.seconds(2),bar);
        FadeTransition barTransition2=new FadeTransition(Duration.seconds(2),bar);

        paneTransition.setFromValue(0);
        paneTransition.setToValue(1);

        paneTransition2.setFromValue(1);
        paneTransition2.setToValue(0);

        barTransition.setFromValue(0);
        barTransition.setToValue(1);

        barTransition2.setFromValue(1);
        barTransition2.setToValue(0);

        paneTransition.setOnFinished(event ->  barTransition.play());
        paneTransition2.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try
                {
                    stage.close();
                    GetPantalla.Loggin();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        barTransition.setOnFinished(event -> barTransition2.play());
        barTransition2.setOnFinished(event -> paneTransition2.play());
        paneTransition.play();



    }
}
