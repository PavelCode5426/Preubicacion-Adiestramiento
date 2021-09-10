package Controlador;

import Auxiliar.Excepciones.AppException;
import Modelo.Area;
import Modelo.Directorio;
import Modelo.Rol;
import Servicio.Service_Locator;
import Vista.GetPantalla;
import Vista.IDraggedScene;
import com.jfoenix.controls.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.embed.swt.FXCanvas;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class Perfil_Controller implements Initializable, IDraggedScene
{


    @FXML
    private AnchorPane PaneExterior;
    @FXML
    private ImageView portada;
    @FXML
    private JFXTextField nom_ape;
    @FXML
    private JFXToggleButton cambiar;
    @FXML
    private JFXButton camb_contr;
    @FXML
    private JFXPasswordField cont_ant;
    @FXML
    private JFXPasswordField new_cont;
    @FXML
    private JFXPasswordField confi_new_cont;
    @FXML
    private JFXTextField carnet;
    @FXML
    private JFXTextField cargo;
    @FXML
    private JFXComboBox<String> area;
    @FXML
    private JFXTextField user;
    @FXML
    private JFXCheckBox check_admin;
    @FXML
    private JFXCheckBox check_vr1;
    @FXML
    private JFXCheckBox check_ja;
    @FXML
    private JFXCheckBox check_drh;
    @FXML
    private JFXCheckBox check_erh;
    @FXML
    private JFXCheckBox check_tutor;
    @FXML
    private JFXButton acept;
    @FXML
    private ImageView imageView;
    @FXML
    private JFXButton change_photo;
    @FXML
    private JFXButton botonCerrar;


    private Directorio directorio;
    private static Stage stage;
    private BooleanProperty changes=new SimpleBooleanProperty(false);
    private ArrayList<Area> areas=new ArrayList<>();
    private File file;
    private FileInputStream fileInputStream;

    public static void setStage(Stage stage) {
        Perfil_Controller.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
         onDraggedScene(PaneExterior);
         ConfigurarMinimizarMaximizar(PaneExterior,null,botonCerrar);
        try
        {
            Rectangle rectangle=new Rectangle(imageView.getFitWidth(),imageView.getFitHeight());
            rectangle.setArcHeight(200);
            rectangle.setArcWidth(200);


            rectangle.setEffect(new DropShadow(0.5,Color.BLACK));
            imageView.setClip(rectangle);
            LlenarDatos();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void LlenarDatos() throws Exception
    {
        directorio= Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser();
        areas=Service_Locator.getInstance().getArea_service().Areas_List();
        Iterator<Rol> iterator=Service_Locator.getInstance().getLoggin_roles_service().geRolesFromDirectorio(directorio.getId()).iterator();
        ArrayList<String> list=new ArrayList<>();
        while (iterator.hasNext())
            list.add(iterator.next().getNombre());

        check_admin.setSelected(list.contains("Super Administrador"));
        if (!check_admin.isSelected())
            check_admin.setSelected(list.contains(check_admin.getText()));

        check_vr1.setSelected(list.contains(check_vr1.getText()));
        check_ja.setSelected(list.contains(check_ja.getText()));
        check_drh.setSelected(list.contains(check_drh.getText()));
        check_erh.setSelected(list.contains(check_erh.getText()));
        check_tutor.setSelected(list.contains(check_tutor.getText()));

        nom_ape.setText(directorio.getNombreApellido());
        user.setText(directorio.getNusuario());
        carnet.setText(directorio.getCarnetIdentidad());
        cargo.setText(directorio.getCargo());



        if (check_admin.isSelected())
            check_admin.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    check_admin.setSelected(true);
                }
            });
        else
            check_admin.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    check_admin.setSelected(false);
                }
            });

        if (check_vr1.isSelected())
            check_vr1.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    check_vr1.setSelected(true);
                }
            });
        else
            check_vr1.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    check_vr1.setSelected(false);
                }
            });


        if (check_ja.isSelected())
            check_ja.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    check_ja.setSelected(true);
                }
            });
        else
            check_ja.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    check_ja.setSelected(false);
                }
            });


        if (check_drh.isSelected())
            check_drh.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    check_drh.setSelected(true);
                }
            });
        else
            check_drh.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    check_drh.setSelected(false);
                }
            });


        if (check_erh.isSelected())
            check_erh.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    check_erh.setSelected(true);
                }
            });
        else
            check_erh.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    check_erh.setSelected(false);
                }
            });

        if (check_tutor.isSelected())
            check_tutor.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    check_tutor.setSelected(true);
                }
            });
        else
            check_tutor.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    check_tutor.setSelected(false);
                }
            });


        changes.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                acept.setDisable(false);
            }
        });
        cambiar.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                camb_contr.setDisable(!newValue);
                confi_new_cont.setDisable(!newValue);
                cont_ant.setDisable(!newValue);
                new_cont.setDisable(!newValue);
                cont_ant.setText("");
                confi_new_cont.setText("");
                new_cont.setText("");

            }
        });
        camb_contr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try
                {
                    if (ValidarContraseña())
                    {

                        int guardar=GetPantalla.panel_De_Notificacion_Confirmar("Está seguro que desea cambiar la contraseña?","Confirmación","Darkgreen");
                        if(guardar==1) {

                            String pas=Service_Locator.getInstance().getSecurity_service().EncriptarTexto(new_cont.getText());
                            directorio.setContrasenna(pas);
                            Service_Locator.getInstance().getDirectorio_service().CreateUpdateDirectorio(directorio);

                            cont_ant.setText("");
                            confi_new_cont.setText("");
                            new_cont.setText("");
                            cambiar.setSelected(false);
                            GetPantalla.Pantalla_Alerta_(Alert.AlertType.INFORMATION,"Información","Contraseña cambiada correctamente","DARKGREEN");
                        }}
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR,"Error", AppException.getJustMensaje(e.getMessage()),"DARKRED");
                }
            }
        });

        nom_ape.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String s=event.getCharacter();
                if (Character.isDigit(s.charAt(0)))
                    event.consume();

            }
        });
        carnet.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String s=event.getCharacter();
                if (!Character.isDigit(s.charAt(0))||carnet.getText().length()>=11)
                    event.consume();
            }
        });



        acept.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try
                {
                    if(ValidarDatos())
                    {
                        if (changes.getValue())
                            GuardarDatos();
                        stage.close();
                    }
                    else
                        GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR,"Error","Formulario Incompleto","DARKRED");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    GetPantalla.Pantalla_Alerta_(Alert.AlertType.ERROR,"Error",AppException.getJustMensaje(e.getMessage()),"DARKRED");

                }

            }
        });

        change_photo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Image image=BuscarFotografia();
                    if (image!=null)
                        imageView.setImage(image);
                    changes.set(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });
        LlenarCombobox();
        area.getSelectionModel().select(Buscar_Area_By_ID(directorio.getArea()).getNombre());
        nom_ape.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                changes.set(true);
            }
        });
        user.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                changes.set(true);
            }
        });
        carnet.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                changes.set(true);
            }
        });
        cargo.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                changes.set(true);
            }
        });
        area.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                changes.set(true);
            }
        });


        imageView.setImage(ConvertirImagen(Service_Locator.getInstance().getDirectorio_service().GetFotoPerfil(directorio.getId())));

    }



    private Area Buscar_Area_By_ID(int id)  {
        Area area=null;
        boolean is=false;
        Iterator<Area> iterator=areas.iterator();
        while (iterator.hasNext()&!is)
        {
            area=iterator.next();
            if (area.getId()==id)
                is=true;
        }
        if (!is)
            area=null;
        return area;
    }
    private Area Buscar_Area_By_Name(String nombre)  {
        Area area=null;
        boolean is=false;
        Iterator<Area> iterator=areas.iterator();
        while (iterator.hasNext()&!is)
        {
            area=iterator.next();
            if (area.getNombre().equals(nombre))
                is=true;
        }
        if (!is)
            area=null;
        return area;
    }
    private void LlenarCombobox()   {
        Iterator<Area> iterator=areas.iterator();
        area.getItems().clear();
        while (iterator.hasNext())
            area.getItems().add(iterator.next().getNombre());

    }
    private Image BuscarFotografia() throws Exception    {
        Image image=null;
        FileChooser fileChooser=new FileChooser();
        file=fileChooser.showOpenDialog(stage);
        if (file!=null)
            image=new Image(new FileInputStream(file));
        return image;
    }
    private boolean ValidarContraseña() throws Exception {
        boolean is=true;

        if (new_cont.getText().equals("")||cont_ant.getText().equals("")||confi_new_cont.getText().equals(""))        {
            is=false;
            throw new Exception("Formulario Incompleto");
        }
        if (!new_cont.getText().equals(confi_new_cont.getText()))         {
            is = false;
            throw new Exception("Las Contraseñas no coinciden");
        }
        if (!Service_Locator.getInstance().getSecurity_service().EncriptarTexto(cont_ant.getText()).equals(directorio.getContrasenna()))        {
            is=false;
            throw new Exception("Contraseña anterior incorrecta");
        }

        return is;
    }
    private void GuardarDatos() throws Exception    {
        directorio.setCargo(cargo.getText());
        directorio.setCarnetIdentidad(carnet.getText());
        directorio.setArea(Buscar_Area_By_Name(area.getValue()).getId());
        directorio.setNombreApellido(nom_ape.getText());
        directorio.setNusuario(user.getText());
        Service_Locator.getInstance().getDirectorio_service().CreateUpdateDirectorio(directorio);

        if (file!=null)
        {
            System.out.println("Guardando Foto");
            Service_Locator.getInstance().getDirectorio_service().SetFotoPerfil(directorio.getId(), file);
        }
    }
    private boolean ValidarDatos()    {
        boolean is=true;
        Effect effect=new DropShadow(0.5,Color.DARKRED);

        cargo.setEffect(null);
        carnet.setEffect(null);
        area.setEffect(null);
        nom_ape.setEffect(null);
        user.setEffect(null);

        if (cargo.getText().equals(""))
        {
            is=false;
            cargo.setEffect(effect);
        }
        if (carnet.getText().equals("")||carnet.getText().length()<11)
        {
            is=false;
            carnet.setEffect(effect);
        }
        if (area.getSelectionModel().isEmpty())
        {
            is=false;
            area.setEffect(effect);
        }
        if (nom_ape.getText().equals(""))
        {
            is=false;
            nom_ape.setEffect(effect);
        }
        if (user.getText().equals(""))
        {
            is=false;
            user.setEffect(effect);
        }
        return is;
    }
    private Image ConvertirImagen(Image image) throws Exception
    {
        Image img=new Image(new File("Vista/Imagenes/logo cujae.png").getPath());
        if(image!=null)
            img=image;
        return img;
    }



}
