package Reportes.Controladoras;

import Auxiliar.Busquedas;
import Modelo.Directorio;
import Modelo.Especialidad;
import Modelo.Estudiante;
import Modelo.Rol;
import Reportes.Auxiliares.ReporteTesisAplicable_Aux;
import Servicio.Graduado_Service;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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


public class Reporte_EstXEspecialidad_Controller implements Initializable, IDraggedScene {


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
    private static int idRol;

    private static Stage stage = new Stage();

    public static void setIdDirectorio(int idDirectorio) {
        Reporte_EstXEspecialidad_Controller.idDirectorio = idDirectorio;
    }

    public static void setIdRol(int idRol) {
        Reporte_EstXEspecialidad_Controller.idRol = idRol;
    }

    public static void setStage(Stage stage) {
        Reporte_EstXEspecialidad_Controller.stage = stage;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Pa que la pantalla se mueva
        onDraggedScene(panel);

        ConfigurarMinimizarMaximizar(panel,null,botonCerrarVentana);


        ObservableList<String> especialidadesList = FXCollections.observableArrayList();

        try {
            Iterator<Especialidad> iterator =new Repository<Especialidad>(new Especialidad()).GetAll().iterator();

            while (iterator.hasNext())
                especialidadesList.add(iterator.next().getNombre());


            comboBox_Esp.setItems(especialidadesList);


            button_Imprimir.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        Connection myConnection = Repository.getConnetion();
                        HashMap myParameters = new HashMap();
                        myParameters.put("idDirect", idDirectorio);
                        if(idRol==9) {
                            JasperFillManager.fillReportToFile("src/Reportes/iReport/Estudiantes_X_Espec_JefeArea.jasper", myParameters, myConnection);
                            JasperViewer.viewReport("src/Reportes/iReport/Estudiantes_X_Espec_JefeArea.jrprint", false, false);
                        }
                       else if(idRol==8){
                            JasperFillManager.fillReportToFile("src/Reportes/iReport/Estudiantes_X_Especialidad.jasper", myParameters, myConnection);
                            JasperViewer.viewReport("src/Reportes/iReport/Estudiantes_X_Especialidad.jrprint", false, false);

                        }
                        else{
                            JasperFillManager.fillReportToFile("src/Reportes/iReport/Estudiantes_X_Espec_Especialista.jasper", myParameters, myConnection);
                            JasperViewer.viewReport("src/Reportes/iReport/Estudiantes_X_Espec_Especialista.jrprint", false, false);

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

    public void LlenarTabla(String especialidad) throws Exception {
        String rol=null;
        ArrayList<ReporteTesisAplicable_Aux> listaReporte = new ArrayList<>();
        Iterator<Estudiante> EstudianteIterator = null;
        ResultSet resultSetReporte = Service_Locator.getInstance().getReport_service().Reporte_Est_X_Espec(idDirectorio, especialidad, idRol);
       if(idRol!=-1) {
           rol = new Repository<Rol>(new Rol()).Get(idRol).getNombre();


           if (rol.equals("Tutor"))
               EstudianteIterator = Service_Locator.getInstance().getDirectorio_service().getTutorados().iterator();
           else if (rol.equals("Jefe de área"))
               EstudianteIterator = Service_Locator.getInstance().getGraduado_service().getGraduadoFromArea_Ara(
                       new Repository<Directorio>(new Directorio()).Get(idDirectorio).getArea()).iterator();
       } else
            EstudianteIterator = Service_Locator.getInstance().getGraduado_service().get_All_GraduadosList_Ara().iterator();


        if (especialidad.equals("")) 
        {
            Estudiante hold=null;
            while (EstudianteIterator.hasNext()) {
                hold=EstudianteIterator.next();
                listaReporte.add(new ReporteTesisAplicable_Aux(hold.getNombreApellidos(), hold.getNidentidad(), hold.getTemaTesis(),
                        String.valueOf(hold.isTesisAplicable())));
            }

        } else {
            while (resultSetReporte.next()) {

                listaReporte.add(new ReporteTesisAplicable_Aux(resultSetReporte.getString(1), resultSetReporte.getString(2), resultSetReporte.getString(3), String.valueOf(resultSetReporte.getBoolean(4))));
            }
        }

        ObservableList<ReporteTesisAplicable_Aux> lis = FXCollections.observableArrayList(listaReporte);


        TreeItem<ReporteTesisAplicable_Aux> item = new RecursiveTreeItem<>(lis, RecursiveTreeObject::getChildren);
        table.setRoot(item);
        table.setShowRoot(false);

    }
}
