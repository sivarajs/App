package meru.app.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class EntityClassRegistryConfig {

    @XmlAttribute(name = "class")
    private String entityClassRegistryClass;

    public Class<?> getEntityClassRegistryClass() throws ClassNotFoundException {
        return Class.forName(entityClassRegistryClass);
    }
}
