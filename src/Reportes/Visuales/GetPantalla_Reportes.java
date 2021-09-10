package Reportes.Visuales;

import Reportes.Controladoras.*;
import Vista.GetPantalla;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GetPantalla_Reportes {

    public static void Reporte_Est_TesisAplicable(int idDirect, int idRol) throws Exception
    {
        Reporte_Est_Tesis_Aplicable_Controller.setIdDirect(idDirect);
        Reporte_Est_Tesis_Aplicable_Controller.setIdRol(idRol);
        Parent parent= FXMLLoader.load(GetPantalla_Reportes.class.getResource("Reporte_Est_Tesis_Aplicable.fxml"));
        Scene scene=new Scene(parent);
        Stage primaryStage=new Stage(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Estudiantes con tesis aplicable");
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.show();
        Reporte_Est_Tesis_Aplicable_Controller.setStage(primaryStage);
    }

    public static void Reporte_Adiestrados_Primero() throws Exception
    {
        Parent parent= FXMLLoader.load(GetPantalla_Reportes.class.getResource("Reporte_Estudiantes_1erAnno.fxml"));
        Scene scene=new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        Stage primaryStage=new Stage(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setTitle("Adiestrados de primer año");

        primaryStage.show();
        Reporte_Estudiantes_1erAnno_Controller.setStage(primaryStage);
    }

    public static void Reporte_EspecialidadXArea() throws Exception
    {
        Parent parent= FXMLLoader.load(GetPantalla_Reportes.class.getResource("Reporte_EstXArea_XEspec.fxml"));
        Scene scene=new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        Stage primaryStage=new Stage(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setTitle("Estudiantes por especialidades por área");
        primaryStage.show();
        Reporte_EstXArea_XEspec_Controller.setStage(primaryStage);
    }

    public static void Reporte_AsistenciaActvidades() throws Exception
    {
        Parent parent= FXMLLoader.load(GetPantalla_Reportes.class.getResource("Reporte_AsistenciaActiv_X_Area.fxml"));
        Scene scene=new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        Stage primaryStage=new Stage(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Asistencia a las actividaes del Plan de Familiarización");
        primaryStage.show();
        Reporte_AsistenciaActiv_X_Area_Controller.setStage(primaryStage);
    }


    public static void Reporte_Plan_Adiestramiento() throws Exception
    {
        Parent parent= FXMLLoader.load(GetPantalla_Reportes.class.getResource("Reporte_PlanAdiestramiento.fxml"));
        Scene scene=new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        Stage primaryStage=new Stage(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Plan de adiestramiento del estudiante");
        primaryStage.show();
        ReportePlanAdiestramiento_Controller.setStage(primaryStage);
    }

    public static void Reporte_Tutor_Est_JefeArea() throws Exception
    {
        Parent parent= FXMLLoader.load(GetPantalla_Reportes.class.getResource("Reporte_Tutor_Est_JefeArea.fxml"));
        Scene scene=new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        Stage primaryStage=new Stage(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setTitle("Plan de adiestramiento del estudiante");
        primaryStage.show();
        Reporte_Tutor_Est_JefeArea_Controller.setStage(primaryStage);
    }

    public static void Reporte_EstX_Espec(int idDirect,int idRol) throws  Exception{
        Reporte_EstXEspecialidad_Controller.setIdDirectorio(idDirect);
        Reporte_EstXEspecialidad_Controller.setIdRol(idRol);
        Parent parent= FXMLLoader.load(GetPantalla_Reportes.class.getResource("Reporte_Est_X_Espec.fxml"));
        Scene scene=new Scene(parent);
        Stage primaryStage=new Stage(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Estudiantes por especialidad");
        scene.setFill(Color.TRANSPARENT);
        primaryStage.show();
        Reporte_EstXEspecialidad_Controller.setStage(primaryStage);

    }

    public static void Reporte_Est_X_Procedencia(int idDirect,int idRol) throws  Exception{
        Reporte_Est_X_Procedencia_Controller.setIdDirectorio(idDirect);
        Reporte_Est_X_Procedencia_Controller.setIdRol(idRol);
        Parent parent= FXMLLoader.load(GetPantalla_Reportes.class.getResource("Reporte_Est_X_Procedencia.fxml"));
        Scene scene=new Scene(parent);
        Stage primaryStage=new Stage(StageStyle.TRANSPARENT);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Estudiantes por lugar de procedencia");
        scene.setFill(Color.TRANSPARENT);
        primaryStage.show();
        Reporte_Est_X_Procedencia_Controller.setStage(primaryStage);

    }

}
