package meru.ui.faces.renderer.html;

import meru.ui.faces.renderer.ViewBuilder;
import meru.xml.XMLBuilder;

public class HtmlBuilder extends XMLBuilder implements ViewBuilder {

    public HtmlBuilder() {
    }

    public String getHtml() {
        return getXML();
    }
    
}