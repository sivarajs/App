package meru.ui.faces.renderer.html.block;

import java.io.IOException;

import meru.ui.faces.renderer.expr.UIConditionExpression;
import meru.ui.faces.tag.block.IfBlock;

public class IfBlockView extends UIBlockView {

    public IfBlockView() {
    }

    @Override
    protected void buildHtml() throws IOException {

        UIConditionExpression expresison = new UIConditionExpression(((IfBlock) mUIBlock).getCondition());
        if (expresison.getValue(this)) {
            super.buildHtml();
        }
    }

}
