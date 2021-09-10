package Reportes.Controladoras;

import Auxiliar.Busquedas;
import Vista.IDraggedScene;
import Modelo.*;
import Reportes.Auxiliares.ReporteTesisAplicable_Aux;
import Servicio.Repository;
import Servicio.Service_Locator;
import Vista.GetPantalla;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.xml.sax.helpers.ParserAdapter;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.FXML;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;

public class Reporte_Est_Tesis_Aplicable_Controller implements Initializable, IDraggedScene {
    @FXML
    private AnchorPane panel;


    @FXML
    private AnchorPane paneArriba;

    @FXML
    private AnchorPane paneAbajo;

    @FXML
    private AnchorPane paneTitulo;

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
    private JFXButton button_Salir;
    @FXML
    private JFXButton botonCerrarVentana;
    @FXML
    private AnchorPane hbox;
    @FXML
    private ImageView fotoFondo;


    ArrayList<Estudiante> estudiantesReporte = null;
    Busquedas busqueda;

/*
Declaraciones
 */




    private static int idDirect;
    private static int idRol;
    private static Stage stage;

    public static void setIdDirect(int idDirect) { Reporte_Est_Tesis_Aplicable_Controller.idDirect = idDirect; }
    public static void setIdRol(int idRol) { Reporte_Est_Tesis_Aplicable_Controller.idRol = idRol; }
    public static void setStage(Stage stage) {
        Reporte_Est_Tesis_Aplicable_Controller.stage = stage;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Pa que la pantalla se mueva
        onDraggedScene(panel);
        ConfigurarMinimizarMaximizar(panel,null,botonCerrarVentana);


        try {

            estudiantesReporte = Service_Locator.getInstance().getReport_service().Reporte_List_Tesis_Cujae(idDirect,idRol);

            busqueda=new Busquedas();

            ObservableList<String> tiposDeBusq = FXCollections.observableArrayList();
            tiposDeBusq.add("Nombre y apellidos");
            tiposDeBusq.add("Carnet de identidad");
            tiposDeBusq.add("Tema de Tesis");
            tiposDeBusq.add("Especialidad");
            tiposDeBusq.add("Lugar de procedencia");
            comboBox_FiltrarBusq.setItems(tiposDeBusq);


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
                        HashMap myParameters = new HashMap();
                        myParameters.put("idDirect", idDirect);

                        if(idRol==9) {
                            JasperFillManager.fillReportToFile("src/Reportes/iReport/Reporte_Tesis_Aplicable_JefeArea.jasper", myParameters, myConnection);
                            JasperViewer.viewReport("src/Reportes/iReport/Reporte_Tesis_Aplicable_JefeArea.jrprint", false, false);
                        }
                       else if(idRol==8){
                            JasperFillManager.fillReportToFile("src/Reportes/iReport/Estudiante_TesisAplic_Tuto.jasper", myParameters, myConnection);
                            JasperViewer.viewReport("src/Reportes/iReport/Estudiante_TesisAplic_Tuto.jrprint", false, false);

                        }else {

                            JasperFillManager.fillReportToFile("src/Reportes/iReport/Reporte_Est_Tesis_Aplic.jasper", new HashMap(), myConnection);
                            JasperViewer.viewReport("src/Reportes/iReport/Reporte_Est_Tesis_Aplic.jrprint", false, false);
                        } } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });


            button_Salir.setOnAction(new EventHandler<ActionEvent>() {

                public void handle(ActionEvent event) {
                    stage.close();
                }
            });



            textField_Buscar.setOnKeyTyped(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {

                    String e=event.getCharacter();

                    if (e.equals("\b")||e.equals("\n")||e.equals("\t"))
                        e="";

                    String filtro = textField_Buscar.getText()+e;
                    String buscarPor = null;

                    if (comboBox_FiltrarBusq.getSelectionModel().getSelectedItem() == null)
                        buscarPor = "";
                    else
                        buscarPor = comboBox_FiltrarBusq.getSelectionModel().getSelectedItem();

                    try {
                        LlenarTabla(filtro, buscarPor);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        //  GetPantalla.panel_De_Notificacion(panel,e.getMessage(),"Error","red","Error");

                    }
                }
            });


            LlenarTabla("", "");
        } catch (Exception e) {
            e.printStackTrace();
            // GetPantalla.panel_De_Notificacion(panel,e.getMessage(),"Error","red","Error");

        }



    }


    private ObservableList<ReporteTesisAplicable_Aux> ListaDeEstudiantes(String filtro, String buscarPor) throws Exception {
        String procedencia = null, espec = null;



        ObservableList<ReporteTesisAplicable_Aux> lis = FXCollections.observableArrayList();
        Iterator<Estudiante> estudiantes = busqueda.buscarEstudiantes(filtro, buscarPor, estudiantesReporte).iterator();
        Estudiante hold=null;

        while (estudiantes.hasNext())
        {
            hold=estudiantes.next();
            espec =busqueda.buscarEspecialidad(hold.getEspecialidad());

            if (hold.getProcedenciaEstudiante() != 0)
                procedencia = busqueda.buscarLugarProc(hold.getProcedenciaEstudiante());
              else
                procedencia = "CUJAE";
            lis.add(new ReporteTesisAplicable_Aux(hold.getNombreApellidos(), hold.getNidentidad(), hold.getTemaTesis(), procedencia, espec));
        }

        return lis;
    }

    private void LlenarTabla(String filtro, String buscarPor) throws Exception {
        TreeItem<ReporteTesisAplicable_Aux> item = new RecursiveTreeItem<>(ListaDeEstudiantes(filtro, buscarPor), RecursiveTreeObject::getChildren);
        table.setRoot(item);
        table.setShowRoot(false);
    }


}
