package meru.ui.faces.renderer.html.block;

import meru.ui.faces.renderer.html.HtmlView;
import meru.ui.faces.tag.block.UIBlock;

public class UIBlockView extends HtmlView {

    protected UIBlock mUIBlock;

    public UIBlockView() {
    }

    public void setBlock(UIBlock uiBlock) {
        mUIBlock = uiBlock;
    }
}
