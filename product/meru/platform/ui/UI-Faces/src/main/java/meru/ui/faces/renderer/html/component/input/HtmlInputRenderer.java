package meru.ui.faces.renderer.html.component.input;

import meru.el.EL;
import meru.persistence.EntityQuery;
import meru.ui.faces.renderer.expr.FieldExpressionParser;
import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.input.Input;
import meru.ui.faces.tag.component.panel.PanelGrid;
import meru.xml.XMLNodeHelper;

public class HtmlInputRenderer<T extends Input> extends HtmlComponentRenderer<T> {

  public HtmlInputRenderer() {
    super("input");
  }

  public HtmlInputRenderer(String tag) {
    super(tag);
  }

  @Override
  protected void addAttributes() {

    htmlBuilder.addAttribute(Input.ATTR_NAME,
                             uiComponent.getName())
               .addAttribute(Input.ATTR_TYPE,
                             uiComponent.getType())
               .addAttribute(Input.ATTR_DISABLED,
                             uiComponent.getDisabled())
               .addAttribute("req",
                             uiComponent.isRequired())
               .addAttribute(Input.ATTR_ON_CHANGE,
                             uiComponent.getOnChange())
               .addAttribute(Input.ATTR_LENGTH,
                             uiComponent.getUIElement()
                                        .getAttribute(Input.ATTR_LENGTH))
               .addAttribute(Input.ATTR_REG_EXP,
                             uiComponent.getUIElement()
                                        .getAttribute(Input.ATTR_REG_EXP))
               .addAttribute(Input.ATTR_HELP,
                             uiComponent.getHelp())
               .addAttribute(Input.ATTR_VAR,
                             uiComponent.getVar());

    processValueAttribute();
  }

  @Override
  protected boolean preRender() {

    if (uiComponent.getLabel() != null) {

      PanelGrid panelGrid = (PanelGrid) uiComponent.getParent("panelGrid");

      htmlBuilder.startElement("div")
                 .addClassAttribute("labeledInput")
                 .addStyleAttribute("width:" + panelGrid.getColumnWidth());

      htmlBuilder.startElement("div")
                 .addClassAttribute(uiComponent.getLabelClass(panelGrid.getLabelClass()))
                 .addStyleAttribute(panelGrid.getLabelStyle())
                 .addText(uiComponent.getLabel());

      Class<?> entityClass = (Class<?>) viewContext.getCurrentView()
                                                   .getVariableValue("entityClass");
      if (entityClass != null) {
        if (XMLNodeHelper.isTrue(uiComponent.getUIElement(),
                                 "req")
            || (!XMLNodeHelper.isPresent(uiComponent.getUIElement(),
                                         "req") && uiComponent.isRequired(entityClass))) {

          htmlBuilder.startElement("span")
                     .addAttribute("class",
                                   "required")
                     .addText(" * ")
                     .endElement();
          XMLNodeHelper.setAttribute(uiComponent.getUIElement(),
                                     "req",
                                     "true");
        }
      }

      htmlBuilder.endElement();

      htmlBuilder.startElement("div")
                 .addClassAttribute("input");

    }

    return true;
  }

  @Override
  protected void postRender() {

    if (uiComponent.getLabel() != null) {

      if (uiComponent.getHelp() != null) {
        htmlBuilder.startElement("span")
                   .addClassAttribute("inputHelp")
                   /*
                    * .endElement() .startElement("div")
                    * .addClassAttribute("clr")
                    */
                   .endElement();
      }

      htmlBuilder.endElement()
                 .endElement();
    }
  }

  protected void processValueAttribute() {

    String value = uiComponent.getValue();

    if (value != null) {

      if (EL.isBindVariable(value)) {

        if (uiComponent.getName() == null) {

          String name = EL.getBindVariable(value);

          if (!name.contains("#{")) {
            htmlBuilder.addAttribute(Input.ATTR_NAME,
                                     name);
          }
          else {
            htmlBuilder.addAttribute("expr",
                                     value);
          }

        }

        htmlBuilder.addText(" value='",
                            false);

        EL.parseText(value,
                     new FieldExpressionParser(htmlBuilder,
                                               viewContext));

        htmlBuilder.addText("'",
                            false);
      }
      else {
        htmlBuilder.addAttribute("value",
                                 value);
      }

    }

    String def = uiComponent.getDefault();
    if (def != null) {
      if (EL.containsBindVariable(def)) {
        Object val = viewContext.getCurrentView()
                                .getVariableValue(EL.getBindVariable(def));

        if (val == null) {

          htmlBuilder.addAttribute("default",
                                   def);

        }
        else {
          def = val.toString();

          if (!def.equals(EntityQuery.AttributeValue.NULL.getValue())) {

            htmlBuilder.addAttribute("default",
                                     def);
          }
        }
      }
      else {
        htmlBuilder.addAttribute("default",
                                 def);
      }

    }

  }

}
