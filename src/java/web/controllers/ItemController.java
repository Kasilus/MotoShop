package web.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import web.beans.Item;
import web.db.Database;

@ManagedBean
@SessionScoped
public class ItemController {

    private boolean editMode;
    private Item lastItemById;
    private int idOfSelectedItem;

    public int getIdOfSelectedItem() {
        return idOfSelectedItem;
    }
    
    public boolean isEdit() {
        return editMode;
    }

    public void changeMode() {
        editMode = !editMode;
    }

    public Item getItemById() {
       
            Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            
            if(params.get("item_id")!=null){
            idOfSelectedItem = Integer.parseInt(params.get("item_id"));
        }


            int id = idOfSelectedItem;

            lastItemById = new Item();
            lastItemById.setId(id);

            Statement statement = null;
            ResultSet rs = null;;
            Connection conn = null;

            try {
                conn = Database.getConnection();
                statement = conn.createStatement();

                rs = statement.executeQuery("SELECT name,price,count FROM motoshop.item WHERE id=" + id);
                rs.next();

                lastItemById.setName(rs.getString("name"));
                lastItemById.setCount(rs.getInt("count"));
                lastItemById.setPrice(rs.getDouble("price"));

            } catch (SQLException ex) {
                Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
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

        return lastItemById;
    }

    public void updateItem() {

        PreparedStatement preStatement = null;
        ResultSet rs = null;
        Connection conn = null;

        try {

            conn = Database.getConnection();
            preStatement = conn.prepareStatement("UPDATE motoshop.item SET name = ?, count = ?, price = ? WHERE id = " + idOfSelectedItem );

            System.out.println(lastItemById.getName());
            preStatement.setString(1, lastItemById.getName());
            preStatement.setInt(2, lastItemById.getCount());
            preStatement.setDouble(3, lastItemById.getPrice());

            preStatement.addBatch();

            preStatement.executeBatch();

        } catch (SQLException ex) {

        } finally {
            try {
                if (preStatement != null) {
                    preStatement.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {

            }
            
            changeMode();

        }
    }

}
