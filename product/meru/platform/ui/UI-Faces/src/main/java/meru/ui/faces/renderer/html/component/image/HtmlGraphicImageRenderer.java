package meru.ui.faces.renderer.html.component.image;

import meru.el.EL;
import meru.ui.faces.renderer.html.HtmlFieldView;
import meru.ui.faces.renderer.html.component.HtmlComponentRenderer;
import meru.ui.faces.tag.component.image.GraphicImage;

public class HtmlGraphicImageRenderer extends
                HtmlComponentRenderer<GraphicImage> {

    public HtmlGraphicImageRenderer() {
        super("img");
    }

    @Override
    public void addAttributes() {
        String value = uiComponent.getValue();
        htmlBuilder.addText(" src='",
                            false);
        if (EL.isBindVariable(value)) {
            HtmlFieldView field = new HtmlFieldView(EL.getBindVariable(uiComponent.getValue()));
            viewContext.addUIView(field);
        }
        else {
            htmlBuilder.addText(value,
                                false);

        }
        htmlBuilder.addText("'",
                            false);

    }

}
