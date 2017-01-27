package web.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import web.db.Database;


@ManagedBean
@SessionScoped
public class Sections {

    private ArrayList<Section> sectionList = new ArrayList<>();

    private ArrayList<Section> getSections() {
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
            Logger.getLogger(Sections.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(Sections.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return sectionList;
    }

    public ArrayList<Section> getSectionList() {
        if (sectionList.isEmpty()) {
            return getSections();
        }
        return sectionList;

    }

}
