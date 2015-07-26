package meru.ui.faces.tag.component.social.google;

import meru.ui.faces.tag.component.UIComponent;

public class GoogleMap extends UIComponent {

    public static final String NAME = "googleMap";

    public GoogleMap() {
        super(NAME);
    }
    
    
    public String getLatLng() {
        return getAttribute("latlng");
    }

    
    public String getRadius() {
        return getAttribute("radius");
    }
}
