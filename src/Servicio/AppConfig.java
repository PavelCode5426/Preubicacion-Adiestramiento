package Servicio;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Scanner;

public class AppConfig {

    private String Driver;
    private String userApp, passApp, database, port, adres;
    private static AppConfig Singleton=null;
    private static AppConfig AppExt=null;


    public AppConfig(String driver, String userApp, String passApp, String database, String port, String adres) {
        this.Driver = driver;
        this.userApp = userApp;
        this.passApp = passApp;
        this.database = database;
        this.port = port;
        this.adres = adres;
    }





    public static AppConfig getIntanceAppDataBase()    {
        if (Singleton==null)
            new AppConfig();
        return Singleton;
    }
    public static AppConfig getIntanceAppExtDataBase()    {
        if (AppExt==null)
            new AppConfig();
        return AppExt;
    }
    public String getDriver() {
        return Driver;
    }
    public String getUserApp() {
        return userApp;
    }
    public String getPassApp() {
        return passApp;
    }
    public String getDatabase() {
        return database;
    }
    public String getPort() {
        return port;
    }





    public String getBaseDatos()
    {
        return Driver+adres+":"+port+"/"+database;
    }
    public static void setSingleton(AppConfig singleton)
    {
        try
        {
            Singleton = singleton;
            MakeXML(Singleton,AppExt);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    public static void setAppExt(AppConfig appExt) {
        try
        {
            AppExt = appExt;
            MakeXML(Singleton,AppExt);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public String getAdres() {
        return adres;
    }



    private AppConfig()
    {
        try {
            if (new File("AppConfig.xml").exists())
                ReadXML();
            else
            {
                AppConfig app = new AppConfig("jdbc:postgresql://", "postgres", "1234", "preubicacion_adiestramiento2.0", "5432", "localhost");
                AppConfig appExt = new AppConfig("jdbc:postgresql://", "postgres", "1234", "Estudiantes2.0", "5432", "localhost");
                MakeXML(app, appExt);
                System.out.println("Se establecio una conexion por defecto");
                Singleton = app;
                AppExt = appExt;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();

        }

    }

    public static void MakeXML(AppConfig App, AppConfig Ext) throws Exception   {

        ArrayList<AppConfig> list=new ArrayList<>();
        list.add(App);
        list.add(Ext);
        DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
        DOMImplementation domImplementation= documentBuilder.getDOMImplementation();

        Document document=domImplementation.createDocument(null,"AppConfiguration",null);
        document.setXmlVersion("1.0");

        Element raiz=document.getDocumentElement();
        Element appConfigElement=document.createElement("AppConfig");
        for (int x=0;x<list.size();x++)
        {
            Element databaseElement = document.createElement("DataBase");

            Element driverElement = document.createElement("driver");
            driverElement.appendChild(document.createTextNode(list.get(x).getDriver()));
            Element portElement = document.createElement("port");
            portElement.appendChild(document.createTextNode(list.get(x).getPort()));
            Element databasenameElement = document.createElement("databasename");
            databasenameElement.appendChild(document.createTextNode(list.get(x).getDatabase()));
            Element userElement = document.createElement("user");
            userElement.appendChild(document.createTextNode(list.get(x).getUserApp()));
            Element passElement = document.createElement("pass");
            passElement.appendChild(document.createTextNode(list.get(x).getPassApp()));
            Element adressElement = document.createElement("adress");
            adressElement.appendChild(document.createTextNode(list.get(x).getAdres()));


            databaseElement.appendChild(driverElement);
            databaseElement.appendChild(adressElement);
            databaseElement.appendChild(portElement);
            databaseElement.appendChild(databasenameElement);
            databaseElement.appendChild(userElement);
            databaseElement.appendChild(passElement);


            appConfigElement.appendChild(databaseElement);
        }
        raiz.appendChild(appConfigElement);

        Source source=new DOMSource(document);
        Result result=new StreamResult(new File("AppConfig.xml"));
        Transformer transformer= TransformerFactory.newInstance().newTransformer();

        transformer.transform(source,result);

    }


















    public static void ReadXML() throws Exception   {
        DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
        Document document= documentBuilder.parse(new File("AppConfig.xml"));

        NodeList nodeList=document.getElementsByTagName("DataBase");
        for (int z=0;z<nodeList.getLength();z++)
        {
            Element node= ((Element) nodeList.item(z));
            if (node.getNodeType()==Node.ELEMENT_NODE)
            {
                NodeList node1=node.getChildNodes();
                int x=0;
                String Driver = node1.item(x++).getTextContent();
                String adres = node1.item(x++).getTextContent();
                String port = node1.item(x++).getTextContent();
                String database = node1.item(x++).getTextContent();
                String userApp = node1.item(x++).getTextContent();
                String passApp = node1.item(x++).getTextContent();

                if (z==0)
                {
                    Singleton=new AppConfig(Driver,userApp,passApp,database,port,adres);
                }
                else
                {
                    AppExt=new AppConfig(Driver,userApp,passApp,database,port,adres);
                }
            }
            else
                throw new Exception("Error al leer el XML");
        }
    }
    public  Connection getConnetionDataBaseApp() throws Exception    {
        Class.forName("org.postgresql.Driver");
        Connection connection= DriverManager.getConnection(Driver+adres+":"+port+"/"+database,userApp,passApp);
        return connection;
    }
}
