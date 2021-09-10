package Auxiliar;

import java.util.ArrayList;

public class TuplaList<T>
{
    protected int operacion;
    protected ArrayList<T> listaobjeto;

    public TuplaList(int operacion) {
        this.operacion = operacion;
        this.listaobjeto=new ArrayList<>();
    }
    public void setOperacion(int operacion) {
        this.operacion = operacion;
    }
    public int getOperacion() {
        return operacion;
    }

    public ArrayList<T> getListaobjeto() {
        return listaobjeto;
    }
}
