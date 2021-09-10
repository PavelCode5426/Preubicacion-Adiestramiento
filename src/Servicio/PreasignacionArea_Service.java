package Servicio;

import Modelo.Area;
import Modelo.AreaPreasignada;
import Modelo.Estudiante;

import java.util.ArrayList;

public class PreasignacionArea_Service
{

    public AreaPreasignada getByID(int id) throws Exception    {
        return new Repository<AreaPreasignada>(new AreaPreasignada()).Get(id);
    }
    public ArrayList<AreaPreasignada> getAllAreasPreasignadas() throws Exception    {
        return new ArrayList<>(new Repository<AreaPreasignada>(new AreaPreasignada()).GetAll());
    }
    public void Remove(int id) throws Exception    {
        new Repository<AreaPreasignada>(new AreaPreasignada()).Remove(id);
    }
    public void Create_UpdateAreaPreasignada(AreaPreasignada areaPreasignada)throws Exception    {
        new Repository<AreaPreasignada>(new AreaPreasignada()).Update(areaPreasignada.getId(),areaPreasignada);
    }
    public void UbicarEstudiante(int estudiante,int area)throws Exception    {
        Graduado_Service service=Service_Locator.getInstance().getGraduado_service();
        Estudiante est=service.getGraduado(estudiante);
        est.setArea(area);
        service.updateGraduado(est);
    }
}
