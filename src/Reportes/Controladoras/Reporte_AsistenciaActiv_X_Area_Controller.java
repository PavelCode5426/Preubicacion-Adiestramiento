package Reportes.Controladoras;

import Modelo.Estudiante;
import Reportes.Auxiliares.ReporteTesisAplicable_Aux;
import Reportes.Auxiliares.Reporte_AsistenciaActiv_X_Area_Aux;
import Servicio.Repository;
import Servicio.Service_Locator;
import Vista.GetPantalla;
import Vista.IDraggedScene;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.*;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Reporte_AsistenciaActiv_X_Area_Controller implements Initializable, IDraggedScene {
    @FXML
    private AnchorPane panel;

    @FXML
    private Label button_Imprimi;

    @FXML
    private HBox hbox;

    @FXML
    private JFXTreeTableView<Reporte_AsistenciaActiv_X_Area_Aux> table;

    @FXML
    private TreeTableColumn<Reporte_AsistenciaActiv_X_Area_Aux, String> columna_nombre;

    @FXML
    private TreeTableColumn<Reporte_AsistenciaActiv_X_Area_Aux, String> columna_fecha;

    @FXML
    private TreeTableColumn<Reporte_AsistenciaActiv_X_Area_Aux, String> columna_Asistencia;

    @FXML
    private TreeTableColumn<Reporte_AsistenciaActiv_X_Area_Aux, String> columna_Hora;

    @FXML
    private JFXButton button_Salir;


    @FXML
    private Pane paneArriba;

    @FXML
    private Pane paneAbajo;

    @FXML
    private Pane paneTitulo;
    @FXML
    private JFXButton botonCerrarVentana;

    /*
Declaraciones

 */

    public static void setStage(Stage stage) {
        Reporte_AsistenciaActiv_X_Area_Controller.stage = stage;
    }

    private static Stage stage;


    @Override

    public void initialize(URL location, ResourceBundle resources) {


        try {
            onDraggedScene(panel);
            ConfigurarMinimizarMaximizar(panel,null,botonCerrarVentana);


            columna_nombre.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Reporte_AsistenciaActiv_X_Area_Aux, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Reporte_AsistenciaActiv_X_Area_Aux, String> param) {
                    return param.getValue().getValue().nombreActProperty();
                }
            });
            columna_fecha.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Reporte_AsistenciaActiv_X_Area_Aux, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Reporte_AsistenciaActiv_X_Area_Aux, String> param) {
                    return param.getValue().getValue().fechaProperty();
                }
            });
            columna_Asistencia.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Reporte_AsistenciaActiv_X_Area_Aux, String>, ObservableValue<String>>() {

                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Reporte_AsistenciaActiv_X_Area_Aux, String> param) {
                    return param.getValue().getValue().asistenciaProperty();
                }
            });
            columna_Hora.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Reporte_AsistenciaActiv_X_Area_Aux, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Reporte_AsistenciaActiv_X_Area_Aux, String> param) {
                    return param.getValue().getValue().horaProperty();
                }
            });


            button_Imprimi.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    try {
                        Connection myConnection = Repository.getConnetion();
                        JasperPrint print = JasperFillManager.fillReport("src/Reportes/iReport/reporte3_6.jasper.jasper", null, myConnection);
                        JasperViewer view = new JasperViewer(print, false);
                        view.setVisible(true);
                    } catch (Exception e2) {

                    }


                }
            });




            button_Salir.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    stage.close();
                }
            });


            LlenarTabla();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private ObservableList<Reporte_AsistenciaActiv_X_Area_Aux> ListaDeEstudiantes() throws Exception {

        ObservableList<Reporte_AsistenciaActiv_X_Area_Aux> lis = FXCollections.observableArrayList();
        ArrayList<Reporte_AsistenciaActiv_X_Area_Aux> listaEstudiantesReporte = null;
        listaEstudiantesReporte = Service_Locator.getInstance().getReport_service().Reporte_AsistenciaAct_X_Area(0);
        lis.setAll(listaEstudiantesReporte);

        return lis;
    }

    private void LlenarTabla() throws Exception {
        TreeItem<Reporte_AsistenciaActiv_X_Area_Aux> item = new RecursiveTreeItem<>(ListaDeEstudiantes(), RecursiveTreeObject::getChildren);
        table.setRoot(item);
        table.setShowRoot(false);
    }

}