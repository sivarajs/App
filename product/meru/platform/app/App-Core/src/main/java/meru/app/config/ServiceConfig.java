package meru.app.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceConfig {

    @XmlAttribute(name = "name")
    private String mName;
    
    @XmlAttribute(name = "class")
    private String mServiceClass;

    public Class<?> getServiceClass() throws ClassNotFoundException {
        return Class.forName(mServiceClass);
    }

    public String getName() throws ClassNotFoundException {
        return mName;
    }

}
