
package web.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import web.beans.Section;
import web.db.Database;

@ManagedBean(eager = true)
@ApplicationScoped
public class SectionController {

    private ArrayList<Section> sectionList;

    public SectionController() {
        getSections();
    }
    
    private void getSections() {
        
        sectionList = new ArrayList<>();
        
        Statement statement = null;
        ResultSet rs = null;;
        Connection conn = null;

        try {
            conn = Database.getConnection();
            statement = conn.createStatement();
            rs = statement.executeQuery("SELECT * FROM motoshop.section");
            while (rs.next()) {
                Section section = new Section();
                section.setId(rs.getInt("id"));
                section.setName(rs.getString("name"));
                sectionList.add(section);
            }
        } catch (SQLException ex) {
            
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                
            }

        }
    }


    public ArrayList<Section> getSectionList() {
        return sectionList;

    }
    
}
