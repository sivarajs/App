package meru.ui.faces.renderer.html.component;

import java.util.List;

import meru.el.EL;
import meru.ui.faces.renderer.RendererContext;
import meru.ui.faces.renderer.UITagRenderer;
import meru.ui.faces.renderer.expr.FieldExpressionParser;
import meru.ui.faces.renderer.expr.UIConditionExpression;
import meru.ui.faces.renderer.html.HtmlBuilder;
import meru.ui.faces.renderer.html.HtmlRendererContext;
import meru.ui.faces.renderer.html.HtmlView;
import meru.ui.faces.tag.UITag;
import meru.ui.faces.tag.component.UIComponent;

public class HtmlComponentRenderer<T extends UIComponent> extends UITagRenderer<T> {

    protected static final String COMPONENT_NAME = "c";
    protected static final String COMPONENT_LINK = "cl";

    protected String tagName;
    protected T uiComponent;
    protected HtmlRendererContext viewContext;
    protected HtmlBuilder htmlBuilder;

    public HtmlComponentRenderer() {
        tagName = "div";
    }

    public HtmlComponentRenderer(String tagName) {
        this.tagName = tagName;
    }

    protected String getHtmlTag() {
        return tagName;
    }

    @Override
    public void render(T uiComponent,
                       RendererContext viewContext) {

        this.uiComponent = uiComponent;
        this.viewContext = (HtmlRendererContext) viewContext;
        htmlBuilder = this.viewContext.getHtmlBuilder();

        
        if (preRender()) {

            if (!uiComponent.isPseudoComponent()) {
                htmlBuilder.startElement(getHtmlTag());
                addCommonAttributes();
                addAttributes();
                if (uiComponent.isChildless()) {
                    htmlBuilder.completeBeginTag();
                }
                else {
                    htmlBuilder.closeBeginTag();
                }
            }

            if (preRenderChildren()) {

                buildChildren(this.viewContext);
            }

            postRenderChildren();

            if (!uiComponent.isPseudoComponent() && !uiComponent.isChildless()) {
                htmlBuilder.endElement(getHtmlTag());
            }
        }

        postRender();

    }

    protected void buildChildren(HtmlRendererContext viewContext) {
        List<? extends UITag> childComponents = uiComponent.getChildTags();

        if (childComponents != null) {

            for (UITag childComponent : childComponents) {

                if (preRenderChild(childComponent)) {

                    viewContext.getTagRenderer(childComponent.getTagNamespace(),
                                               childComponent.getTagName())
                               .render(childComponent,
                                       viewContext);

                }

                postRenderChild(childComponent);
            }
        }
    }
    
    public boolean canBeRendered(UITag tag) {
      if (tag.rendered() != null) {
        UIConditionExpression expresison = new UIConditionExpression(tag.rendered());
        return expresison.getValue(this.viewContext.getCurrentView());
      }

      return true;
    }

    protected void addCommonAttributes() {

        String visibleOnVar = uiComponent.getBinds();
        if (visibleOnVar != null) {
            uiComponent.setCSSStyle("display:none");
            htmlBuilder.addAttribute("binds",
                                     visibleOnVar);
        }

        htmlBuilder.addAttribute("id",
                                 uiComponent.getId())
                   .addAttribute("style",
                                 uiComponent.getCssStyle())
                   .addAttribute("class",
                                 uiComponent.getCssClass());

        String onClick = uiComponent.onClick();
        if (onClick != null) {
            if (EL.containsBindVariable(onClick)) {
                htmlBuilder.addText(" onclick=\"",
                                    false);
                EL.parseText(onClick,
                             new FieldExpressionParser(htmlBuilder,
                                                       viewContext));
                htmlBuilder.addText("\"",
                                    false);
            }
            else {
                htmlBuilder.addAttribute("onclick",
                                         onClick);
            }

        }

        if (uiComponent.requiresClientProcessing()) {
            htmlBuilder.addAttribute(COMPONENT_NAME,
                                     uiComponent.getTagName());
        }

    }

    protected void createChildView(HtmlView parentView) {
        HtmlView htmlView = new HtmlView();
        HtmlRendererContext childContext = viewContext.newContext(htmlView);
        buildChildren(childContext);
        childContext.flushHtml();
        parentView.addUIView(htmlView);
        viewContext.addUIView(parentView);
    }

    protected void addChildView(HtmlView childView) {
        viewContext.addUIView(childView);
    }

    protected void addAttributes() {

    }

    protected boolean preRender() {

        return true;
    }

    protected void postRender() {

    }

    protected boolean preRenderChildren() {

        return true;
    }

    protected void postRenderChildren() {

    }

    protected boolean preRenderChild(UITag childComponent) {

        return true;
    }

    protected void postRenderChild(UITag childComponent) {

    }

}
