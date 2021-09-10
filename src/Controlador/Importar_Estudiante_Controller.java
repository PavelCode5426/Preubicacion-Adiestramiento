package Controlador;

import Auxiliar.Estudiante_Cujae_Data;
import Auxiliar.FiltrarTablas;
import Modelo.Especialidad;
import Modelo.Estudiante;
import Servicio.Especialidad_Service;
import Servicio.Graduado_Service;
import Servicio.Service_Locator;
import Vista.GetPantalla;
import Vista.IDraggedScene;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
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
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

public class Importar_Estudiante_Controller implements Initializable, IDraggedScene
{
    @FXML
    private JFXTreeTableView<Estudiante_Cujae_Data> table;
    @FXML
    private TreeTableColumn<Estudiante_Cujae_Data,String> colum_nom;
    @FXML
    private TreeTableColumn<Estudiante_Cujae_Data,String> colum_carnet;
    @FXML
    private TreeTableColumn<Estudiante_Cujae_Data,String> colum_esp;
    @FXML
    private TreeTableColumn<Estudiante_Cujae_Data,JFXCheckBox> colum_tes;
    @FXML
    private JFXTextField bus_impor;
    @FXML
    private JFXButton put;
    @FXML
    private JFXButton take;
    @FXML
    private JFXComboBox<String> esp_cb;
    @FXML
    private JFXTreeTableView<Estudiante_Cujae_Data> table1;
    @FXML
    private TreeTableColumn<Estudiante_Cujae_Data,String> colum_nom1;
    @FXML
    private TreeTableColumn<Estudiante_Cujae_Data,String> colum_carnet1;
    @FXML
    private TreeTableColumn<Estudiante_Cujae_Data,String> colum_esp1;
    @FXML
    private TreeTableColumn<Estudiante_Cujae_Data, JFXCheckBox> colum_tes1;
    @FXML
    private JFXTextField busar_est;
    @FXML
    private JFXButton save;

    @FXML
    private AnchorPane paneTitulo;
    @FXML
    private JFXButton botonCerrarVentana;
    @FXML
    private JFXButton botonMinimizarVentana;
    @FXML
    private AnchorPane hbox;
    @FXML
    private AnchorPane PaneExterior;


    /**
     * Declaracion de Variables
     */
    private static Stage stage;
    private static EspecialistaRH_Controller padre;
    private ObservableList<Estudiante_Cujae_Data> cujaeData;
    private ObservableList<Estudiante_Cujae_Data> importData;
    private ArrayList<Estudiante> estudiantesInApp;
    private ArrayList<Estudiante> importInApp;
    private ArrayList<Especialidad> especialidadsSECUJAE;
    private ObservableList<Estudiante_Cujae_Data> AUXcujaeData;
    private ObservableList<Estudiante_Cujae_Data> AUXimportData;


