package shop.jsf.controller;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import shop.jsf.shop.Product;
import shop.jsf.user.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static shop.jsf.util.JsonReader.readJsonFromUrl;

@ManagedBean
@SessionScoped
public class ProductController implements Serializable {

    @ManagedProperty(value="#{user}")
    private User user;

    private List<Product> products = new ArrayList<>();

    public ProductController(){
        this.products = this.getProducts();
    }

    public List<Product> getProducts() {
        this.products = new ArrayList<>();

        try {
            String jsonString = readJsonFromUrl("http://api-pawzar.rhcloud.com/rest/products");
            // System.out.println(jsonString);
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                String name = json.getString("name");
                String description = json.getString("description");
                Double price = json.getDouble("price");
                this.products.add(new Product(name, description, new BigDecimal(price)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return this.products;
        }
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }


    public Product getProductByName(String name) {
        for (Product current : this.products) {
            if (current.getName().equals(name)) {
                return current;
            }
        }
        return null;
    }
}
