package vttp.paf.workshop24.model;

import java.sql.Date;
import java.util.Map;

public class Order {
    private int id;
    private String customerName;
    private Date orderDate;
    private String shipAddress;
    private String notes;
    private float tax;
    private Map<Product, Integer> orderItems;
    
    public Map<Product, Integer> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(Map<Product, Integer> orderItems) {
        this.orderItems = orderItems;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public String getShipAddress() {
        return shipAddress;
    }
    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public float getTax() {
        return tax;
    }
    public void setTax(float tax) {
        this.tax = tax;
    }
    @Override
    public String toString() {
        return "Order [id=" + id + ", customerName=" + customerName + ", orderDate=" + orderDate + ", shipAddress="
                + shipAddress + ", notes=" + notes + ", tax=" + tax + ", orderItems=" + orderItems + "]";
    }
    
    
}
