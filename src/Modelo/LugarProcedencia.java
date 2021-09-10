package Modelo;

public class LugarProcedencia
{
    private int id;
    private String lugar;

    public String getLugar() {
        return lugar;
    }

    public int getId() {
        return id;
    }

    public LugarProcedencia() {
        id=-1;
        lugar="";
    }

    public LugarProcedencia(int id, String lugar) {
        this.id = id;
        this.lugar = lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
}
