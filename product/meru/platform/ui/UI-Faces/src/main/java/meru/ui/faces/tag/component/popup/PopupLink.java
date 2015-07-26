package meru.ui.faces.tag.component.popup;

import meru.ui.faces.tag.Identifiable;
import meru.ui.faces.tag.component.UIComponent;

public class PopupLink extends UIComponent implements Identifiable {

    public static final String NAME = "popupLink";

    public PopupLink() {
        super(NAME);
    }

    public String getHref() {
        return getAttribute("href");
    }

    @Override
    public String getLabel() {
        return getMandatoryAttribute("label");
    }
    
    public String getSrc() {
        return getMandatoryAttribute("src");
    }

    public Popup getPopup() {
        
        return (Popup)getFirstChildComponent(Popup.NAME);
        
    }

    @Override
    protected boolean load() {

        //popup = (Popup) rendererContext.loadComponent(getMandatoryAttribute("popupSrc"));

        return true;
    }
}
