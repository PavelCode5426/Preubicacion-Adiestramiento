package Servicio;

import Modelo.Area;
import Modelo.Especialidad;

import java.util.ArrayList;

public class Area_Service
{
    public ArrayList<Area> Areas_List() throws Exception
    {
        return new ArrayList<>(new Repository<Area>(new Area()).GetAll());
    }
    public Area getArea(int id) throws Exception
    {
        return new Repository<Area>(new Area()).Get(id);
    }
    public void CreateUpdate(Area area)throws Exception
    {
        //variante 1
        //new Repository<Area>(new Area()).Update(area.getId(),area);
        Repository<Area> repository=new Repository<>(area);
        if (repository.Get(area.getId())==null)
            repository.Create(area);
        else
            repository.Update(area.getId(),area);
    }
    public void DeleteArea(int id)throws Exception
    {
        new Repository<Area>(new Area()).Remove(id);
    }
}
