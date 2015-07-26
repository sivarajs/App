package meru.ui.faces.renderer.html.activity;

import meru.ui.faces.renderer.html.HtmlView;
import meru.ui.faces.tag.activity.UIActivity;

public class UIActivityView extends HtmlView {

    
    protected UIActivity mUIActivity;

    public UIActivityView() {
    }

    public void setActivity(UIActivity uiActivity) {
        mUIActivity = uiActivity;
    }
}
