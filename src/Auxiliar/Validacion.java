package Auxiliar;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Timer;

public class Validacion
{
    public static boolean isCarnetIdentidadCorrecto(String CI)
    {
        boolean is =true;
        if (CI.length()>11)
            is=false;
        else
        {
            char[] chars = CI.toCharArray();
            for (int x = 0; x < CI.length() || is; x++)
                if (!Character.isDigit(chars[x]))
                    is=false;
        }
        return is;
    }
    public static boolean isSinNumero(String cadena)
    {
        boolean is =true;
        char[] chars = cadena.toCharArray();
        for (int x = 0; x < cadena.length() || is; x++)
            if (!Character.isLetter(chars[x]))
                is=false;
        return is;
    }


    public static Date getNacimiento_From_Carnet(String CI) throws Exception
    {
        Date fechanacimiento=null;
        LocalDate localDate=LocalDate.now();
        int añoActual=Integer.parseInt(String.valueOf(localDate.getYear()).substring(0,2));

        int anno=Integer.parseInt(CI.substring(0,2));
        int mes=Integer.parseInt(CI.substring(2,4));
        int dia=Integer.parseInt(CI.substring(4,6));


        if (mes>12||mes<1||dia>31||dia<1||(mes==2&&dia>29))
            throw new Exception("Fecha de Nacimiento Incorrecta.");



        if (CI.substring(0,1).equals("0"))
            anno=añoActual*100+anno;
        else
            anno=(añoActual-1)*100+anno;

        fechanacimiento=Date.valueOf(LocalDate.of(anno,mes,dia));
        System.out.println(fechanacimiento.toString());
        return  fechanacimiento;
    }
}
