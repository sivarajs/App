package meru.ui.faces.renderer.html.component.social.google;

import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.social.google.GoogleMap;

public class HtmlGoogleMapRenderer extends HtmlComponentRenderer<GoogleMap> {

    protected void addAttributes() {
        super.addAttributes();
        htmlBuilder.addAttribute("latlng",
                                 uiComponent.getLatLng())
                   .addAttribute("radius",
                                 uiComponent.getRadius());
    }
}
