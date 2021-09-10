package Servicio;

import Auxiliar.Conformado_Estado_Data;
import Auxiliar.Plan_Adiestramiento;
import Auxiliar.TuplaList;
import Modelo.Etapa;
import Modelo.PlanAdiestramiento;
import Modelo.Tarea;
import Modelo.TareaParticipante;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Planificacion_Adiestramiento_Service
{
    public void RemovePlanAdiestramientoById(int idplan)throws Exception
    {
        new Repository<PlanAdiestramiento>(new PlanAdiestramiento()).Remove(idplan);
    }
    public ArrayList<PlanAdiestramiento> getAllPlanAdiestramientos()throws Exception
    {
        return new ArrayList<>(new Repository<PlanAdiestramiento>(new PlanAdiestramiento()).GetAll());
    }
    public PlanAdiestramiento getPlanAdiestramientofromEstudiante(int idEst)throws Exception
    {
        PlanAdiestramiento adiestramiento=null;
        Connection connection=Repository.getConnetion();
        PreparedStatement statement=connection.prepareStatement("select * from planadiestramiento where estudiante=?");
        statement.setInt(1,idEst);
        adiestramiento=new Repository<PlanAdiestramiento>(new PlanAdiestramiento()).Adaptador_Object(statement.executeQuery());
        return adiestramiento;
    }
    public PlanAdiestramiento getPlanAdiestramientoID(int id)throws Exception
    {
        return new Repository<PlanAdiestramiento>(new PlanAdiestramiento()).Get(id);
    }
    public ArrayList<Etapa> getEtapas_fromPlan(int idplan) throws Exception
    {
        Connection connection=Repository.getConnetion();
        connection.setAutoCommit(false);
        String consultaSQL="{?=call service_get_etapa_from_plan(?)}";
        CallableStatement statement=connection.prepareCall(consultaSQL);
        statement.registerOutParameter(1,Types.OTHER);
        statement.setInt(2,idplan);
        statement.execute();
        ArrayList<Etapa> etapas=new ArrayList<>(new Repository<Etapa>(new Etapa()).Adaptador_List(((ResultSet) statement.getObject(1))));
        connection.close();
        statement.close();
        return etapas;
    }
    public ArrayList<TuplaList<Object>>  getTareas_and_participantes(int idPlan)throws Exception
    {
        ArrayList<TuplaList<Object>> tuplas=new ArrayList<>();

        Connection connection=Repository.getConnetion();
        connection.setAutoCommit(false);

        String consulta="{?=call service_get_tareas_from_plan(?)}";

        CallableStatement statement=connection.prepareCall(consulta);
        statement.registerOutParameter(1, Types.OTHER);
        statement.setInt(2,idPlan);
        statement.execute();

        Iterator<Tarea> iterator=new Repository<Tarea>(new Tarea()).Adaptador_List(((ResultSet) statement.getObject(1))).iterator();
        connection.close();
        while (iterator.hasNext())
        {

            Tarea tarea=iterator.next();

            List<TareaParticipante> tareaParticipantes=getArrayParticipantesfromTarea_SQL(tarea.getId());
            Iterator<TareaParticipante> iterator1=tareaParticipantes.iterator();
            TuplaList<Object> tuplaList=new TuplaList<>(0);
            tuplaList.getListaobjeto().add(tarea);

            while (iterator1.hasNext())
            {
                tuplaList.getListaobjeto().add(Service_Locator.getInstance().getDirectorio_service().getDirectorio(iterator1.next().getParticipante()));
            }

            tuplas.add(tuplaList);
        }
        return tuplas;
    }
    public PlanAdiestramiento Create_Update_PlanAdiestramiento(PlanAdiestramiento planAdiestramiento)throws Exception
    {
        PlanAdiestramiento adiestramiento=planAdiestramiento;

        //variante 1 funciona mal ya q la bd es una kk
        //new Repository<PlanAdiestramiento>(new PlanAdiestramiento()).Update(planAdiestramiento.getId(),planAdiestramiento);

        //variante 2
        Repository<PlanAdiestramiento> repository=new Repository<PlanAdiestramiento>(new PlanAdiestramiento());
        if (repository.Get(adiestramiento.getId())==null)
            repository.Create(adiestramiento);
        else
            repository.Update(adiestramiento.getId(),adiestramiento);

        if (planAdiestramiento.getId() < 0)
        {
            adiestramiento = getAllPlanAdiestramientos().get(0);
        }
        return adiestramiento;
    }
    public void Create_Update_Etapas(ArrayList<Etapa> etapas)throws Exception
    {
        Iterator<Etapa> etapaIterator=etapas.iterator();
        Repository<Etapa> repository=new Repository<>(new Etapa());

        //variante 1
      /*  while (etapaIterator.hasNext())
        {
            Etapa next =  etapaIterator.next();
                repository.Update(next.getId(),next);
        }
        */
        //variante 2
        while (etapaIterator.hasNext())
        {
            Etapa next =  etapaIterator.next();
            if (repository.Get(next.getId())==null)
                repository.Create(next);
            else
                repository.Update(next.getId(),next);
        }
    }
    public void Create_Update_Actividad_Participantes(Tarea tarea,ArrayList<Integer> idParticipantes)throws Exception
    {
        Delete_Actividad_Participantes(tarea);
        Update_Tarea(tarea);
        Integer IDtarea=tarea.getId();
        if (IDtarea <= 0)
        {
            List<Tarea> t=new Repository<>(new Tarea()).GetAll();
            IDtarea = t.get(0).getId();
            System.out.println(IDtarea);
        }

        Iterator<Integer> iterator = idParticipantes.iterator();
        while (iterator.hasNext())
        {
            new Repository<>(new TareaParticipante()).Create(new TareaParticipante(-1, IDtarea, iterator.next()));
        }
    }
    public void Delete_Actividad_Participantes(Tarea tarea)throws Exception
    {
        String SQL="{call service_delete_participantes_from_tarea(?)}";
        Connection connection=Repository.getConnetion();
        CallableStatement statement = connection.prepareCall(SQL);
        statement.setInt(1,tarea.getId());
        statement.execute();
        connection.close();
    }
    public void Delete_Actividad(Tarea tarea)throws Exception
    {
        Delete_Actividad_Participantes(tarea);
        //variante 1
        //new Repository<Tarea>(new Tarea()).Remove(tarea.getId());
        //variante 2
        Connection connection=Repository.getConnetion();
        PreparedStatement statement=connection.prepareStatement("delete from tarea where tarea.id=?");
        statement.setInt(1,tarea.getId());
        statement.execute();
        connection.close();
    }
    public ArrayList<Etapa> get_Etapas_from_Estudiante(int IDestudiante)throws Exception
    {
        String SQL="{?=call service_get_etapas_from_graduado(?)}";
        Connection connection=Repository.getConnetion();
        connection.setAutoCommit(false);
        CallableStatement statement = connection.prepareCall(SQL);
        statement.registerOutParameter(1,Types.OTHER);
        statement.setInt(2,IDestudiante);
        statement.execute();
        ArrayList<Etapa> etapas= new ArrayList<>(new Repository<Etapa>(new Etapa()).Adaptador_List(((ResultSet) statement.getObject(1))));
        statement.close();
        return etapas;
    }
    public ArrayList<Tarea> get_Tareas_from_Etapas(int etapa)throws Exception
    {
        /*ArrayList<Tarea> tareas=new ArrayList<>();
        String SQL="{?=call service_get_tareas_from_etapa(?)}";
        Connection connection=Repository.getConnetion();
        connection.setAutoCommit(false);
        CallableStatement statement = connection.prepareCall(SQL);
        statement.registerOutParameter(1,Types.OTHER);
        statement.setInt(2,etapa);
        statement.execute();
        tareas.addAll(new Repository<Tarea>(new Tarea()).Adaptador_List(((ResultSet) statement.getObject(1))));
        statement.close();
        return tareas;*/

        System.out.println("Soy la Etapa: "+etapa);
        ArrayList<Tarea> tareas=new ArrayList<>();
        String SQL="select distinct tarea.* from tarea inner join etapa on etapa.id=tarea.etapa where etapa.id=?";
        Connection connection=Repository.getConnetion();
        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement(SQL);
        statement.setInt(1,etapa);
        statement.execute();
        tareas.addAll(new Repository<Tarea>(new Tarea()).Adaptador_List(statement.executeQuery()));
        statement.close();
        return tareas;
    }
    public String getParticipantesfromTarea_SQL(int idtarea) throws Exception
    {
        String part="Sin Participantes";
        Connection connection=Repository.getConnetion();
        PreparedStatement statement=connection.prepareStatement("select * from tareaparticipante where tareaparticipante.tarea=?");
        statement.setInt(1,idtarea);
        ArrayList<TareaParticipante> arrayList=new ArrayList<>(new Repository<TareaParticipante>(new TareaParticipante()).Adaptador_List(statement.executeQuery()));
        connection.close();
        Iterator<TareaParticipante> iterator=arrayList.iterator();
        if (!arrayList.isEmpty())
            part="";
        while (iterator.hasNext())
        {
            part+=Service_Locator.getInstance().getDirectorio_service().getDirectorio(iterator.next().getParticipante()).getNombreApellido();
            if (iterator.hasNext())
                part+=",";
        }
        part+=".";
        return part;
    }
    public ArrayList<TareaParticipante> getArrayParticipantesfromTarea_SQL(int idtarea) throws Exception
    {
        Connection connection=Repository.getConnetion();
        PreparedStatement statement=connection.prepareStatement("select * from tareaparticipante where tareaparticipante.tarea=?");
        statement.setInt(1,idtarea);
        ArrayList<TareaParticipante> arrayList=new ArrayList<>(new Repository<TareaParticipante>(new TareaParticipante()).Adaptador_List(statement.executeQuery()));
        connection.close();
        return arrayList;
    }
    /*public void Update_Tarea_SQL(Tarea a)throws Exception
    {
        Connection connection=Repository.getConnetion();
        PreparedStatement consultaRE=connection.prepareStatement("update tarea set nombre=?,etapa=?,cumplimiento=?,fechacumplimiento=?,observacion=? where id=?;");
        if (a.getFechaCumplimiento()!=null)
            consultaRE.setDate(4,a.getFechaCumplimiento());
        else
            consultaRE.setNull(4,Types.DATE);
        consultaRE.setInt(6,a.getId());
        consultaRE.setString(1,a.getNombre());
        consultaRE.setInt(2,a.getEtapa());
        consultaRE.setBoolean(3,a.isCumplimiento());
        consultaRE.setString(5,a.getObservacion());
        consultaRE.executeUpdate();
        connection.close();
    }*/
    public void Update_Tarea(Tarea a)throws Exception
    {
        //variante 1
        new Repository<Tarea>(new Tarea()).Update(a.getId(),a);

        //varariante 2
        /* Repository<Tarea>repository= new Repository<Tarea>(new Tarea());
         if (repository.Get(a.getId())==null)
             repository.Create(a);
             else
                 repository.Update(a.getId(),a);*/

        //variante 3
        String verificar="select * from tarea where id=?";
        String update="update tarea set nombre=?,etapa=?,cumplimiento=?,fechacumplimiento=?,observacion=?,otra=? where tarea.id=?;";
        String insert="insert into Tarea(nombre,etapa,cumplimiento,fechacumplimiento,observacion,otra) values(?,?,false,null,'',?);";
        Connection connection=Repository.getConnetion();
        PreparedStatement statement=connection.prepareStatement(verificar);
        statement.setInt(1,a.getId());
        if (statement.executeQuery().next())
        {
            statement=connection.prepareStatement(update);
            statement.setString(1,a.getNombre());
            statement.setInt(2,a.getEtapa());
            statement.setBoolean(3,a.isCumplimiento());
            if (a.getFechaCumplimiento()!=null)
                statement.setDate(4,a.getFechaCumplimiento());
            else
                statement.setNull(4,Types.DATE);
            statement.setString(5,a.getObservacion());
            statement.setBoolean(6,a.isOtra());
            statement.setInt(7,a.getId());
          }
        else
        {
            statement=connection.prepareStatement(insert);
            statement.setString(1,a.getNombre());
            statement.setInt(2,a.getEtapa());
            statement.setBoolean(3,a.isOtra());
          }
        connection.close();






    }
    public void Create_Other_Tarea(Tarea a,ArrayList<Integer> idParticipantes)throws Exception
    {
        new Repository<Tarea>(new Tarea()).Create(a);
        List<Tarea> tareas=new Repository<Tarea>(new Tarea()).GetAll();
        int IDtarea =tareas.get(tareas.size()-1).getId();
        Iterator<Integer> iterator = idParticipantes.iterator();
        while (iterator.hasNext())
        {
            new Repository<>(new TareaParticipante()).Create(new TareaParticipante(-1, IDtarea, iterator.next()));
        }
    }
    public double PromedioNotasPlanAdiestramiento(int id)throws Exception
    {
        double nota=0;
        //variante 1
        /* String cons="{?= call service_promedio_nota_etapas_planadiestramiento(?)};";
        Connection connection=Repository.getConnetion();
        connection.setAutoCommit(false);
        CallableStatement statement=connection.prepareCall(cons);
        statement.registerOutParameter(1, Types.DOUBLE);
        statement.setInt(2,id);
        statement.execute();
        connection.close();
        ResultSet resultSet= ((ResultSet) statement.getObject(1));
        resultSet.next();
        nota=resultSet.getDouble(1);*/

        //variante 2

        String cons="select avg(etapa.nota) from etapa where etapa.plan=?;";
        Connection connection=Repository.getConnetion();
        PreparedStatement statement=connection.prepareStatement(cons,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        statement.setInt(1,id);
        ResultSet resultSet=statement.executeQuery();
        connection.close();
        resultSet.next();
        nota=resultSet.getDouble(1);
        return nota;
    }
    public ArrayList<PlanAdiestramiento> getPlanesFromTutor(int idTutor) throws Exception
    {
        ArrayList<PlanAdiestramiento> adiestramiento=new ArrayList<>();
        Connection connection=Repository.getConnetion();
        PreparedStatement statement=connection.prepareStatement("select * from planadiestramiento where tutor=?");
        statement.setInt(1,idTutor);
        adiestramiento.addAll(new Repository<PlanAdiestramiento>(new PlanAdiestramiento()).Adaptador_List(statement.executeQuery()));
        return adiestramiento;
    }



}
