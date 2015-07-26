package meru.app.config;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import meru.sys.IOSystem;

@XmlRootElement(name = "application")
@XmlAccessorType(XmlAccessType.FIELD)
public class AppConfig {

  @XmlTransient
  public static File TEMP_FOLDER = new File("temp/");

  @XmlAttribute
  private String name;

  @XmlAttribute(name = "class")
  private String appClass;

  @XmlElementWrapper(name = "binding-components")
  @XmlElement(name = "binding-component")
  private List<BindingComponentConfig> bindingComponentConfigs;

  @XmlElement(name = "application-engine")
  private AppEngineConfig appEngineConfig;

  @XmlElement(name = "persistent-store")
  private PersistentStoreConfig persistentStoreConfig;

  @XmlElement(name = "entity-class-registry")
  private EntityClassRegistryConfig entityClassRegistryConfig;

  @XmlElementWrapper(name = "services")
  @XmlElement(name = "service")
  private List<ServiceConfig> serviceConfigs;
  
  @XmlElementWrapper(name = "properties")
  @XmlElement(name = "property")
  private List<Property> properties;

  @XmlTransient
  private Map<String, String> propMap;

  public String getName() {
    return name;
  }

  public Class<?> getApplicationClass() throws ClassNotFoundException {
    if (appClass == null || appClass.trim()
                                    .equals("")) {
      return null;
    }

    return Class.forName(appClass);
  }

  public List<BindingComponentConfig> getBindingComponentConfigs() {
    return bindingComponentConfigs;
  }

  public AppEngineConfig getAppEngineConfig() {
    return appEngineConfig;
  }

  public PersistentStoreConfig getPersistentStoreConfig() {
    return persistentStoreConfig;
  }

  public EntityClassRegistryConfig getEntityClassRegistryConfig() {
    return entityClassRegistryConfig;
  }
  
  public List<ServiceConfig> getAppServiceConfigs() {
      return serviceConfigs;
    }

  private void initPropertyMap() {
    if (propMap == null) {
      propMap = new HashMap<String, String>(10);

      if (properties != null) {
        for (Property property : properties) {
          String value = property.getValue()
                                 .trim();
          if (value.length() != 0) {
            propMap.put(property.getName(),
                        value);
          }
        }
      }
    }
  }

  public String getProperty(String name) {

    if (propMap == null) {
      initPropertyMap();
    }
    return propMap.get(name);
  }

  public String getMandatoryProperty(String name) {
    String propValue = getProperty(name);
    if (propValue == null) {
      throw new IllegalArgumentException("Unknown property : " + name);
    }

    return propValue;
  }

  public int getMandatoryIntProperty(String name) {
    String propValue = getMandatoryProperty(name);
    return Integer.parseInt(propValue);
  }

  public Map<String, String> getProperties() {
    if (propMap == null) {
      initPropertyMap();
    }

    return propMap;
  }

  protected void setProperties(Properties properties) {
    for (String name : properties.stringPropertyNames()) {
      propMap.put(name,
                  properties.getProperty(name));
    }
  }

  public static AppConfig readAppConfig(InputStream inputStream) {
    AppConfig appConfig = null;

    try {
      JAXBContext jc = JAXBContext.newInstance(AppConfig.class);
      Unmarshaller u = jc.createUnmarshaller();
      appConfig = (AppConfig) u.unmarshal(inputStream);

    } catch (JAXBException e) {
      throw new RuntimeException(e);
    } finally {
      IOSystem.close(inputStream);
    }

    return appConfig;
  }
}
