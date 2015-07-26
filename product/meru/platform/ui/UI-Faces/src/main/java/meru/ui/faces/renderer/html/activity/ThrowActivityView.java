package meru.ui.faces.renderer.html.activity;

import java.io.IOException;

import meru.exception.RedirectException;
import meru.ui.faces.renderer.expr.UIBooleanExpression;
import meru.ui.faces.tag.activity.ThrowActivity;

public class ThrowActivityView extends UIActivityView {

    public ThrowActivityView() {
    }

    @Override
    protected void buildHtml() throws IOException {

        ThrowActivity throwActivity = (ThrowActivity) mUIActivity;
        UIBooleanExpression expresison = new UIBooleanExpression(throwActivity.getCondition());
        if (expresison.getValue(this)) {
            String message = throwActivity.getMessage();
            if (throwActivity.getLink() != null) {
                message = "<p><a href='" + throwActivity.getLink() + "'>"
                                + message + "</a></p>";
            }
            throw new RedirectException(throwActivity.getLink(),
                                        message);
        }
    }

}
