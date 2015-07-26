package meru.ui.faces.tag.component.tree;

import meru.ui.faces.tag.component.UIComponent;

public class Tree extends UIComponent {

    public static final String NAME = "tree";

    public Tree() {
        super(NAME);
    }
    
    public Tree(String name) {
        super(name);
        setCSSClass(NAME);
    }

    public String getTitle() {
        return getAttribute("title");
    }
    
    public String onTreeItemClick() {
        return getAttribute("onItemClick");
    }
    
    public String onTreeFolderClick() {
        return getAttribute("onFolderClick");
    }
}
