package Controlador;
import Auxiliar.Animaciones;
import Auxiliar.Excepciones.AppException;
import Auxiliar.Tupla;
import Auxiliar.Validacion;
import Modelo.Especialidad;
import Modelo.Estudiante;
import Modelo.LugarProcedencia;
import Servicio.Service_Locator;
import Vista.GetPantalla;
import Vista.IDraggedScene;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class Estudiante_Controller implements Initializable, IDraggedScene
{

    @FXML
    private JFXTextField nomtf;@FXML
private JFXTextField idtf;@FXML
private JFXComboBox<String> proc;@FXML
private Spinner<Integer> anno;@FXML
private JFXComboBox<String> esp;@FXML
private JFXTextField temate;@FXML
private JFXCheckBox appli;@FXML
private JFXButton acepbtn;@FXML
private JFXButton cancelbtn;
@FXML

private AnchorPane anchorPane;


    /****************************************************************************************************************************
     ***********************************************Declaraciones****************************************************************
     **************************************************************************************************************************/

    private static Stage stage;
    private static Estudiante estudiante=null;
    private static EspecialistaRH_Controller especialistaRH_controller;
    private ArrayList<Tupla<LugarProcedencia>> procedenciaTupla;
    private ArrayList<Tupla<Especialidad>> especialidadTupla;









    public static void setStage(Stage stage) {
        Estudiante_Controller.stage = stage;
    }

    public static void setEspecialistaRH_controller(EspecialistaRH_Controller especialistaRH_controller) {       Estudiante_Controller.especialistaRH_controller = especialistaRH_controller;    }

    public static void setEstudiante(Estudiante estudiante) {
        Estudiante_Controller.estudiante = estudiante;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        try {

            onDraggedScene(anchorPane);


            InstanciarAnimaciones();
            especialidadTupla=new ArrayList<>();
            procedenciaTupla=new ArrayList<>();
            Iterator<Especialidad> it = Service_Locator.getInstance().getEspecialidad_service().Especialidades_List().iterator();
            while (it.hasNext())
                especialidadTupla.add(new Tupla<>(0,it.next()));
            Iterator<LugarProcedencia> it2 = Service_Locator.getInstance().getProcedencia_service().Procedencias_List().iterator();
            while (it2.hasNext())
                procedenciaTupla.add(new Tupla<>(0,it2.next()));




            anno.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2000, LocalDate.now().getYear(), LocalDate.now().getYear()));

            acepbtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        CrearEstudiante();
                        especialistaRH_controller.LlenarTablaGraduados();
                        stage.close();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION,"Información","Estudiante añadido al sistema correctamente","DARKGREEN");

                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR,"Error", AppException.getJustMensaje(e.getMessage()),"DARKRED");
                    }
                }
            });
            cancelbtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    stage.close();
                }
            });


        /*    add.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        System.out.println("Nuevo Lugar");
                        TextInputDialog dialog=new TextInputDialog();
                        dialog.setHeaderText("Escriba el nombre del nuevo Lugar de Procedencia");
                        String c=dialog.showAndWait().get();
                        if (!c.equals(""))
                        {
                            procedenciaTupla.add(new Tupla<>(2,new LugarProcedencia(-1,c)));
                            proc.getItems().clear();
                            LlenarCombox(1);
                            proc.getSelectionModel().select(c);
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR,"Error",e.getMessage(),"DARKRED");
                    }


                }
            });
            add2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        System.out.println("Nueva Espacialidad");
                        TextInputDialog dialog=new TextInputDialog();
                        dialog.setHeaderText("Escriba el nombre de la nueva Especialidad");
                        dialog.initStyle(StageStyle.UNDECORATED);
                        String c=dialog.showAndWait().get();
                        if (!c.equals(""))
                        {
                            especialidadTupla.add(new Tupla<>(2,new Especialidad(-1,c)));
                            esp.getItems().clear();
                            LlenarCombox(2);
                            esp.getSelectionModel().select(c);
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR,"Error",e.getMessage(),"DARKRED");
                    }
                }

            });*/
            LlenarCombox(0);
            if (estudiante != null) {
                LlenarFormulario();
                System.out.println(estudiante.getNombreApellidos());
                System.out.println(estudiante.getArea());
                System.out.println(estudiante.getId());
            }

            idtf.setOnKeyTyped(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {

                    if (!Character.isDigit(event.getCharacter().charAt(0)))
                        event.consume();
                    if (idtf.getText().length()==11)
                        event.consume();
                }
            });
            nomtf.setOnKeyTyped(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (Character.isDigit(event.getCharacter().charAt(0)))
                        event.consume();
                }
            });

            proc.getEditor().setOnKeyTyped(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event)
                {
                    String e=event.getCharacter();
                    if (e.equals("\t")||e.equals("\b")||e.equals("\n"))
                        e="";
                    final String text=proc.getEditor().getText()+e;
                    LlenarCombox(1);
                    if (!text.equals(""))
                    {
                        proc.show();
                        proc.getItems().removeIf(new Predicate<String>() {
                            @Override
                            public boolean test(String s) {
                                boolean is =true;
                                if (s.toLowerCase().startsWith(text.toLowerCase()))
                                    is= false;
                                return is;
                            }
                        });
                    }
                }
            });
            esp.getEditor().setOnKeyTyped(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    String e=event.getCharacter();
                    if (e.equals("\t")||e.equals("\b")||e.equals("\n"))
                        e="";
                    final String text=esp.getEditor().getText()+e;
                    LlenarCombox(2);
                    if (!text.equals(""))
                    {
                        esp.show();
                        esp.getItems().removeIf(new Predicate<String>() {
                            @Override
                            public boolean test(String s) {
                                boolean is =true;
                                if (s.toLowerCase().startsWith(text.toLowerCase()))
                                    is= false;
                                return is;
                            }
                        });

                    }
                }
            });








        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void CrearEstudiante() throws Exception
    {
        if (validate())
        {
            Especialidad especialidad=especialidadTupla.get(EspecialidadID(esp.getSelectionModel().getSelectedItem())).getObjeto();
            LugarProcedencia lugarProcedencia= procedenciaTupla.get(LugarProcedenciaID(proc.getSelectionModel().getSelectedItem())).getObjeto();
            if (especialidad.getId()==-1)
            {
                Service_Locator.getInstance().getEspecialidad_service().NuevaEspecialidad(especialidad.getNombre());
                ArrayList<Especialidad> list=Service_Locator.getInstance().getEspecialidad_service().Especialidades_List();
                especialidad=list.get(0);
            }
            if (lugarProcedencia.getId()==-1)
            {
                Service_Locator.getInstance().getProcedencia_service().NuevaProcedencia(lugarProcedencia.getLugar());
                ArrayList<LugarProcedencia> list=Service_Locator.getInstance().getProcedencia_service().Procedencias_List();
                lugarProcedencia=list.get(0);
            }
            if (estudiante==null)
            {


               estudiante = new Estudiante(-1,false
                        ,appli.selectedProperty().getValue(),lugarProcedencia.getId(),temate.getText(),nomtf.getText(), Validacion.getNacimiento_From_Carnet(idtf.getText()) ,anno.getValue(),idtf.getText(),especialidad.getId(),null);
                Service_Locator.getInstance().getGraduado_service().CreateGraduado(estudiante);

            }
            else
            {
                estudiante.setNidentidad(idtf.getText());
                estudiante.setCumpleannos(Validacion.getNacimiento_From_Carnet(idtf.getText()));
                estudiante.setProcedenciaEstudiante(lugarProcedencia.getId());
                estudiante.setEspecialidad(especialidad.getId());
                estudiante.setTemaTesis(temate.getText());
                estudiante.setTesisAplicable(appli.isSelected());
                estudiante.setNombreApellidos(nomtf.getText());
                estudiante.setAnnoIngreso(anno.getValue());

                Service_Locator.getInstance().getGraduado_service().updateGraduado(estudiante);

            }
        }
        else throw new Exception("Formulario Incompleto");
    }
    private void LlenarFormulario() throws Exception
    {
        nomtf.setText(estudiante.getNombreApellidos());
        idtf.setText(estudiante.getNidentidad());
        proc.getSelectionModel().select(Service_Locator.getInstance().getProcedencia_service().ProcedenciaID(estudiante.getProcedenciaEstudiante()));
        temate.setText(estudiante.getTemaTesis());
        anno.getValueFactory().setValue(estudiante.getAnnoIngreso());
        esp.getSelectionModel().select(Service_Locator.getInstance().getEspecialidad_service().EspecialidadID(estudiante.getEspecialidad()).getNombre());
        appli.selectedProperty().setValue(estudiante.isTesisAplicable());
    }
    private boolean validate()
    {
        boolean is=true;
        Effect effect=new DropShadow(0.5,Color.DARKRED);
        nomtf.setEffect(null);
        idtf.setEffect(null);
        proc.setEffect(null);
        temate.setEffect(null);
        anno.setEffect(null);
        esp.setEffect(null);
        if(nomtf.getText().equals("")) {
            nomtf.setEffect(effect);
            is=false;
        }
        if(idtf.getText().equals("")) {
            idtf.setEffect(effect);
            is=false;
        }
        if(idtf.getText().length()<11)
        {
            idtf.setEffect(effect);
            is=false;
        }
        if(proc.getSelectionModel().isEmpty())
        {
            proc.setEffect(effect);
            is=false;
        }

        if(temate.getText().equals(""))
        {
            temate.setEffect(effect);
            is=false;
        }
        if (esp.getSelectionModel().isEmpty()){
            esp.setEffect(effect);
            is=false;
        }
        return is;
    }
    private void LlenarCombox(int num)
    {
        if (num==0)
        {
            LlenarCombox(1);
            LlenarCombox(2);
        }
        else if (num==1) {
            proc.getItems().clear();
            Iterator<Tupla<LugarProcedencia>> it2 = procedenciaTupla.iterator();
            while (it2.hasNext())
                proc.getItems().add(it2.next().getObjeto().getLugar());
        }
        else
        {
            esp.getItems().clear();
            Iterator<Tupla<Especialidad>> it = especialidadTupla.iterator();
            while (it.hasNext())
                esp.getItems().add(it.next().getObjeto().getNombre());
        }
    }
    private int EspecialidadID(String nom)
    {
        int index=-1;
        ListIterator<Tupla<Especialidad>> iterator=especialidadTupla.listIterator();
        while (iterator.hasNext())
        {
            if (iterator.next().getObjeto().getNombre().equals(nom))
                index=iterator.nextIndex()-1;
        }
        return index;
    }

    private int LugarProcedenciaID(String nom)
    {
        int index=-1;
        ListIterator<Tupla<LugarProcedencia>> iterator=procedenciaTupla.listIterator();
        while (iterator.hasNext())
        {
            if (iterator.next().getObjeto().getLugar().equals(nom))
                index=iterator.nextIndex()-1;
        }
        return index;
    }


    private void InstanciarAnimaciones()
    {
        Animaciones.AnimacionTipo1(acepbtn);
        Animaciones.AnimacionTipo1(cancelbtn);

    }

}
