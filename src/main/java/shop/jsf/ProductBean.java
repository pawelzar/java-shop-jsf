package shop.jsf;

import shop.jsf.controller.ProductController;
import shop.jsf.shop.Product;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@ViewScoped
public class ProductBean {

    private Product product;

    public ProductBean() {
        ProductController productController = new ProductController();

        String productName = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("productName");

        this.product = productController.getProductByName(productName);
    }

    public Product getProduct() {
        return this.product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
}
