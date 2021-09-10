package Reportes.Controladoras;

import Modelo.Area;
import Reportes.Auxiliares.ReporteEstXAreaXEspec_Aux;
import Servicio.Repository;
import Servicio.Service_Locator;
import Vista.IDraggedScene;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;


public class Reporte_EstXArea_XEspec_Controller implements Initializable, IDraggedScene{

    @FXML
    private AnchorPane panel;
    @FXML
    private Pane paneArriba;

    @FXML
    private Pane paneAbajo;

    @FXML
    private Pane paneTitulo;

    @FXML
    private Label button_Imprimir;


    @FXML
    private JFXTextField textField_Buscar;

    @FXML
    private JFXTreeTableView<ReporteEstXAreaXEspec_Aux> tablaEsp;

    @FXML
    private TreeTableColumn<ReporteEstXAreaXEspec_Aux, String> columna_nombre;

    @FXML
    private TreeTableColumn<ReporteEstXAreaXEspec_Aux, String> columna_CantEst;

    @FXML
    private JFXListView<String> listView_Areas;


    @FXML
    private JFXButton button_Salir;
    @FXML
    private JFXButton botonCerrarVentana;
    @FXML
    private HBox hbox;

    public static void setStage(Stage stage) {
        Reporte_EstXArea_XEspec_Controller.stage = stage;
    }

    private static Stage stage;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Pa que la pantalla se mueva
        onDraggedScene(panel);

        ConfigurarMinimizarMaximizar(panel,null,botonCerrarVentana);


        columna_nombre.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ReporteEstXAreaXEspec_Aux, String>,          ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ReporteEstXAreaXEspec_Aux, String> param) {
                    return param.getValue().getValue().nombreProperty();
                }
            });
            columna_CantEst.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ReporteEstXAreaXEspec_Aux, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ReporteEstXAreaXEspec_Aux, String> param) {
                    return param.getValue().getValue().cantEstProperty();
                }
            });


        try {
            LlenarListView();
        } catch (Exception e) {
            e.printStackTrace();
        }

        listView_Areas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String seleccionArea= listView_Areas.getSelectionModel().getSelectedItem();
                int seleccion=0;
                try {
                    seleccion=buscarArea(seleccionArea);
                    LlenarTabla(seleccion);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        button_Imprimir.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                try {
                    Connection myConnection=Repository.getConnetion();
                    JasperFillManager.fillReportToFile("src/Reportes/iReport/Especialidades_X_Area.jasper",new HashMap(),myConnection);
                    JasperViewer.viewReport("src/Reportes/iReport/Especialidades_X_Area.jrprint",false,false);
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



    }

    private void LlenarTabla(int areaSeleccionada) throws Exception
    {
        System.out.println(new Integer(areaSeleccionada).toString());
        ObservableList<ReporteEstXAreaXEspec_Aux> ListaDeEstudiantes= FXCollections.observableArrayList(Service_Locator.getInstance().getReport_service().Reporte_Cant_Espec_X_Area(areaSeleccionada));

        TreeItem<ReporteEstXAreaXEspec_Aux> item=new RecursiveTreeItem<ReporteEstXAreaXEspec_Aux>(ListaDeEstudiantes, RecursiveTreeObject::getChildren);
        tablaEsp.setRoot(item);
        tablaEsp.setShowRoot(false);
    }

    private void LlenarListView() throws Exception {

        if(textField_Buscar.getText().equals("")){
            ArrayList<String> listaDeAreasEnc=new ArrayList<String>();
            ArrayList<Area> listArea=(ArrayList<Area>) new Repository<Area>(new Area()).GetAll();
            for(int i=0;i<listArea.size();i++){

                    listaDeAreasEnc.add(listArea.get(i).getNombre());
                }
                ObservableList<String> listtt=FXCollections.observableArrayList(listaDeAreasEnc);
            listView_Areas.setItems(listtt);
        }

        textField_Buscar.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                try {


                    String filtro = textField_Buscar.getText();
                    ArrayList<String> areasEnc=autocompletarAreas(filtro);

                    ObservableList<String> listaEnc = FXCollections.observableArrayList();
                    for (int i = 0; i < areasEnc.size(); i++) {
                        listaEnc.add(areasEnc.get(i));

                    }
                    listView_Areas.setItems(listaEnc);



                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }


        });



    }

    public ArrayList<String> autocompletarAreas (String filtro) throws Exception {
        ArrayList<String> listaDeAreasEnc=new ArrayList<String>();
        ArrayList<Area> listArea=(ArrayList<Area>) new Repository<Area>(new Area()).GetAll();
        char tipoChar=filtro.charAt(0);

        if(Character.isLetter(tipoChar)) {
            for(int i=0;i<listArea.size();i++){
                if(listArea.get(i).getNombre().contains(filtro)){
                    listaDeAreasEnc.add(listArea.get(i).getNombre());
                }
            }
        }
        else if(Character.isDigit(tipoChar)){
            for(int i=0;i<listArea.size();i++){
                if(listArea.get(i).getId().toString().contains(filtro)){
                    listaDeAreasEnc.add(listArea.get(i).getNombre());
                }
            }
        }


        return listaDeAreasEnc;
    }

    private int buscarArea(String areaNombre) throws Exception {
        int id_Area=0;
        ArrayList<Area> litadoDeAreas=new ArrayList<Area>();
        litadoDeAreas=(ArrayList)new Repository<Area>(new Area()).GetAll();
        boolean enc=false;
        int i=0;
        while(!enc && i<litadoDeAreas.size()){
            if(litadoDeAreas.get(i).getNombre().equals(areaNombre)){
                enc=true;
                id_Area=litadoDeAreas.get(i).getId();
            }
            i++;
        }

return id_Area;
    }

}
