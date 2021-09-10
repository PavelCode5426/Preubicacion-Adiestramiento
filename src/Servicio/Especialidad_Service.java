package Servicio;

import Modelo.Especialidad;
import Modelo.Estudiante;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Especialidad_Service
{
    public ArrayList<Especialidad> Especialidades_List() throws Exception
    {
        ArrayList<Especialidad> a =new ArrayList<>(new Repository<Especialidad>(new Especialidad()).GetAll());
        return a;
    }
    public Especialidad EspecialidadID(int id)throws Exception
    {
        Especialidad especialidad=null;
        boolean is=false;
        ListIterator<Especialidad> e=Especialidades_List().listIterator();
        while (e.hasNext()&&!is) {
            especialidad=e.next();
            if (especialidad.getId() == id)
                is=true;
        }
        if (!is)
            especialidad=null;
        return especialidad;
    }
    public void DeleteEspecialidad(int ID)throws Exception
    {
        new Repository<Especialidad>(new Especialidad()).Remove(ID);
    }
    public void CreateUpdateEspecialidad(Especialidad especialidad) throws Exception
    {
        //variante 1 no funciona
        //new Repository<Especialidad>(new Especialidad()).Update(especialidad.getId(),especialidad);
        Repository<Especialidad> repository=new Repository<>(especialidad);
        if (repository.Get(especialidad.getId())==null)
            repository.Create(especialidad);
        else
            repository.Update(especialidad.getId(),especialidad);
    }
    public void NuevaEspecialidad(String lugar)throws Exception
    {
        new Repository<Especialidad>(new Especialidad()).Create(new Especialidad(-1,lugar));
    }
    public Especialidad EspecialidadByNameOffline(String name) throws Exception
    {
        ListIterator<Especialidad> iterator=Especialidades_List().listIterator();
        Especialidad next=null;
        boolean is=false;
        while (iterator.hasNext()&&!is)
        {
            next=iterator.next();
            if (next.getNombre().equals(name))
                is=true;
        }
        if (!is)
        {
            NuevaEspecialidad(name);
            System.out.println("EL ID ES "+(next.getId()+1));
            next=EspecialidadID(next.getId()+1);
        }
        return next;
    }
    public ArrayList<Especialidad> Especialidad_List_From_SECUJAE() throws Exception
    {
        Repository<Especialidad> repository=new Repository<>(new Especialidad());
        Connection connection=repository.getConnetionSECUJAE();
        connection.setAutoCommit(false);
        CallableStatement statement=connection.prepareCall("{?=call get_all_especialidades()}");
        statement.registerOutParameter(1, Types.OTHER);
        statement.execute();
        return new ArrayList<>(repository.Adaptador_List(((ResultSet) statement.getObject(1))));
    }
}
