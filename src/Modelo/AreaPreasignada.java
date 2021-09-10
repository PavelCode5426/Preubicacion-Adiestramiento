package Modelo;

public class AreaPreasignada
{
    int id;
    int estudiante;
    int area;
    public AreaPreasignada()
    {
        id=-1;
        estudiante=-1;
        area=-1;
    }

    public AreaPreasignada(int id, int estudiante, int area) {
        this.id = id;
        this.estudiante = estudiante;
        this.area = area;
    }

    public int getId() {
        return id;
    }

    public int getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(int estudiante) {
        this.estudiante = estudiante;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    @Override
    public boolean equals(Object obj) {
        boolean is=false;
        if (((AreaPreasignada) obj).getArea()==area&&((AreaPreasignada) obj).getEstudiante()==estudiante)
            is=true;
        return is;
    }
}
