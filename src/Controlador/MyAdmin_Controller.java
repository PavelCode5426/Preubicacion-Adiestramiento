package Controlador;

import Auxiliar.Excepciones.AppException;
import Auxiliar.FiltrarTablas;
import Auxiliar.Tupla;
import Auxiliar.TuplaList;
import Modelo.*;
import Servicio.Repository;
import Servicio.Service_Locator;
import Vista.GetPantalla;
import Vista.IDraggedScene;
import com.jfoenix.controls.*;
import com.jfoenix.controls.base.IFXLabelFloatControl;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import javafx.animation.FadeTransition;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

public class MyAdmin_Controller implements Initializable, IDraggedScene {
    //Generales
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


    //Pantalla Gestor de Usuarios
    @FXML
    private Tab tab_Usuarios;
    @FXML
    private JFXTextField text_busqueda;
    @FXML
    private JFXComboBox<String> comboBox_Filtrar;
    @FXML
    private JFXTreeTableView<Directorio_Usuario_Data2> tabla;
    @FXML
    private TreeTableColumn<Directorio_Usuario_Data2, Label> columna_Nombre;
    @FXML
    private TreeTableColumn<Directorio_Usuario_Data2, Label> columna_Usuario;
    @FXML
    private TreeTableColumn<Directorio_Usuario_Data2, Label> columna_Contra;
    @FXML
    private TreeTableColumn<Directorio_Usuario_Data2, Label> columna_Area;
    @FXML
    private JFXButton button_Rest;
    @FXML
    private JFXButton button_Eliminar;
    @FXML
    private VBox pan1;
    @FXML
    private JFXTextField textF_Nombre;
    @FXML
    private JFXTextField textF_Carnet;
    @FXML
    private JFXTextField textF_Usuario;
    @FXML
    private JFXTextField text_cargo;
    @FXML
    private JFXComboBox<String> cb_Area;
    @FXML
    private JFXCheckBox check_VR;
    @FXML
    private JFXCheckBox check_DRH;
    @FXML
    private JFXCheckBox check_ERH;
    @FXML
    private JFXCheckBox check_JA;
    @FXML
    private JFXCheckBox check_Tuto;
    @FXML
    private JFXCheckBox check_admin;
    @FXML
    private JFXCheckBox check_admin1;
    @FXML
    private JFXButton button_OtorgarPerm;
    @FXML
    private VBox pan2;
    @FXML
    private JFXRadioButton radioB_AprobPF;
    @FXML
    private JFXRadioButton radioB_AprobarAGA;
    @FXML
    private JFXRadioButton radioB_AGA;
    @FXML
    private JFXRadioButton radioB_RevisarPF;
    @FXML
    private JFXRadioButton radioB_IngresarPF;
    @FXML
    private JFXRadioButton radioB_IngresarGNCujae;
    @FXML
    private JFXRadioButton radioB_EvaluarPF;
    @FXML
    private JFXRadioButton radioB_AprobarPFC;
    @FXML
    private JFXRadioButton radioB_EvaluarPFC;
    @FXML
    private JFXRadioButton radioB_IngresarPFC;
    @FXML
    private JFXButton button_Volver;
    @FXML
    private JFXButton button_Annadir;


    //Gestinar Areas
    @FXML
    private JFXTextField text_buscarArea;
    @FXML
    private JFXTreeTableView<Area_Lugar> table_area;
    @FXML
    private TreeTableColumn<Area_Lugar, String> colum_Area;
    @FXML
    private JFXTextField text_nom_area;
    @FXML
    private JFXButton btn_addArea;
    @FXML
    private JFXButton btn_delArea;


    //Gestionar Lugar Procedencia
    @FXML
    private JFXTextField text_buscarLugar;
    @FXML
    private JFXTextField text_LugarProced;
    @FXML
    private JFXButton btn_addLugar;
    @FXML
    private JFXButton btn_delLugar;
    @FXML
    private JFXTreeTableView<Area_Lugar> tabla_lugar;
    @FXML
    private TreeTableColumn<Area_Lugar, String> colum_lugarProcedencia;


    //Gestianar Especialidad
    @FXML
    private JFXTextField text_buscarEspecialidad;
    @FXML
    private JFXTextField text_especialidad;
    @FXML
    private JFXButton btn_addEspecialidad;
    @FXML
    private JFXButton btn_delEspecialidad;
    @FXML
    private JFXTreeTableView<Area_Lugar> tabla_especialidad;
    @FXML
    private TreeTableColumn<Area_Lugar, String> colum_especi;


    private boolean isAdmin;
    private boolean isSuperAdmin;


    //Variables Utiles
    private ArrayList<Area> areaArrayList = new ArrayList<>();
    private ArrayList<Directorio> directorioArrayList = new ArrayList<>();
    private ArrayList<Rol> roles = new ArrayList<>();
    private ArrayList<Permiso> permisos = new ArrayList<>();
    private ArrayList<LugarProcedencia> lugarProcedencias = new ArrayList<>();
    private ArrayList<Especialidad> especialidads = new ArrayList<>();


    //Variables para mostrar
    private ObservableList<String> dataCombox;
    private ObservableList<Directorio_Usuario_Data2> usuario_data;
    private ObservableList<Area_Lugar> areasObservableList;
    private ObservableList<Area_Lugar> lugarObservableList;
    private ObservableList<Area_Lugar> especialidadList;


    //Auxiliares Muy muy Utiles
    private ArrayList<Tupla<UsuarioRol>> UsuarioRolesList_Importante;
    private ArrayList<Tupla<RolPermiso>> RolPermisoList_Importarnte;

    private static Stage stage;

