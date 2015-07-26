package meru.app.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class ConstructorArg {

    @XmlAttribute
    private String type;

    @XmlValue
    private String value;

    public Class<?> getType() throws ClassNotFoundException {
        return Class.forName(type);
    }

    public String getValue() {
        return value;
    }

}
