package meru.ui.faces.renderer.html.activity;

import java.io.IOException;

import meru.el.EL;
import meru.exception.RedirectException;
import meru.ui.faces.renderer.expr.TextExpressionParser;
import meru.ui.faces.tag.activity.RedirectActivity;

public class RedirectActivityView extends UIActivityView {

   
    @Override
    protected void buildHtml() throws IOException {

        RedirectActivity activity = (RedirectActivity) mUIActivity;

        String link = activity.getHref();
        if (EL.containsBindVariable(link)) {
            TextExpressionParser parser =  new TextExpressionParser(this,
                                                                    true); 
            EL.parseText(link,parser);
            link = parser.getText();
        }

        throw new RedirectException(link);
    }

}
