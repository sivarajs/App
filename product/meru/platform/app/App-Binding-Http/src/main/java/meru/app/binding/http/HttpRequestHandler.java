package meru.app.binding.http;

import meru.ui.faces.EntityDataProvider;
import meru.ui.faces.renderer.html.HtmlFileView;

public abstract class HttpRequestHandler {

    protected EntityDataProvider entityDataProvider;

    public void setEntityDataProvider(EntityDataProvider entityDataProvider) {
        this.entityDataProvider = entityDataProvider;
    }
    
    public abstract HtmlFileView get(HttpAppRequest appRequest);
}
