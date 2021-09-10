package Auxiliar;

import Controlador.Pantalla_Vicerector_Primero_Controller;
import Modelo.*;
import Reportes.Controladoras.Reporte_Tutor_Est_JefeArea_Controller;
import Servicio.Repository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Iterator;

public class Busquedas {


    public ArrayList<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public ArrayList<LugarProcedencia> getLugares() {
        return lugares;
    }

    public ArrayList<Area> getAreas() {
        return areas;
    }

    public ArrayList<Directorio> getDirectorios() {
        return directorios;
    }

    public ArrayList<UsuarioRol> getUsuariosRoles() {
        return usuariosRoles;
    }

    private ArrayList<Especialidad> especialidades;
    private ArrayList<LugarProcedencia> lugares;
    private ArrayList<Area> areas;
    private ArrayList<Directorio> directorios;
    private ArrayList<UsuarioRol> usuariosRoles;


    public Busquedas() throws Exception {
        especialidades = (ArrayList<Especialidad>) new Repository<Especialidad>(new Especialidad()).GetAll();
        lugares = (ArrayList<LugarProcedencia>) new Repository<LugarProcedencia>(new LugarProcedencia()).GetAll();
        areas = (ArrayList<Area>) new Repository<Area>(new Area()).GetAll();
        directorios = (ArrayList<Directorio>) new Repository<Directorio>(new Directorio()).GetAll();
        usuariosRoles = (ArrayList<UsuarioRol>) new Repository<UsuarioRol>(new UsuarioRol()).GetAll();

    }


    public ArrayList<Estudiante> buscarEstudiantes(String filtro, String filtrarPor, ArrayList<Estudiante> todosEstudiantes) throws Exception {

        ArrayList<Estudiante> estudiantesEncontrados = new ArrayList<Estudiante>();
        Estudiante estAux = null;

        if (todosEstudiantes == null) {
            todosEstudiantes = (ArrayList<Estudiante>) new Repository<Estudiante>(new Estudiante()).GetAll();
        }

        Iterator<Estudiante> estudianteIterator = todosEstudiantes.iterator();


        switch (filtrarPor) {
            case "":
                while (estudianteIterator.hasNext()) {
                    estAux = estudianteIterator.next();
                    if (estAux.getNombreApellidos().toLowerCase().contains(filtro.toLowerCase()))
                        estudiantesEncontrados.add(estAux);
                }
                break;
            case "Nombre y apellidos":
                while (estudianteIterator.hasNext()) {
                    estAux = estudianteIterator.next();
                    if (estAux.getNombreApellidos().toLowerCase().contains(filtro.toLowerCase()))
                        estudiantesEncontrados.add(estAux);
                }

                break;
            case "Tema de Tesis":

                while (estudianteIterator.hasNext()) {
                    estAux = estudianteIterator.next();
                    if (estAux.getTemaTesis().toLowerCase().contains(filtro.toLowerCase()))
                        estudiantesEncontrados.add(estAux);
                }

                break;
            case "Lugar de procedencia":
                for (int j = 0; j < lugares.size(); j++) {
                    if (lugares.get(j).getLugar().toLowerCase().contains(filtro.toLowerCase())) {

                        while (estudianteIterator.hasNext()) {
                            estAux = estudianteIterator.next();
                            if (estAux.getProcedenciaEstudiante() == lugares.get(j).getId())
                                estudiantesEncontrados.add(estAux);
                        }
                    }
                }
                break;
            case "Especialidad":
                for (int j = 0; j < especialidades.size(); j++) {
                    if (especialidades.get(j).getNombre().toLowerCase().contains(filtro.toLowerCase())) {
                        while (estudianteIterator.hasNext()) {
                            estAux = estudianteIterator.next();
                            if (estAux.getEspecialidad() == especialidades.get(j).getId())
                                estudiantesEncontrados.add(estAux);
                        }
                    }
                }
                break;
            case "Carnet de identidad":
                while (estudianteIterator.hasNext()) {
                    estAux = estudianteIterator.next();
                    if (estAux.getNidentidad().contains(filtro))
                        estudiantesEncontrados.add(estAux);
                }

            case "Año de ingreso":
                while (estudianteIterator.hasNext()) {
                    estAux = estudianteIterator.next();
                    if (estAux.getAnnoIngreso().toString().contains(filtro))
                        estudiantesEncontrados.add(estAux);
                }
                break;
        }
      /*  if (filtrarPor.equals("Nombre y apellidos") || filtrarPor.equals("")) {
            for (int i = 0; i < todosEstudiantes.size(); i++) {
                if (todosEstudiantes.get(i).getNombreApellidos().toLowerCase().contains(filtro.toLowerCase()))
                    estudiantesEncontrados.add(todosEstudiantes.get(i));
            }
        } else if (filtrarPor.equals("Tema de Tesis")) {
            for (int i = 0; i < todosEstudiantes.size(); i++) {
                if (todosEstudiantes.get(i).getTemaTesis().toLowerCase().contains(filtro.toLowerCase()))
                    estudiantesEncontrados.add(todosEstudiantes.get(i));
            }

        }*/
    /*    if (filtrarPor.equals("Lugar de procedencia")) {
            for (int j = 0; j < lugares.size(); j++) {
                if (lugares.get(j).getLugar().toLowerCase().contains(filtro.toLowerCase())) {
                    for (int i = 0; i < todosEstudiantes.size(); i++) {
                        if (todosEstudiantes.get(i).getProcedenciaEstudiante() == lugares.get(j).getId())
                            estudiantesEncontrados.add(todosEstudiantes.get(i));
                    }
                }
            }
        } else if (filtrarPor.equals("Especialidad")) {
            for (int j = 0; j < especialidades.size(); j++) {
                if (especialidades.get(j).getNombre().toLowerCase().contains(filtro.toLowerCase())) {
                    for (int i = 0; i < todosEstudiantes.size(); i++) {
                        if (todosEstudiantes.get(i).getEspecialidad() == especialidades.get(j).getId())
                            estudiantesEncontrados.add(todosEstudiantes.get(i));
                    }
                }
            }

        } else if (filtrarPor.equals("Carnet de identidad")) {
            for (int i = 0; i < todosEstudiantes.size(); i++) {
                if (todosEstudiantes.get(i).getNidentidad().contains(filtro))
                    estudiantesEncontrados.add(todosEstudiantes.get(i));
            }

        } else if (filtrarPor.equals("Año de ingreso")) {
            for (int i = 0; i < todosEstudiantes.size(); i++) {
                if (todosEstudiantes.get(i).getAnnoIngreso().toString().contains(filtro))
                    estudiantesEncontrados.add(todosEstudiantes.get(i));
            }

        }*/


        return estudiantesEncontrados;
    }

