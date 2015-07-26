package meru.app.config;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class PersistentStoreConfig {

    @XmlAttribute(name = "class")
    private String persistentStoreClass;

    @XmlElement(name = "constructor-arg")
    private List<ConstructorArg> constructorArgs;

    public Class<?> getPersistentStoreClass() throws ClassNotFoundException,
                                      NoSuchMethodException,
                                      InstantiationException,
                                      IllegalAccessException,
                                      InvocationTargetException {
        return Class.forName(persistentStoreClass);

        
    }
    
    public List<ConstructorArg> getConstructorArgs() {
        return constructorArgs;
    }

}
