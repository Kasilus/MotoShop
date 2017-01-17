package web.db;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Database {

    private static Connection conn;
    private static InitialContext ic;
    private static DataSource ds;

    public static Connection getConnection() {

        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("jdbc/Motoshop");
            conn = ds.getConnection();
        } catch (NamingException ex) {
            System.out.println("Naming exception");
        } catch (SQLException ex) {
            System.out.println("Sql Exception");
        }
        return conn;
    }

}
