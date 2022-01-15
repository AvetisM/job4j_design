package ru.job4j.serialization.java;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

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

    public Goods(String name, double price, Boolean pack, String[] warehouses,
                 Specifications specifications) {
        this.name = name;
        this.price = price;
        this.pack = pack;
        this.warehouses = warehouses;
        this.specifications = specifications;
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
                new String[]{"Moscow", "London"},
                new Specifications("Grey", "M"));

        JAXBContext context = JAXBContext.newInstance(Goods.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(goods, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Goods result = (Goods) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
