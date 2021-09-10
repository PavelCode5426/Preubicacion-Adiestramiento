package Controlador;

import Servicio.AppConfig;
import Vista.IDraggedScene;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Config_App_Controller implements Initializable, IDraggedScene
{
    @FXML
    private JFXTextField dir;
    @FXML
    private JFXTextField puer;
    @FXML
    private JFXTextField based;
    @FXML
    private JFXTextField user;
    @FXML
    private JFXPasswordField pass;
    @FXML
    private JFXTextField dir1;
    @FXML
    private JFXTextField puer1;
    @FXML
    private JFXTextField based1;
    @FXML
    private JFXTextField user1;
    @FXML
    private JFXPasswordField pass1;
    @FXML
    private JFXButton acep;
    @FXML
    private JFXButton canc;

    @FXML
    private AnchorPane paneTitulo;
    @FXML
    private JFXButton botonCerrarVentana;
    @FXML
    private AnchorPane PaneExterior;
    @FXML
    private AnchorPane hbox;

    public static void setStage(Stage stage) {
        Config_App_Controller.stage = stage;
    }

    /*
            Declaraciones
             */
private static Stage stage;
private AppConfig appConfig;
private AppConfig appConfig1;














    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //Pa que la pantalla se mueva
        onDraggedScene(PaneExterior);
        //Para poner la pantalla linda
        ConfigurarMinimizarMaximizar(PaneExterior,null,botonCerrarVentana);


        AppConfig config=AppConfig.getIntanceAppDataBase();
        AppConfig config1=AppConfig.getIntanceAppExtDataBase();

        dir.setText(config.getAdres());
        dir1.setText(config1.getAdres());

        puer.setText(config.getPort());
        puer1.setText(config1.getPort());

        based.setText(config.getDatabase());
        based1.setText(config1.getDatabase());

        user.setText(config.getUserApp());
        user1.setText(config1.getUserApp());

        pass.setText(config.getPassApp());
        pass1.setText(config1.getPassApp());


        acep.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                try {
                    appConfig=new AppConfig("jdbc:postgresql://",user.getText(),pass.getText(),based.getText(),puer.getText(),dir.getText());
                    appConfig1=new AppConfig("jdbc:postgresql://",user1.getText(),pass1.getText(),based1.getText(),puer1.getText(),dir1.getText());
                    AppConfig.MakeXML(appConfig,appConfig1);
                    AppConfig.ReadXML();
                    stage.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }

        });

        canc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });

       //Poner Pantalla linda
        botonCerrarVentana.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.close();
            }
        });


        botonCerrarVentana.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                botonCerrarVentana.setStyle("-fx-background-color: red");


            }
        });



        hbox.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                botonCerrarVentana.setStyle("-fx-background-color: #065325");

            }
        });
        paneTitulo.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                botonCerrarVentana.setStyle("-fx-background-color: #065325");

            }
        });
    }




}
