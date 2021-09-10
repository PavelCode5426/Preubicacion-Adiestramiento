package Controlador;

import Auxiliar.Animaciones;
import Auxiliar.FiltrarTablas;
import Auxiliar.Graduado_Asiganado;
import Auxiliar.Tupla;
import Modelo.*;
import Servicio.PreasignacionArea_Service;
import Servicio.Repository;
import Servicio.Service_Locator;
import Vista.GetPantalla;
import Vista.IDraggedScene;
import com.jfoenix.controls.*;
import com.jfoenix.controls.base.IFXLabelFloatControl;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class Asiganar_Graduado_Controller implements Initializable, IDraggedScene
{


    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTreeTableView<Graduado_Asiganado> graduadosTable;
    @FXML
    private TreeTableColumn<Graduado_Asiganado, String> gradCol;
    @FXML
    private TreeTableColumn<Graduado_Asiganado, String> espCol1;
    @FXML
    private TreeTableColumn<Graduado_Asiganado, String > annocol;
    @FXML
    private TreeTableColumn<Graduado_Asiganado, String> procedcol;
    @FXML
    private JFXTextField buscartf;
    @FXML
    private JFXButton putbtn;
    @FXML
    private JFXButton takebtn;
    @FXML
    private JFXComboBox<String> areascb;
    @FXML
    private JFXTreeTableView<Graduado_Asiganado> asiganadosTable;
    @FXML
    private TreeTableColumn<Graduado_Asiganado, String> asigCol;
    @FXML
    private TreeTableColumn<Graduado_Asiganado, String> espCol2;
    @FXML
    private TreeTableColumn<Graduado_Asiganado, String> annocol1;
    @FXML
    private TreeTableColumn<Graduado_Asiganado, String> procedcol1;
    @FXML
    private JFXTextField buscartf1;
    @FXML
    private JFXButton terminarbtn;

    @FXML
    private AnchorPane paneTitulo;
    @FXML
    private JFXButton botonCerrarVentana;
    @FXML
    private JFXButton botonMinimizarVentana;
    @FXML
    private AnchorPane hbox;

    /*
    DECLARACIONES
     */
    private static Stage stage;
    private ArrayList<Area> areas=new ArrayList<>();
    private ArrayList<Especialidad> especialides=new ArrayList<>();
    private ArrayList<LugarProcedencia> lugarProcedencias=new ArrayList<>();
    private static DirectorRH_Controller directorRH_controller;
    //private ObservableList<Graduado_Asiganado> sinAsig=FXCollections.observableArrayList();
    //private ObservableList<Graduado_Asiganado> asignados=FXCollections.observableArrayList();
    //private ArrayList<Tupla<Estudiante>> tuplas=new ArrayList<>();

    private ObservableList<Graduado_Asiganado> graduado_sinPreasignar;
    private ObservableList<Graduado_Asiganado> graduado_Preasignado;
    private ArrayList<Estudiante> sinUbicar;
    private ArrayList<Tupla<AreaPreasignada>> preubicacion;




    public static void setStage(Stage stage)
    {
        Asiganar_Graduado_Controller.stage = stage;
    }
    public static void setDirectorRH_controller(DirectorRH_Controller directorRH_controller) {
        Asiganar_Graduado_Controller.directorRH_controller = directorRH_controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //Poner pantalla linda
        onDraggedScene(pane);
        ConfigurarMinimizarMaximizar(pane,botonMinimizarVentana,botonCerrarVentana);

        try
        {

            graduadosTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            asiganadosTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            gradCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Graduado_Asiganado, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Graduado_Asiganado, String> param) {
                    return param.getValue().getValue().nombreProperty();
                }
            });
            asigCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Graduado_Asiganado, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Graduado_Asiganado, String> param) {
                    return param.getValue().getValue().nombreProperty();
                }
            });
            espCol1.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Graduado_Asiganado, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Graduado_Asiganado, String> param) {
                    return param.getValue().getValue().especialidadProperty();
                }
            });
            espCol2.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Graduado_Asiganado, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Graduado_Asiganado, String> param) {
                    return param.getValue().getValue().especialidadProperty();
                }
            });
            annocol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Graduado_Asiganado, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Graduado_Asiganado, String> param) {
                    return param.getValue().getValue().annoIngresoProperty();
                }
            });
            annocol1.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Graduado_Asiganado, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Graduado_Asiganado, String> param) {
                    return param.getValue().getValue().annoIngresoProperty();
                }
            });
            procedcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Graduado_Asiganado, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Graduado_Asiganado, String> param) {
                    return param.getValue().getValue().procedenciaProperty();
                }
            });
            procedcol1.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Graduado_Asiganado, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Graduado_Asiganado, String> param) {
                    return param.getValue().getValue().procedenciaProperty();
                }
            });


            asiganadosTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Graduado_Asiganado>>() {
                @Override
                public void changed(ObservableValue<? extends TreeItem<Graduado_Asiganado>> observable, TreeItem<Graduado_Asiganado> oldValue, TreeItem<Graduado_Asiganado> newValue) {
                    takebtn.setDisable(asiganadosTable.getSelectionModel().isEmpty());

                }
            });
            graduadosTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Graduado_Asiganado>>() {
                @Override
                public void changed(ObservableValue<? extends TreeItem<Graduado_Asiganado>> observable, TreeItem<Graduado_Asiganado> oldValue, TreeItem<Graduado_Asiganado> newValue) {
                    putbtn.setDisable(graduadosTable.getSelectionModel().isEmpty()||areascb.getSelectionModel().isEmpty());
                }
            });

            putbtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    AsignarGraduado_V500();
                    LlenarTablas_V500(getAreaINDEX(areascb.getSelectionModel().getSelectedItem()).getId());
                    FiltroporNombre(true,buscartf.getText());
                   }
            });
            takebtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    DesAsignarGraduado_V500();
                    LlenarTablas_V500(getAreaINDEX(areascb.getSelectionModel().getSelectedItem()).getId());
                    FiltroporNombre(false,buscartf1.getText());
                      }
            });
            areascb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (!areascb.getSelectionModel().isEmpty())
                    {
                        LlenarTablas_V500(getAreaINDEX(areascb.getSelectionModel().getSelectedItem()).getId());
                    }
                }
            });
            terminarbtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        SaveData();
                        directorRH_controller.LlenarTablaMAIN();
                        Service_Locator.getInstance().getNotificacion_service().EnviarNotifiacionToVicerrector("Preubicación de estudiantes realizada");
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION,"Información","Estudiantes preubicados correctamente","DARKGREEN");
                        stage.close();
                    } catch (Exception e) {
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR,"Error","Error en la App","DARKRED");
                    }

                }
            });
            areascb.selectionModelProperty().addListener(new ChangeListener<SingleSelectionModel<String>>()            {
                @Override
                public void changed(ObservableValue<? extends SingleSelectionModel<String>> observable, SingleSelectionModel<String> oldValue, SingleSelectionModel<String> newValue) {
                    LlenarTablas_V500(getAreaINDEX(areascb.getSelectionModel().getSelectedItem()).getId());
                }
            });
            areascb.getEditor().setOnKeyTyped(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    FiltrarTablas<String> filtrarTablas=new FiltrarTablas<>();
                    String text=areascb.getEditor().getText();
                    String e=event.getText();
                    if (e.equals("\b")||e.equals("\t")||e.equals("\n"))
                        e="";
                    LlenarComboBox();
                    if (!text.equals(""));
                    {
                        areascb.show();
                        areascb.setItems(filtrarTablas.FiltrarTabla(areascb.getItems(), new Predicate<String>() {
                            @Override
                            public boolean test(String s) {
                                boolean is = true;
                                if (s.toLowerCase().startsWith(text.toLowerCase()))
                                    is = false;
                                return is;
                            }
                        }));
                    }
                }
            });
            buscartf.setOnKeyTyped(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    String text=buscartf.getText();
                    String e=event.getCharacter();
                    if (e.equals("\n")||e.equals("\b")||e.equals("\t"))
                        e="";
                    text+=e;
                    if (!text.equals(""))
                        FiltroporNombre(true,text);
                    else if (areascb.getSelectionModel().isEmpty())
                        LlenarTablas_V500(0);
                    else
                        LlenarTablas_V500(getAreaINDEX(areascb.getSelectionModel().getSelectedItem()).getId());

                    System.out.println("Typing");
                }
            });
            buscartf1.setOnKeyTyped(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    String text=buscartf1.getText();
                    String e=event.getCharacter();
                    if (e.equals("\n")||e.equals("\b")||e.equals("\t"))
                        e="";
                    text+=e;
                    if (!text.equals("")&&!areascb.getSelectionModel().isEmpty())
                        FiltroporNombre(false,text);
                    else
                    if (!areascb.getSelectionModel().isEmpty())
                        LlenarTablas_V500(getAreaINDEX(areascb.getSelectionModel().getSelectedItem()).getId());
                }
            });


            asiganadosTable.setShowRoot(false);
            graduadosTable.setShowRoot(false);
            LlenarDatos_V500();
            LlenarTablas_V500(0);
            LlenarComboBox();
            InstanciarAnimaciones();



        }
        catch (Exception e)
        {
            e.printStackTrace();
            GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR,"Error","Error al inicializar datos","DARKRED");
        }

    }


    private String getEspecialidad(int id)
    {
        String a="";
        boolean is=false;
        Iterator<Especialidad> iterator=especialides.iterator();
        while (iterator.hasNext()&&!is)
        {
            Especialidad next=iterator.next();
            if (next.getId()==id)
            {
                a=next.getNombre();
                is=true;
            }
        }
        return a;
    }
    private String getArea(int id)
    {
        String a="";
        boolean is=false;
        Iterator<Area> iterator=areas.iterator();
        while (iterator.hasNext()&&!is)
        {
            Area next=iterator.next();
            if (next.getId()==id)
            {
                a=next.getNombre();
                is=true;
            }
        }
        return a;
    }
    private String getLugarProcedencia(int id)
    {
        String a="";
        boolean is=false;
        Iterator<LugarProcedencia> iterator=lugarProcedencias.iterator();
        while (iterator.hasNext()&&!is)
        {
            LugarProcedencia next=iterator.next();
            if (next.getId()==id)
            {
                a=next.getLugar();
                is=true;
            }
        }
        return a;
    }
    private Especialidad getEspecialidadINDEX(String nom)
    {
        Especialidad next=null;
        boolean is=false;
        ListIterator<Especialidad> iterator=especialides.listIterator();
        while (iterator.hasNext()&&!is)
        {
            next=iterator.next();
            if (next.getNombre()==nom)
            {

                is=true;
            }
        }
        return next;
    }
    private Area getAreaINDEX(String nom)
    {
        Area next=null;
        boolean is=false;
        ListIterator<Area> iterator=areas.listIterator();
        while (iterator.hasNext()&&!is)
        {
            next=iterator.next();
            if (next.getNombre()==nom)
            {
                is=true;
            }
        }
        return next;
    }
    private int getLugarProcedenciaINDEX(String nom)
    {
        int a=-1;
        boolean is=false;
        ListIterator<LugarProcedencia> iterator=lugarProcedencias.listIterator();
        while (iterator.hasNext()&&!is)
        {
            LugarProcedencia next=iterator.next();
            if (next.getLugar()==nom)
            {
                a=iterator.nextIndex()-1;
                is=true;
            }
        }
        return a;
    }
    private void LlenarComboBox()
    {
        areascb.getItems().clear();
        Iterator<Area> iterator=areas.iterator();
        while (iterator.hasNext())
        {
            areascb.getItems().add(iterator.next().getNombre());
        }
    }
    private void InstanciarAnimaciones()
    {
        Animaciones.AnimacionTipo1(buscartf);
        Animaciones.AnimacionTipo1(buscartf1);
        Animaciones.AnimacionTipo1(takebtn);
        Animaciones.AnimacionTipo1(putbtn);
        Animaciones.AnimacionTipo1(terminarbtn);
        Animaciones.AnimacionTipo1(areascb);
    }
    private void LlenarDatos_V500()throws Exception
    {
        areas=Service_Locator.getInstance().getArea_service().Areas_List();
        lugarProcedencias=Service_Locator.getInstance().getProcedencia_service().Procedencias_List();
        especialides=Service_Locator.getInstance().getEspecialidad_service().Especialidades_List();
        sinUbicar=Service_Locator.getInstance().getGraduado_service().Graduados_Sin_ubicacion_List();
        graduado_Preasignado=FXCollections.observableArrayList();
        graduado_sinPreasignar=FXCollections.observableArrayList();
        Iterator<AreaPreasignada> iterator=Service_Locator.getInstance().getPreasignacionArea_service().getAllAreasPreasignadas().iterator();
        preubicacion=new ArrayList<>();
        while (iterator.hasNext())
        {
            preubicacion.add(new Tupla<>(0,iterator.next()));
        }
    }
    private void LlenarTablas_V500(int Idarea)
    {
        graduado_sinPreasignar=FXCollections.observableArrayList();
        graduado_Preasignado=FXCollections.observableArrayList();

        Iterator<Estudiante> iterator=sinUbicar.iterator();
        Estudiante next=null;
        while (iterator.hasNext())
        {
            String proc="CUJAE";
            next=iterator.next();
            if (next.getProcedenciaEstudiante()!=0)
                proc=getLugarProcedencia(next.getProcedenciaEstudiante());

            int area=getAreafromEstudiante(next.getId());
            if (area==-1)
                graduado_sinPreasignar.add(new Graduado_Asiganado(next.getNombreApellidos(),getEspecialidad(next.getEspecialidad()),proc,next.getAnnoIngreso(),next.getId()));
            else if (area==Idarea)
                graduado_Preasignado.add(new Graduado_Asiganado(next.getNombreApellidos(),getEspecialidad(next.getEspecialidad()),proc,next.getAnnoIngreso(),next.getId()));
        }

        final TreeItem<Graduado_Asiganado> asiganadoTreeItem=new RecursiveTreeItem<>(graduado_sinPreasignar,RecursiveTreeObject::getChildren);
        final TreeItem<Graduado_Asiganado> asiganadoTreeItem1=new RecursiveTreeItem<>(graduado_Preasignado,RecursiveTreeObject::getChildren);

        graduadosTable.setShowRoot(false);
        asiganadosTable.setShowRoot(false);

        graduadosTable.setRoot(asiganadoTreeItem);
        asiganadosTable.setRoot(asiganadoTreeItem1);

    }
    private Estudiante getEstudianteByID(int id)
    {
        Estudiante next=null;
        boolean is=false;
        Iterator<Estudiante> iterator=sinUbicar.iterator();
        if (iterator.hasNext()&&!is)
        {
            next=iterator.next();
            if (next.getId()==id)
                is=false;
        }
        return next;
    }
    private int getAreafromEstudiante(int idEst)
    {
        int area=-1;
        Tupla<AreaPreasignada> next=null;
        Iterator<Tupla<AreaPreasignada>> iterator=preubicacion.iterator();
        while (iterator.hasNext()&&area==-1)
        {
            next=iterator.next();
            if (next.getObjeto().getEstudiante()==idEst&&next.getOperacion()>=0 && next.getOperacion()!=-1)
                area=next.getObjeto().getArea();
        }
        return area;
    }
    private void AsignarGraduado_V500()
    {
        boolean isSelect=!graduadosTable.getSelectionModel().isEmpty();
        if (isSelect)
        {
            int IDArea=getAreaINDEX(areascb.getSelectionModel().getSelectedItem()).getId();
            Iterator<TreeTablePosition<Graduado_Asiganado,?>> iterator=graduadosTable.getSelectionModel().getSelectedCells().listIterator();
            while (iterator.hasNext())
            {
                Graduado_Asiganado next=iterator.next().getTreeItem().getValue();
                ModificarDatos(1,next.getIdest(),IDArea);
            }
        }
    }
    private void DesAsignarGraduado_V500()
    {
        boolean isSelect=!asiganadosTable.getSelectionModel().isEmpty();
        if (isSelect)
        {
            int IDArea=getAreaINDEX(areascb.getSelectionModel().getSelectedItem()).getId();
            Iterator<TreeTablePosition<Graduado_Asiganado,?>> iterator=asiganadosTable.getSelectionModel().getSelectedCells().listIterator();
            while (iterator.hasNext())
            {
                Graduado_Asiganado next=iterator.next().getTreeItem().getValue();
                ModificarDatos(-1,next.getIdest(),IDArea);
            }
        }
    }
    private boolean isPreasignado(int IDEstu)
    {
        boolean is=false;
        Iterator<Tupla<AreaPreasignada>> iterator=preubicacion.iterator();
        Tupla<AreaPreasignada> next=null;
        while (iterator.hasNext()&&!is)
        {
            next=iterator.next();
            if (next.getOperacion()>=0&&next.getObjeto().getEstudiante()==IDEstu)
                is=true;
        }

        return is;
    }
    private void ModificarDatos(int op,int est,int are)
    {
        Tupla<AreaPreasignada> next=null;
        Iterator<Tupla<AreaPreasignada>> iterator=preubicacion.iterator();
        boolean is=false;
        while (iterator.hasNext()&&!is)
        {
            next=iterator.next();
            if (next.getObjeto().getEstudiante()==est) {
                next.setOperacion(op);
                next.getObjeto().setArea(are);
                next.getObjeto().setEstudiante(est);
                is=true;
            }
        }
        if (!is)
        {
            preubicacion.add(new Tupla<>(2,new AreaPreasignada(-1,est,are)));
        }
    }
    private void FiltroporNombre(boolean tablesinAsig,String nom)
    {
        ObservableList<Graduado_Asiganado> filtro=null;
        if (tablesinAsig)
        {
            Predicate<Graduado_Asiganado> predicate=new Predicate<Graduado_Asiganado>() {
                @Override
                public boolean test(Graduado_Asiganado graduado_asiganado) {
                    boolean is=true;
                    if(graduado_asiganado.getNombre().toLowerCase().startsWith(nom.toLowerCase()))
                        is=false;
                    return is;
                }
            };
            filtro=new FiltrarTablas<Graduado_Asiganado>().FiltrarTabla(graduado_sinPreasignar,predicate);
            final TreeItem<Graduado_Asiganado> sinasiganadoTreeItem=new RecursiveTreeItem<>(filtro,RecursiveTreeObject::getChildren);
            graduadosTable.setRoot(sinasiganadoTreeItem);
        }
        else
        {
            Predicate<Graduado_Asiganado> predicate=new Predicate<Graduado_Asiganado>() {
                @Override
                public boolean test(Graduado_Asiganado graduado_asiganado) {
                    boolean is=true;
                    if(graduado_asiganado.getNombre().toLowerCase().startsWith(nom.toLowerCase()))
                        is=false;
                    return is;
                }
            };
            filtro=new FiltrarTablas<Graduado_Asiganado>().FiltrarTabla(graduado_Preasignado,predicate);
            final TreeItem<Graduado_Asiganado> sinasiganadoTreeItem=new RecursiveTreeItem<>(filtro,RecursiveTreeObject::getChildren);
            asiganadosTable.setRoot(sinasiganadoTreeItem);

        }
    }

    // TODO: Codigo para Guardar la Preubicacion de los Graduados en la Tabla Auxiliar
    public void SaveData() throws Exception
    {
        PreasignacionArea_Service service=Service_Locator.getInstance().getPreasignacionArea_service();
        Iterator<Tupla<AreaPreasignada>> iterator=preubicacion.iterator();
        Tupla<AreaPreasignada> next=null;
        while (iterator.hasNext())
        {
            next=iterator.next();
            if (next.getOperacion()>=1)
                service.Create_UpdateAreaPreasignada(next.getObjeto());
            else if (next.getOperacion()==-1)
                service.Remove(next.getObjeto().getId());
        }
    }
}