package Servicio;


import Modelo.Actividad;
import Modelo.ActividadesEstudiante;
import Modelo.ActividadesPlan;
import Modelo.Estudiante;
import Reportes.Auxiliares.ReporteEstXAreaXEspec_Aux;
import Reportes.Auxiliares.Reporte_AsistenciaActiv_X_Area_Aux;

import java.sql.*;
import java.util.ArrayList;


public class Report_Service
{
    public ArrayList<Estudiante> Reporte_List_Tesis_Cujae( int idDirectorio, int rolDirectorio)throws Exception
    {
        String consulta="{?=call Reporte_Lista_Est_Tesis_Aplic(?,?)}";
        Connection connection= Repository.getConnetion();
        connection.setAutoCommit(false);
        CallableStatement statement=connection.prepareCall(consulta);
        statement.setInt(2,idDirectorio);
        statement.setInt(3,rolDirectorio);
        statement.registerOutParameter(1, Types.OTHER);
        statement.execute();
        ArrayList<Estudiante> estudiantes=new ArrayList<>(new Repository<Estudiante>(new Estudiante()).Adaptador_List(((ResultSet)statement.getObject(1))));
        connection.close();
        return estudiantes;
    }


    public ArrayList<ReporteEstXAreaXEspec_Aux> Reporte_Cant_Espec_X_Area(int id_Area )throws Exception {

        ArrayList<ReporteEstXAreaXEspec_Aux> listaRetorno = new ArrayList<ReporteEstXAreaXEspec_Aux>();
        String consulta = "{?=call reporte_cant_est_x_area_y_espec(?)}";
        String contarEspecialidades = "select DISTINCT Count(especialidad.id) from especialidad;";
        int cantEspec = 0;
        int i=1;
        String espec=null;

        Connection connection = Repository.getConnetion();
        connection.setAutoCommit(false);
        CallableStatement statement = connection.prepareCall(consulta);
        ResultSet resultadoReporte = null;


      PreparedStatement preparedStatement1 = Repository.getConnetion().prepareStatement(contarEspecialidades);
       ResultSet resultSet1 = preparedStatement1.executeQuery();
       while(resultSet1.next()) {
           cantEspec = resultSet1.getInt(i);
          // espec=resultSet1.getString(i);
           i++;
           System.out.println(new Integer(cantEspec).toString());
       }


            statement.setInt(2, id_Area);
             statement.registerOutParameter(1, Types.OTHER);
            statement.execute();
            resultadoReporte = (ResultSet) statement.getObject(1);
            while(resultadoReporte.next())
                listaRetorno.add(new ReporteEstXAreaXEspec_Aux(
                        resultadoReporte.getString(1),
                        resultadoReporte.getInt(2)
                ));


         return listaRetorno;

    }


    public ArrayList<Reporte_AsistenciaActiv_X_Area_Aux> Reporte_AsistenciaAct_X_Area(int id_Area )throws Exception {

        ArrayList<Reporte_AsistenciaActiv_X_Area_Aux> listaReturn;
        listaReturn = new ArrayList<Reporte_AsistenciaActiv_X_Area_Aux>();
        ArrayList<ActividadesPlan> listaActividadesPlan= new ArrayList<ActividadesPlan>();
        listaActividadesPlan=actividadesPlans();
        float porcientoAsist=0;
        String consulta="select distinct * from actividadesestudiante WHERE actividadesestudiante.actividad=?;";

        for(int i=0;i<listaActividadesPlan.size();i++) {
            Connection connection= Repository.getConnetion();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement1 = Repository.getConnetion().prepareStatement(consulta);

            preparedStatement1.setInt(1,listaActividadesPlan.get(i).getId());
            ResultSet resultSet1 = preparedStatement1.executeQuery();

            ArrayList<ActividadesEstudiante> estudiantes=new ArrayList<ActividadesEstudiante>(new Repository<ActividadesEstudiante>(new ActividadesEstudiante()).Adaptador_List(resultSet1));

            porcientoAsist=contarAsistencia(estudiantes,listaActividadesPlan.get(i).getId());
            Actividad actividad = new Repository<Actividad>(new Actividad()).Get(listaActividadesPlan.get(i).getAct());

            listaReturn.add(new Reporte_AsistenciaActiv_X_Area_Aux(actividad.getNombre(),listaActividadesPlan.get(i).getFecha().toString(),String.valueOf(porcientoAsist)+"%",listaActividadesPlan.get(i).getHora().toString()));


        }


        return listaReturn;
        }

