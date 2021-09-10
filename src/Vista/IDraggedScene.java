package Vista;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicReference;

public interface IDraggedScene
{
    default void onDraggedScene(Parent fatherPane){
        AtomicReference<Double> xOffSet=new AtomicReference<>((double)0);
        AtomicReference<Double> yOffSet=new AtomicReference<>((double)0);


        fatherPane.setOnMousePressed(e->{
            Stage stage=(Stage) fatherPane.getScene().getWindow();
            xOffSet.set(stage.getX()- e.getScreenX());
            yOffSet.set(stage.getY()- e.getScreenY());
        });
        fatherPane.setOnMouseDragged(e->{
            Stage stage=(Stage) fatherPane.getScene().getWindow();
            stage.setX(e.getScreenX()+xOffSet.get());
            stage.setY(e.getScreenY()+yOffSet.get());
            fatherPane.setOpacity(0.25);
            fatherPane.setCursor(Cursor.CLOSED_HAND);
            //fatherPane.setStyle("-fx-cursor: CLOSED_HAND; -fx-background-radius: 25px; -fx-opacity:0.25");
        });
        fatherPane.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                fatherPane.setOpacity(1);
                fatherPane.setCursor(Cursor.DEFAULT);
            }
        });
        //fatherPane.setOnMouseReleased(e-> fatherPane.setStyle("-fx-cursor: default; -fx-background-radius: 25px;-fx-opacity:1"));

    }

    default void ConfigurarMinimizarMaximizar(Parent fatherPane, Node botonMinimizar,Node botonCerrar)
    {



         if (botonCerrar!=null)
            {

                botonCerrar.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Stage stage=(Stage) fatherPane.getScene().getWindow();
                        stage.close();
                    }
                });

                botonCerrar.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        botonCerrar.setStyle("-fx-background-color: red");
                    }
                });
            }
            if (botonMinimizar!=null)
            {

                botonMinimizar.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Stage stage=(Stage) fatherPane.getScene().getWindow();
                       stage.setIconified(true);
                    }
                });
                botonMinimizar.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        botonMinimizar.setStyle("-fx-background-color: blue");

                    }
                });

            }


            fatherPane.setOnMouseMoved(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(botonCerrar!=null)
                    botonCerrar.setStyle("-fx-background-color: #065325");
                    if(botonMinimizar!=null)
                    botonMinimizar.setStyle("-fx-background-color: #065325");

                }
            });


    }

}
