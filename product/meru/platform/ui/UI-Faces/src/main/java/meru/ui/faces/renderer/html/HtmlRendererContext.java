package meru.ui.faces.renderer.html;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.servlet.ServletContext;

import meru.ui.faces.EntityDataProvider;
import meru.ui.faces.renderer.RendererContext;
import meru.ui.faces.renderer.UITagRenderer;
import meru.ui.faces.renderer.UIView;
import meru.ui.faces.tag.UITag;

public class HtmlRendererContext extends RendererContext {

    private final HtmlView mHtmlView;
    private final HtmlBuilder mHtmlBuilder;
    private ServletContext mServletContext;
    private EntityDataProvider mEntityDataProvider;

    public HtmlRendererContext(HtmlView htmlView,
                               ServletContext servletContext,
                               EntityDataProvider entityDataProvider) {
        mHtmlView = htmlView;
        mHtmlBuilder = new HtmlBuilder();
        mServletContext = servletContext;
        mEntityDataProvider = entityDataProvider;
    }

    public final HtmlBuilder getHtmlBuilder() {
        return mHtmlBuilder;
    }

    public EntityDataProvider getEntityDataProvider() {
        return mEntityDataProvider;
    }

    public void flushHtml() {

        String html = mHtmlBuilder.getHtml();

        if (html != null && html.length() > 0) {
            mHtmlView.addHtml(html);
            mHtmlBuilder.reset();
        }
    }

    public void addUIView(UIView html) {
        flushHtml();
        mHtmlView.addUIView(html);
    }

    public HtmlView getCurrentView() {
        return mHtmlView;
    }

    @Override
    public InputStream getInputStream(String filePath) {
        InputStream inputStream = this.mServletContext.getResourceAsStream(filePath);
        
        if (inputStream == null) {
          
          File file = new File("webapps/"+filePath);
          
          
          try {
            inputStream = new FileInputStream(file.getAbsoluteFile());
          } catch (FileNotFoundException e) {
            
            throw new RuntimeException("File not found : "+filePath);
          }
        }
        
        return inputStream;
    }

    public HtmlRendererContext newContext(HtmlView htmlView) {
        HtmlRendererContext context = new HtmlRendererContext(htmlView,
                                                              mServletContext,
                                                              mEntityDataProvider);
        return context;
    }

    @Override
    public void renderTag(UITag component) {
        UITagRenderer<UITag> renderer = getTagRenderer(component.getTagNamespace(),
                                                       component.getTagName());
        renderer.render(component,
                        this);
    }
}
