package meru.ui.faces.renderer.html.component.panel;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.UITag;
import meru.ui.faces.tag.component.form.EntityForm;
import meru.ui.faces.tag.component.html.TextNodeComponent;
import meru.ui.faces.tag.component.input.Input;
import meru.ui.faces.tag.component.panel.PanelGrid;
import meru.xml.XMLNodeHelper;

import org.w3c.dom.Element;

public class HtmlPanelTableGridRenderer extends HtmlComponentRenderer<PanelGrid> {

    private int mCurrCol = 0;
    private boolean mRowCompleted;

    private Class<?> mEntityClass;

    public HtmlPanelTableGridRenderer() {
        super("table");
    }

    public HtmlPanelTableGridRenderer(String tagName) {
        super(tagName);
    }

    @Override
    protected void addAttributes() {

        htmlBuilder.addAttribute("cellpadding",
                                 "0")
                   .addAttribute("cellspacing",
                                 "0");

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
        }

    }

    @Override
    protected boolean preRenderChild(UITag component) {

        if (!isChildAllowed(component)) {
            return false;
        }

        if ((mCurrCol % uiComponent.getColumns()) == 0) {
            mRowCompleted = false;
            htmlBuilder.startElement("tr")
                       .addAttribute("class",
                                     "panelGridTR");
        }

        addLabel(component);

        htmlBuilder.startElement("td")
                   .addAttribute("class",
                                 "panelGridTD");

        String width = component.getWidth();
        if (width != null) {

            htmlBuilder.addAttribute("width",
                                     width);
        }

        return true;
    }

    @Override
    protected void postRenderChild(UITag component) {

        if (!isChildAllowed(component)) {
            return;
        }

        htmlBuilder.endElement("td");
        mCurrCol++;

        if ((mCurrCol % uiComponent.getColumns()) == 0) {
            mRowCompleted = true;
            htmlBuilder.endElement("tr");
        }

    }

    @Override
    protected void postRenderChildren() {
        if (!mRowCompleted) {
            htmlBuilder.endElement("tr");
        }
    }

    private void addLabel(UITag component) {

        String label = component.getLabel();

        if (label != null) {

            String styleClass = "label panelGridTD";
            if (uiComponent.getLabelClass() != null) {
                styleClass += " " + uiComponent.getLabelClass();
            }

            htmlBuilder.startElement("td")
                       .addAttribute("class",
                                     styleClass);

            if (uiComponent.getLabelStyle() != null) {
                htmlBuilder.addAttribute("style",
                                         uiComponent.getLabelStyle());
            }

            htmlBuilder.addText(label);

            if (mEntityClass != null && component instanceof Input) {

                Input input = (Input) component;

                if (XMLNodeHelper.isTrue(input.getUIElement(),
                                         "req") || (!XMLNodeHelper.isPresent(input.getUIElement(),
                                                                             "req") && input.isRequired(mEntityClass))) {

                    htmlBuilder.startElement("span")
                               .addAttribute("class",
                                             "required")
                               .addText(" * ")
                               .endElement("span");
                    XMLNodeHelper.setAttribute(component.getUIElement(),
                                               "req",
                                               "true");
                }
            }

            htmlBuilder.endElement("td");

        }

    }

    private boolean isChildAllowed(UITag component) {

        if (component instanceof TextNodeComponent) {
            return false;
        }

        return true;
    }
}
