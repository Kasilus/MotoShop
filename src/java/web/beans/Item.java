package web.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Item {

    private int id;
    private String name;
    private String manufacturer;
    private byte[] image;
    private int count;
    private double price;
    private String subcategory;
    private String category;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public byte[] getImage() {
        return image;
    }

    public int getCount() {
        return count;
    }

    public double getPrice() {
        return price;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public String getCategory() {
        return category;
    }

}
