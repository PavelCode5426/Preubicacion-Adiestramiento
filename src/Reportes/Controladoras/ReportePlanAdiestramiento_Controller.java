package Reportes.Controladoras;

import Auxiliar.Busquedas;
import Auxiliar.FiltrarTablas;
import Auxiliar.Graduado_Data;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class ReportePlanAdiestramiento_Controller implements Initializable, IDraggedScene {


    @FXML
    private AnchorPane anchorPane_Exterior;
    @FXML
    private VBox plan_pane1;
    @FXML
    private JFXTreeTableView<Graduado_Data> table_graduado1;
    @FXML
    private TreeTableColumn<Graduado_Data,String > nomapcol1;
    @FXML
    private TreeTableColumn<Graduado_Data,String > cicol1;
    @FXML
    private TreeTableColumn<Graduado_Data,String > especol1;
    @FXML
    private TreeTableColumn<Graduado_Data,String > annocol1;

    @FXML
    private JFXTextField buscartf1;
    @FXML
    private JFXButton button_VerPlan;
    @FXML
    private JFXButton button_Salir;

    @FXML
    private JFXButton botonCerrarVentana;
    @FXML
    private Pane paneAbajo;

    @FXML
    private Pane paneTitulo;
    @FXML
    private JFXComboBox<String> comboBox_FiltrarBusq;


    private ObservableList<Graduado_Data> tutorado;
    private ArrayList<Especialidad> especialidads;


    private ArrayList<Estudiante> estudiantes;
    Busquedas busquedas;


    public static void setStage(Stage stage) {
        ReportePlanAdiestramiento_Controller.stage = stage;
    }

    private static Stage stage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Pa que la pantalla se mueva
        onDraggedScene(anchorPane_Exterior);

        ConfigurarMinimizarMaximizar(anchorPane_Exterior,null,botonCerrarVentana);


        try {

            busquedas=new Busquedas();

            ObservableList<String> tiposDeBusq = FXCollections.observableArrayList();
            tiposDeBusq.add("Nombre y apellidos");
            tiposDeBusq.add("Carnet de identidad");
            tiposDeBusq.add("Especialidad");
            tiposDeBusq.add("Año de ingreso");
            comboBox_FiltrarBusq.setItems(tiposDeBusq);

            estudiantes=Service_Locator.getInstance().getDirectorio_service().getTutorados();
        } catch (Exception e) {
            e.printStackTrace();
        }



        table_graduado1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (table_graduado1.getSelectionModel().getSelectedIndex()!=-1) {
                    button_VerPlan.setDisable(false);
                }
                else
                {
                    button_VerPlan.setDisable(true);
                }
            }
        });


        nomapcol1.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Graduado_Data, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Graduado_Data, String> param) {
                return param.getValue().getValue().nombreProperty();
            }
        });
        cicol1.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Graduado_Data, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Graduado_Data, String> param) {
                return param.getValue().getValue().ciProperty();
            }
        });
        especol1.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Graduado_Data, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Graduado_Data, String> param) {
                return param.getValue().getValue().especialidadProperty();
            }
        });
        annocol1.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Graduado_Data, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Graduado_Data, String> param) {
                return param.getValue().getValue().annoProperty();
            }
        });


        buscartf1.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String filtro = buscartf1.getText();
                String buscarPor = null;

                if (comboBox_FiltrarBusq.getSelectionModel().getSelectedItem()==null)
                    buscarPor = "";
                else
                    buscarPor=comboBox_FiltrarBusq.getSelectionModel().getSelectedItem();

                try {
                    LlenarTabla(filtro, buscarPor);
                } catch (Exception e) {
                    e.printStackTrace();
                    //GetPantalla.panel_De_Notificacion(anchorPane_Exterior,e.getMessage(),"Error","red","Error");

                }
            }
        });

        button_Salir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });


        button_VerPlan.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

               // GetPantalla.panel_De_Notificacion(anchorPane_Exterior,"A continuación se mostrará el plan de adiestramiento del estudiante seleccionado","Notificación","Green","Notificar");

                try {
                    Connection myConnection = Repository.getConnetion();
                    HashMap myParameters = new HashMap();
                    myParameters.put("idEst", table_graduado1.getSelectionModel().getSelectedItem().getValue().getNombre());
                    JasperFillManager.fillReportToFile("src/Reportes/iReport/reporte4_3.jasper", myParameters, myConnection);
                    JasperViewer.viewReport("src/Reportes/iReport/reporte4_3.jrprint", false, false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }});



        try {
            LlenarTabla("","");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private ObservableList<Graduado_Data> ListaDeEstudiantes(String filtro, String buscarPor) throws Exception {
        String procedencia = null, espec = null;

        ObservableList<Graduado_Data> lis = FXCollections.observableArrayList();
        ArrayList<Estudiante> listaEstudiantesReporte = new ArrayList<Estudiante>();
        listaEstudiantesReporte = Service_Locator.getInstance().getDirectorio_service().getTutorados();
        ArrayList<Estudiante> estudiantes = busquedas.buscarEstudiantes(filtro, buscarPor, listaEstudiantesReporte);


        for (int i = 0; i < estudiantes.size(); i++) {
            espec=new Repository<Especialidad>(new Especialidad()).Get(estudiantes.get(i).getEspecialidad()).getNombre();
            //procedencia=new Repository<LugarProcedencia>(new LugarProcedencia()).Get(estudiantes.get(i).getProcedenciaEstudiante()).getLugar();

            lis.add(new Graduado_Data(estudiantes.get(i).getNombreApellidos(), estudiantes.get(i).getNidentidad(),espec,null,estudiantes.get(i).getAnnoIngreso().toString(),i));
        }

        return lis;
    }

    private void LlenarTabla(String filtro, String buscarPor) throws Exception {
        TreeItem<Graduado_Data> item = new RecursiveTreeItem<Graduado_Data>(ListaDeEstudiantes(filtro, buscarPor), RecursiveTreeObject::getChildren);
        table_graduado1.setRoot(item);
        table_graduado1.setShowRoot(false);
    }
}
