package Auxiliar.Excepciones;

public class AppException extends Exception
{
    private int CodeError;

    public AppException(int codeError) {
        super();
        CodeError = codeError;
    }

    @Override
    public String getMessage()
    {
        String sms="";
        switch (CodeError)
        {
            case 22:
                sms="Formulario Incompleto";
            break;
            case 33:
                sms="Esta Plan ya fue aprobado.Solo podra revisar";
            break;
            case 1:
                sms="Graduado siendo adiestrado.No puede ser borrado";
                break;
            case 2:
                sms="Graduado siendo adiestrado en otra area, no puede ser trasladado";
                break;
            case 5:
                sms="Ya existe un Plan de Familiarizacion para este Año";
                break;
            case 23:
                sms="Usuario Incorrecto";
                break;
            case 213:
                sms="";
                break;
        }

        return sms;
    }

    public static String getJustMensaje(String cadena){
        String retorno=null;
        char[] chars = cadena.toCharArray();
        boolean enc=false;
        int j=0;
        int i=0;

        while(!enc && i<chars.length){
            if(chars[i]=='.')
                enc=true;
            if(chars[i]==':')
                j=i;
            i++;
        }

        if(j==0)
            retorno=cadena.substring(j,i);

        else
            retorno=cadena.substring(j+1,i);

        return  retorno;

    }
}
