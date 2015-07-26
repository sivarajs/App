package meru.app.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class AppEngineConfig {

    @XmlAttribute(name = "class")
    private String mEngineClass;

    @XmlElement(name = "entity-class-repository-class")
    private String entityClassRepositoryClass;

    public Class<?> getEngineClass() throws ClassNotFoundException {
        return Class.forName(mEngineClass);
    }

    public Class<?> getEntityClassRepositoryClass() throws ClassNotFoundException {
        return Class.forName(entityClassRepositoryClass);
    }

}
