package ru.job4j.serialization.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Goods {
    private final String name;
    private final double price;
    private final Boolean pack;
    private final String[] stock;
    private final Specifications specifications;

    public Goods(String name, double price, Boolean pack, String[] stock,
                 Specifications specifications) {
        this.name = name;
        this.price = price;
        this.pack = pack;
        this.stock = stock;
        this.specifications = specifications;
    }

    @Override
    public String toString() {
        return "Goods{"
                + "name='" + name + '\''
                + ", price=" + price
                + ", pack=" + pack
                + ", stock=" + Arrays.toString(stock)
                + ", specifications=" + specifications
                + '}';
    }

    public static void main(String[] args) {

       final Goods goods = new Goods("T-shirt", 450.00, false,
                new String[]{"Moscow", "London"},
                new Specifications("Grey", "M"));

        final Gson gson = new GsonBuilder().create();
        final String gsonGoods = gson.toJson(goods);

        System.out.println(gsonGoods);

        final Goods goods2 = gson.fromJson(gsonGoods, Goods.class);
        System.out.println(goods2);
    }
}
