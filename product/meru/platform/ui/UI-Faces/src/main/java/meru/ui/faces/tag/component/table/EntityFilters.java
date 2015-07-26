package meru.ui.faces.tag.component.table;

import java.util.List;

import meru.ui.faces.tag.UITag;
import meru.ui.faces.tag.component.UIComponent;

public class EntityFilters extends UIComponent {

    public static final String NAME = "filters";

    public EntityFilters() {
        super(NAME);
    }

    public List<UITag> getFilters() {
        List<UITag> columns = childComponents;
        return columns;
    }
}
