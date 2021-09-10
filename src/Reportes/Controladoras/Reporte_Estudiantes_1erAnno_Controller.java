package Reportes.Controladoras;

import Auxiliar.Busquedas;
import Modelo.Area;
import Modelo.Especialidad;
import Modelo.Estudiante;
import Modelo.LugarProcedencia;
import Reportes.Auxiliares.ReporteTesisAplicable_Aux;
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
import javafx.fxml.Initializable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;


public class Reporte_Estudiantes_1erAnno_Controller implements Initializable, IDraggedScene {

    @FXML
    private Label button_Imprimir;

    @FXML
    private JFXComboBox<String> comboBox_FiltrarBusq;

    @FXML
    private JFXTextField textField_Buscar;

    @FXML
    private JFXTreeTableView<ReporteTesisAplicable_Aux> table;

    @FXML
    private TreeTableColumn<ReporteTesisAplicable_Aux, String> columna_nombre;

    @FXML
    private TreeTableColumn<ReporteTesisAplicable_Aux, String> columna_carnet;

    @FXML
    private TreeTableColumn<ReporteTesisAplicable_Aux, String> columna_temaTesis;

    @FXML
    private TreeTableColumn<ReporteTesisAplicable_Aux, String> columna_Lugar;

    @FXML
    private TreeTableColumn<ReporteTesisAplicable_Aux, String> columna_Especialidad;

    @FXML
    private JFXComboBox<String> comboBox_FiltrarEstudiantes;

    @FXML
    private JFXButton button_Salir;

    @FXML
    private AnchorPane anchorPaneExterno;
    @FXML
    private Pane paneArriba;

    @FXML
    private Pane paneAbajo;

    @FXML
    private Pane paneTitulo;
    @FXML
    private JFXButton botonCerrarVentana;

    public static void setStage(Stage stage) {
        Reporte_Estudiantes_1erAnno_Controller.stage = stage;
    }

    private static Stage stage;

    Busquedas busqueda;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Pa que la pantalla se mueva
        onDraggedScene(anchorPaneExterno);

        ConfigurarMinimizarMaximizar(anchorPaneExterno,null,botonCerrarVentana);


        //Llenar comboBoxes

        ObservableList<String> tiposDeBusq = FXCollections.observableArrayList();
        tiposDeBusq.add("Nombre y apellidos");
        tiposDeBusq.add("Carnet de identidad");
        tiposDeBusq.add("Tema de Tesis");
        tiposDeBusq.add("Especialidad");
        tiposDeBusq.add("Lugar de procedencia");
        comboBox_FiltrarBusq.setItems(tiposDeBusq);


        ObservableList<String> listaComboEstudiantes = FXCollections.observableArrayList();
        listaComboEstudiantes.add("Especialidad");
        listaComboEstudiantes.add("Área");
        listaComboEstudiantes.add("Ningún criterio");
        comboBox_FiltrarEstudiantes.setItems(listaComboEstudiantes);

