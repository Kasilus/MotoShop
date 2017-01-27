package web.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import web.beans.Item;
import web.db.Database;

@ManagedBean
@SessionScoped
public class SearchController {
    
    private String searchString;
    ArrayList<Item> currentItemList;

    public ArrayList<Item> getCurrentItemList() {
        return currentItemList;
    }

    public void setCurrentItemList(ArrayList<Item> currentItemList) {
        this.currentItemList = currentItemList;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getSearchString() {
        return searchString;
    }
    

    private void fillItemsBySql(String sql) {
        
        currentItemList = new ArrayList<>();

        Statement statement = null;
        ResultSet rs = null;;
        Connection conn = null;

        try {
            conn = Database.getConnection();
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                currentItemList.add(item);
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
    
    public void getByPartOfWord(){
            fillItemsBySql("SELECT * FROM motoshop.item "
                + "WHERE name LIKE '%" + searchString + "%'");
    }
}
