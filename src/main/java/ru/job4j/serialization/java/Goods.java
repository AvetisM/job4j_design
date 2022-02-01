package ru.job4j.serialization.java;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@XmlRootElement(name = "goods")
@XmlAccessorType(XmlAccessType.FIELD)
public class Goods {

    @XmlAttribute
    private String name;
    @XmlAttribute
    private double price;
    @XmlAttribute
    private Boolean pack;
    private Specifications specifications;

    @XmlElementWrapper(name = "warehouses")
    @XmlElement(name = "warehouse")
    private String[] warehouses;

    public Goods() { }

    public Goods(String name, double price, Boolean pack, String[] warehouses) {
        this.name = name;
        this.price = price;
        this.pack = pack;
        this.warehouses = warehouses;
    }

    public void setSpecifications(Specifications specifications) {
        this.specifications = specifications;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Boolean getPack() {
        return pack;
    }

    @Override
    public String toString() {
        return "Goods{"
                + "name='" + name + '\''
                + ", price=" + price
                + ", pack=" + pack
                + ", warehouses=" + Arrays.toString(warehouses)
                + ", specifications=" + specifications
                + '}';
    }

    public static void main(String[] args) throws Exception {

       final Goods goods = new Goods("T-shirt", 450.00, false,
                new String[]{"Moscow", "London"});

       final Specifications specifications = new Specifications(goods, "Grey", "M");
       goods.setSpecifications(specifications);

        JSONObject jsonSpecifications = new JSONObject("{\"color\":\"Grey\",\"size\":\"M\"}");

        List<String> list = new ArrayList<>();
        list.add("Moscow");
        list.add("London");
        JSONArray jsonWarehouses = new JSONArray(list);

        JSONObject jsonGoods = new JSONObject();
        jsonGoods.put("name", goods.getName());
        jsonGoods.put("price", goods.getPrice());
        jsonGoods.put("pack", goods.getPack());
        jsonGoods.put("warehouses", jsonWarehouses);
        jsonGoods.put("specifications", jsonSpecifications);

        System.out.println(jsonGoods.toString());

        System.out.println(new JSONObject(goods).toString());
    }
}
