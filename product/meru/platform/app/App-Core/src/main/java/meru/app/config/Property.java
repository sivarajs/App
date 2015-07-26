package meru.app.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Property {

    @XmlAttribute
    private String name;

    @XmlValue
    private String value;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

}
