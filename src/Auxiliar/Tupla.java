package Auxiliar;

public class Tupla<T>
{
    protected int operacion;   //0- nada,  1-actualizar,    -1-borrar,   2-insertar
    protected T objeto;

    public Tupla(int operacion, T objeto) {
        this.operacion = operacion;
        this.objeto = objeto;
    }
    public void setOperacion(int operacion) {
        this.operacion = operacion;
    }
    public void setObjeto(T objeto) {
        this.objeto = objeto;
    }
    public int getOperacion() {
        return operacion;
    }
    public T getObjeto() {
        return objeto;
    }

    @Override
    public boolean equals(Object o)
    {
        boolean is=false;
        if (o instanceof Tupla<?> && ((T) o).equals(o))
            is=true;
        return is;
    }

}
