
package web.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import web.beans.Category;
import web.beans.Section;
import web.db.Database;

@ManagedBean(eager = true)
@ApplicationScoped
public class SectionController {

    private ArrayList<Section> sectionList;
    private ArrayList<Category> categoryList;
    private int lastSelectedId;

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

    public ArrayList<Category> getCategoryList() {
        return categoryList;
    }
   // Not finished
    public ArrayList<Category> getCategoriesBySectionId(){
        
        categoryList = new ArrayList<>();
        
        Statement statement = null;
        ResultSet rs = null;;
        Connection conn = null;
        
        Map<String,String> params =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        
        lastSelectedId = Integer.parseInt(params.get("section_id"));
        System.out.println(lastSelectedId);
        
        try {
            conn = Database.getConnection();
            statement = conn.createStatement();
            rs = statement.executeQuery("select c.id, c.name "
                    + "from motoshop.category c "
                    + "where c.section_id = " + lastSelectedId);
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                categoryList.add(category);
            }
        } catch (SQLException ex) {
           ex.printStackTrace();
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
        
        return null;
        
    }


    public ArrayList<Section> getSectionList() {
        return sectionList;

    }
    
}