    public static void setStage(Stage stage) {
        MyAdmin_Controller.stage = stage;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Pa que la pantalla se mueva
        onDraggedScene(PaneExterior);
        ConfigurarMinimizarMaximizar(PaneExterior, botonMinimizarVentana, botonCerrarVentana);


        try {
            isAdmin = Service_Locator.getInstance().getLoggin_roles_service().IamAdimistrador();
            isSuperAdmin = Service_Locator.getInstance().getLoggin_roles_service().IamSuperAdministrador();
            check_admin.setDisable(isAdmin);
            InicializarEspecialidades();
            InicializarLugaresdeProcedencia();
            InicializarAreas();
            InicializarGestorUsuarios();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Metodos Basicos
    private Area AreaById(int Id) {
        Iterator<Area> areaIterator = areaArrayList.iterator();
        Area area = null;
        boolean is = false;
        while (areaIterator.hasNext() && !is) {
            area = areaIterator.next();
            if (area.getId() == Id)
                is = true;
        }
        return area;
    }

    private Rol RolById(int id) {
        Rol rol = null;
        Iterator<Rol> iterator = roles.listIterator();
        boolean is = false;
        while (iterator.hasNext() && !is) {
            rol = iterator.next();
            if (rol.getId() == id)
                is = true;
        }
        return rol;
    }

    private Area AreaByName(String name) {
        Iterator<Area> areaIterator = areaArrayList.iterator();
        Area area = null;
        boolean is = false;
        while (areaIterator.hasNext() && !is) {
            area = areaIterator.next();
            if (area.getNombre().equals(name))
                is = true;
        }
        return area;
    }

    private Rol RoleByName(String name) {
        Rol rol = null;
        Iterator<Rol> iterator = roles.listIterator();
        boolean is = false;
        while (iterator.hasNext() && !is) {
            rol = iterator.next();
            if (rol.getNombre().equals(name))
                is = true;
        }
        return rol;
    }

    private Permiso PermisoByName(String name) {
        Permiso perm = null;
        Iterator<Permiso> iterator = permisos.listIterator();
        boolean is = false;
        while (iterator.hasNext() && !is) {
            perm = iterator.next();
            if (perm.getNombre().equals(name))
                is = true;
        }
        return perm;
    }

    private Permiso PermisoByID(int id) {
        Permiso perm = null;
        Iterator<Permiso> iterator = permisos.listIterator();
        boolean is = false;
        while (iterator.hasNext() && !is) {
            perm = iterator.next();
            if (perm.getId() == id)
                is = true;
        }
        return perm;
    }

    private LugarProcedencia LugarProcedenciaByID(int ID) {
        LugarProcedencia next = null;
        Iterator<LugarProcedencia> iterator = lugarProcedencias.iterator();
        boolean is = false;
        while (iterator.hasNext() && !is) {
            next = iterator.next();
            if (next.getId() == ID)
                is = true;
        }
        return next;
    }

    private Especialidad EspecialidadByID(int ID) {
        Especialidad next = null;
        Iterator<Especialidad> iterator = especialidads.iterator();
        boolean is = false;
        while (iterator.hasNext() && !is) {
            next = iterator.next();
            if (next.getId() == ID)
                is = true;
        }
        return next;
    }


    //Metodos de Gestor de Usuarios
    private void ActivarPermisos() {
        check_VR.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                radioB_AprobPF.setDisable(!newValue);
                radioB_AprobarAGA.setDisable(!newValue);
                radioB_AprobPF.setSelected(newValue);
                radioB_AprobarAGA.setSelected(newValue);

            }
        });
        check_DRH.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                radioB_AGA.setDisable(!newValue);
                radioB_RevisarPF.setDisable(!newValue);
                radioB_AGA.setSelected(newValue);
                radioB_RevisarPF.setSelected(newValue);

            }
        });
        check_ERH.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                radioB_IngresarGNCujae.setDisable(!newValue);
                radioB_IngresarPF.setDisable(!newValue);
                radioB_IngresarGNCujae.setSelected(newValue);
                radioB_IngresarPF.setSelected(newValue);


            }
        });
        check_JA.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                radioB_EvaluarPF.setDisable(!newValue);
                radioB_AprobarPFC.setDisable(!newValue);
                radioB_EvaluarPF.setSelected(newValue);
                radioB_AprobarPFC.setSelected(newValue);

            }
        });
        check_Tuto.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                radioB_EvaluarPFC.setDisable(!newValue);
                radioB_IngresarPFC.setDisable(!newValue);
                radioB_EvaluarPFC.setSelected(newValue);
                radioB_IngresarPFC.setSelected(newValue);
            }
        });
    }

    private void InicializarGestorUsuarios() throws Exception {


        columna_Nombre.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Directorio_Usuario_Data2, Label>, ObservableValue<Label>>() {
            @Override
            public ObservableValue<Label> call(TreeTableColumn.CellDataFeatures<Directorio_Usuario_Data2, Label> param) {
                return param.getValue().getValue().nombreProperty();
            }
        });
        columna_Area.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Directorio_Usuario_Data2, Label>, ObservableValue<Label>>() {
            @Override
            public ObservableValue<Label> call(TreeTableColumn.CellDataFeatures<Directorio_Usuario_Data2, Label> param) {
                return param.getValue().getValue().areaPertenecienteProperty();
            }
        });
        columna_Contra.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Directorio_Usuario_Data2, Label>, ObservableValue<Label>>() {
            @Override
            public ObservableValue<Label> call(TreeTableColumn.CellDataFeatures<Directorio_Usuario_Data2, Label> param) {
                return param.getValue().getValue().carnetProperty();
            }
        });
        columna_Usuario.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Directorio_Usuario_Data2, Label>, ObservableValue<Label>>() {
            @Override
            public ObservableValue<Label> call(TreeTableColumn.CellDataFeatures<Directorio_Usuario_Data2, Label> param) {
                return param.getValue().getValue().nombreUsuarioProperty();
            }
        });

        areaArrayList = Service_Locator.getInstance().getArea_service().Areas_List();
        directorioArrayList = Service_Locator.getInstance().getDirectorio_service().getDirectorio();
        roles = new ArrayList<>(new Repository<Rol>(new Rol()).GetAll());
        permisos = new ArrayList<>(new Repository<Permiso>(new Permiso()).GetAll());

        InicializarCombobox();
        InicializarTablaGestorUsuarios();
        ActivarPermisos();
        comboBox_Filtrar.getItems().addAll("Nombre y Apellido", "Carnet");

        text_busqueda.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String typed = text_busqueda.getText();
                String e = event.getCharacter();
                if (e.equals("\b") || e.equals("\t"))
                    e = "";
                typed += e;
                InicializarTablaGestorUsuarios();
                if (!typed.equals(""))
                    FiltrarTablaGestorUsuarios(typed);
            }
        });
        textF_Nombre.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (Character.isDigit(event.getCharacter().charAt(0)))
                    event.consume();
            }
        });
        textF_Carnet.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!Character.isDigit(event.getCharacter().charAt(0)))
                    event.consume();
                if (textF_Carnet.getText().length() > 10)
                    event.consume();
            }
        });
        button_OtorgarPerm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AnimarPaneles(true);
            }
        });
        button_Volver.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AnimarPaneles(false);
            }
        });
        button_Annadir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (ValidarDirectorio()) {
                    try {
                        int idUser=CrearDirectorio();
                         directorioArrayList = Service_Locator.getInstance().getDirectorio_service().getDirectorio();

                        if (button_Annadir.getText().equals("Añadir"))
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Directorio creado correctamente", "DARKGREEN");
                        else
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Directorio editado correctamente", "DARKGREEN");

                        InicializarTablaGestorUsuarios();
                        ClearAllGestorUsuarios();
                        if(!Service_Locator.getInstance().getLoggin_roles_service().BuscarUsuarioRolPorUsuario(idUser))
                            Service_Locator.getInstance().getDirectorio_service().DeleteDirectorio(idUser);

                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", AppException.getJustMensaje(e.getMessage()), "DARKRED");
                    }
                } else
                    GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", "Formulario incompleto", "DARKRED");
                AnimarPaneles(false);
            }
        });
        tabla.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Directorio_Usuario_Data2>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<Directorio_Usuario_Data2>> observable, TreeItem<Directorio_Usuario_Data2> oldValue, TreeItem<Directorio_Usuario_Data2> newValue) {
                try {

                    if (!tabla.getSelectionModel().isEmpty()) {
                        int id = tabla.getSelectionModel().getSelectedItem().getValue().getId();
                        textF_Nombre.setDisable(true);
                        textF_Carnet.setDisable(true);
                        text_cargo.setDisable(true);
                        textF_Usuario.setDisable(true);
                        cb_Area.setDisable(true);

                        button_Annadir.setDisable(false);


                        PrepararEdicionGestorUsuarios();
                        button_Annadir.setText("Editar");
                        button_Rest.setDisable(true);
                        button_Eliminar.setDisable(true);
                        if (Service_Locator.getInstance().getDirectorio_service().getDirectorio(tabla.getSelectionModel().getSelectedItem().getValue().getId()).isActivo())
                            button_Eliminar.setDisable(false);
                        else button_Rest.setDisable(false);


                        if (isAdmin && (Service_Locator.getInstance().getLoggin_roles_service().isSuperAdministrador(id) || Service_Locator.getInstance().getLoggin_roles_service().isAdimistrador(id))) {
                            button_Annadir.setDisable(true);
                            button_Eliminar.setDisable(true);
                        }
                        if (id == Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser().getId())
                            button_Eliminar.setDisable(true);

                    } else {
                        textF_Nombre.setDisable(false);
                        textF_Carnet.setDisable(false);
                        text_cargo.setDisable(false);
                        textF_Usuario.setDisable(false);
                        cb_Area.setDisable(false);
                        button_Annadir.setDisable(false);
                        button_Rest.setDisable(true);
                        button_Eliminar.setDisable(true);
                        ClearAllGestorUsuarios();
                        System.out.println(button_Annadir.getText());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        button_Eliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!tabla.getSelectionModel().isEmpty()) {
                    try {
                        Directorio hold = Service_Locator.getInstance().getDirectorio_service().getDirectorio(tabla.getSelectionModel().getSelectedItem().getValue().getId());
                        boolean esAdmin = Service_Locator.getInstance().getLoggin_roles_service().isAdimistrador(hold.getId());
                        boolean esSuper = Service_Locator.getInstance().getLoggin_roles_service().isSuperAdministrador(hold.getId());
                        boolean esYo = (hold.getId() == Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser().getId());

                        if ((isSuperAdmin && !esYo) || (!esAdmin && !esSuper && !esYo)) {
                            hold.setActivo(false);
                            hold.setContrasenna(Service_Locator.getInstance().getSecurity_service().EncriptarTexto("CUJAE"));
                            Service_Locator.getInstance().getDirectorio_service().CreateUpdateDirectorio(hold);
                            directorioArrayList = Service_Locator.getInstance().getDirectorio_service().getDirectorio();
                            InicializarTablaGestorUsuarios();
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Usuario desactivado correctamente", "DARKGREEN");
                        } else
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", "Usuario no desactivable", "DARKRED");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        button_Rest.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!tabla.getSelectionModel().isEmpty()) {
                    try {
                        Directorio hold = Service_Locator.getInstance().getDirectorio_service().getDirectorio(tabla.getSelectionModel().getSelectedItem().getValue().getId());
                        hold.setContrasenna(Service_Locator.getInstance().getSecurity_service().EncriptarTexto("CUJAE"));
                        hold.setActivo(true);
                        Service_Locator.getInstance().getDirectorio_service().CreateUpdateDirectorio(hold);
                        directorioArrayList = Service_Locator.getInstance().getDirectorio_service().getDirectorio();
                        InicializarTablaGestorUsuarios();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Usuario activado correctamente", "DARKGREEN");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        check_VR.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                check_VR.setSelected(newValue);
                DesactivarRoles();
            }
        });
        check_ERH.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                check_ERH.setSelected(newValue);
                DesactivarRoles();
            }
        });
        check_JA.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                check_JA.setSelected(newValue);
                DesactivarRoles();
            }
        });
        check_DRH.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                check_DRH.setSelected(newValue);
                DesactivarRoles();
            }
        });


    }

    private boolean ValidarDirectorio() {
        boolean is = true;
        Effect effect = new DropShadow(0.5, Color.DARKRED);
        textF_Nombre.setEffect(null);
        textF_Carnet.setEffect(null);
        textF_Usuario.setEffect(null);
        cb_Area.setEffect(null);
        text_cargo.setEffect(null);

        if (textF_Nombre.getText().equals("")) {
            is = false;
            textF_Nombre.setEffect(effect);
        }
        if (textF_Carnet.getText().equals("") || textF_Carnet.getText().length() < 11) {
            is = false;
            textF_Carnet.setEffect(effect);
        }
        if (textF_Usuario.getText().equals("")) {
            is = false;
            textF_Usuario.setEffect(effect);
        }
        if (cb_Area.getSelectionModel().isEmpty()) {
            is = false;
            cb_Area.setEffect(effect);
        }
        if (text_cargo.getText().equals("")) {
            is = false;
            text_cargo.setEffect(effect);
        }
        return is;
    }

    private Directorio BuscarDirectorio(int ID) {
        Iterator<Directorio> iterator = directorioArrayList.iterator();
        Directorio next = null;
        boolean is = false;
        while (iterator.hasNext() && !is) {
            next = iterator.next();
            if (next.getId() == ID)
                is = true;
        }
        if (!is)
            next = null;
        return next;
    }

    private void InicializarCombobox() {
        dataCombox = FXCollections.observableArrayList();
        Iterator<Area> iterator = areaArrayList.listIterator();
        while (iterator.hasNext()) {
            dataCombox.add(iterator.next().getNombre());
        }
        cb_Area.getItems().clear();
        cb_Area.getItems().addAll(dataCombox);
        cb_Area.getEditor().setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                String typed = cb_Area.getEditor().getText();
                String e = event.getCharacter();
                if (e.equals("\b") || e.equals("\t"))
                    e = "";
                typed += e;
                cb_Area.getItems().clear();
                cb_Area.getItems().addAll(dataCombox);
                if (!typed.equals(""))
                    FiltrarCombobox(typed);
                else
                    cb_Area.show();
            }
        });
    }

    private void FiltrarCombobox(String text) {
        cb_Area.show();
        cb_Area.getItems().removeIf(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                boolean is = true;
                if (s.toLowerCase().startsWith(text.toLowerCase()) && !s.equals(""))
                    is = false;
                return is;
            }
        });
    }

    private void InicializarTablaGestorUsuarios() {

        usuario_data = FXCollections.observableArrayList();
        Iterator<Directorio> iterator = directorioArrayList.listIterator();
        while (iterator.hasNext()) {
            Directorio directorio = iterator.next();
            usuario_data.add(new Directorio_Usuario_Data2(directorio.getId(), directorio.getNombreApellido(), directorio.getCarnetIdentidad(), directorio.getNusuario(), AreaById(directorio.getArea()).getNombre(), directorio.isActivo()));
        }
        final TreeItem<Directorio_Usuario_Data2> item = new RecursiveTreeItem<>(usuario_data, RecursiveTreeObject::getChildren);
        tabla.setRoot(item);
        tabla.setShowRoot(false);
    }

    private void FiltrarTablaGestorUsuarios(String text) {
        ObservableList<Directorio_Usuario_Data2> data = FXCollections.observableArrayList(usuario_data);
        FiltrarTablas<Directorio_Usuario_Data2> filtrarTablas = new FiltrarTablas<>();
        Predicate<Directorio_Usuario_Data2> predicate = null;
        if (comboBox_Filtrar.getSelectionModel().isEmpty())
            comboBox_Filtrar.getSelectionModel().select(0);
        if (comboBox_Filtrar.getSelectionModel().getSelectedItem().equals("Carnet")) {
            predicate = new Predicate<Directorio_Usuario_Data2>() {
                @Override
                public boolean test(Directorio_Usuario_Data2 directorio_usuario_data) {
                    boolean is = true;
                    if (directorio_usuario_data.getCarnet().getText().toLowerCase().startsWith(text.toLowerCase()))
                        is = false;
                    return is;
                }
            };
        } else {
            predicate = new Predicate<Directorio_Usuario_Data2>() {
                @Override
                public boolean test(Directorio_Usuario_Data2 directorio_usuario_data) {
                    boolean is = true;
                    if (directorio_usuario_data.getNombre().getText().toLowerCase().startsWith(text.toLowerCase()))
                        is = false;
                    return is;
                }
            };
        }

        final TreeItem<Directorio_Usuario_Data2> item = new RecursiveTreeItem<>(filtrarTablas.FiltrarTabla(data, predicate), RecursiveTreeObject::getChildren);
        tabla.setRoot(item);
    }

    private int CrearDirectorio() throws Exception {

        System.out.println("Antes de crear users: "+button_Annadir.getText());
        int ID = -1;
        boolean act = true;
        Directorio nuevo = null;
        Directorio directAux = null;
        if (!tabla.getSelectionModel().isEmpty()) {
            ID = tabla.getSelectionModel().getSelectedItem().getValue().getId();
            nuevo = Service_Locator.getInstance().getDirectorio_service().getDirectorio(ID);
        }
        if (ID == -1) {
            nuevo = new Directorio(ID, textF_Carnet.getText(), text_cargo.getText(), textF_Nombre.getText(), AreaByName(cb_Area.getSelectionModel().getSelectedItem()).getId(), textF_Usuario.getText(), "", act);
            nuevo.setContrasenna(Service_Locator.getInstance().getSecurity_service().EncriptarTexto("CUJAE"));
            Service_Locator.getInstance().getDirectorio_service().CreateUpdateDirectorio(nuevo);

        }
        directAux = Service_Locator.getInstance().getDirectorio_service().getDirectorioPorUsuario(nuevo.getNusuario());

            EstructurarDatos_V500(directAux.getId());


            if (directAux != null) {
                if (!Service_Locator.getInstance().getLoggin_roles_service().BuscarUsuarioRolPorUsuario(directAux.getId())) {
                    Service_Locator.getInstance().getDirectorio_service().DeleteDirectorio(directAux.getId());
                   // throw new Exception("La operación terminó con fallos.");
                } else {
                    directorioArrayList = Service_Locator.getInstance().getDirectorio_service().getDirectorio();
                    nuevo = directorioArrayList.get(0);
                }
            }





        return Service_Locator.getInstance().getDirectorio_service().getDirectorioPorUsuario(nuevo.getNusuario()).getId();
    }

    private void ClearAllGestorUsuarios() {
        textF_Nombre.setText("");
        text_cargo.setText("");
        textF_Carnet.setText("");
        cb_Area.getSelectionModel().clearSelection();
        textF_Usuario.setText("");
        check_Tuto.setSelected(false);
        check_VR.setSelected(false);
        check_JA.setSelected(false);
        check_ERH.setSelected(false);
        check_DRH.setSelected(false);
        check_admin.setSelected(false);
        check_admin1.setSelected(false);


        radioB_AprobPF.setSelected(false);
        radioB_AprobarAGA.setSelected(false);
        radioB_AGA.setSelected(false);
        radioB_RevisarPF.setSelected(false);
        radioB_IngresarPF.setSelected(false);
        radioB_IngresarGNCujae.setSelected(false);
        radioB_EvaluarPF.setSelected(false);
        radioB_AprobarPFC.setSelected(false);
        radioB_EvaluarPFC.setSelected(false);
        radioB_IngresarPFC.setSelected(false);


        button_Annadir.setText("Añadir");
    }

    private void AnimarPaneles(boolean avanzar) {
        FadeTransition transition = new FadeTransition(Duration.seconds(0.5), pan1);
        FadeTransition transition1 = new FadeTransition(Duration.seconds(0.5), pan2);
        if (avanzar) {
            pan2.setOpacity(0);
            transition.setFromValue(1);
            transition.setToValue(0);
            transition1.setToValue(1);
            transition1.setFromValue(0);

            transition.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    pan2.toFront();
                    transition1.play();
                }
            });
            transition.play();

        } else {
            pan1.setOpacity(0);
            transition.setFromValue(0);
            transition.setToValue(1);
            transition1.setToValue(0);
            transition1.setFromValue(1);
            transition1.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    pan1.toFront();
                    transition.play();
                }
            });

            transition1.play();
        }
    }

    private ArrayList<TuplaList<Object>> getRolesPermisosUpdate(int IDDir) throws Exception {
        ArrayList<TuplaList<Object>> vieja = Service_Locator.getInstance().getLoggin_roles_service().getRol_UsuarioRol_Agrupado(IDDir);
        return vieja;
    }

    private void EstructurarDatos_V500(int DirID) throws Exception {

        if(Service_Locator.getInstance().getDirectorio_service().getDirectorio(DirID)!=null) {


            if (UsuarioRolesList_Importante == null)
                UsuarioRolesList_Importante = new ArrayList<>();
            ArrayList<TuplaList<Object>> lists = new ArrayList<>();
            Rol hold = RoleByName(check_Tuto.getText());


            if (check_Tuto.isSelected()) {
                TuplaList<Object> tuplaList = new TuplaList<>(1);
                tuplaList.getListaobjeto().add(SetOperacionUsuarioRol(hold.getId(), 1, DirID));
                ArrayList<Integer> integers = new ArrayList<>();
                if (radioB_EvaluarPFC.isSelected())
                    integers.add(PermisoByName(radioB_EvaluarPFC.getText()).getId());
                if (radioB_IngresarPFC.isSelected())
                    integers.add(PermisoByName(radioB_IngresarPFC.getText()).getId());
                tuplaList.getListaobjeto().add(integers);
                lists.add(tuplaList);
            } else if (hold != null) {

                try {
                    Service_Locator.getInstance().getLoggin_roles_service().DeleteUsuarioRole(hold.getId(), DirID);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            hold = RoleByName(check_JA.getText());
            if (check_JA.isSelected()) {
                TuplaList<Object> tuplaList = new TuplaList<>(1);
                tuplaList.getListaobjeto().add(SetOperacionUsuarioRol(hold.getId(), 1, DirID));
                ArrayList<Integer> integers = new ArrayList<>();
                if (radioB_AprobarPFC.isSelected())
                    integers.add(PermisoByName(radioB_AprobarPFC.getText()).getId());
                if (radioB_EvaluarPF.isSelected())
                    integers.add(PermisoByName(radioB_EvaluarPF.getText()).getId());
                tuplaList.getListaobjeto().add(integers);
                lists.add(tuplaList);
            } else if (hold != null) {

                try {
                    Service_Locator.getInstance().getLoggin_roles_service().DeleteUsuarioRole(hold.getId(), DirID);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            hold = RoleByName(check_ERH.getText());
            if (check_ERH.isSelected()) {
                TuplaList<Object> tuplaList = new TuplaList<>(1);
                tuplaList.getListaobjeto().add(SetOperacionUsuarioRol(hold.getId(), 1, DirID));
                ArrayList<Integer> integers = new ArrayList<>();
                if (radioB_IngresarGNCujae.isSelected())
                    integers.add(PermisoByName(radioB_IngresarGNCujae.getText()).getId());
                if (radioB_IngresarPF.isSelected())
                    integers.add(PermisoByName(radioB_IngresarPF.getText()).getId());
                tuplaList.getListaobjeto().add(integers);
                lists.add(tuplaList);
            } else if (hold != null) {

                try {
                    Service_Locator.getInstance().getLoggin_roles_service().DeleteUsuarioRole(hold.getId(), DirID);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            hold = RoleByName(check_DRH.getText());
            if (check_DRH.isSelected()) {
                TuplaList<Object> tuplaList = new TuplaList<>(1);
                tuplaList.getListaobjeto().add(SetOperacionUsuarioRol(hold.getId(), 1, DirID));
                ArrayList<Integer> integers = new ArrayList<>();
                if (radioB_AGA.isSelected())
                    integers.add(PermisoByName(radioB_AGA.getText()).getId());
                if (radioB_RevisarPF.isSelected())
                    integers.add(PermisoByName(radioB_RevisarPF.getText()).getId());
                tuplaList.getListaobjeto().add(integers);
                lists.add(tuplaList);
            } else if (hold != null) {

                try {
                    Service_Locator.getInstance().getLoggin_roles_service().DeleteUsuarioRole(hold.getId(), DirID);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            hold = RoleByName(check_VR.getText());
            if (check_VR.isSelected()) {
                TuplaList<Object> tuplaList = new TuplaList<>(1);
                tuplaList.getListaobjeto().add(SetOperacionUsuarioRol(hold.getId(), 1, DirID));
                ArrayList<Integer> integers = new ArrayList<>();
                if (radioB_AprobPF.isSelected())
                    integers.add(PermisoByName(radioB_AprobPF.getText()).getId());
                if (radioB_AprobarAGA.isSelected())
                    integers.add(PermisoByName(radioB_AprobarAGA.getText()).getId());
                tuplaList.getListaobjeto().add(integers);
                lists.add(tuplaList);
            } else if (hold != null) {

                try {
                    Service_Locator.getInstance().getLoggin_roles_service().DeleteUsuarioRole(hold.getId(), DirID);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            hold = RoleByName(check_admin.getText());
            if (check_admin.isSelected()) {
                TuplaList<Object> tuplaList = new TuplaList<>(1);
                ArrayList<Integer> integers = new ArrayList<>();
                tuplaList.getListaobjeto().add(SetOperacionUsuarioRol(hold.getId(), 1, DirID));
                tuplaList.getListaobjeto().add(integers);
                lists.add(tuplaList);
            } else if (hold != null) {

                try {
                    Service_Locator.getInstance().getLoggin_roles_service().DeleteUsuarioRole(hold.getId(), DirID);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            hold = RoleByName(check_admin1.getText());
            if (check_admin1.isSelected()) {
                TuplaList<Object> tuplaList = new TuplaList<>(1);
                ArrayList<Integer> integers = new ArrayList<>();
                tuplaList.getListaobjeto().add(SetOperacionUsuarioRol(hold.getId(), 1, DirID));
                tuplaList.getListaobjeto().add(integers);
                lists.add(tuplaList);
            } else if (hold != null) {

                try {
                    Service_Locator.getInstance().getLoggin_roles_service().DeleteUsuarioRole(hold.getId(), DirID);
                    //Service_Locator.getInstance().getDirectorio_service().DeleteDirectorio(DirID);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            Iterator<TuplaList<Object>> iterator = lists.iterator();
            while (iterator.hasNext()) {
                Iterator<Object> iterator1 = iterator.next().getListaobjeto().iterator();
                UsuarioRol rol = ((UsuarioRol) iterator1.next());

                Service_Locator.getInstance().getLoggin_roles_service().AsignarRol_Permiso(rol, (ArrayList<Integer>) iterator1.next());


            }
        }

    }

    private UsuarioRol SetOperacionUsuarioRol(int ID, int Operacion, int Dir) {
        Iterator<Tupla<UsuarioRol>> iterator = UsuarioRolesList_Importante.iterator();
        Tupla<UsuarioRol> next = null;
        boolean is = false;
        while (iterator.hasNext() && !is) {
            next = iterator.next();
            if (next.getObjeto().getRole() == ID) {
                is = true;
                next.setOperacion(Operacion);
            }
        }
        if (!is && Operacion > 0) {
            next = new Tupla<>(0, new UsuarioRol(-1, Dir, ID, Date.valueOf(LocalDate.now())));
            UsuarioRolesList_Importante.add(next);
        }

        return next.getObjeto();

    }

    private void PrepararEdicionGestorUsuarios() throws Exception {
        int IDDir = tabla.getSelectionModel().getSelectedItem().getValue().getId();
        Directorio directorio = BuscarDirectorio(IDDir);
        UsuarioRolesList_Importante = new ArrayList<>();
        RolPermisoList_Importarnte = new ArrayList<>();
        Iterator<TuplaList<Object>> iterator = getRolesPermisosUpdate(IDDir).iterator();
        while (iterator.hasNext()) {
            TuplaList<Object> next = iterator.next();
            Iterator<Object> objectIterator = next.getListaobjeto().iterator();
            while (objectIterator.hasNext()) {
                Object next2 = objectIterator.next();
                if (next2 instanceof RolPermiso)
                    RolPermisoList_Importarnte.add(new Tupla<RolPermiso>(0, ((RolPermiso) next2)));
                else
                    UsuarioRolesList_Importante.add(new Tupla<UsuarioRol>(0, ((UsuarioRol) next2)));
            }
        }
        textF_Nombre.setText(directorio.getNombreApellido());
        textF_Carnet.setText(directorio.getCarnetIdentidad());
        text_cargo.setText(directorio.getCargo());
        textF_Usuario.setText(directorio.getNusuario());
        cb_Area.getSelectionModel().select(AreaById(directorio.getArea()).getNombre());
        button_Annadir.setText("Editar");

        check_VR.setSelected(false);
        check_DRH.setSelected(false);
        check_ERH.setSelected(false);
        check_JA.setSelected(false);
        check_Tuto.setSelected(false);
        check_admin.setSelected(false);


        boolean is = Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser().getId() == directorio.getId();
        if (is)
            check_admin.setDisable(true);
        else if (isSuperAdmin && !is)
            check_admin.setDisable(false);

        Iterator<Tupla<UsuarioRol>> usuarioRolIterator = UsuarioRolesList_Importante.iterator();
        Iterator<Tupla<RolPermiso>> RolpermisoIter = RolPermisoList_Importarnte.iterator();
        LinkedList<String> strings = new LinkedList<>();
        while (usuarioRolIterator.hasNext() || RolpermisoIter.hasNext()) {
            if (usuarioRolIterator.hasNext())
                strings.add(RolById(usuarioRolIterator.next().getObjeto().getRole()).getNombre());
            if (RolpermisoIter.hasNext())
                strings.add(PermisoByID(RolpermisoIter.next().getObjeto().getPermiso()).getNombre());
        }

        SeleccionarCheck_Radio(strings);
    }

    private void SeleccionarCheck_Radio(List<String> nombre) {
        check_VR.setSelected(true);
        check_DRH.setSelected(true);
        check_ERH.setSelected(true);
        check_JA.setSelected(true);
        check_Tuto.setSelected(true);
        check_admin.setSelected(true);
        check_admin1.setSelected(true);

        if (!nombre.contains(check_VR.getText()))
            check_VR.setSelected(false);
        if (!nombre.contains(check_DRH.getText()))
            check_DRH.setSelected(false);
        if (!nombre.contains(check_ERH.getText()))
            check_ERH.setSelected(false);
        if (!nombre.contains(check_JA.getText()))
            check_JA.setSelected(false);
        if (!nombre.contains(check_Tuto.getText()))
            check_Tuto.setSelected(false);
        if (!nombre.contains(check_admin.getText()))
            check_admin.setSelected(false);
        if (!nombre.contains(check_admin1.getText()))
            check_admin1.setSelected(false);


        if (!nombre.contains(radioB_AprobPF.getText()))
            radioB_AprobPF.setSelected(false);
        if (!nombre.contains(radioB_AprobarAGA.getText()))
            radioB_AprobarAGA.setSelected(false);
        if (!nombre.contains(radioB_AGA.getText()))
            radioB_AGA.setSelected(false);
        if (!nombre.contains(radioB_RevisarPF.getText()))
            radioB_RevisarPF.setSelected(false);
        if (!nombre.contains(radioB_IngresarPF.getText()))
            radioB_IngresarPF.setSelected(false);
        if (!nombre.contains(radioB_IngresarGNCujae.getText()))
            radioB_IngresarGNCujae.setSelected(false);
        if (!nombre.contains(radioB_EvaluarPF.getText()))
            radioB_EvaluarPF.setSelected(false);
        if (!nombre.contains(radioB_AprobarPFC.getText()))
            radioB_AprobarPFC.setSelected(false);
        if (!nombre.contains(radioB_EvaluarPFC.getText()))
            radioB_EvaluarPFC.setSelected(false);
        if (!nombre.contains(radioB_EvaluarPF.getText()))
            radioB_EvaluarPF.setSelected(false);
        if (!nombre.contains(radioB_IngresarPFC.getText()))
            radioB_IngresarPFC.setSelected(false);
    }


    //Metodos de Gestor de Areas
    private void InicializarAreas() throws Exception {
        colum_Area.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Area_Lugar, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Area_Lugar, String> param) {
                return param.getValue().getValue().nombreProperty();
            }
        });
        table_area.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Area_Lugar>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<Area_Lugar>> observable, TreeItem<Area_Lugar> oldValue, TreeItem<Area_Lugar> newValue) {
                if (!table_area.getSelectionModel().isEmpty()) {
                    btn_addArea.setText("Editar");
                    btn_delArea.setDisable(false);
                    text_nom_area.setText(table_area.getSelectionModel().getSelectedItem().getValue().getNombre());
                } else {
                    text_nom_area.setText("");
                    btn_delArea.setDisable(true);
                    btn_addArea.setText("Añadir");
                }
            }
        });
        LLenarTablaArea();
        btn_addArea.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (table_area.getSelectionModel().isEmpty()) {
                    try {
                        if (ValidarAreas()) {
                            int confirmacion = 0;
                            confirmacion = GetPantalla.panel_De_Notificacion_Confirmar("Está seguro que desea crear el área " + text_nom_area.getText() + "?", "Crear área", "#065325");
                            if (confirmacion == 1) {
                                Service_Locator.getInstance().getArea_service().CreateUpdate(new Area(-1, text_nom_area.getText()));
                                LLenarTablaArea();
                                text_nom_area.clear();
                                GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Área creada correctamente", "DARKGREEN");
                            }
                        } else
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", "Formulario incompleto", "DARKRED");
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", AppException.getJustMensaje( e.getMessage()), "DARKRED");
                    }

                } else {
                    try {
                        if (ValidarAreas()) {
                            Area area = AreaById(table_area.getSelectionModel().getSelectedItem().getValue().getId());
                            area.setNombre(text_nom_area.getText());
                            text_nom_area.clear();
                            Service_Locator.getInstance().getArea_service().CreateUpdate(area);
                            LLenarTablaArea();
                            btn_addArea.setText("Añadir");
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Área editada correctamente", "DARKGREEN");
                        } else
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", "Formulario incompleto", "DARKRED");
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", AppException.getJustMensaje( e.getMessage()), "DARKRED");
                    }
                }
                InicializarCombobox();
            }
        });
        btn_delArea.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!table_area.getSelectionModel().isEmpty()) {
                    try {
                        int confirmacion = 0;
                        confirmacion = GetPantalla.panel_De_Notificacion_Confirmar("Está seguro que desea eliminar el área " + table_area.getSelectionModel().getSelectedItem().getValue().getNombre() + "?", "Eliminar área", "#065325");
                        if (confirmacion == 1) {
                            Service_Locator.getInstance().getArea_service().DeleteArea(table_area.getSelectionModel().getSelectedItem().getValue().getId());
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "El área se eliminó correctamente", "darkgreen");
                            LLenarTablaArea();
                            btn_addArea.setText("Añadir");
                            text_nom_area.clear();
                            InicializarCombobox();
                        }
                    } catch (Exception e) {
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", "El área seleccionada no puede ser eliminada.", "DARKRED");
                        e.printStackTrace();
                    }

                }
            }
        });
        text_buscarArea.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                try {

                    String text = text_buscarArea.getText();
                    String e = event.getCharacter();
                    if (e.equals("\t") || e.equals("\b"))
                        e = "";
                    text += e;

                    LLenarTablaArea();
                    if (!text.equals(""))
                        FiltrarTablaAreas(text);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
        text_nom_area.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                try {

                    if (text_nom_area.equals(""))
                        btn_addArea.setText("Añadir");
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
    }

    private void LLenarTablaArea() throws Exception {
        areaArrayList = Service_Locator.getInstance().getArea_service().Areas_List();
        areasObservableList = FXCollections.observableArrayList();
        Iterator<Area> iterator = areaArrayList.iterator();
        while (iterator.hasNext()) {
            Area next = iterator.next();
            areasObservableList.add(new Area_Lugar(next.getNombre(), next.getId()));
        }
        final TreeItem<Area_Lugar> item = new RecursiveTreeItem<>(areasObservableList, RecursiveTreeObject::getChildren);
        table_area.setShowRoot(false);
        table_area.setRoot(item);
    }

    private boolean ValidarAreas() {
        boolean is = true;
        Effect effect = new DropShadow(0.5, Color.DARKRED);
        text_nom_area.setEffect(null);
        if (text_nom_area.getText().equals("")) {
            is = false;
            text_nom_area.setEffect(effect);
        }
        return is;
    }

    private void FiltrarTablaAreas(String texto) {
        Predicate<Area_Lugar> predicate = new Predicate<Area_Lugar>() {
            @Override
            public boolean test(Area_Lugar area_lugar) {
                boolean is = true;
                if (area_lugar.getNombre().toLowerCase().contains(texto.toLowerCase()))
                    is = false;
                return is;
            }
        };
        FiltrarTablas<Area_Lugar> filtrarTablas = new FiltrarTablas<>();
        final TreeItem<Area_Lugar> item = new RecursiveTreeItem<>(filtrarTablas.FiltrarTabla(areasObservableList, predicate), RecursiveTreeObject::getChildren);
        table_area.setRoot(item);
    }


    //Metodos de Gestionar Lugares de Procedencia
    private void InicializarLugaresdeProcedencia() throws Exception {
        colum_lugarProcedencia.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Area_Lugar, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Area_Lugar, String> param) {
                return param.getValue().getValue().nombreProperty();
            }
        });
        tabla_lugar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!tabla_lugar.getSelectionModel().isEmpty()) {
                    btn_addLugar.setText("Editar");
                    btn_delLugar.setDisable(false);
                    text_LugarProced.setText(tabla_lugar.getSelectionModel().getSelectedItem().getValue().getNombre());
                } else {
                    text_LugarProced.setText("");
                    btn_delLugar.setDisable(true);
                    btn_addLugar.setText("Añadir");
                }
            }
        });
        btn_addLugar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (btn_addLugar.getText().equals("Añadir")) {
                    try {
                        if (ValidarLugarProcedencia()) {
                            int confirmacion = 0;
                            confirmacion = GetPantalla.panel_De_Notificacion_Confirmar("Está seguro que desea crear el centro de procedencia " + text_LugarProced.getText() + "?", "Crear centro", "#065325");
                            if (confirmacion == 1) {
                                Service_Locator.getInstance().getProcedencia_service().CreateUpdateLugarProcedencia(new LugarProcedencia(-1, text_LugarProced.getText()));
                                LlenarTablasLugarProcedencia();
                                text_LugarProced.clear();
                                GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Lugar creado correctamente", "DARKGREEN");
                            }
                        } else
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", "Formulario incompleto", "DARKRED");
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", AppException.getJustMensaje( e.getMessage()), "DARKRED");
                    }

                } else {
                    try {
                        if (ValidarLugarProcedencia()) {
                            if (tabla_lugar.getSelectionModel().getSelectedItem() != null) {
                                LugarProcedencia lugarProcedencia = LugarProcedenciaByID(tabla_lugar.getSelectionModel().getSelectedItem().getValue().getId());
                                lugarProcedencia.setLugar(text_LugarProced.getText());
                                text_LugarProced.clear();
                                Service_Locator.getInstance().getProcedencia_service().CreateUpdateLugarProcedencia(lugarProcedencia);
                                LlenarTablasLugarProcedencia();
                                btn_addLugar.setText("Añadir");
                                GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Lugar editado correctamente", "DARKGREEN");
                            }
                        } else
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", "Formulario incompleto", "DARKRED");
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error",AppException.getJustMensaje( e.getMessage()), "DARKRED");
                    }
                }
            }
        });
        btn_delLugar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!tabla_lugar.getSelectionModel().isEmpty()) {
                    try {
                        int confirmacion = 0;
                        confirmacion = GetPantalla.panel_De_Notificacion_Confirmar("Está seguro que desea eliminar el centro de procedencia " + tabla_lugar.getSelectionModel().getSelectedItem().getValue().getNombre() + "?", "Eliminar centro", "#065325");
                        if (confirmacion == 1) {
                            Service_Locator.getInstance().getProcedencia_service().DeleteLugarProcedencia(tabla_lugar.getSelectionModel().getSelectedItem().getValue().getId());
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "El centro de procedencia se eliminó correctamente", "darkgreen");

                            LlenarTablasLugarProcedencia();
                            btn_addLugar.setText("Añadir");
                            text_LugarProced.clear();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", "El centro de procedencia seleccionado no puede ser eliminado", "DARKRED");

                    }

                }
            }
        });
        text_buscarLugar.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                try {
                    String text = text_buscarLugar.getText();
                    String e = event.getCharacter();
                    if (e.equals("\t") || e.equals("\b"))
                        e = "";
                    text += e;

                    LlenarTablasLugarProcedencia();
                    if (!text.equals(""))
                        FiltrarTablaLugarProcedencia(text);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
        LlenarTablasLugarProcedencia();
    }

    private void LlenarTablasLugarProcedencia() throws Exception {
        lugarProcedencias = Service_Locator.getInstance().getProcedencia_service().Procedencias_List();
        Iterator<LugarProcedencia> iterator = lugarProcedencias.iterator();
        lugarObservableList = FXCollections.observableArrayList();
        while (iterator.hasNext()) {
            LugarProcedencia next = iterator.next();
            lugarObservableList.add(new Area_Lugar(next.getLugar(), next.getId()));
        }
        final TreeItem<Area_Lugar> item = new RecursiveTreeItem<>(lugarObservableList, RecursiveTreeObject::getChildren);
        tabla_lugar.setShowRoot(false);
        tabla_lugar.setRoot(item);
    }

    private void FiltrarTablaLugarProcedencia(String text) {
        Predicate<Area_Lugar> predicate = new Predicate<Area_Lugar>() {
            @Override
            public boolean test(Area_Lugar area_lugar) {
                boolean is = true;
                if (area_lugar.getNombre().toLowerCase().contains(text.toLowerCase()))
                    is = false;
                return is;
            }
        };
        final TreeItem<Area_Lugar> item = new RecursiveTreeItem<>(new FiltrarTablas<Area_Lugar>().FiltrarTabla(lugarObservableList, predicate), RecursiveTreeObject::getChildren);
        tabla_lugar.setRoot(item);
    }

    private boolean ValidarLugarProcedencia() {
        boolean is = true;
        Effect effect = new DropShadow(0.5, Color.DARKRED);
        text_LugarProced.setEffect(null);
        if (text_LugarProced.getText().equals("")) {
            is = false;
            text_LugarProced.setEffect(effect);
        }
        return is;
    }


    //GestionarEspecialidades
    private void InicializarEspecialidades() throws Exception {
        colum_especi.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Area_Lugar, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Area_Lugar, String> param) {
                return param.getValue().getValue().nombreProperty();
            }
        });
        tabla_especialidad.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Area_Lugar>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<Area_Lugar>> observable, TreeItem<Area_Lugar> oldValue, TreeItem<Area_Lugar> newValue) {
                if (!tabla_especialidad.getSelectionModel().isEmpty()) {
                    btn_addEspecialidad.setText("Editar");
                    btn_delEspecialidad.setDisable(false);
                    text_especialidad.setText(tabla_especialidad.getSelectionModel().getSelectedItem().getValue().getNombre());
                } else {
                    btn_delEspecialidad.setDisable(true);
                    text_especialidad.setText("");
                    btn_addEspecialidad.setText("Añadir");
                }
            }
        });
        btn_addEspecialidad.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (tabla_especialidad.getSelectionModel().isEmpty()) {
                    try {
                        if (ValidarEspecialidades()) {
                            int confirmacion = 0;
                            confirmacion = GetPantalla.panel_De_Notificacion_Confirmar("Está seguro que desea crear la especialidad " + text_especialidad.getText() + "?", "Crear especialidad", "#065325");
                            if (confirmacion == 1) {

                                Service_Locator.getInstance().getEspecialidad_service().CreateUpdateEspecialidad(new Especialidad(-1, text_especialidad.getText()));
                                LlenarTablasEspecialidades();
                                text_especialidad.clear();
                                GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Especialidad creada correctamente", "DARKGREEN");
                            }
                        } else
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", "Formulario incompleto", "DARKRED");
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error",AppException.getJustMensaje( e.getMessage()), "DARKRED");
                    }

                } else {
                    try {
                        if (ValidarEspecialidades()) {

                            Especialidad especialidad = EspecialidadByID(tabla_especialidad.getSelectionModel().getSelectedItem().getValue().getId());
                            especialidad.setNombre(text_especialidad.getText());
                            text_especialidad.clear();
                            Service_Locator.getInstance().getEspecialidad_service().CreateUpdateEspecialidad(especialidad);
                            LlenarTablasEspecialidades();
                            btn_addEspecialidad.setText("Añadir");
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "Especialidad editada correctamente", "DARKGREEN");
                        } else
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", "Formulario incompleto", "DARKRED");
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", AppException.getJustMensaje( e.getMessage()), "DARKRED");
                    }
                }
            }
        });
        btn_delEspecialidad.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!tabla_especialidad.getSelectionModel().isEmpty()) {
                    try {
                        int confirmacion = 0;
                        confirmacion = GetPantalla.panel_De_Notificacion_Confirmar("Está seguro que desea eliminar la especialidad " + tabla_especialidad.getSelectionModel().getSelectedItem().getValue().getNombre() + "?", "Eliminar especialidad", "#065325");
                        if (confirmacion == 1) {
                            Service_Locator.getInstance().getEspecialidad_service().DeleteEspecialidad(tabla_especialidad.getSelectionModel().getSelectedItem().getValue().getId());
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION, "Información", "La especialidad se eliminó correctamente", "darkgreen");
                            LlenarTablasEspecialidades();
                            btn_addEspecialidad.setText("Añadir");
                            text_especialidad.clear();

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR, "Error", "La especialidad seleccionada no puede ser eliminada.", "DARKRED");
                    }

                }
            }
        });
        text_buscarEspecialidad.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                try {
                    String text = text_buscarEspecialidad.getText();
                    String e = event.getCharacter();
                    if (e.equals("\t") || e.equals("\b"))
                        e = "";
                    text += e;

                    LlenarTablasEspecialidades();
                    if (!text.equals(""))
                        FiltrarTablaEspecialidades(text);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
        LlenarTablasEspecialidades();
    }

    private void LlenarTablasEspecialidades() throws Exception {
        especialidads = Service_Locator.getInstance().getEspecialidad_service().Especialidades_List();
        Iterator<Especialidad> iterator = especialidads.iterator();
        especialidadList = FXCollections.observableArrayList();
        while (iterator.hasNext()) {
            Especialidad next = iterator.next();
            especialidadList.add(new Area_Lugar(next.getNombre(), next.getId()));
        }
        final TreeItem<Area_Lugar> item = new RecursiveTreeItem<>(especialidadList, RecursiveTreeObject::getChildren);
        tabla_especialidad.setShowRoot(false);
        tabla_especialidad.setRoot(item);
    }

    private void FiltrarTablaEspecialidades(String text) {
        Predicate<Area_Lugar> predicate = new Predicate<Area_Lugar>() {
            @Override
            public boolean test(Area_Lugar area_lugar) {
                boolean is = true;
                if (area_lugar.getNombre().toLowerCase().contains(text.toLowerCase()))
                    is = false;
                return is;
            }
        };
        final TreeItem<Area_Lugar> item = new RecursiveTreeItem<>(new FiltrarTablas<Area_Lugar>().FiltrarTabla(especialidadList, predicate), RecursiveTreeObject::getChildren);
        tabla_especialidad.setRoot(item);
    }

    private boolean ValidarEspecialidades() {
        boolean is = true;
        Effect effect = new DropShadow(0.5, Color.DARKRED);
        text_especialidad.setEffect(null);
        if (text_especialidad.getText().equals("")) {
            is = false;
            text_especialidad.setEffect(effect);
        }
        return is;
    }

    private void DesactivarRoles() {
        check_VR.setDisable(false);
        check_DRH.setDisable(false);
        check_JA.setDisable(false);
        check_ERH.setDisable(false);

        if (check_VR.isSelected()) {
            check_DRH.setDisable(true);
            check_JA.setDisable(true);
            check_ERH.setDisable(true);
        } else if (check_DRH.isSelected()) {
            check_VR.setDisable(true);
            check_JA.setDisable(true);
            check_ERH.setDisable(true);
        } else if (check_JA.isSelected()) {
            check_DRH.setDisable(true);
            check_VR.setDisable(true);
            check_ERH.setDisable(true);
        } else if (check_ERH.isSelected()) {
            check_DRH.setDisable(true);
            check_JA.setDisable(true);
            check_VR.setDisable(true);
        }
    }


    private class Directorio_Usuario_Data2 extends RecursiveTreeObject<Directorio_Usuario_Data2> {
        private int id;
        private ObjectProperty<Label> Nombre;
        private ObjectProperty<Label> Carnet;
        private ObjectProperty<Label> NombreUsuario;
        private ObjectProperty<Label> AreaPerteneciente;

        public Directorio_Usuario_Data2(int id, String nombre, String carnet, String nombreUsuario, String areaPerteneciente, boolean act) {
            this.id = id;
            Nombre = new SimpleObjectProperty<>(new Label(nombre));
            Carnet = new SimpleObjectProperty<>(new Label(carnet));
            NombreUsuario = new SimpleObjectProperty<>(new Label(nombreUsuario));
            AreaPerteneciente = new SimpleObjectProperty<>(new Label(areaPerteneciente));
            if (act) {
                Nombre.getValue().setTextFill(Color.GREEN);
                Carnet.getValue().setTextFill(Color.GREEN);
                NombreUsuario.getValue().setTextFill(Color.GREEN);
                AreaPerteneciente.getValue().setTextFill(Color.GREEN);
            } else {
                Nombre.getValue().setTextFill(Color.RED);
                Carnet.getValue().setTextFill(Color.RED);
                NombreUsuario.getValue().setTextFill(Color.RED);
                AreaPerteneciente.getValue().setTextFill(Color.RED);
            }
        }

        public int getId() {
            return id;
        }

        public Label getNombre() {
            return Nombre.get();
        }

        public ObjectProperty<Label> nombreProperty() {
            return Nombre;
        }

        public Label getCarnet() {
            return Carnet.get();
        }

        public ObjectProperty<Label> carnetProperty() {
            return Carnet;
        }

        public Label getNombreUsuario() {
            return NombreUsuario.get();
        }

        public ObjectProperty<Label> nombreUsuarioProperty() {
            return NombreUsuario;
        }

        public Label getAreaPerteneciente() {
            return AreaPerteneciente.get();
        }

        public ObjectProperty<Label> areaPertenecienteProperty() {
            return AreaPerteneciente;
        }
    }

    private class Area_Lugar extends RecursiveTreeObject<Area_Lugar> {
        int id;

        public int getId() {
            return id;
        }

        private StringProperty Nombre;

        public Area_Lugar(String nombre, int id) {
            Nombre = new SimpleStringProperty(nombre);
            this.id = id;
        }

        public String getNombre() {
            return Nombre.get();
        }

        public StringProperty nombreProperty() {
            return Nombre;
        }
    }
}
