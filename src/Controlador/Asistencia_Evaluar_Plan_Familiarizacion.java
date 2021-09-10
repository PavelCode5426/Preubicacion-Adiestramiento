package Controlador;

import Auxiliar.Excepciones.AppException;
import Modelo.*;
import Servicio.Planificacion_Familiarizacion_Service;
import Servicio.Service_Locator;
import Vista.GetPantalla;
import Vista.IDraggedScene;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class Asistencia_Evaluar_Plan_Familiarizacion implements Initializable, IDraggedScene
{
    @FXML
    private Label nom_label;
    @FXML
    private JFXComboBox<String> nota_cb;
    @FXML
    private JFXTreeTableView<Evaluar_Plan_Data> table;
    @FXML
    private TreeTableColumn<Evaluar_Plan_Data,String> colum_nombre;
    @FXML
    private TreeTableColumn<Evaluar_Plan_Data,String> colum_resp;
    @FXML
    private TreeTableColumn<Evaluar_Plan_Data,String> colum_lugar;
    @FXML
    private TreeTableColumn<Evaluar_Plan_Data,String> colum_date;
    @FXML
    private TreeTableColumn<Evaluar_Plan_Data,String> colum_time;
    @FXML
    private TreeTableColumn<Evaluar_Plan_Data,String> colum_desc;
    @FXML
    private TreeTableColumn<Evaluar_Plan_Data,JFXCheckBox> colum_asist;
    @FXML
    private Label label_porciento;
    @FXML
    private JFXButton acept;
    @FXML
    private JFXButton cancel;
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

    //Declarar Variables
    private static int IDEstudiante;
    private static int IDEstudiantePlan;

    public static void setIDEstudiantePlan(int IDEstudiantePlan) {
        Asistencia_Evaluar_Plan_Familiarizacion.IDEstudiantePlan = IDEstudiantePlan;
    }

    private static Stage stage;

    public static void setPadre(Pantalla_Jefe_Area_Controller padre) {
        Asistencia_Evaluar_Plan_Familiarizacion.padre = padre;
    }

    private static Pantalla_Jefe_Area_Controller padre;


    private PlanFamiliarizacion planFamiliarizacion;
    private ArrayList<Actividad> actividadesPlanFamiliarizacion;
    private ArrayList<ActividadesEstudiante> actividadesEstudiantes;
    private ArrayList<Directorio> directorios;
    private ArrayList<Area> areas;
    private ObservableList<Evaluar_Plan_Data> planData;
    private EstudiantePlanFamiliarizacion estudiantePlanFamiliarizacion;





    public static void setStage(Stage stage) {
        Asistencia_Evaluar_Plan_Familiarizacion.stage = stage;
    }

    public static void setIDEstudiante(int IDEstudiante) {
        Asistencia_Evaluar_Plan_Familiarizacion.IDEstudiante = IDEstudiante;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //Pa que la pantalla se mueva
        onDraggedScene(PaneExterior);
        //Poner pantalla linda
        ConfigurarMinimizarMaximizar(PaneExterior,botonMinimizarVentana,botonCerrarVentana);

        try {
            colum_nombre.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Evaluar_Plan_Data, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Evaluar_Plan_Data, String> param) {
                    return param.getValue().getValue().nombreProperty();
                }
            });
            colum_asist.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Evaluar_Plan_Data, JFXCheckBox>, ObservableValue<JFXCheckBox>>() {
                @Override
                public ObservableValue<JFXCheckBox> call(TreeTableColumn.CellDataFeatures<Evaluar_Plan_Data, JFXCheckBox> param) {
                    return param.getValue().getValue().asistProperty();
                }
            });
            colum_resp.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Evaluar_Plan_Data, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Evaluar_Plan_Data, String> param) {
                    return param.getValue().getValue().responsableProperty();
                }
            });
            colum_lugar.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Evaluar_Plan_Data, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Evaluar_Plan_Data, String> param) {
                    return param.getValue().getValue().lugarProperty();
                }
            });
            colum_date.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Evaluar_Plan_Data, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Evaluar_Plan_Data, String> param) {
                    return param.getValue().getValue().fechaProperty();
                }
            });
            colum_time.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Evaluar_Plan_Data, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Evaluar_Plan_Data, String> param) {
                    return param.getValue().getValue().horaProperty();
                }
            });
            colum_desc.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Evaluar_Plan_Data, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Evaluar_Plan_Data, String> param) {
                    return param.getValue().getValue().descripcionProperty();
                }
            });


            nota_cb.getItems().add("Excelente");
            nota_cb.getItems().add("Bien");
            nota_cb.getItems().add("Regular");
            nota_cb.getItems().add("Mal");

            LLenarTablas_Data();
            double porciento=Porciento();
            label_porciento.setText("Porciento de Inasistencia: "+porciento+"%");
            if (isAllSelected())
                nota_cb.setDisable(false);
            if (porciento>=20)
            {
                nota_cb.setDisable(true);
                nota_cb.getSelectionModel().select("Mal");
                label_porciento.setTextFill(Color.DARKRED);
            }
            else if (porciento>=10)
                label_porciento.setTextFill(Color.DARKORANGE);
            else
                label_porciento.setTextFill(Color.DARKGREEN);





            if (estudiantePlanFamiliarizacion.getNota()!=null) {
                acept.setDisable(true);
                GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION,"Información","Este estudiante ya ha sido evaluado,no podrá modificar la información","DARKGREEN");
                switch (estudiantePlanFamiliarizacion.getNota()) {
                    case 5:
                        nota_cb.getSelectionModel().select("Excelente");
                        break;
                    case 4:
                        nota_cb.getSelectionModel().select("Bien");
                        break;
                    case 3:
                        nota_cb.getSelectionModel().select("Regular");
                        break;
                    case 2:
                        nota_cb.getSelectionModel().select("Mal");
                        break;
                }
                nota_cb.setDisable(true);
            }

            acept.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        SaveData();
                        stage.close();
                        padre.Llenar_Tabla_Familiarizacion();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION,"Información","Estudiante evaludado correctamente","DARKGREEN");
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR,"Error", AppException.getJustMensaje(e.getMessage()),"DARKRED");
                    }
                }
            });
            cancel.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    stage.close();
                }
            });

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    private Directorio BuscarDirectorioByID(int id)    {
        Iterator<Directorio>iterator=directorios.iterator();
        Directorio next=null;
        boolean b=false;
        while (iterator.hasNext()&&!b)
        {
            next=iterator.next();
            if (next.getId()==id)
                b=true;
        }
        if (!b)
            next=null;
        return next;
    }
    private Area BuscarAreaByID(int id)    {
        Iterator<Area>iterator=areas.iterator();
        Area next=null;
        boolean b=false;
        while (iterator.hasNext()&&!b)
        {
            next=iterator.next();
            if (next.getId()==id)
                b=true;
        }
        if (!b)
            next=null;
        return next;
    }

    private void LLenarTablas_Data() throws Exception
    {
        planFamiliarizacion=Service_Locator.getInstance().getPlanificacion_familiarizacion_service().get_Plan_Familiarizacion_last();
        nom_label.setText(nom_label.getText()+" "+ Service_Locator.getInstance().getGraduado_service().getGraduado(IDEstudiante).getNombreApellidos());

          if (IDEstudiantePlan!=-1)
        {
            estudiantePlanFamiliarizacion = Service_Locator.getInstance().getPlanificacion_familiarizacion_service().getEstudiantePlanFamiliarizacion_Relacion(IDEstudiantePlan);

        }
        else
        {
            estudiantePlanFamiliarizacion = new EstudiantePlanFamiliarizacion(IDEstudiantePlan, IDEstudiante, planFamiliarizacion.getId(), 0);
             }

        planData= FXCollections.observableArrayList();
        ResultSet resultSet=Service_Locator.getInstance().getPlanificacion_familiarizacion_service().get_Actividades_From_Estudiante(IDEstudiante);
        while (resultSet.next())
        {
            Boolean a;
            if (resultSet.getObject(7)==null)
                a=null;
            else
                a=resultSet.getBoolean(7);
            planData.add(new Evaluar_Plan_Data(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getDate(4).toString()
                    ,resultSet.getTime(5).toString(),resultSet.getString(6),a,resultSet.getInt(8),0,resultSet.getInt(9)));
        }
        final TreeItem<Evaluar_Plan_Data> item=new RecursiveTreeItem<>(planData,RecursiveTreeObject::getChildren);
        table.setShowRoot(false);
        table.setRoot(item);

    }
    private void SaveData() throws Exception
    {
        if (!nota_cb.getSelectionModel().isEmpty()&&isAllSelected())
            switch (nota_cb.getSelectionModel().getSelectedItem())
            {
                case "Mal":
                    estudiantePlanFamiliarizacion.setNota(2);
                    break;
                case "Excelente":
                    estudiantePlanFamiliarizacion.setNota(5);
                    break;
                case "Bien":
                    estudiantePlanFamiliarizacion.setNota(4);
                    break;
                case "Regular":
                    estudiantePlanFamiliarizacion.setNota(3);
                    break;
                default:
                    estudiantePlanFamiliarizacion.setNota(null);
                    break;
            }

        Planificacion_Familiarizacion_Service service=Service_Locator.getInstance().getPlanificacion_familiarizacion_service();
        service.Create_Update_Relacion_EstudiantePlanfamiliarizacion(estudiantePlanFamiliarizacion);
        ActividadesEstudiante actividadesEstudiante=null;
        Evaluar_Plan_Data next=null;
        Iterator<Evaluar_Plan_Data> iterator=planData.iterator();
        while (iterator.hasNext())
        {
            next=iterator.next();
            if (next.getAsist().isSelected())
                actividadesEstudiante=new ActividadesEstudiante(next.getIdInner(),IDEstudiante,next.getIdTarea(),0);
            else if (next.getAsist().isIndeterminate())
                actividadesEstudiante=new ActividadesEstudiante(next.getIdInner(),IDEstudiante,next.getIdTarea(),-1);
            else
                actividadesEstudiante=new ActividadesEstudiante(next.getIdInner(),IDEstudiante,next.getIdTarea(),1);
            service.Create_Update_Relacion_ActividadesEstudiante(actividadesEstudiante);
        }

    }
    private boolean isAllSelected()
    {
        boolean is=true;
        Iterator<Evaluar_Plan_Data>iterator=planData.iterator();
        while (is&&iterator.hasNext())
        {
            Evaluar_Plan_Data x=iterator.next();
            if (x.asistProperty().getValue().isIndeterminate())
                is=false;
        }
        return is;
    }
    private double Porciento()
    {
        int au=0;
        int as=0;
        double tot=0.0;
        Iterator<Evaluar_Plan_Data>iterator=planData.iterator();
        JFXCheckBox x=null;
        while (iterator.hasNext())
        {
            x=iterator.next().getAsist();
            if (!x.isIndeterminate()&&x.isSelected())
                as++;
            else if (!x.isIndeterminate()&&!x.isSelected())
                au++;
        }
        if (as+au==0)
            tot=0;
        else
        {
            as+=au;
            tot = au*100/as;
        }
        return tot;
    }
































    private class Evaluar_Plan_Data extends RecursiveTreeObject<Evaluar_Plan_Data>
    {
        private SimpleStringProperty nombre;
        private SimpleStringProperty responsable;
        private SimpleStringProperty lugar;
        private SimpleStringProperty fecha;
        private SimpleStringProperty hora;
        private SimpleStringProperty descripcion;
        private SimpleObjectProperty<JFXCheckBox> asist;
        private int idTarea;
        private int operacion;
        private int idInner;

        private Evaluar_Plan_Data(String nombre,String resp,String lug,String fecha,String hora,String desc,Boolean asist,int idTarea,int opera,int inner)
        {
            this.nombre=new SimpleStringProperty(nombre);
            this.responsable=new SimpleStringProperty(resp);
            this.lugar=new SimpleStringProperty(lug);
            this.fecha=new SimpleStringProperty(fecha);
            this.hora=new SimpleStringProperty(hora);
            this.descripcion=new SimpleStringProperty(desc);
            this.asist=new SimpleObjectProperty<>(new JFXCheckBox());
            this.idTarea=idTarea;
            this.operacion=opera;
            this.idInner=inner;

            this.asist.getValue().setAllowIndeterminate(true);

            if (asist==null)
                this.asist.getValue().setIndeterminate(true);
            else
                this.asist.getValue().setSelected(asist);

            this.asist.getValue().indeterminateProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    JFXCheckBox box= asistProperty().getValue();
                    box.setAllowIndeterminate(false);
                    box.setIndeterminate(false);
                    box.setSelected(true);
                    operacion=1;
                    double a=Porciento();
                    label_porciento.setText("Porciento de Inasistencia: "+a+"%");
                    if (isAllSelected())
                        nota_cb.setDisable(false);
                    if (a>=20)
                    {
                        nota_cb.setDisable(true);
                        nota_cb.getSelectionModel().select("Mal");
                    }
                }
            });


            this.asist.getValue().selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
                {
                    JFXCheckBox box= asistProperty().getValue();
                    box.setSelected(newValue);
                    operacion=1;
                    double a=Porciento();
                    label_porciento.setText("Porciento de Inasistencia: "+a+"%");
                    if (isAllSelected())
                    {
                        nota_cb.setDisable(false);
                        nota_cb.getSelectionModel().clearSelection();
                    }
                    if (a>=20)
                    {
                        nota_cb.setDisable(true);
                        nota_cb.getSelectionModel().select("Mal");
                        label_porciento.setTextFill(Color.DARKRED);
                    }
                    else if (a>=10)
                        label_porciento.setTextFill(Color.DARKORANGE);
                    else
                        label_porciento.setTextFill(Color.DARKGREEN);
                }
            });
        }

        public String getNombre()
        {
            return nombre.get();
        }

        public SimpleStringProperty nombreProperty() {
            return nombre;
        }

        public String getResponsable() {
            return responsable.get();
        }

        public SimpleStringProperty responsableProperty() {
            return responsable;
        }

        public String getLugar() {
            return lugar.get();
        }

        public SimpleStringProperty lugarProperty() {
            return lugar;
        }

        public String getFecha() {
            return fecha.get();
        }

        public SimpleStringProperty fechaProperty() {
            return fecha;
        }

        public String getHora() {
            return hora.get();
        }

        public SimpleStringProperty horaProperty() {
            return hora;
        }

        public String getDescripcion() {
            return descripcion.get();
        }

        public int getIdInner() {
            return idInner;
        }

        public SimpleStringProperty descripcionProperty() {
            return descripcion;
        }

        public JFXCheckBox getAsist() {
            return asist.get();
        }

        public SimpleObjectProperty<JFXCheckBox> asistProperty() {
            return asist;
        }

        public int getIdTarea() {
            return idTarea;
        }

        public int getOperacion() {
            return operacion;
        }
    }
}
