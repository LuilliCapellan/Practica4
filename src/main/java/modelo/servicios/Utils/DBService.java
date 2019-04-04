package modelo.servicios.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBService {

    private static DBService instancia;

    public static DBService getInstancia(){

        if (instancia == null)
                instancia = new DBService();
        return instancia;
    }

    public Connection connection(){
        Connection connection = null;

        try {
            String url = "jdbc:h2:tcp://localhost/~/blog";
            connection = DriverManager.getConnection(url, "", "");
        }catch (Exception e){
            Logger.getLogger(DBService.class.getName()).log(Level.SEVERE, null, e);
        }

        return connection;
    }
}
