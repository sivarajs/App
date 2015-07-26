package meru.ui.faces.renderer.html;

public class HtmlFileView extends HtmlView {

    private String mURL;

    public HtmlFileView(String resourceURL) {

        if (resourceURL.indexOf(".") == -1) {
            if (!resourceURL.endsWith("/")) {
                resourceURL += "/";
            }
            resourceURL += "index.xhtml";
        }

        mURL = resourceURL;
    }

    public String getURL() {
        return mURL;
    }

}
