package meru.ui.faces.renderer.html.component.panel;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.UITag;
import meru.ui.faces.tag.component.form.EntityForm;
import meru.ui.faces.tag.component.html.TextNodeComponent;
import meru.ui.faces.tag.component.panel.PanelGrid;
import meru.xml.XMLNodeHelper;

import org.w3c.dom.Element;

public class HtmlPanelGridRenderer extends HtmlComponentRenderer<PanelGrid> {

    private Class<?> mEntityClass;

    public HtmlPanelGridRenderer() {
        super("div");
    }

    public HtmlPanelGridRenderer(String tagName) {
        super(tagName);
    }

    @Override
    protected void addAttributes() {

        Element formElement = XMLNodeHelper.getGrandParent(uiComponent.getUIElement(),
                                                           EntityForm.NAME);

        if (formElement != null) {
            String entityName = XMLNodeHelper.getAttribute(formElement,
                                                           "entity");
            if (entityName == null) {
                throw new NullPointerException("'entity' attribute is missing");
            }
            mEntityClass = viewContext.getEntityDataProvider()
                                      .getEntityClass(entityName);

            viewContext.getCurrentView()
                       .setVariable("entityClass",
                                    mEntityClass);
        }

    }

    @Override
    protected void postRenderChildren() {
        htmlBuilder.startElement("div")
                   .addClassAttribute("clr")
                   .endElement();
    }

    @Override
    protected boolean preRenderChild(UITag component) {

        if (!isChildAllowed(component)) {
            return false;
        }

        XMLNodeHelper.setAttribute(component.getUIElement(),
                                   "width",
                                   uiComponent.getColumnWidth());
        XMLNodeHelper.setAttribute(component.getUIElement(),
                                   "labelClass",
                                   uiComponent.getLabelClass());
        XMLNodeHelper.setAttribute(component.getUIElement(),
                                   "labelStyle",
                                   uiComponent.getLabelStyle());
        XMLNodeHelper.setAttribute(component.getUIElement(),
                                   "inputClass",
                                   uiComponent.getInputClass());
        XMLNodeHelper.setAttribute(component.getUIElement(),
                                   "inputStyle",
                                   uiComponent.getInputStyle());
        return true;
    }

    private boolean isChildAllowed(UITag component) {

        if (component instanceof TextNodeComponent) {
            return false;
        }

        return true;
    }
}
