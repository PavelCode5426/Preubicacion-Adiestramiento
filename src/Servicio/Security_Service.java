package Servicio;


import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Security_Service
{

    public  String EncriptarTexto(String text)
    {
        MessageDigest md5;
        String retorno = "";
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(text.getBytes());
            byte[] keys = md5.digest();
            retorno = new String(new BASE64Encoder().encode(keys));
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        return retorno;
    }



}