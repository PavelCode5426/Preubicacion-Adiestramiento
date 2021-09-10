package Controlador;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import Auxiliar.Animaciones;
import Auxiliar.Excepciones.AppException;
import Modelo.Directorio;
import Modelo.Rol;
import Servicio.Service_Locator;
import Vista.IDraggedScene;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import Vista.GetPantalla;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.stage.WindowEvent;
import javafx.util.Duration;


public class Loggin_Controller implements Initializable, IDraggedScene {

    @FXML
    private AnchorPane PaneExterior;
    @FXML
    private VBox pane1;
    @FXML
    private JFXTextField textField_Usuario;
    @FXML
    private JFXPasswordField passField;
    @FXML
    private JFXButton button_Entrar;
    @FXML
    private JFXButton buttonSalir;
    @FXML
    private JFXButton configu;
    @FXML
    private JFXButton configu1;
    @FXML
    private VBox pane2;
    @FXML
    private Label bienv;
    @FXML
    private JFXButton VR1;
    @FXML
    private JFXButton DRH;
    @FXML
    private JFXButton ERH;
    @FXML
    private JFXButton JA;
    @FXML
    private JFXButton Admin;
    @FXML
    private JFXButton TA;
    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private JFXButton botonCerrarVentana;


    private static Stage primaryStage;
    private Directorio directorio;


    public static void setPrimaryStage(Stage primaryStage) {
        Loggin_Controller.primaryStage = primaryStage;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        ConfigurarMinimizarMaximizar(PaneExterior, null, botonCerrarVentana);
        onDraggedScene(PaneExterior);

        accesos_X_Teclado_A_Roles();


        DRH.setDisable(true);
        ERH.setDisable(true);
        VR1.setDisable(true);
        JA.setDisable(true);
        TA.setDisable(true);

        RotateTransition transition = new RotateTransition(Duration.seconds(2.5), img1);
        RotateTransition transition1 = new RotateTransition(Duration.seconds(2.5), img2);

        transition.setFromAngle(0);
        transition.setToAngle(360);
        transition1.setFromAngle(0);
        transition1.setToAngle(360);

        transition.play();
        transition1.play();
        transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                transition.play();
            }
        });
        transition1.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                transition1.play();
            }
        });
        InstanciarAnimaciones();
        buttonSalir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                   System.exit(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        button_Entrar.setDefaultButton(true);
        button_Entrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    directorio = Service_Locator.getInstance().getLoggin_roles_service().Loggin_Service(textField_Usuario.getText(), passField.getText());
                    bienv.setText("Bienvenido " + directorio.getNombreApellido());
                    Animar();
                    Iterator<Rol> it = Service_Locator.getInstance().getLoggin_roles_service().Roles_LoggedUser().iterator();
                    while (it.hasNext()) {
                        Rol next = it.next();
                        if (next.getNombre().equals("Director de Recursos Humanos"))
                            DRH.setDisable(false);
                        else if (next.getNombre().equals("Especialista de Recursos Humanos"))
                            ERH.setDisable(false);
                        else if (next.getNombre().equals("Vicerrector"))
                            VR1.setDisable(false);
                        else if (next.getNombre().equals("Tutor"))
                            TA.setDisable(false);
                        else if (next.getNombre().equals("Jefe de área"))
                            JA.setDisable(false);
                        else if (next.getNombre().equals("Administrador") || next.getNombre().equals("Super Administrador"))
                            Admin.setDisable(false);
                    }
                    VR1.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {
                                GetPantalla.Pantalla_Vicerrector_Primero();
                                Minimizar();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    DRH.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {
                                GetPantalla.PrincipalDirectorRH(directorio.getId());
                                Minimizar();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
                    ERH.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {
                                GetPantalla.Especialista_Recursos_Humanos();
                                Minimizar();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    TA.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {
                                GetPantalla.Pantalla_Tutor();
                                Minimizar();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    JA.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {
                                GetPantalla.Pantalla_Jefe_Area_MAIN();
                                Minimizar();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    Admin.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {
                                GetPantalla.PantallaAdmin();
                                Minimizar();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });


                } catch (Exception e) {
                    e.printStackTrace();
                    GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error de autentificación",  AppException.getJustMensaje(e.getMessage()), "DARKRED");

                }
            }
        });

        configu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    System.out.println("Doy Click");
                    GetPantalla.AppConfiguration();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        configu1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    System.out.println("Doy Click");
                    GetPantalla.Pantalla_Equipo();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void Animar() {
        FadeTransition transition = new FadeTransition(Duration.seconds(1.5), pane1);
        FadeTransition transition1 = new FadeTransition(Duration.seconds(1.5), pane2);

        transition.setFromValue(50);
        transition.setToValue(0);
        transition1.setFromValue(0);
        transition1.setToValue(50);

        transition.play();
        transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                transition1.play();

            }
        });
        transition1.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane2.toFront();
            }
        });

    }

    public void InstanciarAnimaciones() {
        Animaciones.AnimacionTipo1(buttonSalir);
        Animaciones.AnimacionTipo1(button_Entrar);
        Animaciones.AnimacionTipo1(configu);
        Animaciones.AnimacionTipo1(configu1);
    }

    public void Minimizar() {
        primaryStage.setIconified(true);
    }


    public void accesos_X_Teclado_A_Roles() {
        PaneExterior.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if (event.getCode().getName().equals("A") && !Admin.isDisable()) {
                    try {
                        GetPantalla.PantallaAdmin();
                        Minimizar();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (event.getCode().getName().equals("V") && !VR1.isDisable()) {
                    try {
                        GetPantalla.Pantalla_Vicerrector_Primero();
                        Minimizar();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (event.getCode().getName().equals("D") && !DRH.isDisable()) {
                    try {
                        GetPantalla.PrincipalDirectorRH(directorio.getId());
                        Minimizar();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (event.getCode().getName().equals("E") && !ERH.isDisable()) {
                    try {
                        GetPantalla.Especialista_Recursos_Humanos();
                        Minimizar();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (event.getCode().getName().equals("J") && !JA.isDisable()) {
                    try {
                        GetPantalla.Pantalla_Jefe_Area_MAIN();
                        Minimizar();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (event.getCode().getName().equals("T") && !TA.isDisable()) {
                    try {
                        GetPantalla.Pantalla_Tutor();
                        Minimizar();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }


}
