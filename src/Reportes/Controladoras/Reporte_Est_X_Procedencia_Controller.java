package Reportes.Controladoras;


import Modelo.LugarProcedencia;
import Vista.IDraggedScene;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Connection;
import java.util.*;

import Auxiliar.Busquedas;
import Modelo.Directorio;
import Modelo.Especialidad;
import Modelo.Estudiante;
import Reportes.Auxiliares.ReporteTesisAplicable_Aux;
import Servicio.Repository;
import Servicio.Service_Locator;
import Vista.IDraggedScene;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;


public class Reporte_Est_X_Procedencia_Controller implements Initializable, IDraggedScene{


    @FXML
    private AnchorPane panel;

    @FXML
    private AnchorPane paneTitulo;

    @FXML
    private AnchorPane hbox;

    @FXML
    private JFXButton botonCerrarVentana;

    @FXML
    private AnchorPane paneArriba;

    @FXML
    private Label button_Imprimir;

    @FXML
    private AnchorPane paneAbajo;

    @FXML
    private JFXComboBox<String> comboBox_Esp;

    @FXML
    private JFXTreeTableView<ReporteTesisAplicable_Aux> table;

    @FXML
    private TreeTableColumn<ReporteTesisAplicable_Aux, String> columna_nombre;

    @FXML
    private TreeTableColumn<ReporteTesisAplicable_Aux, String> columna_carnet;

    @FXML
    private TreeTableColumn<ReporteTesisAplicable_Aux, String> columna_tesis;

    @FXML
    private TreeTableColumn<ReporteTesisAplicable_Aux, String> columna_isAplicable;

    @FXML
    private JFXButton button_Salir;


    //Declaraciones importantes;


    private static int idDirectorio;

    public static int idRol;

    private static Stage stage = new Stage();

    public static void setIdDirectorio(int idDirectorio) {
        Reporte_Est_X_Procedencia_Controller.idDirectorio = idDirectorio;
    }

    public static void setIdRol(int idRol) {
        Reporte_Est_X_Procedencia_Controller.idRol = idRol;
    }

    public static void setStage(Stage stage) {
        Reporte_Est_X_Procedencia_Controller.stage = stage;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Pa que la pantalla se mueva
        onDraggedScene(panel);
        ConfigurarMinimizarMaximizar(panel,null,botonCerrarVentana);

        ObservableList<String> lugaresList = FXCollections.observableArrayList();

        try {


            lugaresList.add("CUJAE");
            lugaresList.add("Universidad de La Habana");
            lugaresList.add("MININT");
            lugaresList.add("Universidad de Ciencias Medicas");
            lugaresList.add("ISDI");
            lugaresList.add("ISA");
            lugaresList.add("Universidad de Ciencias Informaticas");
            lugaresList.add("FAR");
            lugaresList.add("Extrangero");

            comboBox_Esp.setItems(lugaresList);


            button_Imprimir.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        Connection myConnection = Repository.getConnetion();
                        HashMap myParameters = new HashMap();
                        myParameters.put("idDirect", idDirectorio);
                        System.out.println("Id directivo: "+idDirectorio+", id rol: "+idRol);
                        if(idRol==9) {
                            JasperFillManager.fillReportToFile("src/Reportes/iReport/Estudiantes_X_CentroProc.jasper.jasper", myParameters, myConnection);
                            JasperViewer.viewReport("src/Reportes/iReport/Estudiantes_X_CentroProc.jasper.jrprint", false, false);
                        }
                    else if(idRol==8){
                        JasperFillManager.fillReportToFile("src/Reportes/iReport/Estudiante_X_Centro_Tuto.jasper", myParameters, myConnection);
                        JasperViewer.viewReport("src/Reportes/iReport/Estudiante_X_Centro_Tuto.jrprint", false, false);

                    }
                    else{
                            JasperFillManager.fillReportToFile("src/Reportes/iReport/EstudiantesXProcedencia.jasper", myParameters, myConnection);
                            JasperViewer.viewReport("src/Reportes/iReport/Estudiante_X_Centro_Espec.jrprint", false, false);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
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
            columna_tesis.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ReporteTesisAplicable_Aux, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ReporteTesisAplicable_Aux, String> param) {
                    return param.getValue().getValue().temaTesisProperty();
                }
            });
            columna_isAplicable.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ReporteTesisAplicable_Aux, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ReporteTesisAplicable_Aux, String> param) {
                    return param.getValue().getValue().lugarProcProperty();
                }
            });

            button_Salir.setOnAction(new EventHandler<ActionEvent>() {

                public void handle(ActionEvent event) {
                    stage.close();
                }
            });



            comboBox_Esp.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        if (comboBox_Esp.getSelectionModel().getSelectedItem().equals(""))
                            LlenarTabla("");
                        else
                            LlenarTabla(comboBox_Esp.getSelectionModel().getSelectedItem());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });


            LlenarTabla("");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void LlenarTabla(String procedencia) throws Exception {
        ArrayList<ReporteTesisAplicable_Aux> listaReporte = new ArrayList<>();
        ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();
        ResultSet resultSetReporte = Service_Locator.getInstance().getReport_service().Reporte_Est_X_Procedencia(idDirectorio, idRol,procedencia);
        Iterator<Estudiante> EstudianteIterator = null;


        if (idRol == 8)
            listaEstudiantes = Service_Locator.getInstance().getDirectorio_service().getTutorados();
        else if (idRol == 9)
            listaEstudiantes = Service_Locator.getInstance().getGraduado_service().getGraduadoFromArea_Ara(
                    new Repository<Directorio>(new Directorio()).Get(idDirectorio).getArea());
        else if (idRol == -1)
            listaEstudiantes=Service_Locator.getInstance().getGraduado_service().get_All_GraduadosList_Ara();


        EstudianteIterator=listaEstudiantes.iterator();

        if (procedencia.equals("")) {

            Estudiante hold=null;
            while (EstudianteIterator.hasNext()) {
                hold = EstudianteIterator.next();
                listaReporte.add(new ReporteTesisAplicable_Aux(hold.getNombreApellidos(), hold.getNidentidad(), hold.getTemaTesis(),
                        String.valueOf(hold.isTesisAplicable())));}
        } else {
            while (resultSetReporte.next()) {

                listaReporte.add(new ReporteTesisAplicable_Aux(resultSetReporte.getString(1), resultSetReporte.getString(2), resultSetReporte.getString(3), String.valueOf(resultSetReporte.getBoolean(4)).toLowerCase()));
            }
        }

        ObservableList<ReporteTesisAplicable_Aux> lis = FXCollections.observableArrayList(listaReporte);


        TreeItem<ReporteTesisAplicable_Aux> item = new RecursiveTreeItem<>(lis, RecursiveTreeObject::getChildren);
        table.setRoot(item);
        table.setShowRoot(false);

    }
}
