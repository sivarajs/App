package meru.ui.faces.tag.component.image;

import meru.ui.faces.tag.component.UIComponent;

public class GraphicImage extends UIComponent {

    public static final String NAME = "graphicImage";

    public GraphicImage() {
        super(NAME);
    }

    public String getValue() {
        return getAttribute("value");
    }

}
