package Servicio;

import Auxiliar.Tupla;
import Auxiliar.TuplaList;
import Controlador.Config_App_Controller;
import Modelo.*;
import org.omg.CORBA.INTERNAL;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class Loggin_Roles_Service
{
    private Directorio LoggedUser=null;



    public Directorio Loggin_Service(String user, String pass) throws Exception    {
        Directorio directorio=null;
        Connection connection=Repository.getConnetion();
        connection.setAutoCommit(false);
        String consulta="{?=call servicio_loggin(?,?)}";
        CallableStatement statement=connection.prepareCall(consulta);
        statement.registerOutParameter(1,Types.OTHER);
        statement.setString(2,user);
        statement.setString(3,Service_Locator.getInstance().getSecurity_service().EncriptarTexto(pass));
        statement.execute();
        ResultSet resultSet= ((ResultSet) statement.getObject(1));

        directorio=new Repository<Directorio>(new Directorio()).Adaptador_Object(resultSet);
        if (directorio!=null)
            LoggedUser=directorio;
        else
            throw new Exception("Credenciales Incorrectos.");

        return directorio;
    }
    public boolean IamSuperAdministrador() throws Exception    {
        Iterator<Rol> iterator=Roles_LoggedUser().iterator();
        boolean is=false;
        while (iterator.hasNext()&&!is)
        {
            if (iterator.next().getNombre().equals("Super Administrador"))
                is=true;
        }
        return is;
    }
    public boolean IamAdimistrador() throws Exception    {
        Iterator<Rol> iterator=Roles_LoggedUser().iterator();
        boolean is=false;
        while (iterator.hasNext()&&!is)
        {
            if (iterator.next().getNombre().equals("Administrador"))
                is=true;
        }
        return is;
    }

    public boolean isSuperAdministrador(int id) throws Exception    {
        Iterator<Rol> iterator=geRolesFromDirectorio(id).iterator();
        boolean is=false;
        while (iterator.hasNext()&&!is)
        {
            if (iterator.next().getNombre().equals("Super Administrador"))
                is=true;
        }
        return is;
    }
    public boolean isAdimistrador(int id) throws Exception    {
        Iterator<Rol> iterator=geRolesFromDirectorio(id).iterator();
        boolean is=false;
        while (iterator.hasNext()&&!is)
        {
            if (iterator.next().getNombre().equals("Administrador"))
                is=true;
        }
        return is;
    }


    public ArrayList<Rol> Roles_LoggedUser() throws Exception    {
        if (LoggedUser==null)
            throw new Exception("Logge un usuario antes de pedir sus roles");
        ArrayList<Rol> roles=new ArrayList<>();
        String consulta="{ ?=call servicio_get_roles(?)}";
        Connection connection=Repository.getConnetion();
        connection.setAutoCommit(false);
        CallableStatement statement=connection.prepareCall(consulta);
        statement.registerOutParameter(1,Types.OTHER);
        statement.setInt(2,LoggedUser.getId());
        statement.execute();
        roles=new ArrayList<>(new Repository<Rol>(new Rol()).Adaptador_List((ResultSet) statement.getObject(1)));
        if (roles.size()==0)
            throw new Exception("Usuario sin Rol en la Base de Datos");
        statement.close();
        return roles;
    }
    public Directorio getLoggedUser()
    {
        return LoggedUser;
    }
    public ArrayList<Permiso> getPermisosUsuario() throws Exception  {
        if (LoggedUser==null)
            throw new Exception("Logge un usuario antes de pedir sus roles");
        ArrayList<Permiso> permisos=new ArrayList<>();
        String consulta="{ ?=call servicio_get_permisos(?) }";
        Connection connection=Repository.getConnetion();
        connection.setAutoCommit(false);
        CallableStatement statement=connection.prepareCall(consulta);
        statement.registerOutParameter(1,Types.OTHER);
        statement.setInt(2,LoggedUser.getId());
        statement.execute();
        permisos=new ArrayList<>(new Repository<Permiso>(new Permiso()).Adaptador_List((ResultSet) statement.getObject(1)));
        statement.close();
        return permisos;
    }
    public ArrayList<Rol> geRolesFromDirectorio(int IDDir)throws Exception  {
        ArrayList<Rol> roles=new ArrayList<>();
        String consulta="{ ?=call servicio_get_roles(?) }";
        Connection connection=Repository.getConnetion();
        connection.setAutoCommit(false);
        CallableStatement statement=connection.prepareCall(consulta);
        statement.registerOutParameter(1,Types.OTHER);
        statement.setInt(2,IDDir);
        statement.execute();
        roles=new ArrayList<>(new Repository<Rol>(new Rol()).Adaptador_List((ResultSet) statement.getObject(1)));
        statement.close();
        return roles;
    }
    public ArrayList<Permiso> getPermisosFromDirectorio(int ID) throws Exception  {
        ArrayList<Permiso> permisos=new ArrayList<>();
        String consulta="{ ?=call servicio_get_permisos(?) }";
        Connection connection=Repository.getConnetion();
        connection.setAutoCommit(false);
        CallableStatement statement=connection.prepareCall(consulta);
        statement.registerOutParameter(1,Types.OTHER);
        statement.setInt(2,ID);
        statement.execute();
        permisos=new ArrayList<>(new Repository<Permiso>(new Permiso()).Adaptador_List((ResultSet) statement.getObject(1)));
        if (permisos.size()==0)
            throw new Exception("Usuario sin Permisos en la Base de Datos");
        statement.close();
        return permisos;
    }
    public ArrayList<TuplaList<Object>> getRol_UsuarioRol_Agrupado(int ID)throws Exception    {
        ArrayList<TuplaList<Object>> list=new ArrayList<>();
        String SQL="select * from usuariorol where usuario=?;";
        String SQL2="select * from rolpermiso where rol=?;";
        Connection connection=Repository.getConnetion();
        Connection connection2=Repository.getConnetion();
        PreparedStatement statement=connection.prepareStatement(SQL, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        statement.setInt(1,ID);
        ResultSet resultSet=statement.executeQuery();
        while (resultSet.next())
        {
            TuplaList<Object> add=new TuplaList<>(0);
            UsuarioRol usuarioRol=new UsuarioRol(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getDate(4));
            add.getListaobjeto().add(usuarioRol);

            PreparedStatement statement2=connection2.prepareStatement(SQL2, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            statement2.setInt(1,usuarioRol.getId());
            ResultSet resultSet2=statement2.executeQuery();
            while (resultSet2.next())
            {
                RolPermiso rolPermiso=new RolPermiso(resultSet2.getInt(1),resultSet2.getInt(2),resultSet2.getInt(3));
                add.getListaobjeto().add(rolPermiso);

                System.out.println(usuarioRol.getId()+"----"+rolPermiso.getId());
            }
            list.add(add);
        }
        connection.close();
        connection2.close();
        return list;
    }
    public void AsignarRol_Permiso(UsuarioRol usuarioRol,ArrayList<Integer> permisos) throws Exception    {

        Repository<UsuarioRol> usuarioRolRepository=new Repository<UsuarioRol>(new UsuarioRol());
        usuarioRolRepository.Update(usuarioRol.getId(),usuarioRol);
        UsuarioRol usuarioRol1=usuarioRol;
        if (usuarioRol.getId()==-1)
            usuarioRol1=usuarioRolRepository.GetAll().get(0);

        DeleteRolpermisoFromIdUsuarioRol(usuarioRol.getId());
        Repository<RolPermiso> rolPermisoRepository=new Repository<>(new RolPermiso());
        Iterator<Integer> iterator=permisos.iterator();
        while (iterator.hasNext())
        {
            rolPermisoRepository.Create(new RolPermiso(-1,iterator.next(),usuarioRol1.getId()));
        }
    }
    public void DeleteRolpermisoFromIdUsuarioRol(int id) throws Exception    {
        String SQL="delete from rolpermiso where rol=?";
        Connection connection=Repository.getConnetion();
        PreparedStatement statement=connection.prepareStatement(SQL);
        statement.setInt(1,id);
        statement.execute();
        connection.close();
    }
    public void DeleteUsuarioRole(int id,int user) throws Exception    {

        String SQL="delete from usuariorol where rol=? and usuario=?";
        Connection connection=Repository.getConnetion();
        PreparedStatement statement=connection.prepareStatement(SQL);
        statement.setInt(1,id);
        statement.setInt(2,user);
        statement.execute();
        connection.close();
    }

    public boolean BuscarUsuarioRolPorUsuario(int idUsuario) throws  Exception{
        boolean enc=false;
        ArrayList<UsuarioRol>usuarioRols=(ArrayList<UsuarioRol>) new Repository<UsuarioRol>( new UsuarioRol()).GetAll();
        Iterator<UsuarioRol> it=usuarioRols.iterator();

        while (it.hasNext() && !enc){
            if(it.next().getUsuario()==idUsuario)
                enc=true;
        }

        return  enc;
    }

}
