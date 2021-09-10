package Servicio;

import Auxiliar.Tupla;
import Auxiliar.TuplaList;
import Modelo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Planificacion_Familiarizacion_Service {
    public void AddPlanFamiliarizacion(PlanFamiliarizacion planFamiliarizacion) throws Exception {
        new Repository<PlanFamiliarizacion>(new PlanFamiliarizacion()).Create(planFamiliarizacion);
    }

    public void putActividadinPlanFamiliarizacion(Actividad actividad, ActividadesPlan ActividadPlan, int anno) throws Exception {
        //(nom_act character varying, desc_act character varying, fecha date, hora time with time zone, responsable integer, plan integer, area integer, idplanact integer,confeccionado integer,annnorealiz integer)
        Connection connection = Repository.getConnetion();
        String consulta = "{?=cud_putactividadinplanfamiliarizacion(?,?,?,?,?,?,?,?,?,?,?)}";
        connection.setAutoCommit(false);
        CallableStatement statement = connection.prepareCall(consulta);
        statement.registerOutParameter(1, Types.OTHER);
        statement.setString(2, actividad.getNombre());
        statement.setString(3, actividad.getDescripcion());
        statement.setDate(4, ActividadPlan.getFecha());
        statement.setTime(5, ActividadPlan.getHora());
        statement.setInt(6, ActividadPlan.getResponsable());
        statement.setInt(7, ActividadPlan.getPlan());
        statement.setInt(8, ActividadPlan.getArea());
        statement.setInt(9, ActividadPlan.getId());
        statement.setInt(10, Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser().getId());
        statement.setInt(11, anno);
        statement.execute();
    }

    public void NuevaActividadPlanFamiliarizacion(Actividad actividad, ActividadesPlan actividadesPlan) throws Exception {
        Repository<Actividad> actividadRepository = new Repository<>(new Actividad());
        Repository<ActividadesPlan> actividadesPlanRepository = new Repository<>(new ActividadesPlan());
        String buscarActid = "select  actividad.id from actividad where nombre=? and descripcion=?;";
        String buscarActPlan = "select actividadesplan.id from actividadesplan where actividadesplan.actividad=? and actividadesplan.plan=?;";
        PreparedStatement preparedStatement = Repository.getConnetion().prepareStatement(buscarActid);
        preparedStatement.setString(1, actividad.getNombre());
        preparedStatement.setString(2, actividad.getDescripcion());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) {
            actividadRepository.Create(actividad);
        }
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            actividad.setId(resultSet.getInt(1));
        System.out.println(actividad.getId());
        actividadesPlan.setAct(actividad.getId());
        preparedStatement = Repository.getConnetion().prepareStatement(buscarActPlan);
        preparedStatement.setInt(2, actividadesPlan.getPlan());
        preparedStatement.setInt(1, actividadesPlan.getAct());
        resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) {
            actividadesPlanRepository.Create(actividadesPlan);
            System.out.println("Creado");
        } else throw new Exception("Esa actividad ya esta en el plan");
    }


    public void UpdateActividadPlan(Actividad actividad, ActividadesPlan actividadesPlan) throws Exception {
        Repository<Actividad> actividadRepository = new Repository<>(new Actividad());
        Repository<ActividadesPlan> actividadesPlanRepository = new Repository<>(new ActividadesPlan());

        String buscarActid = "select  actividad.id from actividad where nombre=? and descripcion=?;";
        String buscarActPlan = "select actividadesplan.id from actividadesplan where actividadesplan.actividad=? and actividadesplan.plan=?;";
        PreparedStatement preparedStatement = Repository.getConnetion().prepareStatement(buscarActid);
        preparedStatement.setString(1, actividad.getNombre());
        preparedStatement.setString(2, actividad.getDescripcion());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next())
            actividadRepository.Create(actividad);
        else
            actividad.setId(resultSet.getInt(1));

        actividadesPlan.setAct(actividad.getId());
        preparedStatement = Repository.getConnetion().prepareStatement(buscarActPlan);
        preparedStatement.setInt(1, actividad.getId());
        preparedStatement.setInt(2, actividadesPlan.getPlan());
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            throw new Exception("Esa actividad ya esta en el plan");
        }
        actividadesPlanRepository.Update(actividadesPlan.getId(), actividadesPlan);
    }

    public void RemoveActividadPlan(ActividadesPlan act) throws Exception {
        new Repository<Actividad>(new Actividad()).Remove(act.getAct());
        new Repository<ActividadesPlan>(new ActividadesPlan()).Remove(act.getId());
    }


