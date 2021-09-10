package Servicio;

import Modelo.*;

import java.sql.*;
import java.util.ArrayList;

public class Graduado_Service
{
    public ArrayList<Estudiante> get_All_GraduadosList_Ara() throws Exception    {
        Connection connection=Repository.getConnetion();
        connection.setAutoCommit(false);
        CallableStatement statement = connection.prepareCall("{?=call get_all_graduados()}");
        statement.registerOutParameter(1,Types.OTHER);
        statement.execute();
        ResultSet resultSet=((ResultSet) statement.getObject(1));
        connection.close();
        return new ArrayList<>(new Repository<Estudiante>(new Estudiante()).Adaptador_List(resultSet));
    }

    public ResultSet Graduados_Sin_ubicacion() throws Exception    {
        ResultSet set;
        String consultaSQL="{call servicio_graduados_sin_ubicacion()}";
        CallableStatement statement=Repository.getConnetion().prepareCall(consultaSQL);
        set=statement.executeQuery();
        return set;
    }
    public ArrayList<Estudiante> Graduados_Sin_ubicacion_List() throws Exception    {
        Connection connection=Repository.getConnetion();
        connection.setAutoCommit(false);
        CallableStatement statement = connection.prepareCall("{?=call servicio_get_graduados_sin_ubicacion()}");
        statement.registerOutParameter(1,Types.OTHER);
        statement.execute();
        ResultSet resultSet=((ResultSet) statement.getObject(1));
        connection.close();
        return new ArrayList<>(new Repository<Estudiante>(new Estudiante()).Adaptador_List(resultSet));
    }
    public ResultSet Relacion_Graduados_Area() throws Exception    {
        ResultSet set;
        String consultaSQL="{?=call servicio_relacion_graduados_area()}";
        Connection connection=Repository.getConnetion();
        connection.setAutoCommit(false);
        CallableStatement statement=connection.prepareCall(consultaSQL);
        statement.registerOutParameter(1, Types.OTHER);
        statement.execute();
        set=(ResultSet) statement.getObject(1);
        return set;
    }
    public ResultSet Relacion_Graduados_Preubicado_Area() throws Exception    {
        ResultSet set;
        String consultaSQL="{?=call servicio_relacion_graduados_preubicados_area()}";
        Connection connection=Repository.getConnetion();
        connection.setAutoCommit(false);
        CallableStatement statement=connection.prepareCall(consultaSQL);
        statement.registerOutParameter(1, Types.OTHER);
        statement.execute();
        set=(ResultSet) statement.getObject(1);
        return set;
    }
    public ResultSet Preubicacion_Graduados_Info() throws Exception    {
        ResultSet set;
        String consultaSQL="{?=call get_preasignacion_estudiantes_info()}";
        Connection connection=Repository.getConnetion();
        connection.setAutoCommit(false);
        CallableStatement statement=connection.prepareCall(consultaSQL);
        statement.registerOutParameter(1, Types.OTHER);
        statement.execute();
        set=(ResultSet) statement.getObject(1);
        return set;
    }
    public ResultSet Graduados_sin_terminar(boolean cujaenno) throws Exception    {
        ResultSet resultSet;
        Connection connection=Repository.getConnetion();
        connection.setAutoCommit(false);
        String funcio="{?=call servicio_graduados_sin_terminar(?)}";
        CallableStatement statement=connection.prepareCall(funcio);
        statement.registerOutParameter(1,Types.OTHER);
        statement.setBoolean(2,cujaenno);
        statement.execute();
        resultSet=(ResultSet) statement.getObject(1);
        return resultSet;
    }
    public ArrayList<Estudiante> Graduados_sin_terminar_List(boolean cujaenno) throws Exception    {
        ResultSet resultSet;
        Connection connection=Repository.getConnetion();
        connection.setAutoCommit(false);
        String funcio="{?=call servicio_graduados_sin_terminar_all(?)}";
        CallableStatement statement=connection.prepareCall(funcio);
        statement.registerOutParameter(1,Types.OTHER);
        statement.setBoolean(2,cujaenno);
        statement.execute();
        resultSet=(ResultSet) statement.getObject(1);
        return new ArrayList<Estudiante>(new Repository<Estudiante>(new Estudiante()).Adaptador_List(resultSet));
    }
    public void CreateGraduado(Estudiante estudiante) throws Exception    {
        new Repository<Estudiante>(new Estudiante()).Create(estudiante);
    }
    public void updateGraduado(Estudiante estudiante) throws Exception    {
        System.out.println("Editando el estudiante de id  "+estudiante.getId());
        if (getGraduado(estudiante.getId())==null)
            new Repository<>(new Estudiante()).Create(estudiante);
        else
        new Repository<>(new Estudiante()).Update(estudiante.getId(),estudiante);
    }
    public void deleteGraduado(Estudiante estudiante) throws Exception    {
        new Repository<Estudiante>(new Estudiante()).Remove(estudiante.getId());
    }
    public ArrayList<Estudiante> getGraduadofromArea(int area)throws Exception    {
        ArrayList<Estudiante> estudiantes=null;
        Connection connection=Repository.getConnetion();
        connection.setAutoCommit(false);
        String a="{?=call service_getall_graduado_from_area_sin_planadiestramiento(?)}";
        CallableStatement  statement=connection.prepareCall(a);
        statement.registerOutParameter(1,Types.OTHER);
        statement.setInt(2,area);
        statement.execute();
        estudiantes=new ArrayList<>(new Repository<Estudiante>(new Estudiante()).Adaptador_List(((ResultSet) statement.getObject(1))));
        return estudiantes;
    }
    public Estudiante getGraduado(int id) throws Exception    {
        return new Repository<Estudiante>(new Estudiante()).Get(id);
    }
    public boolean NecesitaProrroga(int idEstudiante)throws Exception    {
        boolean is=false;
        ArrayList<Etapa> etapas=Service_Locator.getInstance().getPlanificacion_adiestramiento_service().get_Etapas_from_Estudiante(idEstudiante);
        if (Service_Locator.getInstance().getPlanificacion_adiestramiento_service().PromedioNotasPlanAdiestramiento(etapas.get(0).getPlan()) < 3 && etapas.get(3).getNota()!=null && etapas.size()==4)
            is=true;

        return is;
    }
    public ResultSet Relacion_Graduados_Plan_Adiestramiento_From_Area(int Area)throws Exception    {
        Connection connection=Repository.getConnetion();
        connection.setAutoCommit(false);
        CallableStatement statement=connection.prepareCall("{?=call servicio_graduados_plan_adiestramiento_relacion(?)}");
        statement.registerOutParameter(1,Types.OTHER);
        statement.setInt(2,Area);
        statement.execute();
        connection.close();
        ResultSet x= (ResultSet) statement.getObject(1);

        return x;
    }
    public ResultSet Relacion_Graduados_Plan_Familiarizacion_From_Area(int area)throws Exception    {
        Connection connection=Repository.getConnetion();
        connection.setAutoCommit(false);
        CallableStatement statement=connection.prepareCall("{?=call servicio_graduados_plan_familiarizacion_relacion(?)}");
        statement.registerOutParameter(1,Types.OTHER);
        statement.setInt(2,area);
        statement.execute();
        connection.close();
        ResultSet x= (ResultSet) statement.getObject(1);

        return x;
    }
    public ResultSet Estudiantes_From_SECUJAE() throws Exception    {
        Connection connection=Repository.getConnetionSECUJAE();
        connection.setAutoCommit(false);
        CallableStatement statement=connection.prepareCall("{?=call get_estudiantes_ultimo_anno()}");
        statement.registerOutParameter(1,Types.OTHER);
        statement.execute();
        ResultSet resultSet= ((ResultSet) statement.getObject(1));
        return resultSet;
    }
    public boolean Set_Adiestramiento_FROM_SECUJAE(int id,boolean is) throws Exception {
        Connection connection=Repository.getConnetionSECUJAE();
        CallableStatement statement=connection.prepareCall("{call set_adiestramiento(?,?)}");
        statement.setInt(1,id);
        statement.setBoolean(2,is);
        return statement.execute();
    }
    public ResultSet get_Estudiante_FROM_SECUJAE_BY_CARNET(String ci) throws Exception
    {
        Connection connection=Repository.getConnetionSECUJAE();
        connection.setAutoCommit(false);
        CallableStatement statement=connection.prepareCall("{?=call get_est_by_name_carnet(?)}");
        statement.registerOutParameter(1,Types.OTHER);
        statement.setString(2,ci);
        statement.execute();
        ResultSet resultSet= ((ResultSet) statement.getObject(1));
        return resultSet;
    }
    public ArrayList<Estudiante> getGraduadoFromArea_Ara(int area)throws Exception    {
        ArrayList<Estudiante> estudiantes=null;
        Connection connection=Repository.getConnetion();
        connection.setAutoCommit(false);
        String a="{?=call servicio_get_graduados_from_area(?)}";
        CallableStatement  statement=connection.prepareCall(a);
        statement.registerOutParameter(1,Types.OTHER);
        statement.setInt(2,area);
        statement.execute();
        estudiantes=new ArrayList<>(new Repository<Estudiante>(new Estudiante()).Adaptador_List(((ResultSet) statement.getObject(1))));
        return estudiantes;
    }
}
