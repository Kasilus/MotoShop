package web.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import web.beans.Item;
import web.db.Database;

@ManagedBean
@SessionScoped
public class SearchController {

    private String searchString;
    ArrayList<Item> currentItemList;
    private int itemsBySearch;
    private int itemsOnPage = 6;
    ArrayList<Integer> pagesNumbered = new ArrayList<>();

    private int selectedPageNumber = 1;
    private String currentSql;

    public int getItemsBySearch() {
        return itemsBySearch;
    }

    public int getItemsOnPage() {
        return itemsOnPage;
    }

    public ArrayList<Integer> getPagesNumbered() {
        return pagesNumbered;
    }

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

        currentSql = sql;
        StringBuilder sb = new StringBuilder(sql);

        currentItemList = new ArrayList<>();

        Statement statement = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = Database.getConnection();
            statement = conn.createStatement();

            // Do query to db to know how many items was found by search
            rs = statement.executeQuery(currentSql);
            rs.last();
            itemsBySearch = rs.getRow();

            fillPagesNumbers(itemsBySearch, itemsOnPage);

            if (itemsBySearch > itemsOnPage) {
                sb.append(" LIMIT ").append((selectedPageNumber - 1) * itemsOnPage).append(",").append(itemsOnPage);
            }

            rs = statement.executeQuery(sb.toString());
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

    public void getByPartOfWord() {
        selectedPageNumber = 1;
        fillItemsBySql("SELECT * FROM motoshop.item "
                + "WHERE name LIKE '%" + searchString + "%'");
    }

    private void fillPagesNumbers(int items, int itemsOnPage) {
        int pages = (int) Math.ceil((double) items / itemsOnPage);

        pagesNumbered.clear();
        for (int i = 1; i <= pages; i++) {
            pagesNumbered.add(i);
        }
    }

    public void selectPage() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        selectedPageNumber = Integer.parseInt(params.get("page_number"));
        fillItemsBySql(currentSql);
    }

    public byte[] getImage(int id) {
        Statement statement = null;
        ResultSet rs = null;;
        Connection conn = null;

        byte[] image = null;

        try {
            conn = Database.getConnection();
            statement = conn.createStatement();
            rs = statement.executeQuery("SELECT image FROM motoshop.item where id=" + id);
            while (rs.next()) {
                image = rs.getBytes("image");
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
        return image;
    }
}
