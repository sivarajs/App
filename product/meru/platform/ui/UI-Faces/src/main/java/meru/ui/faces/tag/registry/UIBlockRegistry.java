package meru.ui.faces.tag.registry;

import meru.ui.faces.tag.block.ElseBlock;
import meru.ui.faces.tag.block.ForEachBlock;
import meru.ui.faces.tag.block.IfBlock;
import meru.ui.faces.tag.block.UIBlock;

public class UIBlockRegistry extends UIComponentRegistry {

    public UIBlockRegistry() {
        super(UIBlock.NAMESPACE);
        addTagClass(IfBlock.NAME,
                    IfBlock.class);
        addTagClass(ElseBlock.NAME,
                    ElseBlock.class);
        addTagClass(ForEachBlock.NAME,
                    ForEachBlock.class);
    }
}
