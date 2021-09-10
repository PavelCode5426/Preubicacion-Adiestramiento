package Servicio;

import Modelo.PlanFamiliarizacion;

public class Service_Locator
{
    private  Loggin_Roles_Service loggin_roles_service;
    private Planificacion_Familiarizacion_Service planificacion_familiarizacion_service;
    private Area_Service area_service;
    private Directorio_Service directorio_service;
    private Especialidad_Service especialidad_service;
    private Graduado_Service graduado_service;
    private Procedencia_Service procedencia_service;
    private Notificacion_Service notificacion_service;
    private Planificacion_Adiestramiento_Service planificacion_adiestramiento_service;
    private Report_Service report_service;
    private PreasignacionArea_Service preasignacionArea_service;
    private Security_Service security_service;

    public Report_Service getReport_service() {
        return report_service;
    }

    public Planificacion_Adiestramiento_Service getPlanificacion_adiestramiento_service() {
        return planificacion_adiestramiento_service;
    }

    private static Service_Locator instance;

    public Procedencia_Service getProcedencia_service() {
        return procedencia_service;
    }

    public Directorio_Service getDirectorio_service() {
        return directorio_service;
    }

    public Especialidad_Service getEspecialidad_service() {
        return especialidad_service;
    }

    public Graduado_Service getGraduado_service() {
        return graduado_service;
    }

    public Notificacion_Service getNotificacion_service() {
        return notificacion_service;
    }

    public Security_Service getSecurity_service() {
        return security_service;
    }

    private Service_Locator()
    {
        security_service=new Security_Service();
        graduado_service=new Graduado_Service();
        especialidad_service=new Especialidad_Service();
        loggin_roles_service=new Loggin_Roles_Service();
        planificacion_familiarizacion_service=new Planificacion_Familiarizacion_Service();
        area_service=new Area_Service();
        directorio_service=new Directorio_Service();
        procedencia_service=new Procedencia_Service();
        notificacion_service=new Notificacion_Service();
        planificacion_adiestramiento_service=new Planificacion_Adiestramiento_Service();
        report_service=new Report_Service();
        preasignacionArea_service=new PreasignacionArea_Service();
    }

    public Loggin_Roles_Service getLoggin_roles_service() {
        return loggin_roles_service;
    }

    public Area_Service getArea_service() {
        return area_service;
    }

    public Planificacion_Familiarizacion_Service getPlanificacion_familiarizacion_service() {
        return planificacion_familiarizacion_service;
    }

    public static Service_Locator getInstance() {
        if(instance==null)
            instance=new Service_Locator();
        return instance;
    }

    public PreasignacionArea_Service getPreasignacionArea_service() {
        return preasignacionArea_service;
    }
}
