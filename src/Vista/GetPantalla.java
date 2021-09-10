package Vista;

import Auxiliar.Animaciones;
import Auxiliar.Parent_Controller_Auxiliar;
import Auxiliar.Plan_Adiestramiento;
import Controlador.*;
import Modelo.*;
import animatefx.animation.Tada;
import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDecorator;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Optional;

public class GetPantalla extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Bienvenido(primaryStage);


    }

    public static void Ayuda(int rol) throws Exception {

        Ayuda_Controller.setRol(rol);
        Parent parent = FXMLLoader.load(GetPantalla.class.getResource("Ayuda2.fxml"));
        Stage primaryStage = new Stage(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Ayuda");
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
        Ayuda_Controller.setStage(primaryStage);
    }

    public static void Loggin() throws Exception {

        // Parent parent = FXMLLoader.load(GetPantalla.class.getResource("Ultimo loggin.fxml"));
        Parent parent = FXMLLoader.load(GetPantalla.class.getResource("LoginLastt.fxml"));
        Stage primaryStage = new Stage(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Control de Acceso");
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
        Loggin_Controller.setPrimaryStage(primaryStage);
    }

    public static void PantallaAdmin() throws Exception {
        Parent parent = FXMLLoader.load(GetPantalla.class.getResource("MyAdmin.fxml"));
        Stage primaryStage = new Stage(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Administrador");
        // primaryStage.initModality(Modality.APPLICATION_MODAL);

        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
        MyAdmin_Controller.setStage(primaryStage);
    }

    public static void AppConfiguration() throws Exception {
        Parent parent = FXMLLoader.load(GetPantalla.class.getResource("Confing App.fxml"));
        Stage primaryStage = new Stage(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setTitle("Configuracion");
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.show();
        Config_App_Controller.setStage(primaryStage);
    }

    public static void PrincipalDirectorRH(int PrimaryKey) throws Exception {
        Parent parent = FXMLLoader.load(GetPantalla.class.getResource("pantallaDirectorRecursosHumanos.fxml"));
        Stage primaryStage = new Stage(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Director de Recursos Humanos");
        // primaryStage.initModality(Modality.APPLICATION_MODAL);

        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);

        DirectorRH_Controller.setIdDirector(PrimaryKey);
        DirectorRH_Controller.setStage(primaryStage);
        primaryStage.show();


    }

    public static void AsignarGraduador_Areas(DirectorRH_Controller padre) throws Exception {
        Asiganar_Graduado_Controller.setDirectorRH_controller(padre);
        Parent parent = FXMLLoader.load(GetPantalla.class.getResource("Ubicacion por Area.fxml"));
        Stage primaryStage = new Stage(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Director de Recursos Humanos>>Asignar Graduados a Areas");
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);

        primaryStage.show();
        Asiganar_Graduado_Controller.setStage(primaryStage);
    }


    public static void PlandeFamiliarizacionERH(int idPlan, EspecialistaRH_Controller padre) throws Exception {
        Plan_de_FamiliarizacionERH.setEspecialistaRH_controller(padre);
        Plan_de_FamiliarizacionERH.setIdPlan(idPlan);
        FXMLLoader loader = new FXMLLoader(GetPantalla.class.getResource("Plan de Familiarizacion_Especialista.fxml"));
        Parent parent = loader.load();
        Stage primaryStage = new Stage(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Especialista de Recursos Humanos>>Plan de Familarizacion");
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);

        primaryStage.show();
        Plan_de_FamiliarizacionERH.setStage(primaryStage);

    }

    public static void PlandeFamiliarizacionERH(int idPlan, DirectorRH_Controller padre) throws Exception {
        Plan_de_FamiliarizacionERH.setDirectorRH_controller(padre);
        Plan_de_FamiliarizacionERH.setIdPlan(idPlan);
        FXMLLoader loader = new FXMLLoader(GetPantalla.class.getResource("Plan de Familiarizacion_Especialista.fxml"));
        Parent parent = loader.load();
        Stage primaryStage = new Stage(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Especialista de Recursos Humanos>>Plan de Familarizacion");
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);

        primaryStage.show();
        Plan_de_FamiliarizacionERH.setStage(primaryStage);

    }

    public static void NuevoEstudiante(Estudiante estudiante) throws Exception {
        Estudiante_Controller.setEstudiante(estudiante);
        Parent parent = FXMLLoader.load(GetPantalla.class.getResource("Nuevo Estudiante.fxml"));
        Stage primaryStage = new Stage(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Estudiante");
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.show();
        Estudiante_Controller.setStage(primaryStage);
    }

    public static void Revisar_Plan_Adiestramiento(Integer idPlan, Pantalla_Jefe_Area_Controller padre) throws Exception {
        Aprobar_Desaprobar_PA_Controller.setIDPlan(idPlan);
        Aprobar_Desaprobar_PA_Controller.setPadre(padre);
        Parent parent = FXMLLoader.load(GetPantalla.class.getResource("Aprobar Desaprobar PlanAdiestramiento.fxml"));
        Stage primaryStage = new Stage(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Revisar Plan de Adiestramiento");
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);

        primaryStage.show();
        Aprobar_Desaprobar_PA_Controller.setStage(primaryStage);
    }

    public static void Evaluar_Plan_Familiarizacion_Estudiante(Integer idEst, int idEstPlan, Pantalla_Jefe_Area_Controller padre) throws Exception {
        Asistencia_Evaluar_Plan_Familiarizacion.setIDEstudiantePlan(idEstPlan);
        Asistencia_Evaluar_Plan_Familiarizacion.setIDEstudiante(idEst);
        Asistencia_Evaluar_Plan_Familiarizacion.setPadre(padre);
        Parent parent = FXMLLoader.load(GetPantalla.class.getResource("Asistencia_Evaluacion_Tarea_Plan_Familiarizacion.fxml"));
        Stage primaryStage = new Stage(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Asistencia y Evaluacion del Plan de Familiarizacion");
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);

        primaryStage.show();
        Asistencia_Evaluar_Plan_Familiarizacion.setStage(primaryStage);
    }


    public static void Especialista_Recursos_Humanos() throws Exception {
        Parent parent = FXMLLoader.load(GetPantalla.class.getResource("Pantalla Especialista Recursos Humanos.fxml"));
        Stage primaryStage = new Stage(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Especialista de Recursos Humanos");
        //primaryStage.initModality(Modality.APPLICATION_MODAL);

        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);

        primaryStage.show();
        EspecialistaRH_Controller.setStage(primaryStage);
    }

    public static void Pantalla_Vicerrector_Primero() throws Exception {
        Parent parent = FXMLLoader.load(GetPantalla.class.getResource("Pantalla Vicerector Primero .fxml"));
        Stage primaryStage = new Stage(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Pantalla Vicerector Primero");
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        Pantalla_Vicerector_Primero_Controller.setStage(primaryStage);
        primaryStage.show();

    }

    public static void Revisar_Plan_Familiarizacion(Pantalla_Vicerector_Primero_Controller controller, PlanFamiliarizacion planFamiliarizacion) throws Exception {
        Revisar_Plan_Familiarizacion_VR1.setPadre(controller);
        Revisar_Plan_Familiarizacion_VR1.setFamiliarizacion(planFamiliarizacion);
        Parent parent = FXMLLoader.load(GetPantalla.class.getResource("Aprobar Desaprobar PlanFamiliarizacion.fxml"));
        Stage stage = new Stage(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        stage.setTitle("Revisar Plan de Familiarizacion");
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
        Revisar_Plan_Familiarizacion_VR1.setStage(stage);

    }

    public static void Plan_Adiestramiento(Integer Graduado, Integer PlanAdiest, boolean isProrroga, Pantalla_Tutor_Controller padre) throws Exception {
        Plan_Adiestramiento_Controller.setPadre(padre);
        Plan_Adiestramiento_Controller.setIdPlan(PlanAdiest);
        Plan_Adiestramiento_Controller.setIdEstud(Graduado);
        Plan_Adiestramiento_Controller.setIsProrroga(isProrroga);
        Parent parent = FXMLLoader.load(GetPantalla.class.getResource("Plan_Formacion_Ara.fxml"));
        Stage stage = new Stage(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        stage.setTitle("Plan de Adiestramiento");
        stage.initModality(Modality.APPLICATION_MODAL);


        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);

        stage.show();
        Plan_Adiestramiento_Controller.setStage(stage);
    }

    public static void Pantalla_Importar_Graduados(EspecialistaRH_Controller padre) throws Exception {
        Importar_Estudiante_Controller.setPadre(padre);
        FXMLLoader loader = new FXMLLoader(GetPantalla.class.getResource("Pantalla_Importar_Graduados.fxml"));
        Parent parent = loader.load();
        Stage primaryStage = new Stage(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Importar Graduados Del Sistema de Estudiantes de la CUJAE");
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);

        primaryStage.show();
        Importar_Estudiante_Controller.setStage(primaryStage);
    }

    public static void Pantalla_Tutor() throws Exception {
        FXMLLoader loader = new FXMLLoader(GetPantalla.class.getResource("Pantalla Tutor.fxml"));
        Parent parent = loader.load();
        Stage primaryStage = new Stage(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Tutor de Area");
        // primaryStage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
        Pantalla_Tutor_Controller.setStage(primaryStage);
    }

    public static void Pantalla_Evaluar_Tarea(Tarea tarea, Pantalla_Tutor_Controller padre) throws Exception {
        Pantalla_Evaluar_Tara_Controller.setPadre(padre);
        Pantalla_Evaluar_Tara_Controller.setTarea(tarea);
        FXMLLoader loader = new FXMLLoader(GetPantalla.class.getResource("Evaluar_Tarea.fxml"));
        Parent parent = loader.load();
        Stage primaryStage = new Stage(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Evaluar Tarea");
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);

        primaryStage.show();
        Pantalla_Evaluar_Tara_Controller.setStage(primaryStage);
    }

    public static void Pantalla_Add_Otra_Tarae(Etapa etapa, Pantalla_Tutor_Controller padre) throws Exception {
        Pantalla_Add_Nueva_Tarea_Controller.setEtapa(etapa);
        Pantalla_Add_Nueva_Tarea_Controller.setPadre(padre);
        FXMLLoader loader = new FXMLLoader(GetPantalla.class.getResource("Add_Nueva_Tarea.fxml"));
        Parent parent = loader.load();
        Stage primaryStage = new Stage();
        primaryStage.setResizable(false);
        primaryStage.setTitle("Añadir Otra Tarea");
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        JFXDecorator decorator = new JFXDecorator(primaryStage, parent, false, false, true);
        Scene scene = new Scene(decorator);
        primaryStage.setScene(scene);

        primaryStage.show();
        Pantalla_Add_Nueva_Tarea_Controller.setStage(primaryStage);
    }

    public static void Pantalla_Notificaciones(MenuItem item) throws Exception {
        Pantalla_Notificaciones_Controller controller = new Pantalla_Notificaciones_Controller(item);
        FXMLLoader loader = new FXMLLoader(GetPantalla.class.getResource("Pantalla_Notificaciones.fxml"));
        loader.setController(controller);
        Parent parent = loader.load();
        Stage primaryStage = new Stage(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Pantalla Notificaciones");
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);

        primaryStage.show();
        Pantalla_Notificaciones_Controller.setStage(primaryStage);
    }

    public static Parent_Controller_Auxiliar<Notificacion_Pane_Controller> Get_Notificacion_Pane(String notif) throws Exception {
        FXMLLoader loader = new FXMLLoader(GetPantalla.class.getResource("Notificacion_Pane.fxml"));
        Notificacion_Pane_Controller controller = new Notificacion_Pane_Controller();
        controller.setTex(notif);
        loader.setController(controller);
        Parent parent = loader.load();
        Parent_Controller_Auxiliar<Notificacion_Pane_Controller> auxiliar = new Parent_Controller_Auxiliar<>(parent, controller);
        return auxiliar;
    }

    public static void Pantalla_Enviar_Notifiacion(String tituloPantalla, Integer receptor, boolean exclusivo) throws Exception {
        Pantalla_Enviar_Notifiacion_Controller controller = new Pantalla_Enviar_Notifiacion_Controller(tituloPantalla, receptor, exclusivo);
        FXMLLoader loader = new FXMLLoader(GetPantalla.class.getResource("Pantalla_Enviar_Notificacion.fxml"));
        loader.setController(controller);
        Parent parent = loader.load();
        Stage stage = new Stage(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        Pantalla_Enviar_Notifiacion_Controller.setStage(stage);
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.show();
    }

    public static void Pantalla_Jefe_Area_MAIN() throws Exception {
        FXMLLoader loader = new FXMLLoader(GetPantalla.class.getResource("Pantalla_JefeArea.fxml"));
        Parent parent = loader.load();
        Stage stage = new Stage(StageStyle.TRANSPARENT);
        stage.setTitle("Jefe de Area");
        // stage.initModality(Modality.APPLICATION_MODAL);

        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);

        stage.show();
        Pantalla_Jefe_Area_Controller.setStage(stage);
    }


    public static int panel_De_Notificacion_Confirmar(String mensaje, String titulo, String color) throws Exception {


        int seleccion = 0;

        Stage stage = new Stage(StageStyle.TRANSPARENT);
        FXMLLoader loader = new FXMLLoader(GetPantalla.class.getResource("Alerta_Confirmar.fxml"));
        Alerta_Confir_Controller controller = new Alerta_Confir_Controller(mensaje, titulo);
        loader.setController(controller);

        Scene scene = new Scene(loader.load());
        scene.setFill(Color.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.showAndWait();
        seleccion=Alerta_Confir_Controller.getEleccion();

        return seleccion;

        /*        int eleccionRetorno = 0;
        String ubicacionImagen = "src/Vista/Imagenes/Ask Question_50px.png";


        Pane paneArriba1 = new Pane();
        paneArriba1.setPrefSize(200, 35);
        paneArriba1.setStyle("-fx-background-color:" + color + ";" + " -fx-background-radius: 10px");

        FileInputStream file = new FileInputStream("src/Vista/Imagenes/Box Important_White_50px.png");
        Image imagenConf = new Image(file);
        ImageView imagen_PanelArriba1 = new ImageView(imagenConf);
        imagen_PanelArriba1.setLayoutX(10);
        imagen_PanelArriba1.setLayoutY(5);
        imagen_PanelArriba1.setFitHeight(25);
        imagen_PanelArriba1.setFitWidth(25);
        paneArriba1.getChildren().add(imagen_PanelArriba1);

        Text label_Titulo1 = new Text(titulo);
        label_Titulo1.setText(titulo);
        label_Titulo1.setLayoutX(40);
        label_Titulo1.setLayoutY(23);
        label_Titulo1.setWrappingWidth(180.0);
        label_Titulo1.setStyle("-fx-font-family: Arial; -fx-font-size: 18px; -fx-fill: #FFF");
        paneArriba1.getChildren().add(label_Titulo1);


        AnchorPane panelPrinc1 = new AnchorPane();
        panelPrinc1.setStyle(" -fx-background-color: white;  -fx-background-radius: 10px");
        panelPrinc1.setPrefSize(190, 80);


        Text label_Mensaje1 = new Text(mensaje);
        label_Mensaje1.setWrappingWidth(170.0);
        label_Mensaje1.setLayoutX(10);
        label_Mensaje1.setLayoutY(40);
        label_Mensaje1.setStyle("-fx-font-family: Arial; -fx-font-size: 14px; -fx-fill: gray");
        panelPrinc1.getChildren().add(label_Mensaje1);


        FileInputStream file1 = new FileInputStream(ubicacionImagen);
        Image imagen1 = new Image(file1);
        ImageView imagen_PanelFondo = new ImageView(imagen1);
        imagen_PanelFondo.setLayoutX(180);
        imagen_PanelFondo.setLayoutY(30);
        imagen_PanelFondo.setFitHeight(30);
        imagen_PanelFondo.setFitWidth(30);
        panelPrinc1.getChildren().add(imagen_PanelFondo);


        Alert alerta=new Alert(Alert.AlertType.CONFIRMATION);
        alerta.initStyle(StageStyle.TRANSPARENT);



        VBox vBox = new VBox();
        vBox.setStyle(" -fx-background-radius: 10px;-fx-border-radius: 10px;");
        vBox.getChildren().addAll(paneArriba1, panelPrinc1);
        DialogPane pane1 = new DialogPane();
        pane1.getStylesheets().add(GetPantalla.class.getResource("CSS/dialog-pane.css").toExternalForm());
        pane1.getStyleClass().remove("alert");
        pane1.setHeader(vBox);

        pane1.setPrefSize(200, 160);
        alerta.setHeight(160);
        alerta.setDialogPane(pane1);
        pane1.getStylesheets().add("Vista/CSS/button.css");
        ButtonType buttonTypeAcep = new ButtonType("Si");


        ButtonType buttonTypeCancelar = new ButtonType("No");
        pane1.getButtonTypes().addAll(buttonTypeAcep, buttonTypeCancelar);



        Optional<ButtonType> result = alerta.showAndWait();
        if (result.isPresent()) {
            if (result.get().getText().equals("Si")) {
                eleccionRetorno = 1;
            } else if (result.get().getText().equals("No"))
                eleccionRetorno = 2;

        }

        //Devuelve 1 si la respesta es Si, y 2 si la respuesta es No
*/

    }

    public static int Pantalla_Alerta_(Alert.AlertType type, String titulo, String mensaje, String color) {
        int accion = 0;
        try {
            File imagen = null;
            File imagenTitulo = null;
            Stage stage = new Stage(StageStyle.TRANSPARENT);
            FXMLLoader loader = null;
            switch (type) {
                case INFORMATION:
                    imagen = new File("src//Vista//Imagenes//icons8_Checked_Green_32.png");
                    imagenTitulo=new File("src//Vista//Imagenes//Box Important_White_50px.png");
                    loader = new FXMLLoader(GetPantalla.class.getResource("Alerta.fxml"));
                    Alert_Controller controller = new Alert_Controller(color, titulo, mensaje, imagen, stage, imagenTitulo);
                    loader.setController(controller);
                    break;
                case ERROR:
                    imagen = new File("src//Vista//Imagenes//Error_50px.png");
                    imagenTitulo=new File("src//Vista//Imagenes//icons8_Error_32.png");
                    loader = new FXMLLoader(GetPantalla.class.getResource("Alerta.fxml"));
                    Alert_Controller controller1 = new Alert_Controller(color, titulo, mensaje, imagen, stage,imagenTitulo);
                    loader.setController(controller1);
                    break;
                case CONFIRMATION:
                    //ara akiiiiiiiiiiiiiii ejjejeje
                    break;
            }
            Scene scene = new Scene(loader.load());
            scene.setFill(Color.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setAlwaysOnTop(true);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accion;
    }

    public static void Pantalla_Equipo() throws Exception {
        Parent parent = FXMLLoader.load(GetPantalla.class.getResource("Desarrolladores.fxml"));
        Stage stage = new Stage(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        Desarrolladores_Controller.setStage(stage);
        stage.show();
    }

    public static void Bienvenido(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(GetPantalla.class.getResource("Bienvenido.fxml"));
        stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(parent);
        stage.setTitle("Bienvenido");
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        Bienvenido_Controller.setStage(stage);
        stage.show();
    }

    public static void Pantalla_Perfil() throws Exception {
        Parent parent = FXMLLoader.load(GetPantalla.class.getResource("Perfil.fxml"));
        Stage stage = new Stage(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
        Perfil_Controller.setStage(stage);
    }


    public static void main(String[] args) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        launch(args);
    }
}
