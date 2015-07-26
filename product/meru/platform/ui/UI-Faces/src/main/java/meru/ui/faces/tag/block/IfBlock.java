package meru.ui.faces.tag.block;

import meru.ui.faces.tag.UITag;

public class IfBlock extends UIBlock {

    public static final String NAME = "if";

    public IfBlock() {
        super(NAME);
    }
    
    @Override
    public void addChildTag(UITag childTag) {
        
        if ("else".equals(childTag.getTagName())) {
            parent.addChildTag(childTag);
        }
        else {
            super.addChildTag(childTag);
        }
    }
    
    public ElseBlock getElseBlock() {
        for (UITag component : childComponents) {
            if (component instanceof ElseBlock) {
                return (ElseBlock)component;
            }
        }
        
        return null;
    }
    
    public String getCondition() {
        return getAttribute("condition");
    }
}
