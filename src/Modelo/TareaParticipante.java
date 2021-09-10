package Modelo;

public class TareaParticipante
{
    private int id;
    private int tarea;
    private int participante;

    public TareaParticipante(int id, int tarea, int participante) {
        this.id = id;
        this.tarea = tarea;
        this.participante = participante;
    }

    public TareaParticipante() {
        id=0;
        tarea=0;
        participante=0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTarea() {
        return tarea;
    }

    public void setTarea(int tarea) {
        this.tarea = tarea;
    }

    public int getParticipante() {
        return participante;
    }

    public void setParticipante(int participante) {
        this.participante = participante;
    }
}
