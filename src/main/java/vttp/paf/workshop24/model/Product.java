package vttp.paf.workshop24.model;

public class Product {
    private String name;
    private float price;
    private float discount;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public float getDiscount() {
        return discount;
    }
    public void setDiscount(float discount) {
        this.discount = discount;
    }
    @Override
    public String toString() {
        return "Product [name=" + name + ", price=" + price + ", discount=" + discount + "]";
    }
}
