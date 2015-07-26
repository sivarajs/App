package meru.ui.faces.tag.block;

public class ForEachBlock extends UIBlock {

    public static final String NAME = "foreach";

    public ForEachBlock() {
        super(NAME);
    }

    public String getEntity() {
        return getMandatoryAttribute("entity");
    }
    
    public String getFilter() {
        return getAttribute("filter");
    }
}