        public ArrayList<ActividadesPlan> actividadesPlans()throws Exception {
            ArrayList<ActividadesPlan> listaActividadesPlan= new ArrayList<ActividadesPlan>();


                String consulta="select distinct actividadesplan.* from actividadesplan,planfamiliarizacion " +
                        " where actividadesplan.plan=(select " +
                        "planfamiliarizacion.id from planfamiliarizacion where " +
                        "planfamiliarizacion.revisado=true and planfamiliarizacion.rechazado=FALSE " +
                        " group by planfamiliarizacion.id " +
                        "having (select planfamiliarizacion.anno from planfamiliarizacion " +
                        "where extract(year from CURRENT_DATE )=planfamiliarizacion.anno and planfamiliarizacion.aprobado is " +
                        "not null)=planfamiliarizacion.anno " +
                        "order by planfamiliarizacion.anno desc limit 1)";
                Connection connection= null;
                connection = Repository.getConnetion();
                PreparedStatement preparedStatement1 = Repository.getConnetion().prepareStatement(consulta);
                ResultSet resultSet1 = preparedStatement1.executeQuery();
                listaActividadesPlan=new ArrayList<ActividadesPlan>(new Repository<ActividadesPlan>(new ActividadesPlan()).Adaptador_List(resultSet1));

              return listaActividadesPlan;

        }



    private float contarAsistencia(ArrayList<ActividadesEstudiante> estudiantes, int activ) {
        float floatretorno = 0;
        int cont=0;

       if(estudiantes.size()>0) {
           for (int i = 0; i < estudiantes.size(); i++) {
               if (estudiantes.get(i).isAsistencia() != null) {
                   if (estudiantes.get(i).isAsistencia())
                       cont++;
               }
           }
           floatretorno = cont * 100 / estudiantes.size();
       }


        return floatretorno;
    }

    public ArrayList<Estudiante> Reporte_Adiestrados_Primero()throws Exception
    {
        String consulta="{?=call reporte_adiestrados_1er_anno()}";
        Connection connection= Repository.getConnetion();
        connection.setAutoCommit(false);
        CallableStatement statement=connection.prepareCall(consulta);
        statement.registerOutParameter(1, Types.OTHER);
        statement.execute();
        ArrayList<Estudiante> estudiantes=new ArrayList<>(new Repository<Estudiante>(new Estudiante()).Adaptador_List(((ResultSet)statement.getObject(1))));
        connection.close();
        return estudiantes;
    }



    public ResultSet Reporte_Tutor_Est_Jefe()throws Exception{

        ResultSet resultSet=null;
        String consulta="{?=call reporte_tutor_estudiante_area()}";
        Connection connection= Repository.getConnetion();
        connection.setAutoCommit(false);
        CallableStatement statement=connection.prepareCall(consulta);
        statement.registerOutParameter(1, Types.OTHER);
        statement.execute();
        resultSet=(ResultSet)statement.getObject(1);
        connection.close();

        return resultSet;
    }

    public ResultSet Reporte_Est_X_Espec(int idDirectorio, String especialidad,int idRol) throws  Exception{
        ResultSet resultSet=null;
        String consulta="{?=call reporte_est_x_espec_tuto(?,?,?)}";
        Connection connection=Repository.getConnetion();
        connection.setAutoCommit(false);
        CallableStatement statement=connection.prepareCall(consulta);
        statement.registerOutParameter(1,Types.OTHER);
        statement.setInt(2,idDirectorio);
        statement.setString(3,especialidad);
        statement.setInt(4,idRol);
        statement.execute();
        resultSet=(ResultSet) statement.getObject(1);
        connection.close();

        return resultSet;
    }

    public ResultSet Reporte_Est_X_Procedencia(int idDirectorio, int idRol, String procedencia) throws  Exception{
        ResultSet resultSet=null;
        String consulta="{?=call reporte_est_x_centro_proced(?,?,?)}";
        Connection connection=Repository.getConnetion();
        connection.setAutoCommit(false);
        CallableStatement statement=connection.prepareCall(consulta);
        statement.registerOutParameter(1,Types.OTHER);
        statement.setInt(2,idDirectorio);
        statement.setString(4,procedencia);
        statement.setInt(3,idRol);
        statement.execute();
        resultSet=(ResultSet) statement.getObject(1);
        connection.close();

        return resultSet;
    }
}
