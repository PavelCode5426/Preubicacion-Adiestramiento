package Servicio;

import Modelo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository<T> implements IRepository<T>
{
    private T tipe;

    public Repository(T table) { tipe=table; }





    public static Connection getConnetion() throws Exception
    {
        AppConfig appConfig=AppConfig.getIntanceAppDataBase();
        return appConfig.getConnetionDataBaseApp();
    }
    public static Connection getConnetionSECUJAE() throws Exception
    {
        AppConfig appConfig=AppConfig.getIntanceAppExtDataBase();
        return appConfig.getConnetionDataBaseApp();
    }
    public List<T> Adaptador_List(ResultSet consultaRE) throws Exception
    {
        List<T> lis=new ArrayList<>();
        if(tipe instanceof Estudiante) {
            while (consultaRE.next()) {
                Integer a=consultaRE.getInt("area");
                if (a==0)
                    a=null;
                lis.add ((T) new Estudiante(consultaRE.getInt("id"),
                        consultaRE.getBoolean("cujaenno"),
                        consultaRE.getBoolean("tesisaplicable"),
                        consultaRE.getInt("procedenciaestudiante"),
                        consultaRE.getString("tematesis"),
                        consultaRE.getString("nombreapellidos"),
                        consultaRE.getDate("cumpleannos"),
                        consultaRE.getInt("annoingreso"),
                        consultaRE.getString("nidentidad"),
                        consultaRE.getInt("especialidad"),
                        a
                ));
            }
        }
        else if (tipe instanceof Actividad) {
            while (consultaRE.next())
                lis.add((T) new Actividad(consultaRE.getInt("id"),
                        consultaRE.getString("nombre"),
                        consultaRE.getString("descripcion")));
        } else if (tipe instanceof ActividadesEstudiante) {
            while (consultaRE.next()) {
                Object o= consultaRE.getObject("asistencia");
                Boolean b=consultaRE.getBoolean("asistencia");
                int n=1;
                if (o!=null)
                    n=-1;
                if (b)
                    n=0;

                lis.add((T) new ActividadesEstudiante(consultaRE.getInt("id"),
                        consultaRE.getInt("estudiante"),
                        consultaRE.getInt("actividad"),n
                ));
            }
        } else if (tipe instanceof ActividadesPlan) {
            while (consultaRE.next())
                lis.add((T) new ActividadesPlan(consultaRE.getInt("id"),
                        consultaRE.getDate("fecha"),
                        consultaRE.getInt("actividad"),
                        consultaRE.getInt("plan"),
                        consultaRE.getInt("responsable"),
                        consultaRE.getTime("hora"),
                        consultaRE.getInt("area")));
        } else if (tipe instanceof Area) {
            while (consultaRE.next())
                lis.add((T) new Area(consultaRE.getInt("id"), consultaRE.getString("nombre")));

        } else if (tipe instanceof Directorio) {
            while (consultaRE.next())
                lis.add((T) new Directorio(consultaRE.getInt("id"),
                        consultaRE.getString("nidentidad"),
                        consultaRE.getString("cargo"),
                        consultaRE.getString("nombreapellido"),
                        consultaRE.getInt("area"),
                        consultaRE.getString("nusuario"),
                        consultaRE.getString("contrasenna"),
                        consultaRE.getBoolean("activo"))
                );
        } else if (tipe instanceof Especialidad) {
            while (consultaRE.next())
                lis.add((T) new Especialidad(consultaRE.getInt(1),
                        consultaRE.getString(2)));
        } else if (tipe instanceof EstudiantePlanFamiliarizacion) {
            while (consultaRE.next())
                lis.add((T) new EstudiantePlanFamiliarizacion(consultaRE.getInt("id"),
                        consultaRE.getInt("estudiante"),
                        consultaRE.getInt("plan"),consultaRE.getInt("nota")));
        } else if (tipe instanceof Etapa) {
            while (consultaRE.next())
                if (consultaRE.getInt("nota")!=0)
                    lis.add((T) new Etapa(consultaRE.getInt("id"), consultaRE.getInt("plan"),
                            consultaRE.getInt("nombre"), consultaRE.getDate("fechainicio"),
                            consultaRE.getDate("fechafinal"), consultaRE.getString("objetivo"),
                            consultaRE.getInt("nota")));
                else
                    lis.add((T) new Etapa(consultaRE.getInt("id"), consultaRE.getInt("plan"),
                            consultaRE.getInt("nombre"), consultaRE.getDate("fechainicio"),
                            consultaRE.getDate("fechafinal"), consultaRE.getString("objetivo"),
                            null));
        } else if (tipe instanceof LugarProcedencia) {
            while (consultaRE.next())
                lis.add((T) new LugarProcedencia(consultaRE.getInt("id"), consultaRE.getString("nombre")));
        } else if (tipe instanceof Notificacion) {
            while (consultaRE.next())
                lis.add((T) new Notificacion(consultaRE.getInt("id"),consultaRE.getString("mensaje"),
                        consultaRE.getInt("subscriptor")));
        } else if (tipe instanceof Permiso) {
            while (consultaRE.next())
                lis.add((T) new Permiso(consultaRE.getInt("id"), consultaRE.getString("nombre")));
        } else if (tipe instanceof PlanAdiestramiento) {
            Integer a;
            while (consultaRE.next()) {
                a=consultaRE.getInt("aprobado");
                if (a==0)
                    a=null;
                lis.add((T) new PlanAdiestramiento(consultaRE.getInt("id"), a,
                        consultaRE.getInt("annoinicio"), consultaRE.getInt("tutor"), consultaRE.getInt("estudiante"), consultaRE.getBoolean("revisado")));
            }
        }else if (tipe instanceof PlanFamiliarizacion) {
            while (consultaRE.next()) {
                Integer b = consultaRE.getInt("aprobado");
                if (b == 0)
                    b = null;
                lis.add((T) new PlanFamiliarizacion(consultaRE.getInt("id"), consultaRE.getInt("anno"), consultaRE.getInt("confeccionado"), b, consultaRE.getBoolean("revisado"),consultaRE.getBoolean("rechazado")));
            }
        } else if (tipe instanceof Rol) {
            while (consultaRE.next())
                lis.add((T) new Rol(consultaRE.getInt("id"), consultaRE.getString("nombre"), consultaRE.getString("descripcion")));
        } else if (tipe instanceof RolPermiso) {
            while (consultaRE.next())
                lis.add((T) new RolPermiso(consultaRE.getInt("id"), consultaRE.getInt("permiso"), consultaRE.getInt("rol")));
        } else if (tipe instanceof Tarea) {
            while (consultaRE.next())
                lis.add((T) new Tarea(consultaRE.getInt("id"), consultaRE.getString("nombre"), consultaRE.getInt("etapa"),
                        consultaRE.getBoolean("cumplimiento"), consultaRE.getDate("fechacumplimiento"), consultaRE.getString("observacion"),consultaRE.getBoolean("otra")));
        } else if (tipe instanceof UsuarioRol) {
            while (consultaRE.next())
                lis.add((T) new UsuarioRol(consultaRE.getInt("id"), consultaRE.getInt("usuario"),
                        consultaRE.getInt("rol"), consultaRE.getDate("fechaasignacion")));
        }
        else if (tipe instanceof TareaParticipante)
        {   while (consultaRE.next())
            lis.add ((T) new TareaParticipante(consultaRE.getInt("id"),consultaRE.getInt("tarea"),consultaRE.getInt("participante")));
        }
        else if (tipe instanceof AreaPreasignada)
        {   while (consultaRE.next())
            lis.add ((T) new AreaPreasignada(consultaRE.getInt("id"),consultaRE.getInt("estudiante"),consultaRE.getInt("area")));
        }

        return lis;
    }
    public T Adaptador_Object(ResultSet consultaRE) throws Exception
    {
        T object=null;
        if(consultaRE.next()) {


            if (tipe instanceof Estudiante) {
                Integer a=consultaRE.getInt("area");
                if (a==0)
                    a=null;
                object = (T) new Estudiante(consultaRE.getInt("id"),
                        consultaRE.getBoolean("cujaenno"),
                        consultaRE.getBoolean("tesisaplicable"),
                        consultaRE.getInt("procedenciaestudiante"),
                        consultaRE.getString("tematesis"),
                        consultaRE.getString("nombreapellidos"),
                        consultaRE.getDate("cumpleannos"),
                        consultaRE.getInt("annoingreso"),
                        consultaRE.getString("nidentidad"),
                        consultaRE.getInt("especialidad"),
                        a
                );
            } else if (tipe instanceof Actividad) {
                object = (T) new Actividad(consultaRE.getInt("id"),
                        consultaRE.getString("nombre"),
                        consultaRE.getString("descripcion"));
            } else if (tipe instanceof ActividadesEstudiante) {
                Object o= consultaRE.getObject("asistencia");
                Boolean b=consultaRE.getBoolean("asistencia");
                int n=1;
                if (o!=null)
                    n=-1;
                else if (b)
                    n=0;
                object = (T) new ActividadesEstudiante(consultaRE.getInt("id"),
                        consultaRE.getInt("estudiante"),
                        consultaRE.getInt("actividad"),
                        n);
            } else if (tipe instanceof ActividadesPlan) {
                object = (T) new ActividadesPlan(consultaRE.getInt("id"),
                        consultaRE.getDate("fecha"),
                        consultaRE.getInt("actividad"),
                        consultaRE.getInt("plan"),
                        consultaRE.getInt("responsable"),
                        consultaRE.getTime("hora"),
                        consultaRE.getInt("area"));
            } else if (tipe instanceof Area) {
                object = (T) new Area(consultaRE.getInt("id"), consultaRE.getString("nombre"));

            } else if (tipe instanceof Directorio) {
                object = (T) new Directorio(consultaRE.getInt("id"),
                        consultaRE.getString("nidentidad"),
                        consultaRE.getString("cargo"),
                        consultaRE.getString("nombreapellido"),
                        consultaRE.getInt("area"),
                        consultaRE.getString("nusuario"),
                        consultaRE.getString("contrasenna"),
                        consultaRE.getBoolean("activo")
                );
            } else if (tipe instanceof Especialidad) {
                object = (T) new Especialidad(consultaRE.getInt("id"),
                        consultaRE.getString("nombre"));
            } else if (tipe instanceof EstudiantePlanFamiliarizacion) {
                object = (T) new EstudiantePlanFamiliarizacion(consultaRE.getInt("id"),
                        consultaRE.getInt("estudiante"),
                        consultaRE.getInt("plan"),consultaRE.getInt("nota"));
            } else if (tipe instanceof Etapa) {
                if (consultaRE.getInt("nota")!=0)
                    object = (T) new Etapa(consultaRE.getInt("id"), consultaRE.getInt("plan"),
                            consultaRE.getInt("nombre"), consultaRE.getDate("fechainicio"),
                            consultaRE.getDate("fechafinal"), consultaRE.getString("objetivo"),
                            consultaRE.getInt("nota"));
                else
                    object = (T) new Etapa(consultaRE.getInt("id"), consultaRE.getInt("plan"),
                            consultaRE.getInt("nombre"), consultaRE.getDate("fechainicio"),
                            consultaRE.getDate("fechafinal"), consultaRE.getString("objetivo"),
                            null);
            }
            else if (tipe instanceof LugarProcedencia) {
                object = ((T) new LugarProcedencia(consultaRE.getInt("id"), consultaRE.getString("nombre")));
            }
            else if (tipe instanceof Notificacion) {
                object = (T) new Notificacion(consultaRE.getInt("id"), consultaRE.getString("mensaje"),
                        consultaRE.getInt("subscriptor"));
            } else if (tipe instanceof Permiso) {
                object = (T) new Permiso(consultaRE.getInt("id"), consultaRE.getString("nombre"));
            } else if (tipe instanceof PlanAdiestramiento) {
                Integer a=consultaRE.getInt("aprobado");
                if (a==0)
                    a=null;
                object = (T) new PlanAdiestramiento(consultaRE.getInt("id"),a,
                        consultaRE.getInt("annoinicio"), consultaRE.getInt("tutor"), consultaRE.getInt("estudiante"),consultaRE.getBoolean("revisado"));
            } else if (tipe instanceof PlanFamiliarizacion) {
               /* Object a = consultaRE.getInt("aprobado");
                Integer b = null;
                if (a != null)
                    b = new Integer((int) a);
                System.out.println(b + "asdasda");*/
                object = (T) new PlanFamiliarizacion(consultaRE.getInt("id"),
                        consultaRE.getInt("anno"), consultaRE.getInt("confeccionado"), consultaRE.getInt("aprobado"),consultaRE.getBoolean("revisado"),consultaRE.getBoolean("rechazado"));
            }
            else if (tipe instanceof Tarea) {
                while (consultaRE.next())
                    object=((T) new Tarea(consultaRE.getInt("id"), consultaRE.getString("nombre"), consultaRE.getInt("etapa"),
                            consultaRE.getBoolean("cumplimiento"), consultaRE.getDate("fechacumplimiento"), consultaRE.getString("observacion"),consultaRE.getBoolean("otra")));
            }
            else if (tipe instanceof Rol) {
                object = (T) new Rol(consultaRE.getInt("id"), consultaRE.getString("nombre"), consultaRE.getString("descripcion"));
            } else if (tipe instanceof RolPermiso) {
                object = (T) new RolPermiso(consultaRE.getInt("id"), consultaRE.getInt("permiso"), consultaRE.getInt("rol"));
            } else if (tipe instanceof Tarea) {
                object = (T) new UsuarioRol(consultaRE.getInt("id"), consultaRE.getInt("usuario"),
                        consultaRE.getInt("role"), consultaRE.getDate("fechaasignacion"));
            } else if (tipe instanceof UsuarioRol) {
                object = (T) new UsuarioRol(consultaRE.getInt("id"), consultaRE.getInt("usuario"),
                        consultaRE.getInt("role"), consultaRE.getDate("fechaasignacion"));
            }
            else if (tipe instanceof TareaParticipante)
            {
                object = (T) new TareaParticipante(consultaRE.getInt("id"),consultaRE.getInt("tarea"),consultaRE.getInt("participante"));
            }
            else if (tipe instanceof AreaPreasignada)
                object=(T) new AreaPreasignada(consultaRE.getInt("id"),consultaRE.getInt("estudiante"),consultaRE.getInt("area"));
        }
        return object;
    }
    public List<T> GetAll() throws Exception
    {
        List<T> lista=new ArrayList<>();
        T retorno;
        String entidad=tipe.getClass().getSimpleName().toLowerCase();
        Connection connection=getConnetion();
        connection.setAutoCommit(false);
        CallableStatement statement=connection.prepareCall("{? = call getAll_from(?)}");
        statement.registerOutParameter(1,Types.OTHER);
        statement.setString(2,entidad);
        statement.execute();
        lista=Adaptador_List((ResultSet) statement.getObject(1));
        connection.close();
         return lista;
    }
    public T Get(int PrimaryKey) throws Exception
    {
        T retorno=null;
        String entidad=tipe.getClass().getSimpleName().toLowerCase();
        Connection connection=getConnetion();
        connection.setAutoCommit(false);
        CallableStatement statement=connection.prepareCall("{? = call get_from(?,?)}");
        statement.registerOutParameter(1,Types.OTHER);
        statement.setString(2,entidad);
        statement.setInt(3,PrimaryKey);
        statement.execute();
        retorno=Adaptador_Object((ResultSet) statement.getObject(1));
        connection.close();
         return retorno;
    }
    public boolean Create(T objeto) throws Exception
    {
        boolean is=false;
        String consultaSQL="";
        if(tipe instanceof Estudiante)
            consultaSQL = "{call create_estudiante(?,?,?,?,?,?,?,?,?)}";
        else if(tipe instanceof Actividad)
            consultaSQL = "{call create_actividad(?,?)}";
        else if(tipe instanceof ActividadesEstudiante)
            consultaSQL="{call create_actividadesplan(?, ?, ?, ?, ?,?)}";
        else if(tipe instanceof ActividadesPlan)
            consultaSQL="{call create_actividadestudiante(?,?)}";
        else if(tipe instanceof Area)
            consultaSQL="{call create_area(?)}";
        else if(tipe instanceof Directorio)
            consultaSQL="{call create_directorio(?,?,?,?, ?,?)}";
        else if (tipe instanceof Especialidad)
            consultaSQL="{call  create_especialidad(?)}";
        else if (tipe instanceof EstudiantePlanFamiliarizacion)
            consultaSQL="{call create_estudianteplanfaminiliarizacion(?, ?)}";
        else if (tipe instanceof Etapa)
            consultaSQL="{call create_etapa(?, ?, ?, ?, ?)}";
        else if(tipe instanceof LugarProcedencia)
            consultaSQL="{call create_lugarprocedencia(?)}";
        else if (tipe instanceof Notificacion)
            consultaSQL="{call create_notificacion(?,?)}";
        else if (tipe instanceof Permiso)
            consultaSQL="{call create_permiso(?)}";
        else if (tipe instanceof PlanAdiestramiento)
            consultaSQL="{call create_planadiestramientro(?,?,?)}";
        else if (tipe instanceof PlanFamiliarizacion)
            consultaSQL="{call create_planfamiliarizacion(?,?,?)}";
        else if (tipe instanceof Rol)
            consultaSQL="{call create_rol(?,?)}";
        else if (tipe instanceof RolPermiso)
            consultaSQL="{call create_rolpermiso(?,?)}";
        else if (tipe instanceof Tarea)
            consultaSQL="{call create_tarea(?,?,?)}";
        else if (tipe instanceof UsuarioRol)
            consultaSQL="{call create_usuariorol(?, ?, ?)}";
        else if (tipe instanceof TareaParticipante)
            consultaSQL="{call create_update_tareaparticipante(?, ?, ?)}";
        else if (tipe instanceof AreaPreasignada)
            consultaSQL="{call create_update_areapreasignada(?, ?, ?)}";
        Connection connection=getConnetion();
        CallableStatement consultaRE=connection.prepareCall(consultaSQL);

        if(tipe instanceof Estudiante)
        {
            Estudiante a=(Estudiante) objeto;

            consultaRE.setBoolean(1,a.isCujaenno());
            consultaRE.setBoolean(2,a.isTesisAplicable());
            consultaRE.setString(4,a.getTemaTesis());
            consultaRE.setString(5,a.getNombreApellidos());
            consultaRE.setDate(6,a.getCumpleannos());
            consultaRE.setInt(7,a.getAnnoIngreso());
            consultaRE.setString(8,a.getNidentidad());
            consultaRE.setInt(9,a.getEspecialidad());
            if (a.getProcedenciaEstudiante()==null)
                consultaRE.setNull(3,Types.INTEGER);
            else
                consultaRE.setInt(3,a.getProcedenciaEstudiante());

        }
        else if(tipe instanceof Actividad)
        {
            Actividad a=(Actividad)objeto;
            consultaRE.setString(1,a.getNombre());
            consultaRE.setString(2,a.getDescripcion());
        }
        else if(tipe instanceof ActividadesEstudiante)
        {
            ActividadesEstudiante a= (ActividadesEstudiante)objeto;
            //consultaRE.setInt(1,a.getId());
            consultaRE.setInt(1,a.getActividad());
            consultaRE.setInt(2,a.getEstudiante());
            //consultaRE.setBoolean(3,a.isAsistencia());
        }
        else if(tipe instanceof ActividadesPlan)
        {
            ActividadesPlan a= (ActividadesPlan) objeto;
            consultaRE.setDate(1,a.getFecha());
            consultaRE.setInt(2,a.getAct());
            consultaRE.setInt(3,a.getPlan());
            consultaRE.setInt(4,a.getResponsable());
            consultaRE.setTime(5,a.getHora());
            consultaRE.setInt(6,a.getArea());
        }
        else if(tipe instanceof Area)
        {
            Area a=(Area)objeto;
            consultaRE.setString(1,a.getNombre());
        }
        else if(tipe instanceof Directorio)
        {
            Directorio a=(Directorio) objeto;
            //consultaRE.setInt(1,a.getId());
            consultaRE.setString(1,a.getCarnetIdentidad());
            consultaRE.setString(2,a.getCargo());
            consultaRE.setString(3,a.getNombreApellido());
            consultaRE.setInt(4,a.getArea());
            consultaRE.setString(5,a.getNusuario());
            consultaRE.setString(6,a.getContrasenna());
            consultaRE.setBoolean(7,a.isActivo());
        }
        else if (tipe instanceof Especialidad)
        {
            Especialidad a=(Especialidad) objeto;
            //consultaRE.setInt(1,a.getId());
            consultaRE.setString(1,a.getNombre());
        }
        else if (tipe instanceof EstudiantePlanFamiliarizacion)
        {
            EstudiantePlanFamiliarizacion a=(EstudiantePlanFamiliarizacion)objeto;
            // consultaRE.setInt(1,a.getId());
            consultaRE.setInt(2,a.getPlan());
            consultaRE.setInt(1,a.getEstudiante());
        }
        else if (tipe instanceof Etapa)
        {
            Etapa a=(Etapa) objeto;
            //consultaRE.setInt(1,a.getId());
            consultaRE.setInt(1,a.getPlan());
            consultaRE.setInt(2,a.getNombre());
            consultaRE.setDate(3,a.getFechaInicio());
            consultaRE.setDate(4,a.getFechaFinal());
            consultaRE.setString(5,a.getObjetivo());
        }
        else if(tipe instanceof LugarProcedencia)
        {
            LugarProcedencia a=(LugarProcedencia)objeto;
            consultaRE.setString(1,a.getLugar());
        }
        else if (tipe instanceof Notificacion)
        {
            Notificacion a=(Notificacion)objeto;
            //consultaRE.setInt(1,a.getId());
            consultaRE.setInt(1,a.getSubscriptor());
            consultaRE.setString(2,a.getMensaje());
        }
        else if (tipe instanceof Permiso)
        {
            Permiso a=(Permiso)objeto;
            //consultaRE.setInt(,a.getId());
            consultaRE.setString(1,a.getNombre());
        }
        else if (tipe instanceof PlanAdiestramiento)
        {
            PlanAdiestramiento a=(PlanAdiestramiento)objeto;
            //consultaRE.setInt(1,a.getId());
            consultaRE.setInt(1,a.getAnnoInicio());
            consultaRE.setInt(2,a.getTutor());
            consultaRE.setInt(3,a.getEstudiante());

        }
        else if (tipe instanceof PlanFamiliarizacion)
        {
            PlanFamiliarizacion a=(PlanFamiliarizacion) objeto;
            // consultaRE.setInt(1,a.getId());
            consultaRE.setInt(1,a.getAnno());
            if (a.getAprobado()!=null)
                consultaRE.setInt(3,a.getAprobado());
            else
                consultaRE.setNull(3,0);
            consultaRE.setInt(2,a.getConfeccionado());
        }
        else if (tipe instanceof Rol)
        {
            Rol a=(Rol)objeto;
            //consultaRE.setInt(1,a.getId());
            consultaRE.setString(1,a.getNombre());
            consultaRE.setString(2,a.getDescripcion());

        }
        else if (tipe instanceof RolPermiso)
        {
            RolPermiso a=(RolPermiso) objeto;
            //consultaRE.setInt(1,a.getId());
            consultaRE.setInt(2,a.getPermiso());
            consultaRE.setInt(1,a.getRol());
        }
        else if (tipe instanceof Tarea)
        {
            Tarea a=(Tarea)objeto;
            consultaRE.setString(1,a.getNombre());
            consultaRE.setInt(2,a.getEtapa());
            consultaRE.setBoolean(3,a.isOtra());
        }
        else if (tipe instanceof UsuarioRol) {
            UsuarioRol a = (UsuarioRol) objeto;
            //   consultaRE.setInt(1,a.getId());
            consultaRE.setInt(1, a.getUsuario());
            consultaRE.setInt(2, a.getRole());
            consultaRE.setDate(3, a.getFechaAsignacion());
        }
        else if (tipe instanceof TareaParticipante)
        {
            TareaParticipante a= ((TareaParticipante) objeto);
            consultaRE.setInt(3,-1);
            consultaRE.setInt(1,a.getTarea());
            consultaRE.setInt(2,a.getParticipante());
        }
        else if (tipe instanceof AreaPreasignada)
        {
            AreaPreasignada a= ((AreaPreasignada) objeto);
            consultaRE.setInt(3,-1);
            consultaRE.setInt(1,a.getEstudiante());
            consultaRE.setInt(2,a.getArea());
        }
        is=consultaRE.execute();
        consultaRE.close();
        connection.close();
        return is;
    }
    public boolean Remove(int PrimaryKey) throws Exception
    {
        boolean is;
        String entidad=tipe.getClass().getSimpleName().toLowerCase();
        Connection connection=getConnetion();
        CallableStatement statement=connection.prepareCall("{call delete_from(?,?)}");
        statement.setString(1,entidad);
        statement.setInt(2,PrimaryKey);
        is=statement.execute();
        connection.close();
        return is;
    }
    public boolean Update(int PrimaryKey,T objeto) throws Exception
    {
        boolean is=false;
        String consultaSQL="";
        if(tipe instanceof Estudiante)
            consultaSQL = "{call create_update_estudiante(?,?,?,?,?,?,?,?,?,?,?)}";
        else if(tipe instanceof Actividad)
            consultaSQL="{call create_update_actividad(?,?,?)}";
        else if(tipe instanceof ActividadesEstudiante)
            consultaSQL="{call create_update_actividadestudiante(?,?,?,?)}";
        else if(tipe instanceof ActividadesPlan)
            consultaSQL="{call create_update_actividadesplan(?, ?, ?, ?, ?, ?, ?)}";
        else if(tipe instanceof Area)
            consultaSQL="{call create_update_area(?,?)}";
        else if(tipe instanceof Directorio)
            consultaSQL="{call create_update_directorio(?,?,?,?,?,?,?,?)}";
        else if (tipe instanceof Especialidad)
            consultaSQL="{call create_update_especialidad(?, ?)}";
        else if (tipe instanceof EstudiantePlanFamiliarizacion)
            consultaSQL="{call create_update_estudianteplanfaminiliarizacion(?,?,?,?)}";
        else if (tipe instanceof Etapa) {
            consultaSQL = "{call create_update_etapa(?, ?, ?, ?, ?, ?, ?)}";
        }
        else if (tipe instanceof LugarProcedencia)
            consultaSQL="{call create_update_lugarprocedencia(?,?)}";
        else if (tipe instanceof Notificacion)
            consultaSQL="{call create_update_notificacion(?, ?, ?)}";
        else if (tipe instanceof Permiso)
            consultaSQL="{call create_update_permiso(?, ?)}";
        else if (tipe instanceof PlanAdiestramiento)
            consultaSQL="{call create_update_planadiestramientro(?, ?, ?, ?, ?,?)}";
        else if (tipe instanceof PlanFamiliarizacion)
            consultaSQL="{call create_update_planfamiliarizacion(?, ?, ?, ?,?,?)}";
        else if (tipe instanceof Rol)
            consultaSQL="{call create_update_rol(?, ?, ?)}";
        else if (tipe instanceof RolPermiso)
            consultaSQL="{call create_update_rolpermiso(?, ?, ?)}";
        else if (tipe instanceof Tarea)
            consultaSQL="{call create_update_tarea(?,?,?,?, ?,?,?)}";
        else if (tipe instanceof UsuarioRol)
            consultaSQL="{call create_update_usuariorol(?, ?, ?, ?)}";
        else if (tipe instanceof TareaParticipante)
            consultaSQL="{call create_update_tareaparticipante(?, ?, ?)}";
        else if (tipe instanceof AreaPreasignada)
            consultaSQL="{call create_update_areapreasignada(?, ?, ?)}";
        Connection connection=getConnetion();
        CallableStatement consultaRE=connection.prepareCall(consultaSQL);

        if(tipe instanceof Estudiante)
        {
            Estudiante a = (Estudiante) objeto;
            consultaRE.setInt(11, PrimaryKey);
            consultaRE.setBoolean(2, a.isTesisAplicable());
            consultaRE.setString(4, a.getTemaTesis());
            consultaRE.setString(5, a.getNombreApellidos());
            consultaRE.setDate(6, a.getCumpleannos());
            consultaRE.setInt(7, a.getAnnoIngreso());
            consultaRE.setString(8, a.getNidentidad());
            consultaRE.setInt(9, a.getEspecialidad());
            consultaRE.setBoolean(1, a.isCujaenno());
            if (a.getProcedenciaEstudiante()==null)
                consultaRE.setNull(3,Types.INTEGER);
            else
                consultaRE.setInt(3, a.getProcedenciaEstudiante());
            if (a.getArea()==null)
                consultaRE.setNull(10,Types.INTEGER);
            else
                consultaRE.setInt(10, a.getArea().intValue());

        }
        else if(tipe instanceof Actividad)
        {
            Actividad a=(Actividad)objeto;
            consultaRE.setInt(3,a.getId());
            consultaRE.setString(1,a.getNombre());
            consultaRE.setString(2,a.getDescripcion());
        }
        else if(tipe instanceof ActividadesEstudiante)
        {
            ActividadesEstudiante a= (ActividadesEstudiante)objeto;
            Object o=a.isAsistencia();

            consultaRE.setInt(3,a.getId());
            consultaRE.setInt(1,a.getActividad());
            consultaRE.setInt(2,a.getEstudiante());
            if (o==null)
                consultaRE.setNull(4,Types.BOOLEAN);
            else
                consultaRE.setBoolean(4,a.isAsistencia());
        }
        else if(tipe instanceof ActividadesPlan)
        {
            ActividadesPlan a= (ActividadesPlan) objeto;
            consultaRE.setInt(7,a.getId());
            consultaRE.setDate(1,a.getFecha());
            consultaRE.setInt(2,a.getAct());
            consultaRE.setInt(3,a.getPlan());
            consultaRE.setInt(4,a.getResponsable());
            consultaRE.setTime(5,a.getHora());
            consultaRE.setInt(6,a.getArea());
        }
        else if(tipe instanceof Area)
        {
            Area a=(Area)objeto;
            consultaRE.setInt(2,a.getId());
            consultaRE.setString(1,a.getNombre());
        }
        else if(tipe instanceof Directorio)
        {
            Directorio a=(Directorio) objeto;
            consultaRE.setInt(8,a.getId());
            consultaRE.setString(1,a.getCarnetIdentidad());
            consultaRE.setString(2,a.getCargo());
            consultaRE.setString(3,a.getNombreApellido());
            consultaRE.setInt(4,a.getArea());
            consultaRE.setString(5,a.getNusuario());
            consultaRE.setString(6,a.getContrasenna());
            consultaRE.setBoolean(7,a.isActivo());
        }
        else if (tipe instanceof Especialidad)
        {
            Especialidad a=(Especialidad) objeto;
            consultaRE.setInt(2,a.getId());
            consultaRE.setString(1,a.getNombre());
        }
        else if (tipe instanceof EstudiantePlanFamiliarizacion)
        {
            EstudiantePlanFamiliarizacion a=(EstudiantePlanFamiliarizacion)objeto;
            consultaRE.setInt(3,a.getId());
            consultaRE.setInt(2,a.getPlan());
            consultaRE.setInt(1,a.getEstudiante());
            if (a.getNota()==null)
                consultaRE.setNull(4,Types.INTEGER);
            else
                consultaRE.setInt(4,a.getNota());
        }
        else if (tipe instanceof Etapa)
        {
            Etapa a=(Etapa) objeto;
            if (a.getNota()!=null)
                consultaRE.setInt(6,a.getNota());
            else
                consultaRE.setNull(6,Types.NULL);
            consultaRE.setInt(7,a.getId());
            consultaRE.setInt(1,a.getPlan());
            consultaRE.setInt(2,a.getNombre());
            consultaRE.setDate(3,a.getFechaInicio());
            consultaRE.setDate(4,a.getFechaFinal());
            consultaRE.setString(5,a.getObjetivo());
        }
        else if(tipe instanceof LugarProcedencia)
        {
            LugarProcedencia a=(LugarProcedencia) objeto;
            consultaRE.setInt(2,a.getId());
            consultaRE.setString(1,a.getLugar());
        }
        else if (tipe instanceof Notificacion)
        {
            Notificacion a=(Notificacion)objeto;
            consultaRE.setInt(3,a.getId());
            consultaRE.setInt(1,a.getSubscriptor());
            consultaRE.setString(2,a.getMensaje());
        }
        else if (tipe instanceof Permiso)
        {
            Permiso a=(Permiso)objeto;
            consultaRE.setInt(2,a.getId());
            consultaRE.setString(1,a.getNombre());
        }
        else if (tipe instanceof PlanAdiestramiento)
        {
            PlanAdiestramiento a=(PlanAdiestramiento)objeto;
            if (a.getAprobado()!=null)
                consultaRE.setInt(4,a.getAprobado());
            else
                consultaRE.setNull(4,Types.INTEGER);

            consultaRE.setInt(5,a.getId());
            consultaRE.setInt(1,a.getAnnoInicio());
            consultaRE.setInt(2,a.getTutor());
            consultaRE.setInt(3,a.getEstudiante());
            consultaRE.setBoolean(6,a.isRevisado());
        }
        else if (tipe instanceof PlanFamiliarizacion)
        {
            PlanFamiliarizacion a=(PlanFamiliarizacion) objeto;
            if (a.getAprobado()==null)
                consultaRE.setNull(3,Types.NULL);
            else
                consultaRE.setInt(3,a.getAprobado());

            consultaRE.setInt(1,a.getAnno());
            consultaRE.setInt(2,a.getConfeccionado());
            consultaRE.setBoolean(4,a.isRevisado());
            consultaRE.setInt(5,a.getId());
            consultaRE.setBoolean(6,a.isRechazado());
        }
        else if (tipe instanceof Rol)
        {
            Rol a=(Rol)objeto;
            consultaRE.setInt(3,a.getId());
            consultaRE.setString(1,a.getNombre());
            consultaRE.setString(2,a.getDescripcion());
        }
        else if (tipe instanceof RolPermiso)
        {
            RolPermiso a=(RolPermiso) objeto;
            consultaRE.setInt(3,a.getId());
            consultaRE.setInt(1,a.getPermiso());
            consultaRE.setInt(2,a.getRol());
        }
        else if (tipe instanceof Tarea)
        {
            Tarea a=(Tarea)objeto;
            if (a.getFechaCumplimiento()!=null)
                consultaRE.setDate(3,a.getFechaCumplimiento());
            else
                consultaRE.setNull(3,Types.DATE);
            consultaRE.setInt(6,a.getId());
            consultaRE.setString(1,a.getNombre());
            consultaRE.setInt(2,a.getEtapa());
            consultaRE.setBoolean(4,a.isCumplimiento());
            consultaRE.setString(5,a.getObservacion());
            consultaRE.setBoolean(7,a.isOtra());
        }
        else if (tipe instanceof UsuarioRol)
        {
            UsuarioRol a=(UsuarioRol)objeto;
            consultaRE.setInt(4,a.getId());
            consultaRE.setInt(1,a.getUsuario());
            consultaRE.setInt(2,a.getRole());
            consultaRE.setDate(3,a.getFechaAsignacion());
        }
        else if (tipe instanceof TareaParticipante)
        {
            TareaParticipante a= ((TareaParticipante) objeto);
            consultaRE.setInt(3,a.getId());
            consultaRE.setInt(1,a.getTarea());
            consultaRE.setInt(2,a.getParticipante());
        }
        else if (tipe instanceof AreaPreasignada)
        {
            AreaPreasignada a= ((AreaPreasignada) objeto);
            consultaRE.setInt(3,a.getId());
            consultaRE.setInt(1,a.getEstudiante());
            consultaRE.setInt(2,a.getArea());
        }
        is=consultaRE.execute();
        consultaRE.close();
        connection.close();
        return is;
    }
}
