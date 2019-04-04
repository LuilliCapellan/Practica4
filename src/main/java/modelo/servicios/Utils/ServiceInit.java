package modelo.servicios.Utils;


import encapsulacion.Usuario;
import modelo.servicios.EntityServices.UsuarioService;
import org.h2.tools.Server;

import java.sql.SQLException;

public class ServiceInit {

    public static void startDb() throws SQLException {
        Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers", "-tcpDaemon").start();
    }

    public static void stopDb() throws SQLException {
        Server.shutdownTcpServer("tcp://localhost:9092", "", true, true);
    }


    public static void crearTablas() throws SQLException {

        String sqlUsuario = "CREATE TABLE IF NOT EXISTS USUARIO \n" +
                "(\n" +
                "ID INTEGER PRIMARY KEY NOT NULL, \n" +
                "USERNAME VARCHAR(50) NOT NULL, \n" +
                "NOMBRE VARCHAR(50) NOT NULL, \n" +
                "PASS VARCHAR(50) NOT NULL, \n" +
                "ADMINISTRADOR BOOLEAN NOT NULL, \n" +
                "AUTOR BOOLEAN NOT NULL \n" +
                ");";

        String sqlEtiqueta = "CREATE TABLE IF NOT EXISTS ETIQUETA \n" +
                "(\n" +
                "ID INTEGER PRIMARY KEY NOT NULL, \n" +
                "ETIQUETA TEXT NOT NULL, \n" +
                "ARTICULO INTEGER NOT NULL , \n" +
                "FOREIGN KEY (ARTICULO) REFERENCES ARTICULO(ID)" +
                "ON DELETE CASCADE );";


        String sqlComentario = "CREATE TABLE IF NOT EXISTS COMENTARIO \n" +
                "(\n" +
                "ID INTEGER PRIMARY KEY NOT NULL, \n" +
                "COMENTARIO TEXT NOT NULL, \n" +
                "AUTOR INTEGER NOT NULL , \n" +
                "ARTICULO INTEGER NOT NULL , \n" +
                "FOREIGN KEY (AUTOR) REFERENCES USUARIO(ID), \n" +
                "FOREIGN KEY (ARTICULO) REFERENCES ARTICULO(ID)" +
                "ON DELETE CASCADE );";

        String sqlArticulo = "CREATE TABLE IF NOT EXISTS ARTICULO \n" +
                "(\n" +
                "ID INTEGER PRIMARY KEY NOT NULL, \n" +
                "TITULO VARCHAR(250) NOT NULL, \n" +
                "CUERPO TEXT NOT NULL, \n" +
                "AUTOR INTEGER NOT NULL, \n " +
                "FECHA DATE NOT NULL , \n" +
                "FOREIGN KEY (AUTOR) REFERENCES USUARIO(ID)" +
                ");";


//        String admin = "INSERT INTO USUARIO VALUES (SECUENCIA_USUARIO.nextval, 'admin', 'admin', 'admin',"+true+", "+true+")";
        String secuenciaUsuario = "CREATE SEQUENCE IF NOT EXISTS SECUENCIA_USUARIO START WITH 0 INCREMENT BY 1";
        String secuenciaArticulo = "CREATE SEQUENCE IF NOT EXISTS SECUENCIA_ARTICULO START WITH 0 INCREMENT BY 1";
        String secuenciaEtiqueta = "CREATE SEQUENCE IF NOT EXISTS SECUENCIA_ETIQUETA START WITH 0 INCREMENT BY 1";
        String secuenciaComentario = "CREATE SEQUENCE IF NOT EXISTS SECUENCIA_COMENTARIO START WITH 0 INCREMENT BY 1";


//        Connection connection = DBService.getInstancia().connection();
//        Statement statement = connection.createStatement();
//
//        statement.execute(sqlUsuario);
//        statement.execute(sqlArticulo);
//        statement.execute(sqlEtiqueta);
//        statement.execute(sqlComentario);
//
//        statement.execute(secuenciaUsuario);
//        statement.execute(secuenciaArticulo);
//        statement.execute(secuenciaEtiqueta);
//        statement.execute(secuenciaComentario);

        UsuarioService usuarioService = new UsuarioService();

        Usuario usuario = new Usuario("admin", "admin", "admin", true, true);


        if (usuarioService.validateLogIn("admin", "admin") == null) {
            usuarioService.insert(usuario);
        }


//
//        statement.close();
//        connection.close();
    }
}
