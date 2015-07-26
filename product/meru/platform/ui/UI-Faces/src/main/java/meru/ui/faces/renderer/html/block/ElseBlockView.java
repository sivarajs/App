package meru.ui.faces.renderer.html.block;

import java.io.IOException;

import meru.ui.faces.renderer.expr.UIConditionExpression;
import meru.ui.faces.tag.block.ElseBlock;

public class ElseBlockView extends UIBlockView {

    public ElseBlockView() {
    }

    @Override
    protected void buildHtml() throws IOException {

        UIConditionExpression expresison = new UIConditionExpression(((ElseBlock) mUIBlock).getCondition());
        if (!expresison.getValue(this)) {
            super.buildHtml();
        }
    }

}
