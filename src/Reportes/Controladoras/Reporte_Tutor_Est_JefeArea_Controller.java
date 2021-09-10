package Reportes.Controladoras;


import Auxiliar.Area_JefeA_CantG;
import Auxiliar.Busquedas;
import Modelo.Directorio;
import Modelo.Estudiante;
import Reportes.Auxiliares.ReporteTesisAplicable_Aux;
import Servicio.Repository;
import Servicio.Service_Locator;
import Vista.IDraggedScene;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;


public class Reporte_Tutor_Est_JefeArea_Controller implements Initializable, IDraggedScene{

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
    private JFXComboBox<String> comboBox_FiltrarBusq;

    @FXML
    private JFXTextField textField_Buscar;

    @FXML
    private JFXTreeTableView<Reporte_Tutor_Est_Jefe> table;

    @FXML
    private TreeTableColumn<Reporte_Tutor_Est_Jefe, String> columna_Tutor;

    @FXML
    private TreeTableColumn<Reporte_Tutor_Est_Jefe, String> columna_adiestrado;

    @FXML
    private TreeTableColumn<Reporte_Tutor_Est_Jefe, String> columna_area;

    @FXML
    private TreeTableColumn<Reporte_Tutor_Est_Jefe, String> columna_jefeArea;

    @FXML
    private JFXButton button_Salir;


    /*
Declaraciones
 */

    public static void setStage(Stage stage) {
        Reporte_Tutor_Est_JefeArea_Controller.stage = stage;
    }

    private static Stage stage;
    Busquedas busqueda;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Pa que la pantalla se mueva
        onDraggedScene(panel);

        ConfigurarMinimizarMaximizar(panel,null,botonCerrarVentana);


        ObservableList<String> tiposDeBusq = FXCollections.observableArrayList();
        tiposDeBusq.add("Tutor");
        tiposDeBusq.add("Estudiante");
        tiposDeBusq.add("Área");
        tiposDeBusq.add("Jefe de área");
         comboBox_FiltrarBusq.setItems(tiposDeBusq);



        try {

            busqueda=new Busquedas();


            columna_Tutor.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Reporte_Tutor_Est_Jefe, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Reporte_Tutor_Est_Jefe, String> param) {
                    return param.getValue().getValue().nombreTutorProperty();
                }
            });
            columna_adiestrado.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Reporte_Tutor_Est_Jefe, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Reporte_Tutor_Est_Jefe, String> param) {
                    return param.getValue().getValue().nombreEstProperty();
                }
            });
            columna_area.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Reporte_Tutor_Est_Jefe, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Reporte_Tutor_Est_Jefe, String> param) {
                    return param.getValue().getValue().areaProperty();
                }
            });
            columna_jefeArea.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Reporte_Tutor_Est_Jefe, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Reporte_Tutor_Est_Jefe, String> param) {
                    return param.getValue().getValue().jefeAreaProperty();
                }
            });
            button_Imprimir.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    try {
                        Connection myConnection = Repository.getConnetion();

                        JasperFillManager.fillReportToFile("src/Reportes/iReport/Reporte_Tutor_Area_Estudiante.jasper", new HashMap(), myConnection);
                        JasperViewer.viewReport("src/Reportes/iReport/Reporte Tutor_Estudiante_Area.jrprint", false, false);
                    } catch (Exception e) {
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

                    String filtro = textField_Buscar.getText();
                    String buscarPor = null;

                    if (comboBox_FiltrarBusq.getSelectionModel().getSelectedItem() == null)
                        buscarPor = "";
                    else
                        buscarPor = comboBox_FiltrarBusq.getSelectionModel().getSelectedItem();

                    try {
                        LlenarTabla(filtro, buscarPor);
                    } catch (Exception e) {
                        e.printStackTrace();
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

    private void LlenarTabla(String filtro, String buscarPor) throws Exception {
        int areaAux = 0;
         Directorio directAux = new Directorio();
         ArrayList<Reporte_Tutor_Est_Jefe> listaReporte=new ArrayList<>();

        ArrayList<Reporte_Tutor_Est_Jefe> listaFiltrada=new ArrayList<>();



        ResultSet resultSet = Service_Locator.getInstance().getReport_service().Reporte_Tutor_Est_Jefe();


        while (resultSet.next()) {
            areaAux = resultSet.getInt(4);
            directAux = busqueda.directorioJefeArea(areaAux);

            if (directAux != null) {

                directAux = busqueda.directorioJefeArea(areaAux);
                listaReporte.add(new Reporte_Tutor_Est_Jefe(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), directAux.getNombreApellido()));
            } else {
                listaReporte.add(new Reporte_Tutor_Est_Jefe(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), "Sin Jefe De Área"));
            }
        }

        listaFiltrada=busqueda.buscarParaReporteTutorJefe(filtro,buscarPor,listaReporte);
        ObservableList<Reporte_Tutor_Est_Jefe> lis = FXCollections.observableArrayList(listaFiltrada);



        TreeItem<Reporte_Tutor_Est_Jefe> item = new RecursiveTreeItem<>(lis, RecursiveTreeObject::getChildren);
        table.setRoot(item);
        table.setShowRoot(false);
    }



    public class Reporte_Tutor_Est_Jefe extends RecursiveTreeObject<Reporte_Tutor_Est_Jefe> {

        private StringProperty nombreTutor;
        private StringProperty nombreEst;
        private StringProperty area;
        private StringProperty jefeArea;


        public String getNombreTutor() {
            return nombreTutor.get();
        }

        public StringProperty nombreTutorProperty() {
            return nombreTutor;
        }

        public String getNombreEst() {
            return nombreEst.get();
        }

        public StringProperty nombreEstProperty() {
            return nombreEst;
        }

        public String getArea() {
            return area.get();
        }

        public StringProperty areaProperty() {
            return area;
        }

        public String getJefeArea() {
            return jefeArea.get();
        }

        public StringProperty jefeAreaProperty() {
            return jefeArea;
        }

        public Reporte_Tutor_Est_Jefe(String nombreTutor, String nombreEst, String area, String jefeArea) {
            this.nombreTutor = new SimpleStringProperty(nombreTutor);
            this.nombreEst = new SimpleStringProperty(nombreEst);
            this.area = new SimpleStringProperty(area);
            this.jefeArea =new SimpleStringProperty( jefeArea);
        }




    }
    }