    public static void setStage(Stage stage) {
        Importar_Estudiante_Controller.stage = stage;
    }
    public static void setPadre(EspecialistaRH_Controller padre) {
        Importar_Estudiante_Controller.padre = padre;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        onDraggedScene(PaneExterior);
        ConfigurarMinimizarMaximizar(PaneExterior,botonMinimizarVentana,botonCerrarVentana);


        try {
            colum_nom.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Estudiante_Cujae_Data, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Estudiante_Cujae_Data, String> param) {
                    return param.getValue().getValue().nombreProperty();
                }
            });
            colum_nom1.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Estudiante_Cujae_Data, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Estudiante_Cujae_Data, String> param) {
                    return param.getValue().getValue().nombreProperty();
                }
            });
            colum_carnet.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Estudiante_Cujae_Data, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Estudiante_Cujae_Data, String> param) {
                    return param.getValue().getValue().carnetProperty();
                }
            });
            colum_carnet1.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Estudiante_Cujae_Data, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Estudiante_Cujae_Data, String> param) {
                    return param.getValue().getValue().carnetProperty();
                }
            });
            colum_esp.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Estudiante_Cujae_Data, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Estudiante_Cujae_Data, String> param) {
                    return param.getValue().getValue().especialidadProperty();
                }
            });
            colum_esp1.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Estudiante_Cujae_Data, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Estudiante_Cujae_Data, String> param) {
                    return param.getValue().getValue().especialidadProperty();
                }
            });
            colum_tes.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Estudiante_Cujae_Data, JFXCheckBox>, ObservableValue<JFXCheckBox>>() {
                @Override
                public ObservableValue<JFXCheckBox> call(TreeTableColumn.CellDataFeatures<Estudiante_Cujae_Data, JFXCheckBox> param) {
                    return param.getValue().getValue().isAplicableProperty();
                }
            });
            colum_tes1.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Estudiante_Cujae_Data, JFXCheckBox>, ObservableValue<JFXCheckBox>>() {
                @Override
                public ObservableValue<JFXCheckBox> call(TreeTableColumn.CellDataFeatures<Estudiante_Cujae_Data, JFXCheckBox> param) {
                    return param.getValue().getValue().isAplicableProperty();
                }
            });



            estudiantesInApp = Service_Locator.getInstance().getGraduado_service().Graduados_sin_terminar_List(true);
            especialidadsSECUJAE=Service_Locator.getInstance().getEspecialidad_service().Especialidad_List_From_SECUJAE();

            LLenarDatos();
            LlenarCombobox();
            LLenarTablas(null);

            esp_cb.getEditor().setOnKeyTyped(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event)
                {
                    String text=esp_cb.getEditor().getText();
                    String e=event.getCharacter();
                    if (e.equals("\n")||e.equals("\t")||e.equals("\b"))
                        e="";
                    text+=e;
                    LlenarCombobox();
                    if (!text.equals(""))
                    {
                        esp_cb.show();
                        FiltrarCombobox(text);
                    }
                }
            });
            esp_cb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    Predicate<Estudiante_Cujae_Data> predicate;
                    FiltrarTablas<Estudiante_Cujae_Data> filtrarTablas = new FiltrarTablas<>();
                    AUXcujaeData=FXCollections.observableArrayList(cujaeData);
                    if (!busar_est.getText().equals("")) {
                        predicate = new Predicate<Estudiante_Cujae_Data>() {
                            @Override
                            public boolean test(Estudiante_Cujae_Data estudiante_cujae_data) {
                                boolean is = true;
                                if (estudiante_cujae_data.getNombre().toLowerCase().startsWith(busar_est.getText().toLowerCase()))
                                    is = false;
                                return is;
                            }
                        };
                        AUXcujaeData = filtrarTablas.FiltrarTabla(AUXcujaeData, predicate);
                    }
                    if (!esp_cb.getSelectionModel().isEmpty()) {
                        predicate = new Predicate<Estudiante_Cujae_Data>() {
                            @Override
                            public boolean test(Estudiante_Cujae_Data estudiante_cujae_data) {
                                boolean is = true;
                                if (estudiante_cujae_data.getEspecialidad().toLowerCase().startsWith(esp_cb.getSelectionModel().getSelectedItem().toLowerCase()))
                                    is = false;
                                return is;
                            }
                        };
                        AUXcujaeData = filtrarTablas.FiltrarTabla(AUXcujaeData, predicate);
                    }
                    LLenarTablas(AUXcujaeData);
                }
            });
            busar_est.setOnKeyTyped(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    Predicate<Estudiante_Cujae_Data> predicate;
                    FiltrarTablas<Estudiante_Cujae_Data> filtrarTablas = new FiltrarTablas<>();

                    String e = event.getCharacter();
                    if (e.equals("\n") || e.equals("\t") || e.equals("\b"))
                        e = "";
                    final String text = busar_est.getText() + e;
                    AUXcujaeData=FXCollections.observableArrayList(cujaeData);
                    if (!text.equals("")) {
                        predicate = new Predicate<Estudiante_Cujae_Data>() {
                            @Override
                            public boolean test(Estudiante_Cujae_Data estudiante_cujae_data) {
                                boolean is = true;
                                if (estudiante_cujae_data.getNombre().toLowerCase().startsWith(text.toLowerCase()))
                                    is = false;
                                return is;
                            }
                        };
                        AUXcujaeData = filtrarTablas.FiltrarTabla(AUXcujaeData, predicate);
                    }
                    if (!esp_cb.getSelectionModel().isEmpty())
                    {
                        predicate = new Predicate<Estudiante_Cujae_Data>() {
                            @Override
                            public boolean test(Estudiante_Cujae_Data estudiante_cujae_data) {
                                boolean is = true;
                                if (estudiante_cujae_data.getEspecialidad().toLowerCase().startsWith(esp_cb.getSelectionModel().getSelectedItem().toLowerCase()))
                                    is = false;
                                return is;
                            }
                        };
                        AUXcujaeData = filtrarTablas.FiltrarTabla(AUXcujaeData, predicate);
                    }
                    LLenarTablas(AUXcujaeData);
                }
            });
            bus_impor.setOnKeyTyped(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    FiltrarTablas<Estudiante_Cujae_Data> filtrarTablas = new FiltrarTablas<>();
                    String e = event.getCharacter();
                    if (e.equals("\n") || e.equals("\t") || e.equals("\b"))
                        e = "";
                    final String text = bus_impor.getText() + e;
                    AUXimportData=FXCollections.observableArrayList(importData);
                    if (!text.equals(""))
                    {
                        Predicate<Estudiante_Cujae_Data> predicate = new Predicate<Estudiante_Cujae_Data>() {
                            @Override
                            public boolean test(Estudiante_Cujae_Data estudiante_cujae_data) {
                                boolean is = true;
                                if (estudiante_cujae_data.getNombre().toLowerCase().startsWith(text.toLowerCase()))
                                    is = false;
                                return is;
                            }
                        };
                        AUXimportData = filtrarTablas.FiltrarTabla(importData, predicate);
                    }
                    LLenarTablas2(AUXimportData);
                }
            });
            put.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    put_Estudiante();
                    ClearAll();
                }
            });
            take.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    take_Estudiante();
                    ClearAll();
                }
            });
            table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Estudiante_Cujae_Data>>() {
                @Override
                public void changed(ObservableValue<? extends TreeItem<Estudiante_Cujae_Data>> observable, TreeItem<Estudiante_Cujae_Data> oldValue, TreeItem<Estudiante_Cujae_Data> newValue) {
                    put.setDisable(table.getSelectionModel().isEmpty());
                    take.setDisable(true);
                }
            });
            table1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Estudiante_Cujae_Data>>() {
                @Override
                public void changed(ObservableValue<? extends TreeItem<Estudiante_Cujae_Data>> observable, TreeItem<Estudiante_Cujae_Data> oldValue, TreeItem<Estudiante_Cujae_Data> newValue) {
                    take.setDisable(table1.getSelectionModel().isEmpty());
                    put.setDisable(true);
                }
            });
            save.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        SaveData();
                        stage.close();
                        padre.LlenarTablaGraduados();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION,"Información","Estudiantes importados correctamente","DARKGREEN");
                    }
                    catch (Exception e)
                    {

                        e.printStackTrace();
                    }
                }
            });
            importData.addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                        save.setDisable(importData.isEmpty());
                }
            });






        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void take_Estudiante()
    {
        Iterator<Integer> iterator=table1.getSelectionModel().getSelectedIndices().iterator();
        while (iterator.hasNext())
            importData.add(cujaeData.remove(iterator.next().intValue()));
        LLenarTablas2(importData);
        LLenarTablas(cujaeData);
    }
    public void put_Estudiante()
    {
        Iterator<Integer> iterator=table.getSelectionModel().getSelectedIndices().iterator();
        while (iterator.hasNext())
            cujaeData.add(importData.remove(iterator.next().intValue()));
        LLenarTablas2(importData);
        LLenarTablas(cujaeData);
    }
    private void EliminarCoincidencias()
    {
        Iterator<Estudiante> iterator=estudiantesInApp.iterator();
        while (iterator.hasNext())
        {
            Estudiante next=iterator.next();
            Predicate<Estudiante> predicate=new Predicate<Estudiante>() {
                @Override
                public boolean test(Estudiante estudiante_cujae_data) {
                    boolean is=false;
                    if (estudiante_cujae_data.getNidentidad().equals(next.getNidentidad()))
                        is=true;
                    return is;
                }
            };
            Predicate<Estudiante_Cujae_Data> predicate1=new Predicate<Estudiante_Cujae_Data>() {
                @Override
                public boolean test(Estudiante_Cujae_Data estudiante_cujae_data)
                {
                    boolean is=false;
                    if (estudiante_cujae_data.getCarnet().equals(next.getNidentidad()))
                        is=true;
                    return is;
                }
            };
            importInApp.removeIf(predicate);
            cujaeData.removeIf(predicate1);
        }
    }
    private void LLenarDatos() throws Exception
    {
        cujaeData= FXCollections.observableArrayList();
        importData=FXCollections.observableArrayList();
        importInApp=new ArrayList<>();
        ResultSet resultSet=Service_Locator.getInstance().getGraduado_service().Estudiantes_From_SECUJAE();
        while (resultSet.next())
        {
            Estudiante es=new Estudiante(resultSet.getInt(1),true,resultSet.getBoolean(6),-1,resultSet.getString(7),resultSet.getString(2),resultSet.getDate(8),LocalDate.now().getYear(),resultSet.getString(3),resultSet.getInt(9),null);
            importInApp.add(es);
            cujaeData.add(new Estudiante_Cujae_Data(es.getId(),es.isTesisAplicable(),es.getNombreApellidos(),es.getNidentidad(),resultSet.getString(4)));
        }
    }
    private void LLenarTablas(ObservableList<Estudiante_Cujae_Data> data)
    {
        if (data==null)
        {
            final TreeItem<Estudiante_Cujae_Data> item = new RecursiveTreeItem<>(cujaeData, RecursiveTreeObject::getChildren);
            table1.setShowRoot(false);
            table1.setRoot(item);
            AUXcujaeData = FXCollections.observableArrayList(cujaeData);
        }
        else
        {
            final TreeItem<Estudiante_Cujae_Data> item = new RecursiveTreeItem<>(data, RecursiveTreeObject::getChildren);
            table1.setShowRoot(false);
            table1.setRoot(item);
        }
    }
    private void LLenarTablas2(ObservableList<Estudiante_Cujae_Data> data)
    {
        if (data==null)
        {
            final TreeItem<Estudiante_Cujae_Data> item = new RecursiveTreeItem<>(importData, RecursiveTreeObject::getChildren);
            table.setShowRoot(false);
            table.setRoot(item);
            AUXimportData = FXCollections.observableArrayList(importData);
        }
        else
        {
            final TreeItem<Estudiante_Cujae_Data> item = new RecursiveTreeItem<>(data, RecursiveTreeObject::getChildren);
            table.setShowRoot(false);
            table.setRoot(item);
        }
    }
    private void LlenarCombobox()
    {
        esp_cb.getItems().clear();
        Iterator<Especialidad> iterator=especialidadsSECUJAE.listIterator();
        while (iterator.hasNext())
            esp_cb.getItems().add(iterator.next().getNombre());
    }
    private void FiltrarCombobox(String text)
    {
        Predicate<String> predicate=new Predicate<String>() {
            @Override
            public boolean test(String s) {
                boolean is=true;
                if (s.toLowerCase().startsWith(text.toLowerCase()))
                    is=false;
                return is;
            }
        };
        esp_cb.getItems().removeIf(predicate);
    }
    private void ClearAll()
    {
        esp_cb.getSelectionModel().clearSelection();
        bus_impor.setText("");
        busar_est.setText("");
    }
    private void SaveData() throws Exception
    {
        Estudiante next=null;
        Especialidad_Service especialidad_service=Service_Locator.getInstance().getEspecialidad_service();
        Graduado_Service graduado_service=Service_Locator.getInstance().getGraduado_service();
        Deque<Estudiante_Cujae_Data> deque=new LinkedList<>(importData);
        Iterator<Estudiante> iterator=importInApp.iterator();
        while (!deque.isEmpty())
        {
            if (!iterator.hasNext())
                iterator=importInApp.iterator();
            next=iterator.next();
            if (deque.peek().getIdEst()==next.getId())
            {
                graduado_service.Set_Adiestramiento_FROM_SECUJAE(next.getId(),true);
                int idEsp=especialidad_service.EspecialidadByNameOffline(deque.poll().getEspecialidad()).getId();
                next.setId(-1);
                next.setEspecialidad(idEsp);
                graduado_service.CreateGraduado(next);
            }
        }
    }




}
