package vttp.paf.workshop24.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.paf.workshop24.exception.OrderUpdateException;
import vttp.paf.workshop24.model.Order;
import vttp.paf.workshop24.model.Product;
import vttp.paf.workshop24.repo.SqlRepo;

@Service
public class OrderService {
    
    @Autowired
    SqlRepo sqlRepo;

    public String test(){
        return sqlRepo.test();
    }

    @Transactional
    public boolean createOrderEntry(String jsonString){

        boolean entryCreated = false;
        Order order = parseOrderEntry(jsonString);
        int createdOrderId = sqlRepo.createOrder(order);
        order.setId(createdOrderId);

        try {
            sqlRepo.createOrderDetails(order);
            entryCreated = true;
            return entryCreated;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new OrderUpdateException(e.getMessage());
        }
    }


    private Order parseOrderEntry(String jsonString){
        Order o = new Order();
        JsonObject jsonData = generateJson(jsonString);
        o.setCustomerName(jsonData.getString("customerName"));
        o.setNotes(jsonData.getString("notes"));
        o.setTax(jsonData.getJsonNumber("tax").longValue());
        o.setShipAddress(jsonData.getString("shipAddress"));
        Date dtn = new Date();
        o.setOrderDate(new java.sql.Date(dtn.getTime()));

        JsonArray itemList = jsonData.getJsonArray("orderItems");
        Map<Product, Integer> orderList = new HashMap<>();
        for (int i=0 ; i<itemList.size() ; i++){
            Product p = new Product();
            JsonObject item = itemList.getJsonObject(i);
            p.setName(item.getString("name"));
            p.setPrice(item.getJsonNumber("price").longValue());
            p.setDiscount(item.getJsonNumber("discount").longValue());
            orderList.putIfAbsent(p, item.getInt("quantity"));
        }
        o.setOrderItems(orderList);
        return o;
    }

    private JsonObject generateJson(String jsonString){
        InputStream is = new ByteArrayInputStream(jsonString.getBytes());
        JsonReader jsonReader = Json.createReader(is);
        JsonObject jsonData = jsonReader.readObject();
        jsonReader.close();
        return jsonData;
    }
}