    public ArrayList<Reporte_Tutor_Est_JefeArea_Controller.Reporte_Tutor_Est_Jefe> buscarParaReporteTutorJefe(String filtro, String filtrarPor, ArrayList<Reporte_Tutor_Est_JefeArea_Controller.Reporte_Tutor_Est_Jefe> listaReporte) {

        ArrayList<Reporte_Tutor_Est_JefeArea_Controller.Reporte_Tutor_Est_Jefe> listaRetorno = new ArrayList<Reporte_Tutor_Est_JefeArea_Controller.Reporte_Tutor_Est_Jefe>();


        if (filtrarPor.equals("Tutor")) {
            for (int i = 0; i < listaReporte.size(); i++) {
                if (listaReporte.get(i).getNombreTutor().contains(filtro))
                    listaRetorno.add(listaReporte.get(i));
            }
        }

        if (filtrarPor.equals("Estudiante") || filtrarPor.equals("")) {
            for (int i = 0; i < listaReporte.size(); i++) {
                if (listaReporte.get(i).getNombreEst().contains(filtro))
                    listaRetorno.add(listaReporte.get(i));
            }
        }

        if (filtrarPor.equals("Área")) {
            for (int i = 0; i < listaReporte.size(); i++) {
                if (listaReporte.get(i).getArea().contains(filtro))
                    listaRetorno.add(listaReporte.get(i));
            }
        }

        if (filtrarPor.equals("Jefe de área")) {
            for (int i = 0; i < listaReporte.size(); i++) {
                if (listaReporte.get(i).getJefeArea().contains(filtro))
                    listaRetorno.add(listaReporte.get(i));
            }
        }


        return listaRetorno;

    }

    public static int posDelPunto(String cadena) {
        int retorno = 0;
        boolean is = true;

        char[] chars = cadena.toCharArray();
        for (int x = 0; x < cadena.length() || is; x++)
            if ((chars[x]) == '.') {
                is = false;
                retorno = x;

            }


        return retorno;
    }

    public String buscarEspecialidad(int id) throws Exception {

        String especialidadEnc = null;
        boolean enc = false;
        int i = 0;


        while (!enc && i < especialidades.size()) {
            if (especialidades.get(i).getId() == id) {
                enc = true;
                especialidadEnc = especialidades.get(i).getNombre();
            }
            i++;
        }
        return especialidadEnc;
    }

    public String buscarArea(int id) throws Exception {

        String areaEnc = null;
        boolean enc = false;
        int i = 0;


        while (!enc && i < areas.size()) {
            if (areas.get(i).getId() == id) {
                enc = true;
                areaEnc = areas.get(i).getNombre();
            }
            i++;
        }
        return areaEnc;
    }

    public String buscarLugarProc(int id) throws Exception {

        String lugaresEnc = null;
        boolean enc = false;
        int i = 0;


        while (!enc && i < lugares.size()) {
            if (lugares.get(i).getId() == id) {
                enc = true;
                lugaresEnc = lugares.get(i).getLugar();
            }
            i++;
        }
        return lugaresEnc;
    }

    private boolean getRol(int usuario) {
        int i = 0;
        boolean enc = false;
        int rolResult = 0;

        while (!enc && i < usuariosRoles.size()) {
            if (usuariosRoles.get(i).getUsuario() == usuario && usuariosRoles.get(i).getRole() == 9) {
                enc = true;

            }
            i++;
        }
        return enc;
    }

    public Directorio directorioJefeArea(int area) {

        Directorio result = null;
        boolean enc = false;
        int i = 0;

        while (!enc && i < directorios.size()) {
            if (directorios.get(i).getArea() == area && getRol(directorios.get(i).getId())) {
                enc = true;
                result = directorios.get(i);
            }
            i++;
        }


        return result;
    }

}
