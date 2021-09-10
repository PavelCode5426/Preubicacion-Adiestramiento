package Servicio;

import Modelo.Especialidad;
import Modelo.LugarProcedencia;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.ResourceBundle;

public class Procedencia_Service
{
    public ArrayList<LugarProcedencia> Procedencias_List() throws Exception
    {
        ArrayList<LugarProcedencia> a =new ArrayList<>(new Repository<LugarProcedencia>(new LugarProcedencia()).GetAll());
        return a;
    }
    public String ProcedenciaID(int id)throws Exception
    {
        String nom="";
        LugarProcedencia lugarProcedencia=null;
        boolean is=false;
        ListIterator<LugarProcedencia> e=Procedencias_List().listIterator();
        while (e.hasNext()&&!is) {
            lugarProcedencia =e.next();
            if (lugarProcedencia.getId() == id) {
                nom = lugarProcedencia.getLugar();
                is=false;
            }
        }
        return nom;
    }
    public void NuevaProcedencia(String lugar)throws Exception
    {
        new Repository<LugarProcedencia>(new LugarProcedencia()).Create(new LugarProcedencia(-1,lugar));
    }
    public void CreateUpdateLugarProcedencia(LugarProcedencia lugarProcedencia) throws Exception
    {
        new Repository<LugarProcedencia>(new LugarProcedencia()).Update(lugarProcedencia.getId(),lugarProcedencia);
    }
    public void DeleteLugarProcedencia(int ID)throws Exception
    {
        new Repository<LugarProcedencia>(new LugarProcedencia()).Remove(ID);
    }
}
