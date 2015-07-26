package meru.ui.faces.renderer;

import java.io.InputStream;
import java.util.logging.Logger;

import meru.sys.IOSystem;
import meru.sys.JVM;
import meru.sys.lang.StringHelper;
import meru.ui.faces.renderer.registry.UITagRendererRegistryFactory;
import meru.ui.faces.tag.UITag;
import meru.ui.faces.tag.block.IfBlock;
import meru.ui.faces.tag.block.UIBlock;
import meru.ui.faces.tag.component.UIComponent;
import meru.ui.faces.tag.component.UIFileComponent;
import meru.ui.faces.tag.component.html.CDataComponent;
import meru.ui.faces.tag.component.html.TextNodeComponent;
import meru.ui.faces.tag.registry.UITagRegistryFactory;
import meru.xml.XMLHelper;
import meru.xml.XMLNodeHelper;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public abstract class RendererContext {

  protected Logger mLogger = Logger.getLogger(RendererContext.class.getPackage()
                                                                   .getName());

  public RendererContext() {
  }

  public abstract InputStream getInputStream(String file);

  public abstract <T extends UITag> void renderTag(T component);

  public <T extends UITag> UITagRenderer<T> getTagRenderer(String namespace,
                                                           String componentName) {
    return UITagRendererRegistryFactory.getTagRenderer(namespace,
                                                       componentName);
  }

  public UITag loadTag(String file) {

    Element element = getRootElement(file);
    UITag uiComponent = loadTag(element);

    return uiComponent;
  }

  private Element getRootElement(String file) {

    InputStream inputStream = getInputStream(file);

    if (inputStream == null) {
      throw new RuntimeException("File not found : " + file);
    }

    Element rootElem = null;
    try {

      // String data = IOSystem.read(inputStream);
      rootElem = XMLHelper.parse(inputStream)
                          .getDocumentElement();

    } catch (Exception e) {
      throw new RuntimeException("Unable to build the file '" + file + "'",
                                 e);
    } finally {
      IOSystem.close(inputStream);
    }

    return rootElem;
  }

  private UITag loadTag(Element element) {

    try {

      UITag component = getTag(element);

      // mViewBuilderContext.getComponentStack().push(element.getLocalName());

      component.load(element);
      loadChildren(component,
                   element);
      // mViewBuilderContext.getComponentStack().pop();

      return component;

    } catch (Exception e) {
      mLogger.severe("Unable to create the html for the component "
          + JVM.SystemProperty.NEW_LINE + JVM.SystemProperty.NEW_LINE
          + XMLHelper.toString(element) + JVM.SystemProperty.NEW_LINE
          + JVM.SystemProperty.NEW_LINE + StringHelper.toString(e));

      if (e instanceof RuntimeException) {
        throw (RuntimeException) e;
      }

      throw new RuntimeException(e);
    }

  }

  private void loadChildren(UITag parentComponent,
                            Element parentElem) {

    Node node = parentElem.getFirstChild();

    while (node != null) {

      UITag component = null;

      switch (node.getNodeType()) {

        case Node.CDATA_SECTION_NODE:
          if (!parentComponent.ignoreTextNode()) {
            component = new CDataComponent();
            ((TextNodeComponent) component).loadTextNode(node);
            parentComponent.addChildTag(component);
          }
          break;

        case Node.TEXT_NODE:
          if (!parentComponent.ignoreTextNode()) {
            component = new TextNodeComponent();
            ((TextNodeComponent) component).loadTextNode(node);
            parentComponent.addChildTag(component);
          }
          break;

        case Node.ELEMENT_NODE:
          Element child = (Element) node;

          if (child.getLocalName()
                   .equals("include")) {

            String src = XMLNodeHelper.getAttribute(child,
                                                    "src");
            component = loadTag(src);
            parentComponent.addChildTag(component);
          }
          else {

            component = getTag(child);
            addChildComponent(component,
                              parentComponent);
            // parentComponent.addChildTag(component);
            component.load(child);
            loadChildren(component,
                         child);

          }

          break;

      }

      node = node.getNextSibling();
    }

    if (parentComponent instanceof UIFileComponent) {
      UITag component = loadTag(((UIFileComponent) parentComponent).getSrc());
      parentComponent.addChildTag(component);

    }

  }

  private void addChildComponent(UITag component,
                                 UITag parentComponent) {
    if (component.getUIElement() != null && component.rendered() != null) {
      Element element = component.getUIElement()
                                 .getOwnerDocument()
                                 .createElementNS(UIBlock.NAMESPACE,
                                                  IfBlock.NAME);
      element.setAttribute("condition",
                           component.rendered());
      UIComponent ifBlock = (UIComponent) loadTag(element);
      ifBlock.addChildTag(component);
      parentComponent.addChildTag(ifBlock);
    }
    else {
      parentComponent.addChildTag(component);
    }

  }

  private UITag getTag(Element element) {

    UITag tag = UITagRegistryFactory.getTagRegistry(element.getNamespaceURI())
                                    .getTag(element.getLocalName());
    tag.setUIElement(element);
    
    return tag;
  }

}
