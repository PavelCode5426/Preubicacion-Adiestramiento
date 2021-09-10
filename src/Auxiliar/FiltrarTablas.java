package Auxiliar;

import com.jfoenix.controls.JFXTreeTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.function.Predicate;

public class FiltrarTablas<T>
{
    public ObservableList<T> FiltrarTabla(ObservableList<T> listOrig, Predicate<T> predicate)
    {
        ObservableList<T> list= FXCollections.observableArrayList(listOrig);
        list.removeIf(predicate);
        return list;
    }
}
