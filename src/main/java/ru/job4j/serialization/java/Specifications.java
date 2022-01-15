package ru.job4j.serialization.java;

import org.json.JSONPropertyIgnore;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "specifications")
public class Specifications {

    @XmlAttribute
    private String color;
    @XmlAttribute
    private String size;

    private Goods owner;

    public Specifications() { }

    public Specifications(Goods owner, String color, String size) {
        this.owner = owner;
        this.color = color;
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    @JSONPropertyIgnore
    public Goods getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Specifications{"
                + "owner='" + owner + '\''
                + "color='" + color + '\''
                + ", size='" + size + '\''
                + '}';
    }
}