//Que borrador mas lindooo, TODO: "LO QUE NO SIRVE SE BOTAAAA"
    public boolean DeletePlanFamiliarizacion() throws Exception {
        boolean is;

        Connection connection= Repository.getConnetion();
        CallableStatement statement=connection.prepareCall("{call delete_planes_innecesarios()}");
        is=statement.execute();
        connection.close();


        return is;
    }
    public boolean DeleteActividadesFromPlanFamiliarizacion() throws Exception {

        boolean is1;

        Connection connection1= Repository.getConnetion();
        CallableStatement statement1=connection1.prepareCall("{call delete_actividades_innecesarias()}");
        is1=statement1.execute();
        connection1.close();

        return is1;
    }


    public PlanFamiliarizacion getPlanFamiliarizacion(int year) throws Exception {
        PlanFamiliarizacion plan = null;
        String consulta = "select * from PlanFamiliarizacion where anno=?";
        PreparedStatement preparedStatement = Repository.getConnetion().prepareStatement(consulta);
        preparedStatement.setInt(1, year);
        plan = new Repository<PlanFamiliarizacion>(new PlanFamiliarizacion()).Adaptador_Object(preparedStatement.executeQuery());
        return plan;
    }

    public PlanFamiliarizacion getPlanFamiliarizacionbyID(int id) throws Exception {
        return new Repository<PlanFamiliarizacion>(new PlanFamiliarizacion()).Get(id);
    }


    //Esta funcion esta en sql no en la bd
    public ArrayList<ActividadesPlan> getActividadesPlanFamiliarizacion(int PlanId) throws Exception {
        ArrayList<ActividadesPlan> actividads;
        String consulta = "select * from actividadesplan where plan=?";
        PreparedStatement preparedStatement = Repository.getConnetion().prepareStatement(consulta);
        preparedStatement.setInt(1, PlanId);
        actividads = new ArrayList<ActividadesPlan>(new Repository<ActividadesPlan>(new ActividadesPlan()).Adaptador_List(preparedStatement.executeQuery()));
        preparedStatement.close();
        return actividads;
    }

    public Actividad getActividad(int idActi) throws Exception {
        return new Repository<Actividad>(new Actividad()).Get(idActi);
    }

    public ResultSet getPlanesaRevisar() throws Exception {
        ResultSet resultSet;
        //String consultaSQL="{?=call getplanesapevisar();}";
        String consultaSQL = "select distinct directorio.nombreapellido,count(actividadesplan.actividad),planfamiliarizacion.anno\n" +
                "                ,case when planfamiliarizacion.aprobado > 0 then 'Aprobado' \n" +
                "                when planfamiliarizacion.aprobado is null and  planfamiliarizacion.revisado is true and planfamiliarizacion.rechazado is false then 'Revisado por mi'\n" +
                "\t\twhen planfamiliarizacion.aprobado is null  and planfamiliarizacion.rechazado is true then 'Rechazado'\n" +
                "                else 'N/A' \n" +
                "                end\n" +
                "                ,planfamiliarizacion.id from directorio join planfamiliarizacion on planfamiliarizacion.confeccionado=directorio.id\n" +
                "                join actividadesplan on actividadesplan.plan=planfamiliarizacion.id\n" +
                "                join actividad on actividad.id=actividadesplan.actividad\n" +
                "                \n" +
                "                group by directorio.nombreapellido,planfamiliarizacion.anno,planfamiliarizacion.aprobado,planfamiliarizacion.rechazado,planfamiliarizacion.revisado,planfamiliarizacion.id;";
        Connection connection = Repository.getConnetion();
        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement(consultaSQL);
        // CallableStatement statement=connection.prepareCall(consultaSQL);
        //statement.registerOutParameter(1,Types.OTHER);
        //statement.execute();
        resultSet = statement.executeQuery();
        return resultSet;
    }

    public void RemovePlanFamiliarizacion(int id) throws Exception {
        new Repository<PlanFamiliarizacion>(new PlanFamiliarizacion()).Remove(id);
    }

    public ArrayList<PlanFamiliarizacion> getAllPlan_Familiarizacion() throws Exception {
        return new ArrayList<>(new Repository<PlanFamiliarizacion>(new PlanFamiliarizacion()).GetAll());
    }

    public void Aprobar_Desaprobar_PlanFamiliarizacion(PlanFamiliarizacion planFamiliarizacion) throws Exception {
        System.out.println(planFamiliarizacion.getConfeccionado());
        new Repository<PlanFamiliarizacion>(new PlanFamiliarizacion()).Update(planFamiliarizacion.getId(), planFamiliarizacion);
    }


    public ResultSet Planes_Familiarizacion_Logged() throws Exception {
        ResultSet resultSet;
        Connection connection = Repository.getConnetion();
        connection.setAutoCommit(false);
        String funcio = "{?=call servicio_planes_familiarizacion_from_directorio()}";
        CallableStatement statement = connection.prepareCall(funcio);
        statement.registerOutParameter(1, Types.OTHER);
        statement.execute();
        resultSet = (ResultSet) statement.getObject(1);
        connection.close();
        return resultSet;
    }

    //nueva tixa
    public ArrayList<TuplaList<Object>> getActividades_Plan_No_Normalizado(int idPlan) throws Exception {
        Actividad actividad = null;
        ActividadesPlan actividadesPlan = null;
        TuplaList<Object> tuplaList = null;
        ArrayList<TuplaList<Object>> list = new ArrayList<>();
        Connection connection = Repository.getConnetion();
        connection.setAutoCommit(false);
        CallableStatement statement = connection.prepareCall("{?=call service_get_actividad_plan_nonormalizado(?)}");
        statement.registerOutParameter(1, Types.OTHER);
        statement.setInt(2, idPlan);
        statement.execute();
        ResultSet resultSet = ((ResultSet) statement.getObject(1));

        while (resultSet.next()) {
            actividad = new Actividad(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
            actividadesPlan = new ActividadesPlan(resultSet.getInt(4), resultSet.getDate(5), resultSet.getInt(6)
                    , resultSet.getInt(7), resultSet.getInt(8), resultSet.getTime(9), resultSet.getInt(10));

            tuplaList = new TuplaList<>(0);
            tuplaList.getListaobjeto().add(actividad);
            tuplaList.getListaobjeto().add(actividadesPlan);
            list.add(tuplaList);
        }

        return list;
    }

    public void NuevoEditarPlanFamiliarizacionActividades(Actividad actividad, ActividadesPlan actividadesPlan) throws Exception {
        Repository<Actividad> actividadRepository = new Repository<Actividad>(new Actividad());
        actividadRepository.Update(actividad.getId(), actividad);
        if (actividadesPlan.getAct() == -1) {
            List<Actividad> a = actividadRepository.GetAll();
            int id=BuscarActividadEnBaseDatos().getId();
            actividadesPlan.setAct(id);


        }

        new Repository<ActividadesPlan>(new ActividadesPlan()).Update(actividadesPlan.getId(), actividadesPlan);
    }
    public Actividad BuscarActividadEnBaseDatos() throws Exception{
        Actividad retorno=null;
        String consulta="select actividad.* from actividad order by actividad.id DESC limit 1";
        PreparedStatement preparedStatement = Repository.getConnetion().prepareStatement(consulta);
        retorno=new Repository<Actividad>(new Actividad()).Adaptador_Object(preparedStatement.executeQuery());
        preparedStatement.close();

        return retorno;
    }


    public void NuevoEditarPlanFamiliarizacion(PlanFamiliarizacion planFamiliarizacion) throws Exception {
        //variante 1
        //Esta linea fue comentada ya q no le sale de la pinga funcionar
        // new Repository<PlanFamiliarizacion>(new PlanFamiliarizacion()).Update(planFamiliarizacion.getId(),planFamiliarizacion);

        //variante 2
        Connection connection = Repository.getConnetion();
        PreparedStatement preparedStatement;
        PlanFamiliarizacion familiarizacion = getPlanFamiliarizacionbyID(planFamiliarizacion.getId());
        if (familiarizacion == null) {
            preparedStatement = connection.prepareStatement("insert into PlanFamiliarizacion(anno,confeccionado) values(?,?);");
            preparedStatement.setInt(1, planFamiliarizacion.getAnno());
            preparedStatement.setInt(2, planFamiliarizacion.getConfeccionado());
        } else {
            preparedStatement = connection.prepareStatement("update PlanFamiliarizacion set anno=?,confeccionado=?,aprobado=?,revisado=?,rechazado=? where PlanFamiliarizacion.id=?;");
            preparedStatement.setInt(1, planFamiliarizacion.getAnno());
            preparedStatement.setInt(2, planFamiliarizacion.getConfeccionado());
            if (planFamiliarizacion.getAprobado() != null)
                preparedStatement.setInt(3, planFamiliarizacion.getAprobado());
            else preparedStatement.setNull(3, Types.INTEGER);
            preparedStatement.setBoolean(4, planFamiliarizacion.isRevisado());
            preparedStatement.setBoolean(5, planFamiliarizacion.isRechazado());
            preparedStatement.setInt(6, planFamiliarizacion.getId());
        }
        preparedStatement.execute();
        connection.close();
    }

    public void RemovePlanFamiliarizacionActividades(Actividad actividad, ActividadesPlan actividadesPlan) throws Exception {
        //variante 1
        //estas lineas de codigo no no funcionnan
        new Repository<ActividadesPlan>(new ActividadesPlan()).Remove(actividadesPlan.getId());
        new Repository<Actividad>(new Actividad()).Remove(actividad.getId());

    }

    public ResultSet get_Actividades_From_Estudiante(int id) throws Exception {
        //VARIANTE 1 NO PINCHA
     /*   ResultSet resultSet;
        Connection connection=Repository.getConnetion();
        connection.setAutoCommit(false);
        String funcio="{?=call service_get_actividades_from_estudiante(?)}";
        CallableStatement statement=connection.prepareCall(funcio);
        statement.registerOutParameter(1,Types.OTHER);
        statement.setInt(2,id);
        statement.execute();
        resultSet=(ResultSet) statement.getObject(1);
        connection.close();
        */
        //VARIANTE 2
        ResultSet resultSet;
        Connection connection = Repository.getConnetion();
        connection.setAutoCommit(false);
        String funcio = "select estudianteplanfaminiliarizacion.* from estudianteplanfaminiliarizacion where estudianteplanfaminiliarizacion.estudiante=?;";
        PreparedStatement statement = connection.prepareStatement(funcio);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            funcio = "select actividad.nombre,directorio.nombreapellido,area.nombre,actividadesplan.fecha,actividadesplan.hora,actividad.descripcion\n" +
                    "\t\t,actividadesestudiante.asistencia,actividadesplan.id,actividadesestudiante.id\n" +
                    "\t\tfrom actividadesestudiante \n" +
                    "\t\tinner join actividadesplan on actividadesestudiante.actividad=actividadesplan.id\n" +
                    "\t\tinner join actividad on actividad.id=actividadesplan.actividad\n" +
                    "\t\tinner join directorio on directorio.id=actividadesplan.responsable\n" +
                    "\t\tinner join area on directorio.area=area.id\n" +
                    "\t\twhere actividadesestudiante.estudiante=?;";
            statement = connection.prepareStatement(funcio);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
        } else {
            funcio = "select actividad.nombre,directorio.nombreapellido,area.nombre,actividadesplan.fecha,actividadesplan.hora,actividad.descripcion,null as asistencia,actividadesplan.id,-1 as idInner\n" +
                    "\t\tfrom  actividad\n" +
                    "\t\tinner join actividadesplan on actividad.id=actividadesplan.actividad\n" +
                    "\t\tinner join planfamiliarizacion on planfamiliarizacion.id=actividadesplan.plan\n" +
                    "\t\tinner join directorio on directorio.id=actividadesplan.responsable\n" +
                    "\t\tinner join area on area.id=directorio.area\t\n" +
                    "\t\tgroup by planfamiliarizacion.anno,actividad.nombre,directorio.nombreapellido,area.nombre,actividadesplan.fecha,actividadesplan.hora,actividad.descripcion,actividadesplan.id\n" +
                    "\t\thaving (select planfamiliarizacion.anno from planfamiliarizacion where " +
                    "extract(year from CURRENT_DATE )=planfamiliarizacion.anno and planfamiliarizacion.aprobado is not null)=planfamiliarizacion.anno;";

            statement = connection.prepareStatement(funcio);
            resultSet = statement.executeQuery();
        }

        connection.close();

        return resultSet;
    }

    public PlanFamiliarizacion get_Plan_Familiarizacion_last() throws Exception {
        ResultSet resultSet;
        String consultaSQL = "select planfamiliarizacion.* from planfamiliarizacion \n" +
                "order by  planfamiliarizacion.anno desc\n" +
                "limit 1";
        Connection connection = Repository.getConnetion();
        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement(consultaSQL);
        resultSet = statement.executeQuery();
        return new Repository<PlanFamiliarizacion>(new PlanFamiliarizacion()).Adaptador_Object(resultSet);
    }


    //Relacion estudiante Plan Familiarizacion

    public EstudiantePlanFamiliarizacion getEstudiantePlanFamiliarizacion_Relacion(int id) throws Exception {
        return new Repository<EstudiantePlanFamiliarizacion>(new EstudiantePlanFamiliarizacion()).Get(id);
    }

    public void Remove_Relacion_EstudiantePlanfamiliarizacion(int id) throws Exception {
        new Repository<EstudiantePlanFamiliarizacion>(new EstudiantePlanFamiliarizacion()).Remove(id);
    }

    public void Create_Update_Relacion_EstudiantePlanfamiliarizacion(EstudiantePlanFamiliarizacion estudiantePlanFamiliarizacion) throws Exception {
        //variante 1 no funciona
        //new Repository<EstudiantePlanFamiliarizacion>(new EstudiantePlanFamiliarizacion()).Update(estudiantePlanFamiliarizacion.getId(),estudiantePlanFamiliarizacion);

        //variante 2
        Connection connection = Repository.getConnetion();
        PreparedStatement statement = null;
        String consultaSQL = "update estudianteplanfaminiliarizacion set plan=?,estudiante=?,nota=? where estudianteplanfaminiliarizacion.id=?;";
        if (estudiantePlanFamiliarizacion.getId() != -1) {
            statement = connection.prepareStatement(consultaSQL);
            if (estudiantePlanFamiliarizacion.getNota() == null)
                statement.setNull(3, Types.INTEGER);
            else
                statement.setInt(3, estudiantePlanFamiliarizacion.getNota());
            statement.setInt(1, estudiantePlanFamiliarizacion.getPlan());
            statement.setInt(2, estudiantePlanFamiliarizacion.getEstudiante());
            statement.setInt(4, estudiantePlanFamiliarizacion.getId());
        } else {
            consultaSQL = "insert into estudianteplanfaminiliarizacion(plan,estudiante,nota) values(?,?,?);";
            statement = connection.prepareStatement(consultaSQL);
            statement.setInt(1, estudiantePlanFamiliarizacion.getPlan());
            statement.setInt(2, estudiantePlanFamiliarizacion.getEstudiante());
            statement.setObject(3, estudiantePlanFamiliarizacion.getNota());
        }
        statement.execute();
    }

    public ActividadesEstudiante getActividadesEstudiante_Relacion(int id) throws Exception {
        return new Repository<ActividadesEstudiante>(new ActividadesEstudiante()).Get(id);
    }

    public void Remove_Relacion_ActividadesEstudiante(int id) throws Exception {
        new Repository<ActividadesEstudiante>(new ActividadesEstudiante()).Remove(id);
    }

    public void Create_Update_Relacion_ActividadesEstudiante(ActividadesEstudiante ActividadesEstudiante) throws Exception {
        //variante 1 no funciona
        // new Repository<ActividadesEstudiante>(new ActividadesEstudiante()).Update(ActividadesEstudiante.getId(),ActividadesEstudiante);

        //variante 2
        Connection connection = Repository.getConnetion();
        PreparedStatement statement = null;
        String consultaSQL = "update ActividadesEstudiante set actividad=?,estudiante=?,asistencia=? where actividadesestudiante.id=?;";
        if (ActividadesEstudiante.getId() != -1) {
            statement = connection.prepareStatement(consultaSQL);
            if (ActividadesEstudiante.isAsistencia() == null)
                statement.setNull(3, Types.BOOLEAN);
            else
                statement.setBoolean(3, ActividadesEstudiante.isAsistencia());
            statement.setInt(1, ActividadesEstudiante.getActividad());
            statement.setInt(2, ActividadesEstudiante.getEstudiante());
            statement.setInt(4, ActividadesEstudiante.getId());
        } else {
            consultaSQL = "insert into ActividadesEstudiante(actividad,estudiante,asistencia) values(?,?,?);";
            statement = connection.prepareStatement(consultaSQL);
            statement.setInt(1, ActividadesEstudiante.getActividad());
            statement.setInt(2, ActividadesEstudiante.getEstudiante());
            if (ActividadesEstudiante.isAsistencia() == null)
                statement.setNull(3, Types.BOOLEAN);
            else
                statement.setBoolean(3, ActividadesEstudiante.isAsistencia());
        }
        statement.execute();

    }


}
