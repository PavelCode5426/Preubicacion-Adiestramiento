package Servicio;

import Auxiliar.ConversionImage;
import Modelo.Directorio;
import Modelo.Estudiante;
import Modelo.UsuarioRol;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Directorio_Service
{
    public ArrayList<Directorio> getDirectorio()throws Exception
    {


        return new ArrayList<>(new Repository<Directorio>(new Directorio()).GetAll());
    }
    public ArrayList<Directorio> getAllActivos() throws Exception
    {
        Connection connection=Repository.getConnetion();
        connection.setAutoCommit(false);
        String Consulta="{?=call getdirectoriosactivos()}";
        CallableStatement statement=connection.prepareCall(Consulta);
        statement.registerOutParameter(1,Types.OTHER);
        statement.execute();
        ResultSet resultSet=(ResultSet) statement.getObject(1);
        return new ArrayList<Directorio>(new Repository<Directorio>(new Directorio()).Adaptador_List((resultSet)));
    }
    public Directorio getDirectorio(int id) throws Exception
    {
        Directorio directorio= new Repository<Directorio>(new Directorio()).Get(id);
        return directorio;
    }
    public ArrayList<Estudiante> getTutorados()throws Exception
    {
        String consulta="{?=call service_get_tutorados_from_tutor(?)}";
        Connection connection=Repository.getConnetion();
        connection.setAutoCommit(false);
        CallableStatement statement=connection.prepareCall(consulta);
        statement.registerOutParameter(1, Types.OTHER);
        statement.setInt(2,Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser().getId());
        statement.execute();
        ArrayList<Estudiante> estudiantes=new ArrayList<>(new Repository<Estudiante>(new Estudiante()).Adaptador_List(((ResultSet) statement.getObject(1))));
        connection.close();
        return estudiantes;
    }
    public ResultSet getTutoradosInfo() throws Exception
    {
        String consulta="{?=call service_get_tutoradosInfo(?)}";
        Connection connection=Repository.getConnetion();
        connection.setAutoCommit(false);
        CallableStatement statement=connection.prepareCall(consulta);
        statement.registerOutParameter(1, Types.OTHER);
        statement.setInt(2,Service_Locator.getInstance().getLoggin_roles_service().getLoggedUser().getId());
        statement.execute();
        return ((ResultSet) statement.getObject(1));
    }
    public void CreateUpdateDirectorio(Directorio directorio)throws Exception
    {
        //QUITADO PORQUE A POSTGRESQL NO LE DA LA GANA DE FUNCIONAR
        /*if (getDirectorio(directorio.getId())!=null)
            new Repository<>(new Directorio()).Update(directorio.getId(),directorio);
        else
            new Repository<>(new Directorio()).Create(directorio);*/
        String update="update Directorio set nidentidad=?,cargo=?,nombreapellido=?,area=?,nusuario=?,contrasenna=?,activo=? where Directorio.id=?;";
        String insert="insert into Directorio(nidentidad,cargo,nombreapellido,area,nusuario,contrasenna,activo) values(?,?,?,?,?,?,true);";
       Connection connection=Repository.getConnetion();
       PreparedStatement statement=null;
       if (getDirectorio(directorio.getId())!=null)
       {
           statement=connection.prepareStatement(update);
           statement.setInt(8,directorio.getId());
           statement.setString(1,directorio.getCarnetIdentidad());
           statement.setString(2,directorio.getCargo());
           statement.setString(3,directorio.getNombreApellido());
           statement.setInt(4,directorio.getArea());
           statement.setString(5,directorio.getNusuario());
           statement.setString(6,directorio.getContrasenna());
           statement.setBoolean(7,directorio.isActivo());
       }
       else
       {
           statement=connection.prepareStatement(insert);
           statement.setString(1,directorio.getCarnetIdentidad());
           statement.setString(2,directorio.getCargo());
           statement.setString(3,directorio.getNombreApellido());
           statement.setInt(4,directorio.getArea());
           statement.setString(5,directorio.getNusuario());
           statement.setString(6,directorio.getContrasenna());
       }
        statement.execute();
    }
    public void DeleteDirectorio(int ID)throws Exception
    {
        new Repository<Directorio>(new Directorio()).Remove(ID);
    }

    public boolean SetFotoPerfil(int id, File foto) throws Exception
    {
        String consulta="update directorio set foto=? where directorio.id=?;";

        Connection connection=Repository.getConnetion();
        PreparedStatement statement=connection.prepareStatement(consulta);
        statement.setInt(2,id);
        if (foto!=null)
            //statement.setBytes(1, ConversionImage.convertirFileImagetoArrByte(foto));
            statement.setBinaryStream(1,new FileInputStream(foto),(int) foto.length());
        else
            statement.setNull(1,Types.BINARY);
        return statement.execute();
    }
    public Image GetFotoPerfil(int id) throws Exception
    {
        String consulta="select foto from directorio where directorio.id=?";
        Connection connection=Repository.getConnetion();
        PreparedStatement statement=connection.prepareStatement(consulta);
        statement.setInt(1,id);
        ResultSet resultSet=statement.executeQuery();
        Image image=null;
         if (resultSet.next())
             image=ConversionImage.getImagefromByteArray(resultSet.getBinaryStream(1));

        return image;
    }
    public Directorio getDirectorioPorUsuario(String nombreUsuario) throws Exception{
        boolean enc = false;
        ArrayList<Directorio> usuarioRols = (ArrayList<Directorio>) new Repository<Directorio>(new Directorio()).GetAll();
        Iterator<Directorio> it = usuarioRols.iterator();
        Directorio direct=new Directorio();

        while (it.hasNext() && !enc) {
            direct=it.next();
            if (direct.getNusuario().equals(nombreUsuario))
                enc = true;
        }

        return direct ;
    }


}
