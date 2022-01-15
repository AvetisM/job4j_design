package ru.job4j.serialization.java;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "specifications")
public class Specifications {

    @XmlAttribute
    private String color;
    @XmlAttribute
    private String size;

    public Specifications() { }

    public Specifications(String color, String size) {
        this.color = color;
        this.size = size;
    }
}