        try {

            busqueda = new Busquedas();

            comboBox_FiltrarEstudiantes.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String verEstudiantesX = null;

                    if (!comboBox_FiltrarEstudiantes.getSelectionModel().getSelectedItem().equals("")) {
                        if (comboBox_FiltrarEstudiantes.getSelectionModel().getSelectedItem().equals("Especialidad")) {
                            columna_Especialidad.setText("Especialidad");
                            verEstudiantesX = "Especialidad";
                        } else if (comboBox_FiltrarEstudiantes.getSelectionModel().getSelectedItem().equals("Área")) {
                            columna_Especialidad.setText("Área");
                            verEstudiantesX = "Área";

                        } else if (comboBox_FiltrarEstudiantes.getSelectionModel().getSelectedItem().equals("Ningún criterio")) {
                            columna_Especialidad.setText("Tesis aplicable?");
                            verEstudiantesX = "Ningún criterio";
                        }
                    } else
                        verEstudiantesX = "";

                    try {
                        LlenarTabla("", "", verEstudiantesX);
                    } catch (Exception e) {
                        e.printStackTrace();
                        // GetPantalla.panel_De_Notificacion(anchorPaneExterno, e.getMessage(), "Error", "red", "Error");

                    }
                }
            });

            columna_nombre.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ReporteTesisAplicable_Aux, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ReporteTesisAplicable_Aux, String> param) {
                    return param.getValue().getValue().nombreProperty();
                }
            });
            columna_carnet.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ReporteTesisAplicable_Aux, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ReporteTesisAplicable_Aux, String> param) {
                    return param.getValue().getValue().carnetProperty();
                }
            });
            columna_temaTesis.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ReporteTesisAplicable_Aux, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ReporteTesisAplicable_Aux, String> param) {
                    return param.getValue().getValue().temaTesisProperty();
                }
            });
            columna_Lugar.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ReporteTesisAplicable_Aux, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ReporteTesisAplicable_Aux, String> param) {
                    return param.getValue().getValue().lugarProcProperty();
                }
            });

            columna_Especialidad.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ReporteTesisAplicable_Aux, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ReporteTesisAplicable_Aux, String> param) {
                    return param.getValue().getValue().especialidadProperty();
                }
            });


            button_Imprimir.setOnMouseClicked(new EventHandler<MouseEvent>() {


                public void handle(MouseEvent event) {
                    try {
                        Connection myConnection = Repository.getConnetion();

                        JasperFillManager.fillReportToFile("src/Reportes/iReport/Reporte_Adiestrados_1ro.jasper", new HashMap(), myConnection);
                        JasperViewer.viewReport("src/Reportes/iReport/Reporte_Adiestrados_1ro.jrprint", false, false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });


            button_Salir.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    stage.close();
                }
            });

            textField_Buscar.setOnKeyTyped(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {

                    String filtro = textField_Buscar.getText();
                    String buscarPor = null;
                    String verEstudiantesX = null;
                    if (comboBox_FiltrarBusq.getSelectionModel().getSelectedItem() == null) {
                        buscarPor = "";

                    } else {
                        buscarPor = comboBox_FiltrarBusq.getSelectionModel().getSelectedItem().toString();
                    }
                    if (comboBox_FiltrarEstudiantes.getSelectionModel().getSelectedItem() == null) {
                        verEstudiantesX = "";
                    } else {
                        verEstudiantesX = comboBox_FiltrarEstudiantes.getSelectionModel().getSelectedItem();
                    }
                    try {
                        LlenarTabla(filtro, buscarPor, verEstudiantesX);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            LlenarTabla("", "", "");
        } catch (Exception e) {
            //    GetPantalla.panel_De_Notificacion(anchorPaneExterno, e.getMessage(), "Error", "red", "Error");

            e.printStackTrace();
        }


    }


    private ObservableList<ReporteTesisAplicable_Aux> ListaDeEstudiantes(String filtro, String buscarPor, String verPor) throws Exception {
        String procedencia = null, espec = null, area = null;

        ObservableList<ReporteTesisAplicable_Aux> lis = FXCollections.observableArrayList();
        ArrayList<Estudiante> listaEstudiantesReporte = new ArrayList<Estudiante>();
        listaEstudiantesReporte = Service_Locator.getInstance().getReport_service().Reporte_Adiestrados_Primero();
        ArrayList<Estudiante> estudiantes = busqueda.buscarEstudiantes(filtro, buscarPor, listaEstudiantesReporte);


        for (int i = 0; i < estudiantes.size(); i++) {

            espec = busqueda.buscarEspecialidad(estudiantes.get(i).getEspecialidad());
            System.out.println(estudiantes.get(i).getArea());
            area = busqueda.buscarArea(estudiantes.get(i).getArea());


            if (estudiantes.get(i).getProcedenciaEstudiante() != 0)
                procedencia = busqueda.buscarLugarProc(estudiantes.get(i).getProcedenciaEstudiante());
            else
                procedencia = "CUJAE";


            if (verPor.equals("Especialidad") )
                lis.add(new ReporteTesisAplicable_Aux(estudiantes.get(i).getNombreApellidos(), estudiantes.get(i).getNidentidad(), estudiantes.get(i).getTemaTesis(), procedencia, espec));
            else if (verPor.equals("Área"))
                lis.add(new ReporteTesisAplicable_Aux(estudiantes.get(i).getNombreApellidos(), estudiantes.get(i).getNidentidad(), estudiantes.get(i).getTemaTesis(), procedencia, area));
            else if(verPor.equals("Ningún criterio") || verPor.equals(""))
                lis.add(new ReporteTesisAplicable_Aux(estudiantes.get(i).getNombreApellidos(), estudiantes.get(i).getNidentidad(), estudiantes.get(i).getTemaTesis(), procedencia,String.valueOf(estudiantes.get(i).isTesisAplicable())));
        }

        return lis;
    }

    private void LlenarTabla(String filtro, String buscarPor, String verX) throws Exception {
        TreeItem<ReporteTesisAplicable_Aux> item = new RecursiveTreeItem<>(ListaDeEstudiantes(filtro, buscarPor, verX), RecursiveTreeObject::getChildren);
        table.setRoot(item);
        table.setShowRoot(false);
    }


}

