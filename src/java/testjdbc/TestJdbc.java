package testjdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import web.db.Database;


public class TestJdbc {

    public void check() {
       try {

                    Connection connection = Database.getConnection();
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT * FROM motoshop.item");
                    while(rs.next()){
                        System.out.println(rs.getString("name"));
                    }
                }  catch (SQLException ex) {
                
                }
            } 
    }


