package Servicio;

import Modelo.Directorio;
import Modelo.Notificacion;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;

public class Notificacion_Service
{
    public String RevisarNotificaciones(int subscriptor) throws Exception    {
        String not="";
        Iterator<Notificacion> notificacions=getAllNotificaciones(subscriptor).iterator();
        while (notificacions.hasNext())
            not+=notificacions.next().getMensaje()+"\n";
        return not;
    }
    public ArrayList<Notificacion> getAllNotificaciones(int idSubs) throws Exception    {
        ArrayList<Notificacion> notificacions=new ArrayList<>();
        Connection connection=Repository.getConnetion();
        connection.setAutoCommit(false);
        CallableStatement statement=connection.prepareCall("{?= call servicio_notificaciones(?)}");
        statement.registerOutParameter(1, Types.OTHER);
        statement.setInt(2,idSubs);
        statement.execute();

        ResultSet resultSet= ((ResultSet) statement.getObject(1));
        notificacions=new ArrayList<>(new Repository<Notificacion>(new Notificacion()).Adaptador_List(resultSet));
        connection.close();
        Iterator<Notificacion> iterator=notificacions.iterator();
        Notificacion notificacion=null;
        while (iterator.hasNext())
        {
            notificacion=iterator.next();
        }

        return notificacions;
    }
    public void EnviarNotificacion(String sms,int Subs)throws Exception
    {
        String s1="\t "+Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser().getNombreApellido()+"\n";
        String s2=sms+"\n";
        String s3="\tFecha: "+ LocalDate.now().toString()+"  Hora: "+ LocalTime.now().toString();
        String s4=s1+s2+s3;
        new Repository<Notificacion>(new Notificacion()).Create(new Notificacion(-1,s4,Subs));
    }
    public void RemoveAllNotificaciones(int idSuscritor) throws Exception    {
        Connection connection=Repository.getConnetion();
        connection.setAutoCommit(false);
        CallableStatement statement=connection.prepareCall("{call service_remove_notificacion(?)}");
        statement.setInt(1, idSuscritor);
        statement.execute();
        connection.close();
    }
    public void RemoveNotificacion(int idNotif) throws Exception  {
        new Repository<Notificacion>(new Notificacion()).Remove(idNotif);
    }
    public void EnviarNotificacionToDirectorRecursosHumanos(String sms)  throws Exception    {
        String SQL="select directorio.* from directorio inner join usuariorol on directorio.id=usuariorol.usuario join rol on usuariorol.rol=rol.id where rol.nombre='Director de Recursos Humanos' and directorio.activo=true";
        Connection connection=Repository.getConnetion();
        PreparedStatement statement=connection.prepareStatement(SQL);
        Directorio directorio=new Repository<Directorio>(new Directorio()).Adaptador_Object(statement.executeQuery());

        EnviarNotificacion(sms,directorio.getId());
    }
    public void EnviarNotificacionToJefeArea(int area,String sms)throws Exception    {
        String SQL="select directorio.* from directorio inner join usuariorol on directorio.id=usuariorol.usuario join rol on usuariorol.rol=rol.id where rol.nombre='Jefe de área' and directorio.area=? and directorio.activo=true";
        Connection connection=Repository.getConnetion();
        PreparedStatement statement=connection.prepareStatement(SQL);
        statement.setInt(1,area);
        Directorio directorio=new Repository<Directorio>(new Directorio()).Adaptador_Object(statement.executeQuery());

        EnviarNotificacion(sms,directorio.getId());
    }
    public void EnviarNotifiacionToVicerrector(String sms)throws Exception    {
        String SQL="select directorio.* from directorio inner join usuariorol on directorio.id=usuariorol.usuario join rol on usuariorol.rol=rol.id where rol.nombre='Vicerrector' and directorio.activo=true";
        Connection connection=Repository.getConnetion();
        PreparedStatement statement=connection.prepareStatement(SQL);
        Directorio directorio=new Repository<Directorio>(new Directorio()).Adaptador_Object(statement.executeQuery());

        EnviarNotificacion(sms,directorio.getId());
    }
    public void EnviarNotificacionMasiva(String sms) throws Exception
    {
        int miId=Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser().getId();
        String s1=Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser().getNombreApellido()+"\n";
        String s2=sms+"\n";
        String s3="\tFecha: "+ LocalDate.now().toString()+"  Hora: "+ LocalTime.now().toString();
        String s4=s1+s2+s3;
        Iterator<Directorio> iterator=Service_Locator.getInstance().getDirectorio_service().getAllActivos().iterator();
        while (iterator.hasNext()) {
            Directorio directorio=iterator.next();
            if (miId!=directorio.getId())
            new Repository<Notificacion>(new Notificacion()).Create(new Notificacion(-1, s4, directorio.getId()));
        }
    }

}
