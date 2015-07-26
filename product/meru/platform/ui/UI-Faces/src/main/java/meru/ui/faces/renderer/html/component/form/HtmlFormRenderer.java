package meru.ui.faces.renderer.html.component.form;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.form.Form;

public class HtmlFormRenderer<T extends Form> extends HtmlComponentRenderer<T> {

    public HtmlFormRenderer() {
        super(Form.NAME);
    }

    @Override
    protected String getHtmlTag() {
        return "form";
    }

    @Override
    protected void addAttributes() {

        htmlBuilder.addAttribute("name",
                                 uiComponent.getName())
                   .addAttribute("action",
                                 uiComponent.getAction())
                   .addAttribute("method",
                                 uiComponent.getMethod())
                   .addAttribute("target",
                                 uiComponent.getTarget())
                   .addAttribute("successStatus",
                                 uiComponent.getSuccessStatus())
                   .addAttribute("onsubmit",
                                 uiComponent.getOnsubmit())
                   .addAttribute("successMsg",
                                 uiComponent.getSuccessMsg())
                   .addAttribute("accept-charset",
                                 "utf-8")
                   .addAttribute("enctype",
                                 uiComponent.getEncoding());
    }

}
