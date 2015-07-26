package meru.ui.faces.renderer;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletContext;

import meru.ui.faces.EntityDataProvider;
import meru.ui.faces.renderer.html.HtmlBuilder;
import meru.ui.faces.renderer.html.HtmlFileRenderer;
import meru.ui.faces.renderer.html.HtmlFileView;

public class XHtmlRenderer {

    private ServletContext mServletContext;
    private EntityDataProvider mEntityDataProvider;

    public XHtmlRenderer(ServletContext servletContext,
                         EntityDataProvider resourceDataProvider) {
        mServletContext = servletContext;
        mEntityDataProvider = resourceDataProvider;
    }

    public void writeHtml(Writer writer,
                          HtmlFileView htmlView) throws IOException {

        HtmlFileRenderer htmlRenderer = new HtmlFileRenderer(htmlView,
                                                             mServletContext,
                                                             mEntityDataProvider);

        htmlRenderer.render();
        // Needed for IE 7 to recognize :hover rules
        // simply adding the HTML 4.01 Strict DOCTYPE to the top of the HTML
        // document made IE7 obey my :hover rules as well:
        // String htmlVersion =
        // "<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01//EN' 'http://www.w3.org/TR/html4/strict.dtd'>"
        // + OS.NEW_LINE;

        HtmlBuilder htmlBuilder = new HtmlBuilder();

        htmlView.build(htmlBuilder,
                       mEntityDataProvider);
        
        //System.out.println(htmlBuilder.getHtml());
        writer.append(htmlBuilder.getHtml());
    }
}
